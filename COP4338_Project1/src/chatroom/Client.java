package chatroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client implements Runnable
{

    private final Socket client;
    private Scanner in;
    private PrintWriter out;

    public Client(Socket aSocket)
    {
        client = aSocket;
    }
    @Override
    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(client.getInputStream());
                out = new PrintWriter(client.getOutputStream());
                runChat();
            }
            finally
            {
                client.close();
            }
        }
        catch(Exception e)
        {
        }

    }

    public void runChat() throws InterruptedException{
        while(true)
        {
            System.out.println("Connected");
//            try
//            {
                //Thread.sleep(1);
                String text = in.nextLine();
                if(text.equalsIgnoreCase("Quit"))
                {

                }
                else
                {
                    out.write("Client: " + text);
                }
//            } catch (InterruptedException ex)
//            {
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    public static void main(String args[]) throws IOException {
        Client client = new Client(new Socket("127.0.0.1", 5000));
        client.run();
    }
}
