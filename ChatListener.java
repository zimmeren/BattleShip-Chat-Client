package eecs285.battleship.GUI.ChatBox;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created ChatListener by Austin for Battleship Server on 4/7/2015.
 */
public class ChatListener implements Runnable
{
    private static DataInputStream fromChatServer;
    private ChatBox toChatBox;
    private boolean running;

    public ChatListener(DataInputStream inFromChatServer, ChatBox inChatBox)
    {
        fromChatServer = inFromChatServer;
        toChatBox = inChatBox;
    }

    @Override
    public void run()
    {
        String message;
        running = true;
        while (running)
        {
            try
            {
                message = fromChatServer.readUTF();
                System.out.println(message);
                int pos = message.indexOf(" ");
                String username = message.substring(0, pos);
                String msg = message.substring(pos+1);
                toChatBox.addMsg(username, msg);
            }
            catch (IOException e)
            {
                System.out.println("ChatListener has closed");
            }
        }
    }

    public void stopRunning()
    {
        running = false;
    }
}
