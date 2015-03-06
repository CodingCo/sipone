package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import facades.SubjectFacade;
import java.io.IOException;
import java.io.OutputStream;
import webInterface.SubjectFacadeIF;
import webServer.Factory;

public class SubjectHandler implements HttpHandler {

    SubjectFacadeIF sF;
    Gson transformer;
    //SubjectFacadeInterface subjectFacade;
    ServerResponse sr;

    private String response;
    private int statusCode;

    public SubjectHandler(Gson transformer, ServerResponse sr, SubjectFacadeIF facade) {
        this.transformer = transformer;
        this.sr = sr;
        this.sF = facade;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String method = he.getRequestMethod().toUpperCase();
        String pathName = he.getRequestURI().getPath().substring(5).split("/")[1];
        response = "";
        statusCode = 0;
        System.out.println("pathName: " + pathName);

        switch (pathName) {
            case "first":
                handleSubject(he, method);
                break;
            default:
                sendNotFound();

        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, statusCode, response);
    }

    private void handleSubject(HttpExchange he, String method) throws IOException {
        System.out.println("handleSubject");
        switch (method) {
            
            case "GET":
                System.out.println("Inside Get");
                String electiveSubject = this.sF.getFirstElectiveSubjects();
                System.out.println(electiveSubject
                );
                
                
                he.sendResponseHeaders(200, electiveSubject.length());
                try (OutputStream os = he.getResponseBody()) {
                    os.write(electiveSubject.getBytes());
                }
                
                break;

            case "POST":
                break;

            case "DELETE":
                break;

            case "PUT":
                break;
        }
    }

    private void handleSubmit(HttpExchange he, String method) {

        System.out.println("handleSubmit");
    }

    private void sendNotFound() {
        statusCode = 404;
        response = "path not supported!";
        System.out.println("sendNotFound");
    }

}
