import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import expressions.*;
import numbers.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        Expression num1 = new TerminalExpression(new RealNumber(5.0));
        Expression num2 = new TerminalExpression(new RealNumber(7.0));
        Expression num3 = new BinaryExpression(num1, num2);

        String res1 = num1.solve().value().toString();
        String res2 = num2.solve().value().toString();
        String res3 = num3.solve().value().toString();

        // Create JavaFX Label
        Label l = new Label(res1 + "+" + res2 + "=" + res3);

        // Setup JavaFX Scene for creating windows
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}