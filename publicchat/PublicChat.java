package server.PublicChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created PublicChat by Austin for Battleship Server on 4/7/2015.
 */
public class PublicChat implements Runnable{

    private static final int PORT = 46000;
    private final int maxUsers = 100;
    private static Socket[] users = new Socket[100];
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
                HandleAPublicChat task = new HandleAPublicChat(users, id);
                new Thread(task).start();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
