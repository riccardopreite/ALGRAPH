package sample;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.util.LinkedList;


public class Main extends Application {
    Boolean edge1 = false, edge2= false, removed = false, add = false;
    Scene scene1, scene2;
    Stage window;
    BorderPane layout, layout2;
    Circle circle;
    Integer cont_node=0, count = 0;
    Node node;
    Node pd = null,pd2;
    Node head;
    Node node2;
    public Node drag;
    Graph graph = new Graph();

    @Override
    public void start(Stage primaryStage) throws Exception {
        layout2 = new BorderPane();


        window = primaryStage;
        window.setTitle("making Menux");

        //DRAGGED
        /*
        EventHandler<MouseEvent> drag = mouseEvent -> DragCircle(mouseEvent);
        layout2.addEventHandler(MouseEvent.MOUSE_DRAGGED,drag);
*/

        //File menu


        Menu filemenu = new Menu("File");
        window.setOnCloseRequest(e -> {

            e.consume();
            closeProgram();
        });


        MenuItem newfile = new MenuItem("New");
        newfile.setOnAction(e -> {
            if(cont_node>0) {
                layout2 = head.clearLayout(layout2);
            }

            window.setScene(scene2);

        });

        MenuItem openfile = new MenuItem("Open");
        openfile.setOnAction(e -> {
            if(cont_node>0) {
                layout2 = head.clearLayout(layout2);
            }
            window.setScene(scene2);
            Openfile();
         });


        MenuItem randomfile = new MenuItem("Generate random");
        randomfile.setOnAction(e -> {
            if(cont_node>0) {
                layout2 = head.clearLayout(layout2);
            }
            window.setScene(scene2);
        });


        MenuItem exitfile = new MenuItem("Exit");
        exitfile.setOnAction(e -> {

            e.consume();
            closeProgram();
        });


        filemenu.getItems().addAll(newfile,openfile,randomfile,exitfile);

        //Edit menu
        Menu editmenu = new Menu("Edit");
        MenuItem Deletenode = new MenuItem("Delete Node");
        Deletenode.setOnAction(e -> {
            System.out.println("porcodio2");
            this.removed = true;
            System.out.println("enterr");

            EventHandler<MouseEvent> delete = mouseEvent -> removed = Removenode(mouseEvent,removed);
            layout2.addEventHandler(MouseEvent.MOUSE_PRESSED,delete);
        });

        MenuItem Addnode = new MenuItem("Add Node");
        Addnode.setOnAction(e -> {
            this.add = true;
            EventHandler<MouseEvent> AddNode = mouseEvent -> this.add = addCircle(mouseEvent, add);
            layout2.addEventHandler(MouseEvent.MOUSE_PRESSED,AddNode);


        });

        MenuItem Startedge = new MenuItem("Choose Start Node");
        Startedge.setOnAction(e -> {
            this.edge1 = true;
            EventHandler<MouseEvent> open = mouseEvent -> this.pd = AddStart(mouseEvent, pd,this.edge1);
            layout2.addEventHandler(MouseEvent.MOUSE_PRESSED,open);
            System.out.println(pd);

        });

        MenuItem EndEdge = new MenuItem("Choose End Node");
        EndEdge.setOnAction(e -> {
            this.edge2 = true;
            System.out.println("Start: " + pd);
            EventHandler<MouseEvent> end = mouseEvent2 ->  AddEnd(mouseEvent2, pd, this.edge2);
            layout2.addEventHandler(MouseEvent.MOUSE_PRESSED,end);

        });


        editmenu.getItems().addAll(Addnode, Startedge, EndEdge, Deletenode);


        //Help menu
        Menu helpmenu = new Menu("Help");
        CheckMenuItem showlinenumbers = new CheckMenuItem("Show line numbers");
        helpmenu.getItems().addAll(showlinenumbers);



        //Main menu bar
        MenuBar menuBar = new MenuBar();
        MenuBar menuBar2 = new MenuBar();
        menuBar.getMenus().addAll(filemenu,editmenu,helpmenu);
        menuBar2.getMenus().addAll(filemenu,editmenu,helpmenu);


        //EDGE


        layout2.setTop(menuBar2);
        layout2.getChildren().addAll();

        scene2 = new Scene(layout2, 1200, 900);
        layout = new BorderPane();
        layout.setTop(menuBar);
        scene1 = new Scene(layout,200,250);
        layout2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node delete = null;
                System.out.println("ciao");
                    if (event.getButton() == MouseButton.PRIMARY) {
                        delete = head.FindElement(event.getX(), event.getY());
                    }
               drag= delete;
            }
        });
        layout2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (drag == null) return;
                    if(event.getY() < 44){
                        if(event.getX()>1180){
                            drag.ChangeCoordinates(1180.0, 49.0);
                        }
                        else if(event.getX() < 20){
                            drag.ChangeCoordinates(21.0, 49.0);
                        }
                        else{
                            drag.ChangeCoordinates(event.getX(), 49.0);
                        }
                        return;
                    }
                    else if(event.getY() > 880){
                        if(event.getX()>1180){
                            drag.ChangeCoordinates(1180.0, 880.0);
                        }
                        else if(event.getX() < 20){
                            drag.ChangeCoordinates(21.0, 880.0);
                        }
                        else{
                            drag.ChangeCoordinates(event.getX(), 880.0);
                        }
                        return;
                    }
                    if(event.getX()<20){
                        if(event.getY()<44){
                            drag.ChangeCoordinates(20.0, 49.0);
                        }
                        else if(event.getY()>880){
                            drag.ChangeCoordinates(20.0, 880.0);
                        }
                        else{
                            drag.ChangeCoordinates(20.0, event.getY());
                        }

                        return;
                    }
                    else if(event.getX() > 1180 ){
                        if(event.getY()<44){
                            drag.ChangeCoordinates(1180.0, 49.0);
                        }
                        else if(event.getY()>880){
                            drag.ChangeCoordinates(1180.0, 880.0);
                        }
                        else{
                            drag.ChangeCoordinates(1180.0, event.getY());
                        }
                        return;

                    }
                    else {
                        drag.ChangeCoordinates(event.getX(), event.getY());
                    }
            }

        });
        layout2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drag = null;
            }
        });
        window.setScene(scene1);
        window.show();
        window.setX(200);
        window.setY(200);
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
public Boolean addCircle(MouseEvent mouseEvent, Boolean add){
        List Start, Finish;
        if(!add){
            return false;
        }
        else if (mouseEvent.getButton() == MouseButton.PRIMARY) {

            if(mouseEvent.getY() < 45) {
                //circle = new Circle(mouseEvent.getX(), 45, 20);
                System.out.println("Non va bene porcodio");
                return false;

            }
            else if(mouseEvent.getY() > 880) {
                //circle = new Circle(mouseEvent.getX(), 45, 20);
                System.out.println("Non va bene porcodio");
                return false;

            }
             if (mouseEvent.getX() < 20) {
               // circle = new Circle( window.getMaxHeight()-20, mouseEvent.getY(), 20);
                System.out.println("Non va bene porcodio2");
                return false;


            }

            else if (mouseEvent.getX() > 1180) {
                // circle = new Circle( window.getMaxHeight()-20, mouseEvent.getY(), 20);
                System.out.println("Non va bene porcodio2");
                return false;


            }else {
                circle = new Circle(mouseEvent.getX(), mouseEvent.getY(), 20);
            }
                if (count == 0) {

                    head = new Node(mouseEvent.getX(), mouseEvent.getY(), cont_node, circle);
                    graph.insertNode(head);
                    node2 = head;
                    head.AddNode(head, null);


                    layout2.getChildren().addAll(circle);
                    cont_node++;
                    count++;
                } else {
                    node = new Node(mouseEvent.getX(), mouseEvent.getY(), cont_node, circle);
                    graph.insertNode(node);
                    node2 = node;

                    head.AddNode(node2, node);
                    node2 = node;

                    layout2.getChildren().addAll(circle);
                    cont_node++;

                }
            }
        return false;
    }


