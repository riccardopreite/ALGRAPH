package sample;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.*;




public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesbutton = new Button("Yes");
        Button nobutton = new Button("No");

        yesbutton.setOnAction(e -> {

            answer = true;
            window.close();

        });

        nobutton.setOnAction(e -> {

            answer = false;
            window.close();

        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesbutton,nobutton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;


    }

}
