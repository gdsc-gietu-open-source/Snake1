import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Graphics extends JPanel implements ActionListener {
   // Clip clip;
   // URL soundURL[]=new URL[30];
    Random random=new Random();
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int TICK_SIZE = 25;
    static final int BOARD_SIZE = (WIDTH * HEIGHT) / (TICK_SIZE * TICK_SIZE);

    final Font font = new Font("TimesRoman", Font.BOLD, 30);

    int[] snakePosX = new int[BOARD_SIZE];
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;
    Food food;
    int foodEaten;
    char direction = 'R';
    boolean isMoving = false;
    final Timer timer = new Timer(50, this);

    public Graphics() {
       // soundURL[0]=getClass().getResource("audio.unknown");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.GREEN);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (isMoving) {
                    timer.start();
                    switch (e.getKeyCode()) {

                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                    }
                } else {
                    start();
                }
            }

        });
        start();
    }

    protected void start() {
        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        snakeLength = 1;
        foodEaten = 0;
        direction = 'R';
        isMoving = true;
        spawnFood();



    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (isMoving) {
            g.setColor(Color.red);
            g.fillOval(food.getPosx(), food.getPosY(), TICK_SIZE, TICK_SIZE);
            g.setColor(Color.GREEN);
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE);

                g.setFont(new Font("Arial",Font.ITALIC,20));

                g.drawString("Score :" +foodEaten,700,20);
               g.setColor(Color.DARK_GRAY);

            }
        }else{String scoreText = String.format("GAME OVER...Score: %d..." +
                    " Press any key to play again!%n", foodEaten);
                               g.setColor(Color.BLACK);
                               g.setFont(new Font("Ariel",Font.BOLD,30));
                               g.drawString(scoreText,(WIDTH-getFontMetrics(g.getFont()).stringWidth(scoreText))/2,HEIGHT/2);

                              }
    }
    protected void move(){
        for(int i=snakeLength;i>0;i--){
            snakePosX[i]=snakePosX[i-1];
            snakePosY[i]=snakePosY[i-1];
        }
        switch (direction) {
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }
    protected void spawnFood(){
        food=new Food();
    }
    protected void eatFood() {
        if ((snakePosX[0] == food.getPosx()) && (snakePosY[0] == food.getPosY())) {
            snakeLength++;
            foodEaten++;
            spawnFood();
        }
    }
    protected void collisionTest() {
        for (int i = snakeLength; i > 0; i--) {
            if ((snakePosX[0] == snakePosX[i]) && (snakePosY[0] == snakePosY[i])) {
                isMoving = false;
                break;
            }
        }

        if (snakePosX[0] < 0 || snakePosX[0] > WIDTH - TICK_SIZE || snakePosY[0] < 0 || snakePosY[0] > HEIGHT - TICK_SIZE) {
            isMoving = false;
        }

        if (!isMoving) {
            timer.stop();
        }
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            move();
            collisionTest();
            eatFood();
        }

        repaint();

    }
}
