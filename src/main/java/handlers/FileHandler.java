package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Elving
 */
public class FileHandler implements HttpHandler {

    String contentType = "";
    String contentFolder = "public/";

    @Override

    public void handle(HttpExchange he) throws IOException {
        String fileName = he.getRequestURI().getPath().substring(1);
        File file;

        System.out.println(fileName);

        if (fileName.isEmpty() || fileName.equals("/")) {
            System.out.println("If");
            file = new File(contentFolder + "index.html");
        } else {
            System.out.println("Inside Else");
            //Fix so it handles packages
            contentType = getContentType(fileName);
            System.out.println(contentType);
            file = new File(contentFolder + fileName);
            System.out.println(contentFolder + fileName);
        }

        if (file.exists()) {
            byte[] bytesToSend = new byte[(int) file.length()];
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                bis.read(bytesToSend, 0, bytesToSend.length);
            } catch (IOException ie) {
            }
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", contentType);
            he.sendResponseHeaders(200, bytesToSend.length);

            try (OutputStream os = he.getResponseBody()) {
                os.write(bytesToSend, 0, bytesToSend.length);
            }
        } else {
            String response = "404 (Not Found)\n";
            he.sendResponseHeaders(404, response.length());
            try (OutputStream os = he.getResponseBody()) {
                os.write(response.getBytes());
            }

        }
    }

    private static String getContentType(String s) {

        String contentType = s.substring(s.lastIndexOf(".") + 1);
        switch (contentType) {
            case "html":
                return "text/html";
            case "js":
                return "text/javascript";
            case "css":
                return "text/css";
            case "pdf":
                return "application/pdf";
            case "jar":
                return "applikation/zip";
            case "png":
            case "jpeg":
            case "jpg":
            case "gif":
                return "image/" + contentType;
        }
        return contentType;
    }

}
