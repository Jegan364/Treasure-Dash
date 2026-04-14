import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

// GameView handles rendering and user input (keyboard controls)
public class GameView extends UserView {

    // Reference to the main game controller
    private Game game;

    // Background image for the game
    private Image bg = Toolkit.getDefaultToolkit().getImage("data/background.png");

    // Constructor: sets up the view and input
    public GameView(BaseLevel world, int width, int height, Game game) {
        super(world, width, height);
        this.game = game;

        // Allow this component to receive keyboard input
        setFocusable(true);
        requestFocus();

        // Set up key controls
        setupKeys();
    }

    // Draw the background image every frame
    @Override
    protected void paintBackground(Graphics2D g) {
        // Draw image stretched to fit window
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }

    // Handles all keyboard controls
    private void setupKeys() {

        // InputMap links key presses to actions
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        // ActionMap defines what each action does
        ActionMap am = getActionMap();

        // ===== KEY BINDINGS =====

        // Movement keys
        im.put(KeyStroke.getKeyStroke("pressed A"), "left");
        im.put(KeyStroke.getKeyStroke("pressed D"), "right");
        im.put(KeyStroke.getKeyStroke("pressed W"), "jump");
        im.put(KeyStroke.getKeyStroke("pressed SHIFT"), "sprint");
        im.put(KeyStroke.getKeyStroke("pressed SPACE"), "shoot");

        // Debug key (skip level)
        im.put(KeyStroke.getKeyStroke("N"), "next");

        // ===== ACTIONS =====

        // Move player left
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentLevel().getPlayer().moveLeft();
            }
        });

        // Move player right
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentLevel().getPlayer().moveRight();
            }
        });

        // Make player jump
        am.put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentLevel().getPlayer().jump();
            }
        });

        // Sprint (faster movement)
        am.put("sprint", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentLevel().getPlayer().sprint();
            }
        });

        // Shoot projectile
        am.put("shoot", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentLevel().shootProjectile();
            }
        });

        // Skip level (for testing/debugging)
        am.put("next", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.nextLevel();
            }
        });
    }
}