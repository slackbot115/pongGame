package ponggame;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    public double x, y;

    public int width, height;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }

    public void tick() {
        // o -6 é apenas para ajuste de posição //
        x += (PongGame.ball.x - x - 6) * 0.4; //o 0.4 é pra dificultar a chegada do inimigo a bola

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, width, height);
    }

}
