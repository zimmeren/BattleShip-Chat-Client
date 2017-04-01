package server.PrivateChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created PrivateChat by Austin for Battleship Server on 4/7/2015.
 */
public class PrivateChat implements Runnable{

    private static final int PORT = 47000;
    private final int maxUsers = 100;
    private static Socket[] users = new Socket[100];
    private static String[] gameIDs = new String[100];
    private int id;

    // ---------------------------------------------------------------------------

    @Override
    public void run()
    {
        while (true)
        {
            try (ServerSocket serverSocket = new ServerSocket(PORT))
            {
                Socket user = serverSocket.accept();
                for (int i = 0; i < maxUsers; i++)
                {
                    if (users[i] == null)
                    {
                        id = i;
                    }
                }
                users[id] = user;
                gameIDs[id] = "noID";
                HandleAPrivateChat task = new HandleAPrivateChat(users, gameIDs, id);
                new Thread(task).start();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}