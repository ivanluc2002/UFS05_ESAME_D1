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
        String[] param = query.split("[=&]");

        String output = "";
        ArrayList<Wine> box = (ArrayList<Wine>) wines.clone();
        if(param[0].equals("cmd")){
            switch(param[1]){
                case "all":
                    for(int i = 0; i < wines.size(); i++) {
                        output += wines.get(i) + "<br>";
                    }
                    break;
                case "red":
                    for(int i = 0; i < wines.size(); i++) {
                        if(wines.get(i).getType().equals("red")){
                            output += wines.get(i) + "<br>";
                        }
                    }
                    break;
                case "white":
                    for(int i = 0; i < wines.size(); i++) {
                        if(wines.get(i).getType().equals("white")){
                            output += wines.get(i) + "<br>";
                        }
                    }
                    break;
                case "sorted_by_name":
                    box.sort((w1, w2) -> {
                        return w1.getName().compareTo(w2.getName());
                    });
                    for(int i = 0; i < box.size(); i++) {
                        output += box.get(i) + "<br>";
                    }
                    break;
                case "sorted_by_price":
                    box.sort((w1, w2) -> {
                        if(w1.getPrice() > w2.getPrice()) return 1;
                        if(w1.getPrice() < w2.getPrice()) return -1;
                        return 0;
                    });
                    for(int i = 0; i < box.size(); i++) {
                        output += box.get(i) + "<br>";
                    }
                    break;
            }
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