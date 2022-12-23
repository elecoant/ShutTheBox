package shutthebox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Keyset {
  
  private HBox box;

  public Keyset() {
    box = new HBox();
    box.setPadding(new Insets(5));
    box.setSpacing(5);
  }
  
  private class Key {

    private int number;
    private boolean down;

    public Key(int number) {
      this.number = number;
      this.down = false;
    }

    public Button getButton() {
      Button button = new Button();
      button.setText(number + "");
      button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          System.out.println(number);
          down = !down;
        }
      });
      button.setMinWidth(640 / 10);
      return button;
    }
  }

  public Pane getPane() {
    for (int i = 1; i < 10; i++) {
      box.getChildren().add((new Key(i).getButton()));
    }
    return box;
  }
}
