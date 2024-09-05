package chatroom;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread  implements Runnable    {

    Server server;
    Socket client;
    InputStream inStream;
    OutputStream outStream;
    Scanner in;
    PrintWriter out;
    int id;
    String s;

    ServerThread(Socket client, int count, Server server) throws IOException
    {
        this.client=client;
        this.server=server;
        this.id=count;
        System.out.println("Connection "+id+"established with client "+client);

        inStream = new DataInputStream(client.getInputStream());
        outStream = client.getOutputStream();
        in = new Scanner(inStream);
        out =  new PrintWriter(outStream);
    }

    @Override
    public void run()
    {
        int x=1;
        try{
            while(true){
                s = in.nextLine();

                System. out.print("Client("+id+") :"+s+"\n");
                System.out.print("Server : ");
                //s=stdin.readLine();
                s = in.nextLine();
                if (s.equalsIgnoreCase("bye"))
                {
                    out.println("BYE");
                    x=0;
                    System.out.println("Connection ended by server");
                    break;
                }
                out.println(s);
            }


            in.close();
            client.close();
            out.close();
            if(x==0) {
                System.out.println( "Server cleaning up." );
                System.exit(0);
            }
        }
        catch(IOException ex){
            System.out.println("Error : "+ex);
        }


    }
}