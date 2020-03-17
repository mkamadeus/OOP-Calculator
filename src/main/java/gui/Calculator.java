package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 

public class Calculator extends Application
{
    private TextField displayValue;

    public HBox displayBox()
    {
        // Create new HBox
        HBox displayContainer = new HBox();

        displayValue.setEditable(false);
        displayValue.setPadding(new Insets(12,12,12,12));
        displayValue.setStyle("-fx-background-color: rgba(0,0,0,0), #ffffff;");
        displayValue.setText("");

        displayContainer.setStyle("-fx-background-color:#eeeeee");
        displayContainer.setPadding(new Insets(12,12,12,12));
        HBox.setHgrow(displayValue, Priority.ALWAYS);
        displayContainer.getChildren().addAll(displayValue);

        return displayContainer;
        
    }

    public GridPane calculatorButtons()
    {
        GridPane buttonContainer = new GridPane();

        GridPane.setColumnSpan(new Button(), 5);
        GridPane.setRowSpan(new Button(), 5);
        
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setHgap(10);
        buttonContainer.setVgap(10);
        
        
        // Button text contents
        String buttonText[][] = {
            {"sin", "cos", "tan", "CLR"},
            {"(", ")", "sqrt", "^"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "/"},
            {"1", "2", "3", "-"},
            {"0", ".", "=", "+"},
            {"ANS", "MC", "MR", "<="},
        };
        
        for(int i=0;i<7;i++)
        {
            HBox h = new HBox();
            Button b;
            for(int j=0;j<4;j++)
            {
                b = new Button(buttonText[i][j]);

                final String s = buttonText[i][j];
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        String tmp = displayValue.getText();
                        tmp += s;
                        displayValue.setText(tmp);
                    }
                };

                b.setMinWidth(50);
                b.setOnAction(event);
                h.getChildren().add(b);
                HBox.setHgrow(b, Priority.ALWAYS);
            }
            buttonContainer.add(h,0,i);
            
        }

        return buttonContainer;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout.fxml"));

        Scene scene = new Scene(root, 350, 700);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
}