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

        // String query1 = "2+3/5-7.5";
        String query1 = "9-(-3)";
        // String query3 = "23+5-7";

        EvaluateExpression exp1 = new EvaluateExpression(query1);
        // EvaluateExpression exp2 = new EvaluateExpression(query2);
        // EvaluateExpression exp3 = new EvaluateExpression(query3);

        RealNumber ans1 = exp1.parse();
        // RealNumber ans2 = exp2.parse();
        // RealNumber ans3 = exp3.parse();

        String res1 = ans1.value().toString();
        // String res2 = ans2.value().toString();
        // String res3 = ans3.value().toString();

        // Create JavaFX Label
        // Label l = new Label("2+3/5-7.5 = " + res1 + "\n" + "3-(7-(3)) = " + res2 + "\n" + "-(2-(-3*5)) = ");
        Label l = new Label(res1);

        // Setup JavaFX Scene for creating windows
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}