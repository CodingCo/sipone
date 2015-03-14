package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import webInterface.SubjectVoteFacadeIF;

public class SubjectVoteHandler implements HttpHandler {

    private ServerResponse sr;
    private SubjectVoteFacadeIF facade;

    private String response;
    private int status;

    public SubjectVoteHandler(ServerResponse sr, SubjectVoteFacadeIF facade) {
        this.sr = sr;
        this.facade = facade;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String method = he.getRequestMethod().toUpperCase();
        response = "";
        status = 0;

        switch (method) {
            case "GET":
                getRequest(he);
                break;

            case "POST":
                postRequest(he);
                break;

            case "DELETE":
                deleteRequest(he);
                break;

            case "PUT":
                putRequest(he);
                break;
        }
        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, status, response);
    }

    private void getRequest(HttpExchange he) throws IOException {
        response = facade.getSubjectVotes();
        status = 200;
    }

    private void postRequest(HttpExchange he) throws IOException {
        try {
            String subjectAsJson = readInput(he);
            response = facade.submitSubjectVote(subjectAsJson);
            status = 200;
        } catch (IOException e) {
            status = 400;
            response = e.getMessage();
        }

    }

    private void deleteRequest(HttpExchange he) throws IOException {
        System.out.println("Delete subjectVote called, not yet supported!");
        response = "{\n"
                + "err: \"true\",\n"
                + "msg: \"\"\n"
                + "}";
        status = 200;
    }

    private void putRequest(HttpExchange he) throws IOException {
        System.out.println("Put subjectVote called, not yet supported!");
        response = "{\n"
                + "err: \"true\",\n"
                + "msg: \"\"\n"
                + "}";
        status = 200;
    }

    private String readInput(HttpExchange he) throws UnsupportedEncodingException, IOException {
        String temp = "";

        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String jsonInput = br.readLine();

        while ((temp = br.readLine()) != null) {
            jsonInput += temp;
        }

        return jsonInput;
    }

}
