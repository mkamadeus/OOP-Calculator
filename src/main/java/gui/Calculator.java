package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Calculator extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layout.fxml"));

        // Load outer outermost element from the FXML file
        Parent root = loader.load();

        // Setup new scene
        Scene scene = new Scene(root, 350, 700);

        // Set stage to the scene
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}