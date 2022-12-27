package shutthebox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Roll {
  
  private HBox box;
  private Button button;
  private Dice dice;

  public Roll(Dice dice) {
    box = new HBox();
    box.setAlignment(Pos.CENTER);
    button = new Button();
    button.setText("Roll");
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        roll();
      }
    });
    box.getChildren().add(button);
    this.dice = dice;
  }

  public Pane getPane() {
    return box;
  }

  private void roll() {
    dice.roll();
  }
}
