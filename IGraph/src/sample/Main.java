package sample;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class Main extends Application {
    Scene scene1, scene2;
    Stage window;
    BorderPane layout, layout2;
    Circle circle;
    Integer cont_node=0;
    Node node;
    Graph root = new Graph();

    // Node nodeY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        layout2 = new BorderPane();


        window = primaryStage;
        window.setTitle("making Menux");

        //File menu


        Menu filemenu = new Menu("File");
        window.setOnCloseRequest(e -> {

            e.consume();
            closeProgram();
        });


        MenuItem newfile = new MenuItem("New");
        newfile.setOnAction(e -> {
            window.setScene(scene2);

        });


       EventHandler<MouseEvent> mouse = mouseEvent -> addCircle(mouseEvent);
        layout2.addEventHandler(MouseEvent.MOUSE_CLICKED,mouse);

        MenuItem openfile = new MenuItem("Open");
        openfile.setOnAction(e -> {
            window.setScene(scene2);
            Openfile();
         });


        MenuItem randomfile = new MenuItem("Generate random");
        randomfile.setOnAction(e -> {
            window.setScene(scene2);
            Openfile();
        });


        MenuItem exitfile = new MenuItem("Exit");
        exitfile.setOnAction(e -> {

            e.consume();
            closeProgram();
        });


        filemenu.getItems().addAll(newfile,openfile,randomfile,exitfile);

        //Help menu
        Menu helpmenu = new Menu("Help");
        CheckMenuItem showlinenumbers = new CheckMenuItem("Show line numbers");
        helpmenu.getItems().addAll(showlinenumbers);


        //Main menu bar
        MenuBar menuBar = new MenuBar();
        MenuBar menuBar2 = new MenuBar();
        menuBar.getMenus().addAll(filemenu,helpmenu);
        menuBar2.getMenus().addAll(filemenu,helpmenu);

        layout2.setTop(menuBar2);
        layout2.getChildren().addAll();
        scene2 = new Scene(layout2, 1200, 900);

        layout = new BorderPane();
        layout.setTop(menuBar);
        scene1 = new Scene(layout,200,250);
        window.setScene(scene1);
        window.show();
    }

    public void Openfile(){

        File file;
        file = new File("data.txt");
        URL path = null;
        try {
            path = file.toURL();
            InputStream is = path.openStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int i;
            do
            {
                i=br.read();
                System.out.print((char)i);
            }
            while (i!=-1);
            System.out.println("\n");
            is.close();
        }


        catch (MalformedURLException e)
        {
            System.out.println("Attenzione:" + e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Fuck God!!!", "Are you sure you want to exit?");

        if (answer) {
            window.close();
            System.out.println("File saved!");
        }
    }
public void addCircle(MouseEvent mouseEvent){
    {

        if (mouseEvent.getButton() == MouseButton.PRIMARY){

            circle = new Circle(mouseEvent.getX(), mouseEvent.getY(), 20);
            layout2.getChildren().addAll(circle);
            if(cont_node==0) {
                cont_node++;

            }
            else{
                 node = new Node(circle.getCenterX(),circle.getCenterY());
               //  nodeY = new Node(circle.getCenterY());
                 //root.insertNode(node);
                 System.out.println(node.exist(circle));

            }
            System.out.println("ciao");
        }

    }
}


    public static void main(String[] args) {
        launch(args);
    }
}

