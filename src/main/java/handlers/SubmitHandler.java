package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import facades.SubjectFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import webInterface.SubjectFacadeIF;
import webServer.Factory;

/**
 *
 * @author Robert Elving
 */
public class SubmitHandler implements HttpHandler {

    SubjectFacadeIF sF;
    Gson transformer;
    //SubjectFacadeInterface subjectFacade;
    ServerResponse sr;

    private String response;
    private int statusCode;

    public SubmitHandler(Gson transformer, ServerResponse sr, SubjectFacadeIF facade) {
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
                handleSubmit(he, method);
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

                break;

            case "POST":

                InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                
                int b;
                StringBuilder buf = new StringBuilder(512);
                while ((b = br.read()) != -1) {
                    buf.append((char) b);
                }

                br.close();
                isr.close();

                String submittedJsonString = buf.toString();

                sF.submitElectiveSubject(submittedJsonString);

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
