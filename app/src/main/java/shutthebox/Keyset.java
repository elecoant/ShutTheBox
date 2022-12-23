package shutthebox;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Keyset {

  private class Key {
  
    private Button button;
    private boolean down;
  
    public Key(int number) {
      this.button = new Button();
      this.down = false;
  
      button.setText(number + "");
      button.getStyleClass().add("released-btn");
      button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          press();
        }
      });
    }
  
    private void press() {
      if (down) {
        button.getStyleClass().remove("pressed-btn");
        button.getStyleClass().add("released-btn");
      }
      else {
        button.getStyleClass().remove("released-btn");
        button.getStyleClass().add("pressed-btn");
      }
  
      down = !down;
    }

    public Button getButton() {
      return button;
    }
  }
  
  private HBox box;
  private Map<Integer, Key> keys;

  public Keyset() {
    box = new HBox();
    box.setPadding(new Insets(5, 0, 5, 0));
    box.setSpacing(5);
    box.setAlignment(Pos.CENTER);

    keys = new HashMap<>();
  
    Key k;
    for (int i = 1; i <= 9; i++) {
      k = new Key(i);
      keys.put(i, k);
      box.getChildren().add(k.getButton());
    }
  }
  

  public Pane getPane() {
    return box;
  }
}
