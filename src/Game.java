import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main game controller class
// Handles levels, score, lives, timing, and UI updates
public class Game {

    // Current level being played
    private BaseLevel currentLevel;

    // Game display (renders the world)
    private GameView view;

    // Top UI panel (buttons + stats)
    private ControlPanel controlPanel;

    // Game state variables
    private int levelNumber = 1;
    private int score = 0;
    private int lives = 3;
    private int timeLeft = 60;

    // Timer that counts down every second
    private Timer secondTimer;

    // Constructor: sets up game
    public Game() {

        // Create first level
        currentLevel = createLevel(1);

        // Create UI panel
        controlPanel = new ControlPanel(this);

        // Create game view (window)
        view = new GameView(currentLevel, 800, 600, this);
        view.requestFocus(); // ensures keyboard input works

        // Timer runs every 1000ms (1 second)
        secondTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Only run timer if game is active
                if (currentLevel != null && lives > 0) {
                    timeLeft--;        // decrease time
                    updatePanel();     // update UI

                    // If time runs out → lose a life
                    if (timeLeft <= 0) {
                        loseLife();
                    }
                }
            }
        });
    }

    // Creates a level based on number
    private BaseLevel createLevel(int number) {
        BaseLevel level;

        if (number == 1) {
            level = new Level1(this);
        } else if (number == 2) {
            level = new Level2(this);
        } else {
            level = new Level3(this);
        }

        // Set level timer from level settings
        timeLeft = level.getLevelTime();

        return level;
    }

    // Starts the game
    public void start() {
        currentLevel.start();   // start physics world
        secondTimer.start();    // start timer
        updatePanel();          // update UI
    }

    // Loads a specific level
    public void loadLevel(int number) {

        // Stop previous level
        if (currentLevel != null) {
            currentLevel.stop();
        }

        levelNumber = number;
        currentLevel = createLevel(number);

        // Update the game view
        if (view != null) {
            view.setWorld(currentLevel);
            view.repaint();
            view.requestFocus(); // fix keyboard input
        }

        updatePanel();
        currentLevel.start();
    }

    // Restart current level
    public void restartLevel() {
        loadLevel(levelNumber);
    }

    // Move to next level
    public void nextLevel() {
        System.out.println("Next level called. Current level = " + levelNumber);

        if (levelNumber == 1) {
            loadLevel(2);
        } else if (levelNumber == 2) {
            loadLevel(3);
        } else {
            // Game finished
            currentLevel.stop();
            secondTimer.stop();
            System.out.println("Game complete");
        }
    }

    // Increase score
    public void addScore(int amount) {
        score += amount;
        updatePanel();
    }

    // Player loses a life
    public void loseLife() {
        lives--;
        updatePanel();

        if (lives <= 0) {
            // Game over
            currentLevel.stop();
            secondTimer.stop();
        } else {
            // Restart level if lives remain
            restartLevel();
        }
    }

    // Save game state
    public void saveGame() {
        SaveManager.save(levelNumber, score, lives, timeLeft);
    }

    // Load saved game state
    public void loadSavedGame() {
        int[] data = SaveManager.load();

        if (data != null) {
            levelNumber = data[0];
            score = data[1];
            lives = data[2];
            timeLeft = data[3];

            loadLevel(levelNumber);
            timeLeft = data[3];
            updatePanel();
        }
    }

    // Update UI labels
    public void updatePanel() {
        if (controlPanel != null) {
            controlPanel.updateLabels(levelNumber, score, lives, timeLeft);
        }
    }

    // ===== GETTERS =====

    public BaseLevel getCurrentLevel() {
        return currentLevel;
    }

    public GameView getView() {
        return view;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}