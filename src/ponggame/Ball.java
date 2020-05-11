package ponggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

    public double x, y;

    public int width, height;

    public double dx, dy, speed = 1.6;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;

        int angle = new Random().nextInt((120 - 45) + 45 + 1); //aleatoriazando de uma forma mais dinamica
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));

    }

    public void tick() {
        if (x + (dx * speed) + width >= PongGame.WIDTH) {
            dx *= -1;
        } else if (x + (dx * speed) < 0) {
            dx *= -1;
        }

        if (y >= PongGame.HEIGHT) {
            //Ponto do Inimigo
            System.out.println("Ponto do Inimigo");
            new PongGame();
        } else if (y < 0) {
            //Ponto do Player
            System.out.println("Ponto nosso");
            new PongGame();
        }

        Rectangle bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), width, height);

        Rectangle boundsPlayer = new Rectangle(PongGame.player.x, PongGame.player.y, PongGame.player.width, PongGame.player.height);
        Rectangle boundsEnemy = new Rectangle((int) PongGame.enemy.x, (int) PongGame.enemy.y, PongGame.enemy.width, PongGame.enemy.height);

        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt((120 - 45) + 45 + 1); //aleatoriazando de uma forma mais dinamica
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy > 0) {
                dy *= -1;
            }
        } else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt((120 - 45) + 45 + 1); //aleatoriazando de uma forma mais dinamica
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy < 0) {
                dy *= -1;
            }
        }

        x += dx * speed;
        y += dy * speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, width, height);
    }
}
