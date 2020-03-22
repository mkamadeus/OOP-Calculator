package gui;

import exceptions.BaseException;
import expressions.EvaluateExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import numbers.RealNumber;

public class Controller {

    public static Double ans = 0.0;

    /* -=-=-=-=- NUMBER RELATED BUTTONS -=-=-=-=- */
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
    private Button buttonPoint;
    @FXML
    private Button buttonAns;

    /* -=-=-=-=- OPERATOR RELATED BUTTONS -=-=-=-=- */
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
    private Button buttonPower;

    /* -=-=-=-=- FUNCTION RELATED BUTTONS -=-=-=-=- */
    @FXML
    private Button buttonSine;
    @FXML
    private Button buttonCosine;
    @FXML
    private Button buttonTangent;
    @FXML
    private Button buttonSqrt;


    /* -=-=-=-=- DISPLAY RELATED FIELDS -=-=-=-=- */
    @FXML
    private TextField equationDisplay;
    @FXML
    private TextField numberDisplay;
    @FXML
    private Button buttonBackspace;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonEquals;


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
            KeyCode.DIGIT9,
            KeyCode.PERIOD
    };

    // Array to store operator buttons reference
    private Button[] operatorButtons;
    private KeyCombination[] operatorKeyCodes = {
            new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.MINUS),
            new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.SLASH),
            new KeyCodeCombination(KeyCode.DIGIT9, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.DIGIT0, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.DIGIT6, KeyCombination.SHIFT_DOWN),
    };

    // Array to store function buttons reference
    private Button[] functionButtons;
    private KeyCombination[] functionKeyCodes = {
            new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.C, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.T, KeyCombination.SHIFT_DOWN),
            new KeyCodeCombination(KeyCode.R, KeyCombination.SHIFT_DOWN),
    };

    @FXML
    public void initialize()
    {
        // Initialize array to number buttons reference
        numberButtons = new Button[11];
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
        numberButtons[10] = buttonPoint;

        // Initialize array to operator buttons reference
        operatorButtons = new Button[7];
        operatorButtons[0] = buttonPlus;
        operatorButtons[1] = buttonMinus;
        operatorButtons[2] = buttonMultiply;
        operatorButtons[3] = buttonDivide;
        operatorButtons[4] = buttonParOpen;
        operatorButtons[5] = buttonParClose;
        operatorButtons[6] = buttonPower;

        // Initialize array to function buttons reference
        functionButtons = new Button[4];
        functionButtons[0] = buttonSine;
        functionButtons[1] = buttonCosine;
        functionButtons[2] = buttonTangent;
        functionButtons[3] = buttonSqrt;

    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) throws BaseException {
        System.out.println(event);

        // Check for ans
        if(event.getCode() == KeyCode.A)
        {
            buttonAns.getStyleClass().removeAll("buttons");
            buttonAns.getStyleClass().add("buttons:pressed");

            if(numberDisplay.getText().equals(""))
                equationDisplay.setText(equationDisplay.getText() + "Ans");
            else
            {
                equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + "*Ans");
                numberDisplay.setText("");
            }
        }

        // Check for operators
        for(int i = 0; i < 7 ; i++) if(operatorKeyCodes[i].match(event)) {
            operatorButtons[i].getStyleClass().removeAll("buttons");
            operatorButtons[i].getStyleClass().add("buttons:pressed");

            equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + operatorButtons[i].getText());
            numberDisplay.setText("");

            return;
        }

        // Check for equal button
        if(event.getCode() == KeyCode.EQUALS || event.getCode() == KeyCode.ENTER)
        {
            buttonEquals.getStyleClass().removeAll("buttons");
            buttonEquals.getStyleClass().add("buttons:pressed");

            equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText());

            // Evaluate expression
            EvaluateExpression evaluator = new EvaluateExpression(equationDisplay.getText());
            Double result = evaluator.parse().value();

            // Save value to ans
            ans = result;

            numberDisplay.setText(result.toString());

            equationDisplay.setText("");

        }

        // Check for functions
        for(int i = 0; i < 4; i++) if(functionKeyCodes[i].match(event)){
            functionButtons[i].getStyleClass().removeAll("buttons");
            functionButtons[i].getStyleClass().add("buttons:pressed");

            equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + functionButtons[i].getText() + "(");
            numberDisplay.setText("");

            return;
        }

        // Check for number key
        for(int i = 0; i < 11; i++)
        {
            if(event.getCode() == numberKeyCodes[i])
            {
                numberButtons[i].getStyleClass().removeAll("buttons");
                numberButtons[i].getStyleClass().add("buttons:pressed");

                // If period...
                if(i==10)
                {
                    if (numberDisplay.getText().equals("")) numberDisplay.setText("0");
                    numberDisplay.setText(numberDisplay.getText() + ".");
                }
                // If input is 0 and currently no text...
                else if(i!=0 || !("".equals(numberDisplay.getText())))
                {
                    numberDisplay.setText(numberDisplay.getText() + i);
                }

                return;
            }
        }

        // Check for BACKSPACE Key
        if(event.getCode() == KeyCode.BACK_SPACE)
        {
            buttonBackspace.getStyleClass().removeAll("buttons");
            buttonBackspace.getStyleClass().add("buttons:pressed");

            if(!numberDisplay.getText().equals(""))
            {
                String currentText = numberDisplay.getText();
                numberDisplay.setText(currentText.substring(0,currentText.length()-1));
                return;
            }
        }

        // Check for DELETE Key
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonClear.getStyleClass().removeAll("buttons");
            buttonClear.getStyleClass().add("buttons:pressed");
            numberDisplay.setText("");
            equationDisplay.setText("");

            return;
        }

    }

    @FXML
    private void handleOnKeyReleased(KeyEvent event) {
        // Check for ans
        if(event.getCode() == KeyCode.A)
        {
            buttonAns.getStyleClass().removeAll("buttons:pressed");
            buttonAns.getStyleClass().add("buttons");
        }

        // Check for operators
        for(int i = 0; i < 7; i++) if(operatorKeyCodes[i].match(event))
        {
            operatorButtons[i].getStyleClass().removeAll("buttons:pressed");
            operatorButtons[i].getStyleClass().add("buttons");

            return;
        }

        // Check for equal button
        if(event.getCode() == KeyCode.EQUALS || event.getCode() == KeyCode.ENTER)
        {
            buttonEquals.getStyleClass().removeAll("buttons:pressed");
            buttonEquals.getStyleClass().add("buttons");

        }

        // Check for functions
        for(int i = 0; i < 4; i++) if(functionKeyCodes[i].match(event))
        {
            functionButtons[i].getStyleClass().removeAll("buttons:pressed");
            functionButtons[i].getStyleClass().add("buttons");

            return;
        }
        // Check for number key
        for (int i = 0; i < 11; i++) {
            if (event.getCode() == numberKeyCodes[i]) {
                numberButtons[i].getStyleClass().removeAll("buttons:pressed");
                numberButtons[i].getStyleClass().add("buttons");
                return;
            }
        }

        // Check for BACKSPACE key
        if(event.getCode() == KeyCode.BACK_SPACE)
        {
            buttonBackspace.getStyleClass().removeAll("buttons:pressed");
            buttonBackspace.getStyleClass().add("buttons");
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