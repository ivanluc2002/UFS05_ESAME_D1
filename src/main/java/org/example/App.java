package org.example;

// http://127.0.0.1:8000/?cmd=all


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

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


    }
}