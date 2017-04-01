package eecs285.battleship.GUI.ChatBox;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zimmer on 4/14/15.
 * from http://stackoverflow.com/questions/15783014/jtextarea-on-jpanel-inside-jscrollpane-does-not-resize-properly
 * to fix regular jPanel not resizing properly
 */
public class ScrollablePanel extends JPanel implements Scrollable{
    public Dimension getPreferredScrollableViewportSize() {
        return super.getPreferredSize(); //tell the JScrollPane that we want to be our 'preferredSize'
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 1;
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 1;
    }

    public boolean getScrollableTracksViewportWidth() {
        return true;//track the width, and re-size as needed.
    }

    public boolean getScrollableTracksViewportHeight() {
        return false; //we don't want to track the height, because we want to scroll vertically.
    }
}
