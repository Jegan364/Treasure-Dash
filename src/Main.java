import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("Treasure Dash - CityEngine");
        frame.setLayout(new BorderLayout());
        frame.add(game.getControlPanel(), BorderLayout.NORTH);
        frame.add(game.getView(), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}