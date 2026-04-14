import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

// Displays game stats (level, score, lives, time)
// This class creates the top UI panel (buttons + game info)
public class ControlPanel extends JPanel {

    // Labels to display game information
    private JLabel levelLabel;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel timeLabel;

    // Constructor: builds the UI panel
    public ControlPanel(Game game) {

        // Use a simple left-to-right layout
        setLayout(new FlowLayout());

        // Create buttons for game controls
        JButton restartButton = new JButton("Restart Level");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        // Create labels (initially empty)
        levelLabel = new JLabel();
        scoreLabel = new JLabel();
        livesLabel = new JLabel();
        timeLabel = new JLabel();

        // Restart button: resets the current level
        restartButton.addActionListener(e -> {
            game.restartLevel();               // restart logic
            game.getView().requestFocus();     // return focus to game (so keys work)
        });

        // Save button: saves current progress
        saveButton.addActionListener(e -> game.saveGame());

        // Load button: loads saved game
        loadButton.addActionListener(e -> {
            game.loadSavedGame();              // load logic
            game.getView().requestFocus();     // fix input after loading
        });

        // Add components to the panel (order matters visually)
        add(restartButton);
        add(saveButton);
        add(loadButton);
        add(levelLabel);
        add(scoreLabel);
        add(livesLabel);
        add(timeLabel);
    }

    // Updates the UI labels with current game values
    public void updateLabels(int level, int score, int lives, int time) {

        // Display current level
        levelLabel.setText("Level: " + level);

        // Display score
        scoreLabel.setText(" Score: " + score);

        // Display remaining lives
        livesLabel.setText(" Lives: " + lives);

        // Display time remaining
        timeLabel.setText(" Time: " + time);
    }
}