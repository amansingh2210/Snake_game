import java.io.IOException;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * The Gui class exports a simple text based user interface
 * that is used to draw characters, lines and other elements on
 * the Screen. It simplifies the Lanterna library.
 *
 * The Gui uses coordinates where 0, 0 is the upper left corner of the
 * screen. x coordinates specify the number of columns to the right, and
 * y coordinates specify the number of rows down.
 *
 * Colors in the Gui are specified as strings. The valid color strings are:
 * WHITE, BLACK, RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
 */
public class Gui {
    private static DefaultTerminalFactory defaultTerminalFactory
        = new DefaultTerminalFactory();

    private Terminal terminal;
    private Screen screen;
    private TextGraphics graphics;

    /**
     * Creates a Gui that occupies the whole screen.
     */
    public Gui() {
        try {
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            graphics = screen.newTextGraphics();
        } catch (Exception e) {
            System.err.println("Could not create GUI");
            System.exit(-1);
        }
    }

    /**
     * Call start before using the Gui class
     */
    public void start() {
        try {
            terminal.setCursorVisible(false);
            screen.clear();
            screen.startScreen();
        } catch (Exception e) {}
    }

    /**
     * Call stop after you're done with the Gui class
     */
    public void stop() {
        try {
            terminal.setCursorVisible(true);
            screen.stopScreen();
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * Clear the in memory screen. This not not shown untill you call refresh
     */
    public void clear() {
        try {
            screen.clear();
        } catch (Exception e) {
            System.err.println("Could not clear screen");
            System.exit(-1);
        }
    }

    /**
     * Refresh the screen so that all the screen clears, text and lines you
     * have drawn shows up on the screen
     */
    public void refresh() {
        try {
            screen.refresh();
        } catch (Exception e) {
            System.err.println("Could not refresh GUI");
            System.exit(-1);
        }
    }

    /**
     * The Gui takes over the whole screen. This returns the height in an integer
     * number of rows of the screen
     */
    public int getScreenHeight() {
        return screen.getTerminalSize().getRows();
    }

    /**
     * The Gui takes over the whole screen. This returns the height in an integer
     * number of columns of the screen
     */
    public int getScreenWidth() {
        return screen.getTerminalSize().getColumns();
    }

    /**
     * Private member function that converts string colors to Lanterna TextColor
     * objects.
     */
    private static TextColor toTextColor(String color) {
        color = color.toUpperCase();
        if (color.equals("WHITE"))
            return TextColor.ANSI.WHITE;
        if (color.equals("BLACK"))
            return TextColor.ANSI.BLACK;
        if (color.equals("RED"))
            return TextColor.ANSI.RED;
        if (color.equals("ORANGE"))
            return new TextColor.RGB(0xff, 0xa5, 0x00);
        if (color.equals("YELLOW"))
            return TextColor.ANSI.YELLOW;
        if (color.equals("GREEN"))
            return TextColor.ANSI.GREEN;
        if (color.equals("BLUE"))
            return TextColor.ANSI.BLUE;
        if (color.equals("INDIGO"))
            return TextColor.ANSI.CYAN;
        if (color.equals("VIOLET"))
            return TextColor.ANSI.MAGENTA;

        return TextColor.ANSI.DEFAULT;
    }

    /**
     * Draw a character at the given screen position x and y with the
     * given colors
     *
     * @param x The x coordinate to draw the character
     * @param y The y coordinates to draw the character
     * @param c The character to draw
     * @param foregroundColor The foreground color of the character
     * @param backgroundColor The background color of the character
     */
    public void drawCharacter(int x, int y, char c,
        String foregroundColor, String backgroundColor) {
        TextColor foregroundTextColor = toTextColor(foregroundColor);
        TextColor backgroundTextColor = toTextColor(backgroundColor);
        screen.setCharacter(x, y, new TextCharacter(c, foregroundTextColor,
            backgroundTextColor));
    }

    /**
     * Draw a character at the given screen position x and y with the
     * given colors
     *
     * @param sx The x coordinate to start the line
     * @param sy The y coordinates to start the line
     * @param ex The x coordinates to end the line
     * @param ey The y coordinates to end the line
     * @param c The character to draw as the line
     * @param foregroundColor The foreground color of the character
     * @param backgroundColor The background color of the character
     */
    public void drawLine(int sx, int sy, int ex, int ey, char c,
        String foregroundColor, String backgroundColor) {
        TextColor foregroundTextColor = toTextColor(foregroundColor);
        TextColor backgroundTextColor = toTextColor(backgroundColor);
        graphics.drawLine(sx, sy, ex, ey, new TextCharacter(c,
            foregroundTextColor, backgroundTextColor));
    }

    /**
     * Return the character that the user pressed. If the user didn't press
     * a key, it returns (char) 0x0;
     *
     * @return The character that the user pressed, or 0x0 if none
     */
    public char getKeypress() {
        try {
            KeyStroke ks = screen.pollInput();
            KeyType kt = ks.getKeyType();
            // System.err.println("KeyType=" + kt);
            switch (kt) {
                case ArrowDown:
                    return Gui.DOWN_ARROW_KEY;

                case ArrowUp:
                    return Gui.UP_ARROW_KEY;

                case ArrowRight:
                    return Gui.RIGHT_ARROW_KEY;

                case ArrowLeft:
                    return Gui.LEFT_ARROW_KEY;
            }
            return ks.getCharacter();
        } catch (Exception e) {
            return (char) 0x0;
        }
    }

    /**
     * Use this to check if the user pressed the up arrow
     <code>
       Gui gui = new Gui();
       char c = gui.getKeypress();
       if (c == Gui.UP_ARROW_KEY) { ... }
     </code>
     */
    public static final char UP_ARROW_KEY = (char) 24;

    /**
     * Use this to check if the user pressed the up arrow
     <code>
       Gui gui = new Gui();
       char c = gui.getKeypress();
       if (c == Gui.DOWN_ARROW_KEY) { ... }
     </code>
     */
    public static final char DOWN_ARROW_KEY = (char) 25;

    /**
     * Use this to check if the user pressed the right arrow key
    <code>
      Gui gui = new Gui();
      char c = gui.getKeypress();
      if (c == Gui.RIGHT_ARROW_KEY) { ... }
    </code>
     * Use this to check if the user pressed the right arrow
     */
    public static final char RIGHT_ARROW_KEY = (char) 26;

    /**
     * Use this to check if the user pressed the left arrow key
    <code>
      Gui gui = new Gui();
      char c = gui.getKeypress();
      if (c == Gui.LEFT_ARROW_KEY) { ... }
    </code>
     */
    public static final char LEFT_ARROW_KEY = (char) 27;

    /**
     * Sleep the Thread for the given interval in milliseconds
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(Exception e) {
            // do nothing
        }
    }
}
