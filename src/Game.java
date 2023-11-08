import javax.swing.*;

public class Game extends JFrame {
    ImageIcon logo=new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
    public Game() {
        this.add(new Graphics());
        this.setTitle("A Snake Game");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}