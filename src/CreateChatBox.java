/**
 * Created by Zimmer on 4/8/15.
 */

import javax.swing.*;
import java.awt.*;

public class CreateChatBox extends JFrame
{
    private final JButton sendButton = new JButton("send");
    private final JTextField messageTextField = new JTextField(20);
    private JLabel headerLabel = new JLabel("Public Chat");
    private JPanel centerPan = new JPanel();

    private static final int SCREEN_WIDTH = 300;
    private static final int SCREEN_HEIGHT = 575;

    public CreateChatBox()
    {
        super("Battleship Chat Box");

        //try
        //{
            setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            setLayout(new BorderLayout());

            setBackground(Color.BLACK);
            headerLabel.setForeground(Color.WHITE);

            JPanel topPan = new JPanel(new FlowLayout());
            topPan.add(headerLabel);

            JPanel bottomPan = new JPanel(new BorderLayout());
            bottomPan.add(messageTextField, BorderLayout.CENTER);
            bottomPan.add(sendButton, BorderLayout.EAST);

            centerPan.setLayout(new BoxLayout(centerPan, BoxLayout.Y_AXIS));
            centerPan.setBackground(Color.BLACK);
            topPan.setBackground(Color.BLACK);
            bottomPan.setBackground(Color.BLACK);

            add(topPan, BorderLayout.NORTH);
            add(centerPan, BorderLayout.CENTER);
            add(bottomPan, BorderLayout.SOUTH);

            //EXAMPLE MESSAGES
            JPanel exMsg1 = new JPanel(new BorderLayout());
            JLabel exMsg1Name = new JLabel("player1" + ":   ");
            exMsg1Name.setForeground(Color.RED);
            JLabel exMsg1Text = new JLabel("Hey what is up?", SwingConstants.LEFT);
            exMsg1.add(exMsg1Name, BorderLayout.WEST);
            exMsg1.add(exMsg1Text, BorderLayout.CENTER);
            exMsg1.setBackground(Color.BLACK);
            exMsg1Name.setBackground(Color.BLACK);
            exMsg1Text.setBackground(Color.BLACK);
            exMsg1Text.setForeground(Color.WHITE);
            centerPan.add(exMsg1);

            JPanel exMsg2 = new JPanel(new BorderLayout());
            JLabel exMsg2Name = new JLabel("player2" + ":   ");
            exMsg2Name.setForeground(Color.RED);
            JLabel exMsg2Text = new JLabel("Nothing much. you?", SwingConstants.LEFT);
            exMsg2.add(exMsg2Name, BorderLayout.WEST);
            exMsg2.add(exMsg2Text, BorderLayout.CENTER);
            exMsg2.setBackground(Color.BLACK);
            exMsg2Name.setBackground(Color.BLACK);
            exMsg2Text.setBackground(Color.BLACK);
            exMsg2Text.setForeground(Color.WHITE);
            centerPan.add(exMsg2);

            JPanel exMsg3 = new JPanel(new BorderLayout());
            JLabel exMsg3Name = new JLabel("player1" + ":   ");
            exMsg3Name.setForeground(Color.RED);
            JLabel exMsg3Text = new JLabel("Nothing much either", SwingConstants.LEFT);
            exMsg3.add(exMsg3Name, BorderLayout.WEST);
            exMsg3.add(exMsg3Text, BorderLayout.CENTER);
            exMsg3.setBackground(Color.BLACK);
            exMsg3Name.setBackground(Color.BLACK);
            exMsg3Text.setBackground(Color.BLACK);
            exMsg3Text.setForeground(Color.WHITE);
            centerPan.add(exMsg3);

            JPanel exMsg4 = new JPanel(new BorderLayout());
            JLabel exMsg4Name = new JLabel("player2" + ":   ");
            exMsg4Name.setForeground(Color.RED);
            JLabel exMsg4Text = new JLabel("Cool", SwingConstants.LEFT);
            exMsg4.add(exMsg4Name, BorderLayout.WEST);
            exMsg4.add(exMsg4Text, BorderLayout.CENTER);
            exMsg4.setBackground(Color.BLACK);
            exMsg4Name.setBackground(Color.BLACK);
            exMsg4Text.setBackground(Color.BLACK);
            exMsg4Text.setForeground(Color.WHITE);
            centerPan.add(exMsg4);

            JPanel exMsg5 = new JPanel(new BorderLayout());
            JLabel exMsg5Name = new JLabel("player1" + ":   ");
            exMsg5Name.setForeground(Color.RED);
            JLabel exMsg5Text = new JLabel("Cool.", SwingConstants.LEFT);
            exMsg5.add(exMsg5Name, BorderLayout.WEST);
            exMsg5.add(exMsg5Text, BorderLayout.CENTER);
            exMsg5.setBackground(Color.BLACK);
            exMsg5Name.setBackground(Color.BLACK);
            exMsg5Text.setBackground(Color.BLACK);
            exMsg5Text.setForeground(Color.WHITE);
            centerPan.add(exMsg5);

            //initializeButtons();

            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*}
        catch(IOException e)
        {
            System.out.print("error creating chat box");
        }*/
    }

    private void initializeButtons()
    {
        //OpeningScreenListener buttonListener = new OpeningScreenListener(this);
        //sendButton.addActionListener(buttonListener);
    }
}
