package javalab2;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdDraw3D;
import java.awt.Color;
import java.awt.*;
import java.util.Random;

public class Rubik {

    Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW};
    Color[] currentColors = new Color[4];
    Rectangle[] squares = new Rectangle[currentColors.length];

    public static final int WWIDTH = 500;

    final static Random random = new Random();

    public void Rubik() {

        StdDraw.setCanvasSize(WWIDTH, WWIDTH);
        for (int i = 0; i < currentColors.length; ++i) {
            currentColors[i] = randomColor();
        }

        int sideLength = (int) Math.floor(Math.sqrt(currentColors.length));

        int j = 0;
        int y = 0;
        int WIDTH = WWIDTH / sideLength;

        for (int i = 0; i < squares.length; ++i) {
            if (j >= sideLength) {
                j = 0;
                y += WIDTH;
            }

            squares[i] = new Rectangle(j * WIDTH, y, WIDTH, WIDTH);
            ++j;
        }
        redraw();
    }

    public void redraw() {

        int colorIndex = 0;
       

        for (Rectangle square : squares) {

            double centerX = (square.x  + square.width / 2.0) / WWIDTH;
            double centerY = (square.y  + square.width / 2.0) / WWIDTH;

            StdDraw.setPenColor(currentColors[colorIndex++]);
            StdDraw.filledSquare(centerX, centerY, square.width / (2.0 * WWIDTH));
        }
    }

   
    public static void main(String[] args) {
        Rubik r = new Rubik();
        r.play();
    }
    public Color randomColor() {
        return colors[random.nextInt(colors.length)];
    }
    public boolean match(Color rand1, Color rand2, Color rand3, Color rand4) {
        return rand1 == rand2 && rand2 == rand3 && rand3 == rand4;
    }


    public void play() {
        int i = 0;
        while (! match(currentColors[0], currentColors[1], currentColors[2], currentColors[3])) {
            while (!StdDraw.mousePressed());
            int square = whichSquare(mouseLocation());
            changeColor(square);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, 0.5, "You won!");
    }

    public double[] mouseLocation() {
        return new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
    }

    public int whichSquare(double[] mouseLoc) {

        for (int i = 0; i < squares.length; ++i) {
            if (squares[i].contains(WWIDTH * mouseLoc[0], WWIDTH * mouseLoc[1])) {
                return i;
            }
        }
        return -1;
    }


    public void changeColor(int whichSquare) {
        currentColors[whichSquare] = randomColor();
        redraw();
        StdDraw.show();
    }
}
