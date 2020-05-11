package ponggame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class PongGame extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 160;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    
    public static Player player;
    
    public static Enemy enemy;
    
    public static Ball ball;
    
    public PongGame() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);
        player = new Player(100, HEIGHT-10);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT/2 - 1);
    }

    public static void main(String[] args) {
        PongGame game = new PongGame();
        JFrame frame = new JFrame("Pong");
        frame.requestFocus();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(g);
        enemy.render(g);
        ball.render(g);
        
        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
        
        bs.show();
    }

    @Override
    public void run() {
        while(true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException ex) {
                Logger.getLogger(PongGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = true;
        }
//        else if(e.getKeyCode() == KeyEvent.VK_UP){
//            player.up = true;
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//            player.down = true;
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = false;
        }
//        else if(e.getKeyCode() == KeyEvent.VK_UP){
//            player.up = false;
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//            player.down = false;
//        }
    }

}
