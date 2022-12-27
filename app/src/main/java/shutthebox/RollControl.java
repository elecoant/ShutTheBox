package shutthebox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class RollControl {
  
  private GameControl gameControl;
  private HBox box;
  private Button button;

  public RollControl(GameControl gameControl) {
    box = new HBox();
    button = new Button();
    this.gameControl = gameControl;

    box.setAlignment(Pos.CENTER);
    box.setPadding(new Insets(0, 0, 20, 0));

    button.setText("Roll");
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        roll();
      }
    });
    box.getChildren().add(button);
  }

  public void setDisable(boolean value) {
    button.setDisable(value);
  }

  public Pane getPane() {
    return box;
  }

  private void roll() {
    gameControl.rollDice();
  }
}
