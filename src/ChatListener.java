/**
 * Created by Zimmer on 4/8/15.
 */
import java.io.DataInputStream;
import java.io.IOException;

public class ChatListener implements Runnable
{
    private static DataInputStream fromChatServer;

    public ChatListener(DataInputStream inFromChatServer)
    {
        fromChatServer = inFromChatServer;
    }

    @Override
    public void run()
    {
        String message;
        while (true)
        {
            try
            {
                message = fromChatServer.readUTF();
                System.out.println(message);
            }
            catch (IOException e)
            {
                System.out.println("Error in ChatListener");
                e.printStackTrace();
            }
        }
    }
}
