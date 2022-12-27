/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package shutthebox;
 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
 
public class GameControl {

    private BorderPane border;
    private Keyset keyset;
    private Dice dice;
    private RollControl rollControl;
    private boolean gameOver;
    
    public GameControl() {
        gameOver = false;
        border = new BorderPane();

        keyset = new Keyset(this);
        dice = new Dice(this);
        rollControl = new RollControl(this);
        
        border.setTop(keyset.getPane());
        border.setCenter(dice.getPane());
        border.setBottom(rollControl.getPane());
        
        border.getStyleClass().add("pane");
    }

    public void rollDice() {
        if (gameOver) {
            keyset.reset();
            gameOver = false;
        }

        dice.roll();

        if (!keyset.isDoable(dice.getRoll())) {
            gameOver = true;
            dice.draw();
            return;
        }

        rollControl.setDisable(true);
        keyset.setDisable(false);
        keyset.setGoal(dice.getRoll());
    }

    public void endRound() {
        rollControl.setDisable(false);
        keyset.setDisable(true);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Pane getPane() {
        return border;
    }
}