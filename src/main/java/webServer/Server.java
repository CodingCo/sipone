package webServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.SubjectFacade;
import facades.SubjectVoteFacade;
import handlers.FileHandler;
import handlers.ServerResponse;
import handlers.SubjectHandler;
import java.util.Properties;
import utility.Utility;
import webInterface.SubjectFacadeIF;
import webInterface.SubjectVoteFacadeIF;
import facades.SubjectVoteFacade;
import handlers.SubjectVoteHandler;

public class Server {

    private HttpServer server;
    // Insert handler declaration here >>
    private ServerResponse sr;
    private final Properties property = Utility.initProperties("serverproperties.txt");

    GsonBuilder gsonBuilder;
    private Gson gson;
    private int port;
    private String ip;

    // Constructor
    public Server() throws IOException {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        // Instantiate handler here >>
        this.sr = new ServerResponse();
    }

    public void start() throws IOException {

        ip = property.getProperty("ipaddress");
        port = Integer.parseInt(property.getProperty("webport"));
        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        // HANDLERS BEGIN
        server.createContext("/", new FileHandler());
//      server.createContext("/api/subject", new SubjectHandlerOLD(gson, sr, null)); // TODO: new facade
        server.createContext("/api/subject", new SubjectHandler(sr, new SubjectFacade(gson)));
        server.createContext("/api/subjectVote", new SubjectVoteHandler(sr, new SubjectVoteFacade(gson)));
        // HANDLERS STOP
        server.setExecutor(null);
        server.start();
        System.out.println("Server startet on: " + server.getAddress());

    }

    public void closeHttpServer() {
        server.stop(2);
        System.out.println("webserver closed");
    }

    private HttpHandler testHandler() {
        return new HttpHandler() {

            @Override
            public void handle(HttpExchange he) throws IOException {
                // Should return index.html
                String contentType = "text/html";
                String response = "Server is running!";
                Headers h = he.getResponseHeaders();
                h.add("Content-Type", contentType);
                h.add("charset", "UTF-8");

                sr.sendMessage(he, port, response);
            }

        };
    }

}
