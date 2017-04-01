package eecs285.battleship.GUI.ChatBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatBox extends JFrame
{
    private final JButton sendButton = new JButton("send");
    private final JTextField messageTextField = new JTextField(20);
    private String user;
    private String gameID;
    private JLabel headerLabel;
    private ScrollablePanel centerPan;
    private JScrollPane centerScrollPan;
    private JScrollBar centerScrollBar;
    private ChatClient chatClient;
    private int msgNum = 0;
    private GridBagConstraints gbc = new GridBagConstraints();

    private static final int SCREEN_WIDTH = 300;
    private static final int SCREEN_HEIGHT = 575;
    private static final int MIN_SCREEN_WIDTH = 300;
    private static final int MIN_SCREEN_HEIGHT = 300;
    private static final int GRID_COLUMN_NUM = 0;
    private static final int ENTER_KEY = 10;
    private static final String PUBLIC_HEADER = "Public Chat";
    private static final String PRIVATE_HEADER = "Private Chat";

    private static final Color innerColor = new Color(25,25,25);


    /**
     * Created ChatBox by Austin for Battleship Server on 4/7/2015.
     */
    public ChatBox(String in_username, ChatClient in_ChatClient)
    {
        super("Battleship Chat Box");

        user = in_username;
        chatClient = in_ChatClient;
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setMinimumSize(new Dimension(MIN_SCREEN_WIDTH, MIN_SCREEN_HEIGHT));
        setLayout(new BorderLayout());

        setBackground(Color.BLACK);
        headerLabel = new JLabel(PUBLIC_HEADER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font(headerLabel.getFont().getFontName(), Font.PLAIN, 25));

        JPanel topPan = new JPanel(new FlowLayout());
        topPan.add(headerLabel);

        JPanel bottomPan = new JPanel(new BorderLayout());
        bottomPan.add(messageTextField, BorderLayout.CENTER);
        bottomPan.add(sendButton, BorderLayout.EAST);


        centerPan = new ScrollablePanel();
        centerPan.setLayout(new GridBagLayout());
        centerScrollPan = new JScrollPane(centerPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerScrollBar = centerScrollPan.getVerticalScrollBar();

        centerScrollPan.setBackground(Color.BLACK);
        centerScrollPan.getViewport().setBackground(Color.BLACK);
        centerPan.setBackground(Color.BLACK);
        topPan.setBackground(Color.BLACK);
        bottomPan.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);

        topPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerScrollPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(topPan, BorderLayout.NORTH);
        add(centerScrollPan, BorderLayout.CENTER);
        add(bottomPan, BorderLayout.SOUTH);

        addMsg("System", "Welcome to Battleship!");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
            }
        });

        messageTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == ENTER_KEY) {
                    sendMsg();
                }
            }
        });

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void addMsg(String userName, String msg)
    {
        JPanel newMsg = new JPanel(new BorderLayout());
        JLabel newMsgName = new JLabel(userName + ":");
        JTextArea newMsgTextArea = new JTextArea();
        newMsgTextArea.setEditable(false);
        newMsgTextArea.setText(msg);
        newMsgTextArea.setLineWrap(true);
        newMsgTextArea.setWrapStyleWord(true);
        newMsg.setBorder(BorderFactory.createLineBorder(innerColor, 2));
        newMsgName.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        newMsgTextArea.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        newMsg.setBackground(Color.BLACK);
        newMsgName.setBackground(innerColor);
        newMsgTextArea.setBackground(innerColor);
        if (userName.equals("System"))
        {
            newMsgName.setForeground(Color.YELLOW);
        }
        else if(userName.equals(user))
        {
            newMsgName.setForeground(Color.CYAN);
        }
        else
        {
            newMsgName.setForeground(Color.RED);
        }
        newMsgTextArea.setForeground(Color.WHITE);
        newMsg.add(newMsgName, BorderLayout.WEST);
        newMsg.add(newMsgTextArea, BorderLayout.CENTER);
        gbc.gridx = GRID_COLUMN_NUM;
        gbc.gridy = msgNum;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(2,0,2,0);
        centerPan.add(newMsg, gbc);
        msgNum++;
        revalidate();
        centerScrollBar.setValue(centerScrollBar.getMaximum());
    }

    private void sendMsg()
    {
        //do not send if empty string
        if (!messageTextField.getText().isEmpty())
        {
            addMsg(user, messageTextField.getText());
            chatClient.sendToServer(user + " " + messageTextField.getText());
            messageTextField.setText("");
        }
    }

    public void publicState()
    {
        headerLabel.setText(PUBLIC_HEADER);
        addMsg("System", "You are in Public Chat, you can now talk to everyone currently in the lobby");
    }

    public void privateState(String in_GameID)
    {
        headerLabel.setText(PRIVATE_HEADER);
        gameID = in_GameID;
        chatClient.sendToServer("GameID: " + gameID);
        addMsg("System", "You are in Private Chat, you can now talk to your opponent");
    }
}
