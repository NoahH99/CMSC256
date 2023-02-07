package cmsc256;

import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.games.NonBlockingGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class "GameBasics1" extends the "NonBlockingGame" class and creates a game that displays messages
 * on a game board. The game board has a height and width, which can be changed in the main method by
 * modifying the "height" and "width" variables. The game board is initialized with randomly generated
 * background colors, and the message "CS Rocks" is drawn in the center of the board. The user can interact
 * with the game board using key commands (left, right, up, down) to clear the screen, draw different messages,
 * or display a multi-line message. The messages are drawn using the "drawMessage" method, which splits the
 * message into lines and draws each line centered on the board. The "clearScreen" method is used to clear the
 * entire game board, and the "getRandomColor" method returns a random color from a list of available colors.
 *
 * @author Noah Hendrickson
 * @version 0.1.0
 */
class GameBasics1 extends NonBlockingGame {

    private static final List<NamedColor> VALUES;
    private final Random random;

    static {
        VALUES = new ArrayList<>(NamedColor.values().length - 1);

        Arrays.stream(NamedColor.values())
                .filter(c -> c != NamedColor.black)
                .forEach(VALUES::add);
    }

    /**
     * Constructor for objects of class cmsc256.GameBasics1
     *
     * @param assignmentNumber your Bridges assignment number
     * @param username         your Bridges username
     * @param apiKey           your Bridges apiKey
     * @param numRows          number of rows in the game grid (height)
     * @param numCols          number of columns in the game grid (width)
     */
    public GameBasics1(int assignmentNumber, String username, String apiKey, int numRows, int numCols) {
        //create a game board of numRows x numCols within a new Bridges object
        super(assignmentNumber, username, apiKey, numRows, numCols);
        setTitle("Game Basics 1");
        setDescription("Create messages to display on the game board.");

        this.random = new Random();
    }


    public static void main(String[] args) {
        // declare and initialize a height variable (number of rows)
        int height = 12;
        // declare and initialize a width variable (number of columns))
        int width = 18;
        // create the cmsc256.GameBasics1 object. Make sure to use your own assignment number,  username, and API key as arguments
        GameBasics1 game = new GameBasics1(520, args[0], args[1], height, width);
        // start the game
        game.start();
    }


    /**
     * initialize() method runs exactly once
     */
    public void initialize() {
        // create a single color or multicolored background to your grid
        // YOUR CODE HERE:

        for (int i = 0; i < getBoardHeight(); i++) {
            for (int j = 0; j < getBoardWidth(); j++) {
                setBGColor(i, j, getRandomColor());
            }
        }

        drawMessage("CS Rocks");
    }

    /**
     * gameLoop() method runs once per each frame of the game
     */
    public void gameLoop() {
        // use the drawSymbol() method to put symbols in the board
        // put up a message  "CS Rocks" or something interesting
        //
        // Extra: Use the input key methods to make so the user can make the message appear and disappear
        //     for instance, keyLeft(), keyRight(), etc
        // Another Extra: Use the input key methods to make it so the user can make it display different messages
        //     
        // YOUR CODE HERE:

        if (keyLeft()) clearScreen();
        if (keyRight()) drawMessage("CS Rocks");
        if (keyUp()) drawMessage("By Noah");
        if (keyDown()) drawMessage("This is a short\nmessage");
    }

    // When your gameLoop() method gets undweildy and needs to be decomposed, create methods below
    // YOUR new methods HERE:


    /**
     * Draws a message on the game board.
     *
     * @param message The message to be displayed on the game board.
     */
    private void drawMessage(String message) {
        clearScreen();

        String[] split = message.split("\n");
        int splitLength = split.length;
        int x;
        int y = (getBoardHeight() / 2) - splitLength;
        NamedSymbol symbol;

        for (String s : split) {
            char[] letters = s.toCharArray();
            x = (getBoardWidth() / 2) - (letters.length / 2);

            for (char c : letters) {
                try {
                    if (!Character.isAlphabetic(c)) {
                        x++;
                        continue;
                    }

                    symbol = NamedSymbol.valueOf(Character.toString(c).toUpperCase());
                    drawSymbol(y, x++, symbol, NamedColor.black);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }

            y++;
        }
    }

    /**
     * Clears the game board.
     */
    private void clearScreen() {
        for (int i = 0; i < getBoardHeight(); i++) {
            for (int j = 0; j < getBoardWidth(); j++) {
                drawSymbol(i, j, NamedSymbol.none, NamedColor.black);
            }
        }
    }

    /**
     * Gets a random color from the list of possible values.
     *
     * @return A random color from the list of possible values.
     */
    private NamedColor getRandomColor() {
        return VALUES.get(random.nextInt(VALUES.size() - 1));
    }
}
