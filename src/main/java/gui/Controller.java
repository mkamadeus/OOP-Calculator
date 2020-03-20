package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

public class Controller {

    @FXML
    private Button buttonClear;
    @FXML
    private Button button0;
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
    private Button buttonPlus;
    @FXML
    private Button buttonMinus;
    @FXML
    private Button buttonMultiply;
    @FXML
    private Button buttonDivide;
    @FXML
    private Button buttonParOpen;
    @FXML
    private Button buttonParClose;


    @FXML
    private TextField equationDisplay;
    @FXML
    private TextField numberDisplay;

    // Array to store number buttons reference
    private Button[] numberButtons;
    private KeyCode[] numberKeyCodes = {
            KeyCode.DIGIT0,
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

    // Array to store operator buttons reference
    private Button[] operatorButtons;
    private KeyCombination[] operatorKeyCodes = {
            new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.MINUS),
            new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.SLASH),
            new KeyCodeCombination(KeyCode.DIGIT9, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.DIGIT0, KeyCombination.SHIFT_DOWN)
    };

    @FXML
    public void initialize()
    {
        numberButtons = new Button[10];
        numberButtons[0] = button0;
        numberButtons[1] = button1;
        numberButtons[2] = button2;
        numberButtons[3] = button3;
        numberButtons[4] = button4;
        numberButtons[5] = button5;
        numberButtons[6] = button6;
        numberButtons[7] = button7;
        numberButtons[8] = button8;
        numberButtons[9] = button9;

        operatorButtons = new Button[6];
        operatorButtons[0] = buttonPlus;
        operatorButtons[1] = buttonMinus;
        operatorButtons[2] = buttonMultiply;
        operatorButtons[3] = buttonDivide;
        operatorButtons[4] = buttonParOpen;
        operatorButtons[5] = buttonParClose;

    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        System.out.println(event);

        // Check for operators
        for(int i = 0; i < 6 ; i++) if(operatorKeyCodes[i].match(event))
        {
            operatorButtons[i].getStyleClass().removeAll("buttons");
            operatorButtons[i].getStyleClass().add("buttons:pressed");

            equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + operatorButtons[i].getText());
            numberDisplay.setText("");

            return;
        }

        // Check for number key press
        for(int i = 0; i < 10; i++)
        {
            if(event.getCode() == numberKeyCodes[i])
            {
                numberButtons[i].getStyleClass().removeAll("buttons");
                numberButtons[i].getStyleClass().add("buttons:pressed");

                // If input is 0 and currently no text...
                if(i!=0 || !("".equals(numberDisplay.getText()))) {
                    numberDisplay.setText(numberDisplay.getText() + i);
                }

                return;
            }
        }

        // Check for DELETE Key
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonClear.getStyleClass().removeAll("buttons");
            buttonClear.getStyleClass().add("buttons:pressed");
            numberDisplay.setText("");

            return;
        }

    }

    @FXML
    private void handleOnKeyReleased(KeyEvent event) {
        // Check for operators
        for(int i = 0; i < 6; i++) if(operatorKeyCodes[i].match(event))
        {
            operatorButtons[0].getStyleClass().removeAll("buttons:pressed");
            operatorButtons[0].getStyleClass().add("buttons");

            return;
        }

        // Check for number key press
        for (int i = 0; i < 10; i++) {
            if (event.getCode() == numberKeyCodes[i]) {
                numberButtons[i].getStyleClass().removeAll("buttons:pressed");
                numberButtons[i].getStyleClass().add("buttons");
                return;
            }
        }

        // Check for DELETE key
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonClear.getStyleClass().removeAll("buttons:pressed");
            buttonClear.getStyleClass().add("buttons");

            return;
        }


    }

    @FXML
    private void handleOnNumberClick(MouseEvent mouseEvent)
    {
        String text = ((Button)mouseEvent.getSource()).getText();
        numberDisplay.setText(numberDisplay.getText() + text);
    }

}