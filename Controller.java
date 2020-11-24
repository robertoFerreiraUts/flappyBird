import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


class Controller implements ActionListener, KeyListener, MouseListener {
    private FlappyBird flappyBird;
    private Renderer renderer;
    public Controller(FlappyBird flappyBird, Renderer renderer) {
        this.flappyBird = flappyBird;
        this.renderer = renderer;
    }

    public void keyPressed(KeyEvent e) {
        FlappyBird flappyBird = getFlappyBird();
        if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            flappyBird.jump();
        }
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
        FlappyBird flappyBird = getFlappyBird();
        Renderer renderer = getRenderer();
        if (flappyBird.getPlaying()) {
            for (Rectangle column : flappyBird.getColumnList()) {
                column.x -= flappyBird.getSpeed();
            }
            renderer.repaint();
            flappyBird.manageCols();
            flappyBird.gravity();
            flappyBird.checkCollision();
        } else {
            JButton playAgain = new JButton();
            playAgain.setBounds(380, 470, 250, 70);
            playAgain.setText("playAgain");
            playAgain.setOpaque(false);
            playAgain.setFocusable(false);
            playAgain.setContentAreaFilled(false);
            playAgain.setBorderPainted(false);
            playAgain.addMouseListener(this);
            flappyBird.add(playAgain);
        }
    }

    public void mousePressed(MouseEvent e) {
        FlappyBird flappyBird = getFlappyBird();
        if (e.getButton() ==  MouseEvent.BUTTON1 && !flappyBird.getPlaying() && ((JButton)e.getSource()).getText() == "playAgain") {
            flappyBird.reset();
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    public FlappyBird getFlappyBird() {
        return flappyBird;
    }
    public Renderer getRenderer() {
        return renderer;
    }
}
