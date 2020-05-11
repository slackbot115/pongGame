package ponggame;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    public boolean right, left;
//    public boolean up, down;

    public int x, y, width, height;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        if (right) {
            x++;
        } else if (left) {
            x--;
        }
//        else if(up){
//            y--;
//        }
//        else if(down){
//            y++;
//        }
        if (x + width > PongGame.WIDTH) {
            x = PongGame.WIDTH - width;
        }
        else if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }

}
