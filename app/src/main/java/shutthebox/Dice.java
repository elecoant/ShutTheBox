package shutthebox;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public class Dice {

  private final int CANVAS_WIDTH = 300, CANVAS_HEIGHT = 200;
  
  private StackPane stack;
  private Canvas canvas;
  private GraphicsContext context;
  private Map<Integer, Image> images;

  private Random random;
  private int die1 = 1, die2 = 1;

  public Dice() {
    stack = new StackPane();
    canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    context = canvas.getGraphicsContext2D();
    images = new HashMap<>();
    random = new Random();

    for (int i = 1; i <= 6; i++) {
      images.put(i, new Image("die_" + i + ".png"));
    }

    context.setFill(Paint.valueOf("#000"));
    draw();

    stack.getChildren().add(canvas);
  }

  public void roll() {
    die1 = random.nextInt(6) + 1;
    die2 = random.nextInt(6) + 1;
    draw();
  }

  public Pane getPane() {
    return stack;
  }

  private void draw() {
    context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    context.drawImage(images.get(die1), 40, 50);
    context.drawImage(images.get(die2), 160, 50);
  }
}
