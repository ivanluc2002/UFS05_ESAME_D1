package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;

public class MyHandler implements HttpHandler {
    private final ArrayList<Wine> wines;

    public MyHandler(ArrayList<Wine> wines) {
        this.wines = wines;
    }

    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();

        URI uri = t.getRequestURI();
        String query = uri.getQuery();
        String output = "";

        for(int i = 0; i < wines.size(); i++) {
            output += wines.get(i) + "<br>";
        }

        String response = "<!doctype html>\n" +
                "<html lang=en>\n" +
                "<head>\n" +
                "<meta charset=utf-8>\n" +
                "<title>MyJava Sample</title>\n" +
                "</head>\n" +
                "<body>\n" +
                output +
                "</body>\n" +
                "</html>\n";

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String read(InputStream is) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is));

        System.out.println("\n");
        String received = null;
        while (true) {
            String s = null;
            try {
                if ((s = br.readLine()) == null ) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received += s;

        }
        return received;
    }




}