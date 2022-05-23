package org.example;

// http://127.0.0.1:8000/?cmd=all


import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class App
{
    public static void main( String[] args ) {
        ArrayList<Wine> wines = new ArrayList<>();
        wines.add(new Wine(13, "Dom perignon Vintage Moet & chandon 2008", 225.94, "white"));
        wines.add(new Wine(14, "Pignoli", 133.0, "red"));
        wines.add(new Wine(124, "Pinot nero", 43, "red"));

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //server.createContext("/applications/myapp", new MyHandler());
        server.createContext("/", new MyHandler(wines));
        server.setExecutor(null); // creates a default executor
        server.start();
    }


}