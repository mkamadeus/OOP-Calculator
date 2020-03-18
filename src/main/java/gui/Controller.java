package gui;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    private void handleOnNumberButtonsPressed(KeyEvent event) {
        System.out.println("Pressed key text: " + event.getText());
        System.out.println("Pressed key code: " + event.getCode());
    }
}