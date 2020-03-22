package gui;

import exceptions.BaseException;
import exceptions.QueueEmptyException;
import expressions.EvaluateExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import numbers.Number;
import numbers.RealNumber;

import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    public static Double ans = 0.0;
    public static Queue<Double> history = new LinkedList<>();

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
    @FXML
    private Button buttonPi;

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

    /* -=-=-=-=- HISTORY RELATED FIELDS -=-=-=-=- */
    @FXML
    private Button buttonMC;
    @FXML
    private Button buttonMR;


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
        // Check for MC
        if(event.getCode() == KeyCode.C)
        {
            // ubah style pas pressed, panggil action, ini jgn lupa ada
            buttonOnKeyPressStyle(buttonMC);
            buttonMCAction();

            return;
        }

        // Check for MR
        if(event.getCode() == KeyCode.R)
        {
            // ubah style pas pressed, panggil action
            buttonOnKeyPressStyle(buttonMR);
            buttonMRAction();
            return;
        }

        // Check for ans
        if(event.getCode() == KeyCode.A)
        {
            buttonOnKeyPressStyle(buttonAns);
            buttonAnsAction();

            return;
        }

        // Check for operators
        for(int i = 0; i < 7 ; i++) if(operatorKeyCodes[i].match(event))
        {
            buttonOnKeyPressStyle(operatorButtons[i]);
            buttonOperatorAction(operatorButtons[i]);

            return;
        }

        // Check for equal button
        if(event.getCode() == KeyCode.EQUALS || event.getCode() == KeyCode.ENTER)
        {
            buttonOnKeyPressStyle(buttonEquals);
            buttonEqualsAction();

            return;
        }

        // Check for functions
        for(int i = 0; i < 4; i++) if(functionKeyCodes[i].match(event))
        {
            buttonOnKeyPressStyle(functionButtons[i]);
            buttonFunctionAction(functionButtons[i]);

            return;
        }

        // Check for number key
        for(int i = 0; i < 11; i++)
        {
            if(event.getCode() == numberKeyCodes[i])
            {
                buttonOnKeyPressStyle(numberButtons[i]);

                // If period...
                if(i==10)
                    buttonPeriodAction(numberButtons[i]);
                // If input is not 0 and currently display is not empty...
                else if(i!=0 || !(numberDisplay.getText().equals("")))
                    buttonNumberAction(numberButtons[i]);

                return;
            }
        }

        // Check for BACKSPACE Key
        if(event.getCode() == KeyCode.BACK_SPACE)
        {
            buttonOnKeyPressStyle(buttonBackspace);
            buttonBackspaceAction();

            return;
        }

        // Check for DELETE Key
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonOnKeyPressStyle(buttonClear);
            buttonClearAction();

            return;
        }

        // Check for pi button
        if(new KeyCodeCombination(KeyCode.P, KeyCombination.SHIFT_DOWN).match(event))
        {
            buttonOnKeyPressStyle(buttonPi);
            buttonPiAction();

            return;
        }

    }

    @FXML
    private void handleOnKeyReleased(KeyEvent event) {
        // Check for MC
        if(event.getCode() == KeyCode.C)
        {
            // ubah style pas pressed, pasangan yg on pressed
            buttonOnKeyReleaseStyle(buttonMC);

            return;
        }

        // Check for MR
        if(event.getCode() == KeyCode.R) {
            // ubah style pas pressed, panggil action
            buttonOnKeyReleaseStyle(buttonMR);

            return;
        }

        // Check for ans
        if(event.getCode() == KeyCode.A)
        {
            buttonOnKeyReleaseStyle(buttonAns);
        }

        // Check for operators
        for(int i = 0; i < 7; i++) if(operatorKeyCodes[i].match(event))
        {
            buttonOnKeyReleaseStyle(operatorButtons[i]);
            return;
        }

        // Check for equal button
        if(event.getCode() == KeyCode.EQUALS || event.getCode() == KeyCode.ENTER)
        {
            buttonOnKeyReleaseStyle(buttonEquals);
            return;
        }

        // Check for functions
        for(int i = 0; i < 4; i++) if(functionKeyCodes[i].match(event))
        {
            buttonOnKeyReleaseStyle(functionButtons[i]);
            return;
        }

        // Check for number key
        for (int i = 0; i < 11; i++) {
            if (event.getCode() == numberKeyCodes[i]) {
                buttonOnKeyReleaseStyle(numberButtons[i]);
                return;
            }
        }

        // Check for BACKSPACE key
        if(event.getCode() == KeyCode.BACK_SPACE)
        {
            buttonOnKeyReleaseStyle(buttonBackspace);
            return;
        }

        // Check for DELETE key
        if(event.getCode() == KeyCode.DELETE)
        {
            buttonOnKeyReleaseStyle(buttonClear);
            return;
        }

        // Check for pi button
        if(new KeyCodeCombination(KeyCode.P, KeyCombination.SHIFT_DOWN).match(event))
        {
            buttonOnKeyReleaseStyle(buttonPi);
            return;
        }
    }

    @FXML
    private void handleOnMouseClick(MouseEvent mouseEvent) throws BaseException {
        String id = ((Control)mouseEvent.getSource()).getId().substring(6);
        System.out.println(id);

        switch(id)
        {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                buttonNumberAction(numberButtons[Integer.parseInt(id)]);
                break;
            case "Point":
                buttonPeriodAction(numberButtons[10]);
                break;
            case "Plus":
                buttonOperatorAction(operatorButtons[0]);
                break;
            case "Minus":
                buttonOperatorAction(operatorButtons[1]);
                break;
            case "Multiply":
                buttonOperatorAction(operatorButtons[2]);
                break;
            case "Divide":
                buttonOperatorAction(operatorButtons[3]);
                break;
            case "ParOpen":
                buttonOperatorAction(operatorButtons[4]);
                break;
            case "ParClose":
                buttonOperatorAction(operatorButtons[5]);
                break;
            case "Power":
                buttonOperatorAction(operatorButtons[6]);
                break;
            case "Sine":
                buttonOperatorAction(functionButtons[0]);
                break;
            case "Cosine":
                buttonOperatorAction(functionButtons[1]);
                break;
            case "Tangent":
                buttonOperatorAction(functionButtons[2]);
                break;
            case "Sqrt":
                buttonOperatorAction(functionButtons[3]);
                break;
            case "Ans":
                buttonAnsAction();
                break;
            case "Clear":
                buttonClearAction();
                break;
            case "Backspace":
                buttonBackspaceAction();
                break;
            case "Equals":
                buttonEqualsAction();
                break;
            case "Pi":
                buttonPiAction();
                break;
            case "MC":
                //action
                buttonMCAction();
                break;
            case "MR":
                //action
                buttonMRAction();
                break;
            default:
                break;
        }

    }

    /* -=-=-=-=- ACTION RELATED METHODS -=-=-=-=- */

    private void buttonOnKeyPressStyle(Button b)
    {
        b.getStyleClass().removeAll("buttons");
        b.getStyleClass().add("buttons:pressed");
    }

    private void buttonOnKeyReleaseStyle(Button b)
    {
        b.getStyleClass().removeAll("buttons:pressed");
        b.getStyleClass().add("buttons");
    }

    private void buttonAnsAction()
    {
        if(numberDisplay.getText().equals("")) equationDisplay.setText(equationDisplay.getText() + "Ans");
        else
        {
            equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + "*Ans");
            numberDisplay.setText("");
        }

    }

    private void buttonOperatorAction(Button b)
    {
        equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + b.getText());
        numberDisplay.setText("");
    }

    private void buttonEqualsAction() throws BaseException {
        equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText());

        /* -=-=-= TRY CATCH BLOCK HERE -=-=-==-= */
        try{
            // Evaluate expression
            EvaluateExpression evaluator = new EvaluateExpression(equationDisplay.getText());
            RealNumber result = evaluator.parse();

            ans = result.value();
            numberDisplay.setText(result.value().toString());
            equationDisplay.setText("");

        } catch (BaseException e){
            equationDisplay.setText(equationDisplay.getText() + "  ERROR: " + e.error());
            numberDisplay.setText("");
        }

        // Evaluate expression
        // EvaluateExpression evaluator = new EvaluateExpression(equationDisplay.getText());
        // RealNumber result = evaluator.parse();

        // Save value to ans

    }

    private void buttonFunctionAction(Button b)
    {
        equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() + b.getText() + "(");
        numberDisplay.setText("");
    }

    private void buttonPeriodAction(Button b)
    {
        if (numberDisplay.getText().equals("")) numberDisplay.setText("0");
        numberDisplay.setText(numberDisplay.getText() + ".");
    }

    private void buttonNumberAction(Button b)
    {
        numberDisplay.setText(numberDisplay.getText() + b.getText());
    }

    private void buttonBackspaceAction()
    {
        if(!numberDisplay.getText().equals(""))
        {
            String currentText = numberDisplay.getText();
            numberDisplay.setText(currentText.substring(0,currentText.length()-1));
        }
    }

    private void buttonClearAction()
    {
        numberDisplay.setText("");
        equationDisplay.setText("");
        history.clear();
    }

    private void buttonPiAction()
    {
        if(numberDisplay.getText().equals(""))
            equationDisplay.setText(equationDisplay.getText() + "pi");
        else
            equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() +"*pi");

        numberDisplay.setText("");
    }

    private void buttonMCAction()
    {
        // Action
        history.add(ans);

    }

    private void buttonMRAction() throws BaseException
    {
        // Action

        try
        {
            Double mr = history.peek();
            String mrString = mr.toString();
            history.remove();
            if(numberDisplay.getText().equals(""))
                equationDisplay.setText(equationDisplay.getText() + mrString);
            else
                equationDisplay.setText(equationDisplay.getText() + numberDisplay.getText() +"*" +mrString);

            numberDisplay.setText("");
        }
        catch (NullPointerException err)
        {
            throw new QueueEmptyException();
        }


    }


}