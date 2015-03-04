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
import handlers.FileHandler;
import handlers.RestHandler;
import handlers.ServerResponse;
import java.util.Properties;
import utility.Utility;

public class Server {

    private HttpServer server;
    // Insert handler declaration here >>
    private ServerResponse sr;
    private final Properties property = Utility.initProperties("serverproperties.txt");

    private Gson gson;
    private int port;
    private String ip;

    // Constructor
    public Server() throws IOException {
        this.gson = new GsonBuilder().create();
        // Instantiate handler here >>
        this.sr = new ServerResponse();
    }

    public void start() throws IOException {

        ip = property.getProperty("ipaddress");
        port = Integer.parseInt(property.getProperty("webport"));

        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        // insert createContext paths here
        server.createContext("/", new FileHandler());
        server.createContext("/api", new RestHandler());
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
                byte[] bytesToSend = response.getBytes();

                Headers h = he.getResponseHeaders();
                h.add("Content-Type", contentType);
                h.add("charset", "UTF-8");

                he.sendResponseHeaders(200, 0);
                try (OutputStream os = he.getResponseBody()) {
                    os.write(bytesToSend, 0, bytesToSend.length);
                }

            }

        };
    }

}
