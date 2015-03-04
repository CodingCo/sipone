package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 *
 * @author christophermortensen
 */
public class RestHandler implements HttpHandler {
    
    Gson transformer;
    ServerResponse sr;
    
    private String response;
    private int status;

    @Override
    public void handle(HttpExchange he) throws IOException {
        String fileName = he.getRequestURI().getPath().substring(4);
        System.out.println("fileName: " + fileName);
    }

}
