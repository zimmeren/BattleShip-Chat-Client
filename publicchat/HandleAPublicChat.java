package server.PublicChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created HandleAPublicChat by Austin for Battleship Server on 4/7/2015.
 */
public class HandleAPublicChat implements Runnable
{
    private final int maxUsers = 100;
    private int id;
    private DataInputStream fromClient;
    private DataOutputStream toClient;
    private DataOutputStream toListeners;
    private Socket[] users;
    private String message;
    
    public HandleAPublicChat( Socket[] usersIn, int idIn ) throws IOException
    {
        id = idIn;
        users = usersIn;
        fromClient = new DataInputStream(users[id].getInputStream());
        toClient = new DataOutputStream(users[id].getOutputStream());
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                message = fromClient.readUTF();  //get input from client
                System.out.println("Public server received message: " + message);
                for (int i = 0; i < maxUsers; i++)
                {
                    if (i != id)
                    {
                        if (users[i] != null)  //send to all users
                        {
                            toListeners = new DataOutputStream(users[i].getOutputStream());
                            toListeners.writeUTF(message);
                        }
                    }
                }
            }
            catch (IOException e)
            {  //if a user disconnects remove them from users array to open room
                users[id] = null;
                System.out.println("a user has logged off");
                break;
            }
        }
    }
}
