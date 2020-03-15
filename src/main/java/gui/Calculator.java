package gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Calculator extends Application
{
    public HBox displayBox()
    {
        // Create new HBox
        HBox displayContainer = new HBox();

        // Create a new TextField
        TextField displayValue = new TextField();

        displayValue.setEditable(false);
        displayValue.setPadding(new Insets(12,12,12,12));
        displayValue.setStyle("-fx-background-color: rgba(0,0,0,0), #ffffff;");
        displayValue.setText("cilukba");

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
        
        for(int i=0;i<6;i++)
        {
            HBox h = new HBox();
            Button b;
            for(int j=0;j<4;j++)
            {
                b = new Button(buttonText[i][j]);
                b.setMinWidth(50);
                h.getChildren().add(b);
                HBox.setHgrow(b, Priority.ALWAYS);
            }
            buttonContainer.add(h,0,i);
            
        }

        return buttonContainer;
    }

    @Override
    public void start(Stage stage)
    {
        // Create new border pane
        BorderPane border = new BorderPane();
        
        // Create display box
        HBox hbox = displayBox();
        
        border.setTop(hbox);
        border.setCenter(calculatorButtons());

        Scene scene = new Scene(border,300, 500);    
        stage.setScene(scene);
        stage.show();
    }
}