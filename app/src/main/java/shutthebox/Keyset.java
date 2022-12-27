package shutthebox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Keyset {

  private final String RELEASED = "released-btn";
  private final String PRESSED = "pressed-btn";
  private final String INACTIVE = "inactive-btn";

  private GameControl gameControl;
  private boolean disabled;
  private HBox box;
  private Map<Integer, Key> keys;
  private int count;
  private int goal;
  
  private class Key {
    
    private Button button;
    private int number;
    private boolean down;
    private boolean active;
    private String style;
    
    private Key(int number) {
      button = new Button();
      this.number = number;
      down = false;
      active = true;
      style = "";
      
      button.setText(number + "");
      button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          press();
        }
      });

      setStyle(RELEASED);
    }
  
    private void press() {
      if (disabled || !active) {
        return;
      }

      if (down) {
        setStyle(RELEASED);
        count -= number;
      }
      else {
        setStyle(PRESSED);
        count += number;
      }
  
      down = !down;
      if (count == goal) {
        endRound();
      }
    }

    private boolean isDown() {
      return down;
    }

    private void setActive(boolean value) {
      active = value;
    }

    private void setStyle(String newStyle) {
      button.getStyleClass().remove(style);
      button.getStyleClass().add(newStyle);
      style = newStyle;
    }

    private Button getButton() {
      return button;
    }
  }
  

  public Keyset(GameControl gameControl) {
    this.gameControl = gameControl;
    this.box = new HBox();
    this.disabled = true;
    this.keys = new HashMap<>();

    this.box.setPadding(new Insets(20, 0, 5, 0));
    this.box.setSpacing(5);
    this.box.setAlignment(Pos.CENTER);

  
    Key k;
    for (int i = 1; i <= 9; i++) {
      k = new Key(i);
      this.keys.put(i, k);
      this.box.getChildren().add(k.getButton());
    }
  }

  public void setDisable(boolean value) {
    disabled = value;
  }

  public void setGoal(int value) {
    count = 0;
    goal = value;
  }

  public void endRound() {
    for (Key k : keys.values()) {
      if (k.isDown()) {
        k.setActive(false);
        k.setStyle(INACTIVE);
      }
    }
    gameControl.endRound();
  }

  public Pane getPane() {
    return box;
  }

  public boolean isDoable(int n) {
    return getSums().contains(n);
  }

  private Set<Integer> getSums() {
    Stack<Integer> numbers = new Stack<>();
    for (int n : keys.keySet()) {
      if (!keys.get(n).down) {
        numbers.add(n);
      }
    }

    return getSumsRec(numbers);
  }

  public void reset() {
    for (Key k : keys.values()) {
      k.active = true;
      k.down = false;
      k.setStyle(RELEASED);
    }
  }

  private Set<Integer> getSumsRec(Stack<Integer> numbers) {
    if (numbers.isEmpty()) {
      return new HashSet<>();
    }
    int currentNumber = numbers.pop();
    Set<Integer> res = getSumsRec(numbers);
    for (int n : new HashSet<>(res)) {
      res.add(n + currentNumber);
    }
    res.add(currentNumber);
    return res;
  }
}