public Boolean Removenode(MouseEvent mouseEvent, Boolean removed){
    Node delete;
System.out.println("ciao");
if(removed) {
    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
        delete = head.FindElement(mouseEvent.getX(), mouseEvent.getY());


        if (delete != null) {
            circle = head.CircleToDelete(delete);
            count = head.DeleteElement(delete);
            graph.deleteNode(delete);
            layout2.getChildren().remove(circle);
            System.out.println("Element Deleted.");
            // graph.print();
        }
        head.print();

    }

}
    return false;

}

    public Node AddStart(MouseEvent mouseEvent, Node start, Boolean Edge1){
        if(!Edge1){
            return  start;
        }

        else if(Edge1) {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {

                start = head.FindElement(mouseEvent.getX(), mouseEvent.getY());
                System.out.println(edge1);
                this.edge1 = false;


            }

        }
        return start;

     }

public void AddEnd(MouseEvent mouseEvent2, Node start, Boolean Edge2) {

        Node finish = null;

        if(Edge2) {
            if (mouseEvent2.getButton() == MouseButton.SECONDARY) {

            finish = head.FindElement(mouseEvent2.getX(), mouseEvent2.getY());
        graph.insertEdge(start, finish);

        this.edge2 = false;

        graph.print();
        start = null;
    }

}
    }

    public void DragCircle(MouseEvent t){
        /*Node Oldcircle = null;

        if(drag.getButton() == MouseButton.PRIMARY) {
            Oldcircle = head.FindElement(drag.getX(), drag.getY());
        }*/
        //Dopo spostamento

//        head.ChangeCoordinates(drag,t.getX(),t.getY());

    }
/*
    public Node Drag(MouseEvent mouseEvent){

    }
*/


    public static void main(String[] args) {
        launch(args);
    }
}

