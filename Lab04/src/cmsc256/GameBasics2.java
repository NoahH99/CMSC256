package cmsc256;

import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.games.NonBlockingGame;

/**
 * Created by Noah Hendrickson on 02/06/2030
 *
 * <br>
 * This class represents the game 'GameBasics2' which extends the NonBlockingGame class.
 * It creates a player represented by a red cell that can be moved around the game board with the
 * upArrow, downArrow, rightArrow, and leftArrow keys.
 *
 * @author Noah Hendrickson
 * @version 0.1.0
 */
class GameBasics2 extends NonBlockingGame {

    /**
     * Represents the column of the player cell.
     */
    private int col;

    /**
     * Represents the row of the player cell.
     */
    private int row;

    /**
     * Represents whether the game is being played.
     */
    private boolean playGame;

    /**
     * Constructor for objects of class GameBasics2Sol
     * @param assignmentNumber your Bridges assignment number
     * @param username   your Bridges username
     * @param apiKey  your Bridges apiKey
     * @param numRows number of rows in the game grid (height)
     * @param numCols number of columns in the game grid (width)
     */
    public GameBasics2(int assignmentNumber, String username, String apiKey, int numRows, int numCols) {
        super(assignmentNumber, username, apiKey, numRows, numCols);
        setTitle("Game Basics 2");
        setDescription("Create a \"player\" (a red cell that can be moved around the game board with the " +
                "upArrow, downArrow, rightArrow, and leftArrow keys. ");

        this.playGame = true;
    }

    /**
     * The main method which starts the game.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        GameBasics2 game = new GameBasics2(521, args[0], args[1], 20, 25);
        game.start();
    }

    /**
     * Initializes the game board and player.
     */
    @Override
    protected void initialize() {
        initBackground();
        initPlayer();
    }

    /**
     * The game loop that moves the player cell.
     */
    @Override
    protected void gameLoop() {
        if (!playGame) {
            if (keyUp()) {
                playGame = true;
                initialize();
            }

            return;
        }

        if (!keyLeft() && !keyRight() && !keyUp() && !keyDown()) return;

        setBGColor(row, col, NamedColor.lightgray);

        if (keyLeft()) col--;
        if (keyRight()) col++;
        if (keyUp()) row--;
        if (keyDown()) row++;

        if (isTouchingEdge(row, col) || isOutsideBoard(row, col)) {
            endGame();
        } else {
            drawPlayer();
        }
    }

    /**
     * Initializes the background of the game board.
     */
    private void initBackground() {
        for (int row = 0; row < getBoardHeight(); row++) {
            for (int col = 0; col < getBoardWidth(); col++) {
                if (isTouchingEdge(row, col)) {
                    setBGColor(row, col, NamedColor.blue);
                    drawSymbol(row, col, NamedSymbol.none, NamedColor.blue);
                } else {
                    setBGColor(row, col, NamedColor.lightgray);
                }
            }
        }
    }

    /**
     * Initializes the player by setting its row and column position to the center of the board, and drawing it.
     */
    private void initPlayer() {
        row = getBoardHeight() / 2;
        col = 1;
        drawPlayer();
    }

    /**
     * Ends the game by setting the playGame flag to false, restricting the player's position to within the board,
     * setting its background color to blue, and drawing a bomb symbol.
     */
    private void endGame() {
        playGame = false;

        col = Math.max(0, Math.min(col, getBoardWidth()));
        row = Math.max(0, Math.min(row, getBoardHeight()));

        setBGColor(row, col, NamedColor.blue);
        drawSymbol(row, col, NamedSymbol.bomb, NamedColor.black);
    }

    /**
     * Draws the player by setting its background color to red.
     */
    private void drawPlayer() {
        setBGColor(row, col, NamedColor.red);
    }

    /**
     * Determines if the player is touching the edge of the board.
     *
     * @param row The row position of the player
     * @param col The column position of the player
     *
     * @return true if the player is touching the edge of the board, false otherwise
     */
    private boolean isTouchingEdge(int row, int col) {
        return row == 0 || row == getBoardHeight() - 1 || col == 0 || col == getBoardWidth() - 1;
    }

    /**
     * Determines if the player is outside the board.
     *
     * @param row The row position of the player
     * @param col The column position of the player
     *
     * @return true if the player is outside the rectangle of the board, false otherwise
     */
    private boolean isOutsideBoard(int row, int col) {
        return row < 0 || row >= getBoardHeight() || col < 0 || col >= getBoardWidth();
    }
}
