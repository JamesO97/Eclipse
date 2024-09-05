package chatroom;


import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server
{
    ArrayList<Socket> clients = new ArrayList<>();
    static int port = 5000;
    String addy = "127.0.0.1";
    int clientCount = 0;
    ServerSocket server;
    Socket client;

    public Server(int port)
    {
        Server.port = port;
    }

    public void startServer() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server starting...");
        System.out.println("Any client can stop the server by sending -1");
        while(true)
        {
            client = server.accept();
            clients.add(client);
            System.out.println("Client accepted");
            clientCount++;

            ServerThread runnable= new ServerThread(client,clientCount,this);
        }

    }
    public static void main(String [] args)
    {
        try
        {
            Server serverobj=new Server(5000);
            serverobj.startServer();

        }
        catch(Exception e)
        {
            System.out.print("Error" + e.getMessage());
        }
//        finally
//        {
//
//        }

    }
}
