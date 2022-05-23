package org.example;

// http://127.0.0.1:8000/?cmd=all


import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
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
        ArrayList<Wine> vino= new ArrayList<Wine>();
        vino.add(new Wine(13,"dom perignon",225.94,"white"));
        vino.add(new Wine(14,"Pignoli",133.0,"red"));
        vino.add(new Wine(124,"PInot nero",43.0,"red"));

        }


    }
