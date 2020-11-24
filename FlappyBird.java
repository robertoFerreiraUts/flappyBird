
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class FlappyBird extends JFrame {
    private Renderer renderer;
    private Rectangle bird;
    private ArrayList<Rectangle> columnList;
    private int score = 0;
    private double velY = 0;
    private boolean passedFirst = false, playing = true;
    private static final int WIDTH = 1000, HEIGHT = 800, SPEED = 2, JUMP = 13, SIZE = 20;
    private static final double GRAVITY = 0.3;

    public FlappyBird() {
        super("Flappy Bird");

        Renderer renderer = new Renderer(this);
        Controller controller = new Controller(this, renderer);
        Timer timer = new Timer(10, controller);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(renderer);
        addKeyListener(controller);

        bird = new Rectangle(WIDTH / 4, HEIGHT / 2 - 50, SIZE, SIZE);
        columnList = new ArrayList<Rectangle>();

        addColumn();
        timer.start();
        setVisible(true);
    }

    public static void main(String args[]) {
        FlappyBird flappyBird = new FlappyBird();
    }

    public void gravity() {
        Rectangle bird = getBird();
        int maxVel = 10;
        this.velY += getGravity();
        if (this.velY > maxVel) {
            this.velY = maxVel;
        }
        bird.y = (int)(bird.y + this.velY);
    }

    public void addColumn() {
        int randY, buffer = 0;
        Rectangle column1, column2;
        boolean first = true;
        int width = getWidth();
        int height = getHeight();
        ArrayList<Rectangle> columnList = getColumnList();
        Random r = new Random();
            for (int i = 0; i < 4; i++) {
                randY = r.nextInt(height/2) + 275;
                column1 = new Rectangle(width/2 + buffer, randY, 100, (height-125)-randY);
                columnList.add(column1);
                column2 = new Rectangle(width/2 + buffer, 0, 100, randY-150);
                columnList.add(column2);
                buffer += 300;
            }
            first = false;
        }

    public void manageCols() {
        int randY, buffer = 300;
        Rectangle column1, column2, first, second;
        int height = getHeight();
        ArrayList<Rectangle> columnList = getColumnList();
        Random r = new Random();
        first = columnList.get(0);
        second = columnList.get(1);
        if (first.x <= -160) {
            columnList.remove(first);
            columnList.remove(second);
            randY = r.nextInt(height/2) + 230;
            int size = columnList.size();
            Rectangle lastCol = columnList.get(size-1);
            column1 = new Rectangle(lastCol.x + buffer, randY, 100, (height-125)-randY);
            columnList.add(column1);
            column2 = new Rectangle(lastCol.x + buffer, 0, 100, randY-150);
            columnList.add(column2);
        }
    }

    public void jump() {
        int jumpHeight = getJump();
        int minVel = -3;
        if (this.velY < minVel) {
            this.velY = minVel;
        }
        this.velY -= jumpHeight;
    }

    public void checkCollision() {
        Rectangle firstCol, secCol;
        int height = getHeight();
        int size = getSizeNum();
        ArrayList<Rectangle> columnList = getColumnList();
        Rectangle bird = getBird();
        if (!getPassedFirst()) {
            firstCol = columnList.get(0);
            secCol = columnList.get(1);
        } else {
            firstCol = columnList.get(2);
            secCol = columnList.get(3);
        }
        if (bird.x + size >= firstCol.x && (bird.y > firstCol.y || (bird.y + size) < secCol.height)) {
            playing(false);
        } else if (bird.x >= firstCol.x && (bird.x + size) <= (firstCol.x + firstCol.width) && (bird.y  <= secCol.height || bird.y + size >= firstCol.y)) {
            playing(false);
        } else if (bird.y >= (height-145)) {
            playing(false);
        }
        if (bird.x == firstCol.x + firstCol.width) {
            passedFirst(true);
            score++;
        }
    }

    public void reset(){
        ArrayList<Rectangle> columnList = getColumnList();
        this.score = 0;
        this.velY = 0;
        passedFirst(true);
        playing(true);
        bird.y = HEIGHT / 2 - 50;

        columnList.clear();
        addColumn();
    }

    public Rectangle getBird() { return bird; }

    public ArrayList<Rectangle> getColumnList() { return columnList; }

    public int getScore() { return score; }
    public void score(int score) { this.score = score; }

    public double getVelY() { return velY; }
    public void velY(double velY) { this.velY = velY; }

    public boolean getPassedFirst() { return passedFirst; }
    public void passedFirst(boolean passedFirst) { this.passedFirst = passedFirst; }
    public boolean getPlaying() { return playing; }
    public void playing(boolean playing) { this.playing = playing; }

    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }
    public int getSpeed() { return SPEED; }
    public int getJump() { return JUMP; }
    public int getSizeNum() { return SIZE; }

    public double getGravity() { return GRAVITY; }

} // end of FlappyBird class
