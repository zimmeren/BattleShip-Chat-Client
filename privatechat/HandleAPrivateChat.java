package server.PrivateChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created HandleAPrivateChat by Austin for Battleship Server on 4/7/2015.
 */
public class HandleAPrivateChat implements Runnable
{
    private final int maxUsers = 100;
    private int id;
    private DataInputStream fromClient;
    private DataOutputStream toClient;
    private DataOutputStream toListeners;
    private Socket[] users;
    private String[] gameIDs;
    private String message;


    public HandleAPrivateChat( Socket[] usersIn, String[] gameIDsIn, int idIn ) throws IOException
    {
        id = idIn;
        users = usersIn;
        gameIDs = gameIDsIn;
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
                System.out.println("Private server received message: " + message);
                if (gameIDs[id].equals("noID") && message.indexOf("GameID: ") == 0)
                {
                    gameIDs[id] = message.substring(8);
                    System.out.println("user " + id + " has been assigned a game id of " + gameIDs[id]);
                }
                else
                {
                    for (int i = 0; i < maxUsers; i++) {
                        if (i != id) {
                            if (gameIDs[i] != null && gameIDs[i].equals(gameIDs[id]))  //send to all users
                            {
                                toListeners = new DataOutputStream(users[i].getOutputStream());
                                toListeners.writeUTF(message);
                            }
                        }
                    }
                }
            }
            catch (IOException e)
            {  //if a user disconnects remove them from users array to open room
                users[id] = null;
                gameIDs[id] = null;
                System.out.println("a user has logged off");
                break;
            }
        }
    }
}
