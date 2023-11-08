import java.awt.*;
import java.util.Random;

public class Food {
    private  final int posx;
    private final int posY;
    public Food(){
        posx = generatePos(Graphics.WIDTH);
        posY =generatePos(Graphics.HEIGHT);

    }

    public int generatePos(int size){
        Random random=new Random();
        return random.nextInt(size/Graphics.TICK_SIZE)* Graphics.TICK_SIZE;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosY() {
        return posY;
    }
}
