/**
 * Created by Zimmer on 4/8/15.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient
{
    private Socket socket;
    private static DataInputStream fromChatServerStream;
    private static DataOutputStream toChatServerStream;
    private static ChatListener chatListener;

    public static void main(String[] args)
    {
        System.out.println("creating chat box GUI");
        CreateChatBox chatBox = new CreateChatBox();

        //System.out.println("starting client");
        //ChatClient chatClient = new ChatClient("localhost");
        //chatClient.sendToServer("hello server");

    }

    public ChatClient(String serverName)
    {
        try
        {
            socket = new Socket(serverName, 46000);
            fromChatServerStream = new DataInputStream(socket.getInputStream());
            toChatServerStream = new DataOutputStream(socket.getOutputStream());
            chatListener = new ChatListener(fromChatServerStream);
            Thread thread = new Thread(chatListener);
            thread.start();
        }
        catch(IOException e)
        {
            System.out.println("error in Chat Client: could not connect to chat server");
        }
    }

    private static void sendToServer(String message)
    {
        try
        {
            toChatServerStream.writeUTF(message);
        }
        catch(IOException e)
        {
            System.out.println("error in Chat Client: could not write message to server");
            System.out.println("error causing message: " + message);
        }
    }
}
