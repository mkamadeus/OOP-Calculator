package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Button buttonClear;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    @FXML
    private TextField numberDisplay;

    private Button[] numberButtons;
    private KeyCode[] numberKeyCodes = {
            KeyCode.DIGIT1,
            KeyCode.DIGIT2,
            KeyCode.DIGIT3,
            KeyCode.DIGIT4,
            KeyCode.DIGIT5,
            KeyCode.DIGIT6,
            KeyCode.DIGIT7,
            KeyCode.DIGIT8,
            KeyCode.DIGIT9
    };

    public void initialize()
    {
        numberButtons = new Button[9];
        numberButtons[0] = button1;
        numberButtons[1] = button2;
        numberButtons[2] = button3;
        numberButtons[3] = button4;
        numberButtons[4] = button5;
        numberButtons[5] = button6;
        numberButtons[6] = button7;
        numberButtons[7] = button8;
        numberButtons[8] = button9;
    }

    @FXML
    private void handleOnKeyPress(KeyEvent event) {
        // Check for number key press
        for(int i=0;i<9;i++)
        {
            if(event.getCode() == numberKeyCodes[i])
            {
                numberButtons[i].getStyleClass().removeAll("buttons");
                numberButtons[i].getStyleClass().add("buttons:pressed");
                numberDisplay.setText(numberDisplay.getText() + (i+1));
                return;
            }
        }

        // Check for DELETE key press
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonClear.getStyleClass().removeAll("buttons");
            buttonClear.getStyleClass().add("buttons:pressed");
            numberDisplay.setText("");
        }
    }

    @FXML
    private void handleOnKeyRelease(KeyEvent event) {
        // Check for number key press
        for (int i = 0; i < 9; i++) {
            if (event.getCode() == numberKeyCodes[i]) {
                numberButtons[i].getStyleClass().removeAll("buttons:pressed");
                numberButtons[i].getStyleClass().add("buttons");
                return;
            }
        }

        // Check for DELETE key press
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonClear.getStyleClass().removeAll("buttons:pressed");
            buttonClear.getStyleClass().add("buttons");
        }

    }

    @FXML
    private void handleOnNumberClick(MouseEvent mouseEvent)
    {
        String text = ((Button)mouseEvent.getSource()).getText();
        numberDisplay.setText(numberDisplay.getText() + text);
    }

}