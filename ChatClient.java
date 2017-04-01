package eecs285.battleship.GUI.ChatBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * Created ChatClient by Austin for Battleship Server on 4/7/2015.
 */
public class ChatClient
{
    private Socket socket;
    private DataInputStream fromChatServerStream;
    private DataOutputStream toChatServerStream;
    private ChatBox chatBox;
    private ChatListener chatListener;
    private String serverName;
    private String username;
    private Thread thread;


    public ChatClient(String in_serverName, String in_username)
    {
        username = in_username;
        serverName = in_serverName;
        chatBox = new ChatBox(username, this);
        setStatePublic();
    }

    public void sendToServer(String message)
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

    public void sendSystemMessage(String message)
    {
        chatBox.addMsg("System", message);
    }

    public void setStatePublic()
    {
        try
        {
            if (thread != null && thread.isAlive())
            {
                chatListener.stopRunning();
            }
            if (socket != null && socket.isConnected())
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    System.out.print("public socket has been closed");
                }
            }
            socket = new Socket(serverName, 46000);
            fromChatServerStream = new DataInputStream(socket.getInputStream());
            toChatServerStream = new DataOutputStream(socket.getOutputStream());
            chatListener = new ChatListener(fromChatServerStream, chatBox);
            thread = new Thread(chatListener);
            thread.start();
            chatBox.publicState();
        }
        catch(IOException e)
        {
            System.out.println("error in Chat Client: could not connect to public chat server");
        }
    }

    public void setStatePrivate(String gameID)
    {

        try
        {
            if (thread != null && thread.isAlive())
            {
                chatListener.stopRunning();
            }
            if (socket != null && socket.isConnected())
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    System.out.print("public socket has been closed");
                }
            }
            socket = new Socket(serverName, 47000);
            fromChatServerStream = new DataInputStream(socket.getInputStream());
            toChatServerStream = new DataOutputStream(socket.getOutputStream());
            chatListener = new ChatListener(fromChatServerStream, chatBox);
            thread = new Thread(chatListener);
            thread.start();
            chatBox.privateState(gameID);
        }
        catch(IOException e)
        {
            System.out.println("error in Chat Client: could not connect to private chat server");
        }
    }
}
