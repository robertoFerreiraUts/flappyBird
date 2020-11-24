
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


class Renderer extends JPanel {
    private FlappyBird flappyBird;
    public Renderer(FlappyBird flappyBird) {
        this.flappyBird = flappyBird;
    }

    protected void paintComponent(Graphics g) {
        FlappyBird flappyBird = getFlappyBird();
        super.paintComponent(g);
        repaint(g);
    }

    public void paintColumn(Graphics g) {
        FlappyBird flappyBird = getFlappyBird();
        for (Rectangle column : flappyBird.getColumnList()) {
            g.setColor(Color.green.darker());
            g.fillRect(column.x, column.y, column.width, column.height);
        }
    }

    public void paintMenu(Graphics g) {
        Font font = new Font("SanSerif", Font.PLAIN, 50);
        g.setColor(Color.black);
        g.fillRect(300, 200, 400, 400);

        g.setColor(Color.lightGray);
        g.fillRect(315, 215, 370, 370);

        g.setColor(Color.white);
        g.drawString("You Lose!", 360, 300);
        g.drawString("Score : " + Integer.toString(flappyBird.getScore()), 380, 400);

        g.setColor(Color.red);
        g.fillRect(380, 470, 250, 70);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Play Again", 385, 520);
    }

    public void repaint(Graphics g) {
        FlappyBird flappyBird = getFlappyBird();
        int width = flappyBird.getWidth();
        int height = flappyBird.getHeight();
        Rectangle bird = flappyBird.getBird();
        Font font = new Font("SanSerif", Font.BOLD, 60);

        g.setColor(Color.cyan);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.orange);
        g.fillRect(0, height-100, width, 100);

        g.setColor(Color.green);
        g.fillRect(0, height-125, width, 25);

        g.setColor(Color.red);
        g.fillRect(flappyBird.getBird().x, flappyBird.getBird().y, flappyBird.getBird().width, flappyBird.getBird().height);

        paintColumn(g);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(Integer.toString(flappyBird.getScore()), width / 2, 50);

        if (!flappyBird.getPlaying()) {
            paintMenu(g);
        }
    }

    public FlappyBird getFlappyBird() {
        return flappyBird;
    }
}
