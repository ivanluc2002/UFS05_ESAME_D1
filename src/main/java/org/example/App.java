package org.example;

// http://127.0.0.1:8000/?cmd=all


import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //server.createContext("/applications/myapp", new MyHandler());
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

            String jsonStr = "[ {\"id\": \"13\",\n" +
                    " \"name\": \"Dom perignon Vintage Moet &chandon 2008\"\n" +
                    "\"price\": \"225,94\"\n"+
                    "\"type\": \"white\"\n"+
                    " },\n" +
                    "\"id\": \"14\",\n" +
                " \"name\": \"Pignoli\"\n" +
                "\"price\": \"133,0\"\n"+
                "\"type\": \"red\"\n"+
                " },\n" +
                    "\"id\": \"124\",\n" +
                " \"name\": \"pinot nero\"\n" +
                "\"price\": \"43\"\n"+
                "\"type\": \"red\"\n"+
                " },\n"+
                    "]\n";

            Gson gson = new Gson();
            Wine[] vino = gson.fromJson( jsonStr, Wine[].class);
            System.out.println(Arrays.toString(vino));
        }


    }
