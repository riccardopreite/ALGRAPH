package sample;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.internal.cmm.SystemResourcePressureImpl;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;


public class Main extends Application {
  Boolean edge1 = false, edge2= false, removed = false, add = false,move = false;
  Double startLineX, startLineY, endLineX, endLineY, LinX, LinY;
  Scene scene1, scene2;
  Stage window, window2;
  BorderPane layout, layout2;
  Circle circle;
  Line line;
  Integer cont_node=0, count = 0;
  Node node;
  New_Node pd = null,pd2;
  Node head = null;
  New_Node tmphead = null,tmpnode = null;
  public New_Node drag;

  Graph graph = new Graph();
  TextField nameinput;
  TextField priceinput;
  TextField quantityinput;
  TableView table;
  Stage secondStage;
  VBox vbox;
  private Desktop desktop = Desktop.getDesktop();


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

<<<<<<< HEAD
=======
      MenuItem newfile = new MenuItem("New");
      newfile.setOnAction(e -> {
          if(cont_node > 0){
              layout2 = head.clearLayout(layout2);

          }
              cont_node = 0;
              count = 0;
              line = null;
              edge1 = false;
              edge2 = false;
              removed = false;
              add = false;
              move = false;
              head = null;
              graph = new Graph();

>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

    MenuItem newfile = new MenuItem("New");
    newfile.setOnAction(e -> {
      if(cont_node > 0){
        layout2 = head.clearLayout(layout2,tmphead);

      }
      if(secondStage != null) {
        secondStage.close();
      }
      set_View();
      cont_node = 0;
      count = 0;
      line = null;
      edge1 = false;
      edge2 = false;
      removed = false;
      add = false;
      move = false;
      head = null;
      graph = new Graph();

<<<<<<< HEAD

      window.setScene(scene2);
=======
      MenuItem openfile = new MenuItem("Open");
      openfile.setOnAction(e -> {
          if(cont_node > 0){
              layout2 = head.clearLayout(layout2);

          }
          cont_node = 0;
          count = 0;
          line = null;
          edge1 = false;
          edge2 = false;
          removed = false;
          add = false;
          move = false;
          head = null;
          graph = new Graph();

          window.setScene(scene2);
          Openfile();
      });
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

    });

<<<<<<< HEAD
    MenuItem openfile = new MenuItem("Open");
    openfile.setOnAction(e -> {
      if(cont_node > 0){
        layout2 = head.clearLayout(layout2,tmphead);

      }
      if(secondStage != null) {
        secondStage.close();
      }
      set_View();
      cont_node = 0;
      count = 0;
      line = null;
      edge1 = false;
      edge2 = false;
      removed = false;
      add = false;
      move = false;
      head = null;
      graph = new Graph();

      Openfile();
      window.setScene(scene2);
=======
      MenuItem randomfile = new MenuItem("Generate random");
      randomfile.setOnAction(e -> {
          if(cont_node > 0){
              layout2 = head.clearLayout(layout2);

          }
          cont_node = 0;
          count = 0;
          line = null;
          edge1 = false;
          edge2 = false;
          removed = false;
          add = false;
          move = false;
          head = null;
          graph = new Graph();
          Create_rand_Graph(15);
          window.setScene(scene2);
      });
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

    });


    MenuItem randomfile = new MenuItem("Generate random");
    randomfile.setOnAction(e -> {
      if(cont_node > 0){
        layout2 = head.clearLayout(layout2,tmphead);

      }
      if(secondStage != null) {
        secondStage.close();
      }

      cont_node = 0;
      count = 0;
      line = null;
      edge1 = false;
      edge2 = false;
      removed = false;
      add = false;
      move = false;
      head = null;
      graph = new Graph();
      set_View();
      Create_rand_Graph(20,table);

      window.setScene(scene2);
    });


    MenuItem exitfile = new MenuItem("Exit");
    exitfile.setOnAction(e -> {

      e.consume();
      closeProgram();
    });


<<<<<<< HEAD
    EventHandler<MouseEvent> select = mouseEvent -> {
      New_Node selected;
      if(head != null) {
        if ((selected = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead)) != null) {
=======
      MenuItem Startedge = new MenuItem("Choose Start Node");
      Startedge.setOnAction(e -> {
          this.edge1 = true;
          EventHandler<MouseEvent> open = mouseEvent -> this.pd = AddStart(mouseEvent, pd, this.edge1);
          layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, open);
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

          circle = selected.getCircle();
          circle.setFill(BLUE);

<<<<<<< HEAD
        }
        else{
          head.rePrint_Circle(tmphead);
        }
      }
=======
      MenuItem EndEdge = new MenuItem("Choose End Node");
      EndEdge.setOnAction(e -> {
          this.edge2 = true;
          EventHandler<MouseEvent> end = mouseEvent2 -> AddEnd(mouseEvent2, pd, this.edge2);
          layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, end);
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

    };
    layout2.addEventHandler(MouseEvent.MOUSE_MOVED, select);

<<<<<<< HEAD
    filemenu.getItems().addAll(newfile, openfile, randomfile, exitfile);

    //Edit menu
    Menu editmenu = new Menu("Edit");
    MenuItem Deletenode = new MenuItem("Delete Node");
    Deletenode.setOnAction(e -> {
      this.removed = true;
=======
      MenuItem MoveNode = new MenuItem("Move The Node");
      MoveNode.setOnAction(e -> {
          move = true;
          move = MoveCircle(move);

      });


      editmenu.getItems().addAll(Addnode, Startedge, EndEdge, Deletenode,MoveNode);
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

      EventHandler<MouseEvent> delete = mouseEvent -> removed = Removenode(mouseEvent, removed);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, delete);

<<<<<<< HEAD
    });
=======
      //Help menu
      Menu helpmenu = new Menu("Help");
      MenuItem Help = new MenuItem("Show Help Menu");
      Help.setOnAction(e -> {

      });
      helpmenu.getItems().addAll(Help);
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

    MenuItem Addnode = new MenuItem("Add Node");
    Addnode.setOnAction(e -> {
      this.add = true;
      EventHandler<MouseEvent> AddNode = mouseEvent -> this.add = addCircle(mouseEvent, add);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, AddNode);


    });

    MenuItem Startedge = new MenuItem("Choose Start Node");
    Startedge.setOnAction(e -> {
      this.edge1 = true;
      EventHandler<MouseEvent> open = mouseEvent -> this.pd = AddStart(mouseEvent, pd, this.edge1);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, open);

    });

    MenuItem EndEdge = new MenuItem("Choose End Node");
    EndEdge.setOnAction(e -> {
      this.edge2 = true;
      EventHandler<MouseEvent> end = mouseEvent2 -> AddEnd(mouseEvent2, pd, this.edge2);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, end);

    });

<<<<<<< HEAD
    MenuItem MoveNode = new MenuItem("Move The Node");
    MoveNode.setOnAction(e -> {
      move = true;
      move = MoveCircle(move);

    });



    editmenu.getItems().addAll(Addnode, Startedge, EndEdge, Deletenode,MoveNode);


    //Help menu
    Menu helpmenu = new Menu("Help");
    MenuItem Help = new MenuItem("Show Help Menu");
    Help.setOnAction(e -> {

    });
    helpmenu.getItems().addAll(Help);


    //Main menu bar
    MenuBar menuBar = new MenuBar();
    MenuBar menuBar2 = new MenuBar();
    menuBar.getMenus().addAll(filemenu, editmenu, helpmenu);
    menuBar2.getMenus().addAll(filemenu, editmenu, helpmenu);


    //EDGE


    layout2.setTop(menuBar2);
    layout2.getChildren().addAll();

    scene2 = new Scene(layout2, 1200, 900);
    layout = new BorderPane();
    layout.setTop(menuBar);
    scene1 = new Scene(layout, 200, 250);

    window.setScene(scene1);
    window.show();
    window.setX(200);
    window.setY(200);
=======
      window.setScene(scene1);
      window.show();
      window.setX(200);
      window.setY(200);
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94
  }
  private void openFile(File file) {
    URL path = null;

    try {
      desktop.open(file);

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

  public void Openfile() {
    Stage stage = new Stage();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Txt File");
    fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
    );

    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Txt", ".txt")
    );
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      openFile(file);
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
    if(!add){
      // this.edge1 = false;
      // this.edge2 = false;
      // this.removed = false;
      return false;
    }
    else if (mouseEvent.getButton() == MouseButton.PRIMARY) {
      // this.edge1 = false;
      // this.edge2 = false;
      // this.removed = false;

      if(mouseEvent.getY() < 45) {
        System.out.println("Non va bene porcodio");
        return false;

      }
      else if(mouseEvent.getY() > 880) {
        System.out.println("Non va bene porcodio");
        return false;

      }
      if (mouseEvent.getX() < 20) {
        System.out.println("Non va bene porcodio2");
        return false;


      }

      else if (mouseEvent.getX() > 1180) {
        System.out.println("Non va bene porcodio2");
        return false;


      }else {
        circle = new Circle(mouseEvent.getX(), mouseEvent.getY(), 20);
      }
      New_Node tmp2 = new New_Node();
      if (this.count == 0) {
        head = null;
<<<<<<< HEAD
        head = new Node();
        tmphead = new New_Node();
        tmphead.setCircleX(mouseEvent.getX());
        tmphead.setCircleY(mouseEvent.getY());
        tmphead.setCircle(circle);
        graph.insertNode(tmphead);
        head.AddNode(tmphead, null, count, tmphead);

        tmp2 = tmphead;
=======
        head = new Node(mouseEvent.getX(), mouseEvent.getY(), cont_node, circle);
        graph.insertNode(head);
        node2 = head;
        head.AddNode(head, null, count);
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94


        layout2.getChildren().addAll(circle);
        this.cont_node++;
        this.count++;
        this.move = false;
      } else {
        node = new Node();
        tmpnode = new New_Node();
        tmpnode.setCircleX(mouseEvent.getX());
        tmpnode.setCircleY(mouseEvent.getY());
        tmpnode.setCircle(circle);
        graph.insertNode(tmpnode);

        head.AddNode(tmp2, tmpnode,count,tmphead);
        tmp2 = tmpnode;

        layout2.getChildren().addAll(circle);
        this.cont_node++;
        this.count++;
        this.move = false;



      }
    }
    else return false;
    return false;
  }


  public Boolean Removenode(MouseEvent mouseEvent, Boolean removed){
    New_Node delete;
    if(removed) {
      //  this.edge1 = false;
      // this.edge2 = false;
      //  this.add = false;
      if(count == 0) {
        return false;
      }

      if (mouseEvent.getButton() == MouseButton.PRIMARY) {
        delete = head.FindElement(mouseEvent.getX(), mouseEvent.getY(),tmphead);


        if (delete != null) {
          circle = head.CircleToDelete(delete);
          this.count = head.DeleteElement(delete, this.layout2,count,tmphead);
          graph.deleteNode(delete);
          System.out.println("Element Deleted.");
          graph.print();
        }
<<<<<<< HEAD
        head.print(tmphead);
=======
        head.print();
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94
        this.move = false;

      }
      else return false;

    }
    return false;

  }

  public New_Node AddStart(MouseEvent mouseEvent, New_Node start, Boolean Edge1){
    if(!Edge1){
      return  start;
    }

    else if(Edge1) {
      //   this.edge2 = false;
      //  this.removed = false;
      //  this.add = false;
      if(count < 2) {
        System.out.println("poecodio minore di due start");
        return null;
      }
      else if (mouseEvent.getButton() == MouseButton.PRIMARY) {

<<<<<<< HEAD
        start = head.FindElement(mouseEvent.getX(), mouseEvent.getY(),tmphead);
=======
        start = head.FindElement(mouseEvent.getX(), mouseEvent.getY());
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94
        this.startLineX = mouseEvent.getX();
        this.startLineY = mouseEvent.getY();

        this.edge1 = false;
        this.move = false;

        return start;


      }
      else {
<<<<<<< HEAD
        this.move = false;
        this.edge1 = false;
      }


    }
    this.move = false;
    return start;
=======
            this.move = false;
            this.edge1 = false;
        }


    }
      this.move = false;
      return start;
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

  }

  public void AddEnd(MouseEvent mouseEvent2, New_Node start, Boolean Edge2) {

    New_Node finish = null;

    if(Edge2) {
      //this.edge1 = false;
      //  this.removed = false;
<<<<<<< HEAD
      // this.add = false;
      if(count < 2) {
        System.out.println("poecodio minore di due finish");
        this.move = false;
        return;
      }
      else if (mouseEvent2.getButton() == MouseButton.PRIMARY) {

        finish = head.FindElement(mouseEvent2.getX(), mouseEvent2.getY(),tmphead);
        line = new Line();
        line.setStartX(start.getCircleX());
        line.setStartY(start.getCircleY());
        line.setEndX(finish.getCircleX());
        line.setEndY(finish.getCircleY());

        //    System.out.println("Startx: " + this.startLineX + " Starty: " + this.startLineY + " Endx: " + endLineX + " Endy: " + endLineY  );
        start.setLine(line);
        finish.setLine(line);
        layout2.getChildren().addAll(line);

        graph.insertEdge(start, finish);
        //   this.pd = null;
=======
       // this.add = false;
        if(count < 2) {
            System.out.println("poecodio minore di due finish");
            this.move = false;
            return;
        }
        else if (mouseEvent2.getButton() == MouseButton.PRIMARY) {

        finish = head.FindElement(mouseEvent2.getX(), mouseEvent2.getY());
          this.endLineX = head.return_CenterX(finish);
          this.endLineY = head.return_CenterY(finish);
          line = new Line();
          line.setStartX(head.return_CenterX(start));
          line.setStartY(head.return_CenterY(start));
          line.setEndX(endLineX);
          line.setEndY(endLineY);

      //    System.out.println("Startx: " + this.startLineX + " Starty: " + this.startLineY + " Endx: " + endLineX + " Endy: " + endLineY  );
          start.setLine(line);
          finish.setLine(line);
          layout2.getChildren().addAll(line);

          graph.insertEdge(start, finish);
         //   this.pd = null;
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

        this.edge2 = false;
        this.move = false;

<<<<<<< HEAD
        graph.print();
=======
            graph.print();
>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94
      }
      else this.edge2 = false;


    }

  }

  public Boolean MoveCircle(Boolean move){

<<<<<<< HEAD
    if(move) {
      move = false;
      layout2.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          New_Node delete = null;
          System.out.println("ciao");
          if (event.getButton() == MouseButton.PRIMARY) {
            if (head == null)
            drag = null;
            else {
              drag = head.FindElement(event.getX(), event.getY(), tmphead);
              if (drag != null) {
                LinX = drag.getCircleX();
                LinY = drag.getCircleY();
              }
            }
          }


        }
      });

      layout2.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if (drag == null) return;
          if (event.getY() < 44) {
            if (event.getX() > 1180) {
              drag.getLine(1180.0, 49.0, LinX, LinY);
              drag.ChangeCoordinates(1180.0, 49.0);
              //  if(drag.ifLine()) {
              // drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }

            } else if (event.getX() < 20) {
              drag.getLine(21.0, 49.0, LinX, LinY);

              drag.ChangeCoordinates(21.0, 49.0);
              //   if(drag.ifLine()) {
              //   drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }

            } else {
              drag.getLine(event.getX(), 49.0, LinX, LinY);

              drag.ChangeCoordinates(event.getX(), 49.0);
              //   if(drag.ifLine() == true) {
              // drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }

            }
            return;
          } else if (event.getY() > 880) {
            if (event.getX() > 1180) {
              drag.getLine(1180.0, 880.0, LinX, LinY);

              drag.ChangeCoordinates(1180.0, 880.0);
              //  if(drag.ifLine()) {
              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //   }

            } else if (event.getX() < 20) {
              // if(drag.ifLine()) {
              drag.getLine(21.0, 880.0, LinX, LinY);
              drag.ChangeCoordinates(21.0, 880.0);

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              // }

            } else {
              //  if(drag.ifLine()) {
              drag.getLine(event.getX(), 880.0, LinX, LinY);
              drag.ChangeCoordinates(event.getX(), 880.0);

              // drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }

            }
            return;
          }
          if (event.getX() < 20) {
            if (event.getY() < 44) {
              //  if(drag.ifLine()) {
              drag.getLine(20.0, 49.0, LinX, LinY);
              drag.ChangeCoordinates(20.0, 49.0);

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              // }
            } else if (event.getY() > 880) {
              //   if(drag.ifLine()) {
              drag.getLine(20.0, 880.0, LinX, LinY);
              drag.ChangeCoordinates(20.0, 880.0);

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }
            } else {
              //           if(drag.ifLine()) {
              drag.getLine(20.0, event.getY(), LinX, LinY);
              drag.ChangeCoordinates(20.0, event.getY());

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //       }

            }

            return;
          } else if (event.getX() > 1180) {
            if (event.getY() < 44) {
              //      if(drag.ifLine()) {
              drag.getLine(1180.0, 49.0, LinX, LinY);
              drag.ChangeCoordinates(1180.0, 49.0);

              // drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //   }

            } else if (event.getY() > 880) {
              //    if(drag.ifLine()) {
              drag.getLine(1180.0, 880.0, LinX, LinY);
              drag.ChangeCoordinates(1180.0, 880.0);

              //   drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //   }

            } else {
              //   if(drag.ifLine()) {
              drag.getLine(1180.0, event.getY(), LinX, LinY);
              drag.ChangeCoordinates(1180.0, event.getY());

              //   drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }
            }
            return;

          } else {
            //    if(drag.ifLine()) {
            drag.getLine(event.getX(), event.getY(), LinX, LinY);
            drag.ChangeCoordinates(event.getX(), event.getY());

            // drag = head.FindElement(event.getX(), event.getY());
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            //    }

          }
        }

      });
      layout2.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          drag = null;
        }
      });
    }
    return move;
  }

  public void Create_rand_Graph(Integer vertex, TableView table) {

    Double randX, randY;
    Integer index = 0;
    if(vertex != 0){
      while (index < vertex) {
        randX = (double) (Math.random() * 1180 + 20);
        randY = (double) (Math.random() * 831 + 49);

        circle = new Circle(randX, randY, 20);
        New_Node tmp2 = new New_Node();
        if (this.count == 0) {
          head = null;
          head = new Node();
          tmphead = new New_Node();
          tmphead.setCircleX(randX);
          tmphead.setCircleY(randY);
          tmphead.setCircle(circle);
          graph.insertNode(tmphead);


          //ObservableList<New_Node> nodes = FXCollections.observableArrayList();
          // nodes.add(new New_Node("A",10.0,10.0));
          // nodes.add(new New_Node("B",randX,randY));

          //   tmphead.setElement("A");
          //  tmphead.setPrice(Double.parseDouble(priceinput.getText()));
          //   product.setQuantity(Integer.parseInt(quantityinput.getText()));

          //  table.setItems(nodes);
          table.getItems().add(head);

          head.AddNode(tmphead, null, count, tmphead);

          tmp2 = tmphead;


          layout2.getChildren().addAll(circle);
          this.cont_node++;
          this.count++;
          this.move = false;
          index++;

        } else {
          tmpnode = new New_Node();
          tmpnode.setCircleX(randX);
          tmpnode.setCircleY(randY);
          tmpnode.setCircle(circle);
          graph.insertNode(tmpnode);
          head.AddNode(tmp2, tmpnode,count,tmphead);
          tmp2 = tmpnode;

          layout2.getChildren().addAll(circle);
          this.cont_node++;
          this.count++;
          this.move = false;
          index++;


        }


      }


      index = 0;
      Double control = 100 - (vertex * 4.75);
      while(index < vertex){
        head.random_graph(layout2,index, control,graph,tmphead);
        index++;

      }
      Scene scene3 = new Scene(vbox);
      secondStage = new Stage();
      secondStage.setScene(scene3);
      secondStage.show();

    }


  }
  public void set_View() {
    TableColumn<New_Node, String> namecolumn = new TableColumn<>("Name");
    namecolumn.setMinWidth(100);
    namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    //Price column
    TableColumn<New_Node, String> pricecolumn = new TableColumn<>("Price");
    pricecolumn.setMinWidth(100);
    pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    //Quantity column
    TableColumn<New_Node, String> quantitycolumn = new TableColumn<>("Quantity");
    quantitycolumn.setMinWidth(100);
    quantitycolumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));


    Button addbutton = new Button("Add");
    addbutton.setOnAction(e -> {
      this.add = true;
      tmpnode = new New_Node();
      tmpnode.setElement(nameinput.getText());
      tmpnode.setCircleX(Double.parseDouble(priceinput.getText()));
      tmpnode.setCircleY(Double.parseDouble(quantityinput.getText()));

      table.getItems().add(tmpnode);
      nameinput.clear();
      priceinput.clear();
      quantityinput.clear();
      EventHandler<MouseEvent> AddNode = mouseEvent -> this.add = addCircle(mouseEvent, add);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, AddNode);

    });
    Button deletebutton = new Button("Delete");
    deletebutton.setOnAction(e -> {
      this.removed = true;

      EventHandler<MouseEvent> delete = mouseEvent -> removed = Removenode(mouseEvent, removed);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, delete);

    });


    //Name input
    nameinput = new TextField();
    nameinput.setPromptText("Name");
    nameinput.setMinWidth(100);

    //Price Input
    priceinput = new TextField();
    priceinput.setPromptText("Price");

    //Quantity Input
    quantityinput = new TextField();
    quantityinput.setPromptText("Quantity");
    HBox hbox = new HBox();

    hbox.setPadding(new Insets(10, 10, 10, 10));
    hbox.setSpacing(10);
    hbox.getChildren().addAll(nameinput, priceinput, quantityinput, addbutton, deletebutton);


    table = new TableView();
    table.getColumns().addAll(namecolumn, pricecolumn, quantitycolumn);

    vbox = new VBox();
    vbox.getChildren().addAll(table, hbox);


  }

=======
      if(move) {
          move = false;
          layout2.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  Node delete = null;
                  System.out.println("ciao");
                  if (event.getButton() == MouseButton.PRIMARY) {
                      if (head == null)
                          delete = null;
                      else {
                          delete = head.FindElement(event.getX(), event.getY());
                          drag = delete;
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                      }
                  }


              }
          });

          layout2.setOnMouseDragged(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  if (drag == null) return;
                  if (event.getY() < 44) {
                      if (event.getX() > 1180) {
                          drag.getLine(1180.0, 49.0, LinX, LinY);
                          drag.ChangeCoordinates(1180.0, 49.0);
                          //  if(drag.ifLine()) {
                          // drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //  }

                      } else if (event.getX() < 20) {
                          drag.getLine(21.0, 49.0, LinX, LinY);

                          drag.ChangeCoordinates(21.0, 49.0);
                          //   if(drag.ifLine()) {
                          //   drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //  }

                      } else {
                          drag.getLine(event.getX(), 49.0, LinX, LinY);

                          drag.ChangeCoordinates(event.getX(), 49.0);
                          //   if(drag.ifLine() == true) {
                          // drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //  }

                      }
                      return;
                  } else if (event.getY() > 880) {
                      if (event.getX() > 1180) {
                          drag.getLine(1180.0, 880.0, LinX, LinY);

                          drag.ChangeCoordinates(1180.0, 880.0);
                          //  if(drag.ifLine()) {
                          //  drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //   }

                      } else if (event.getX() < 20) {
                          // if(drag.ifLine()) {
                          drag.getLine(21.0, 880.0, LinX, LinY);
                          drag.ChangeCoordinates(21.0, 880.0);

                          //  drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          // }

                      } else {
                          //  if(drag.ifLine()) {
                          drag.getLine(event.getX(), 880.0, LinX, LinY);
                          drag.ChangeCoordinates(event.getX(), 880.0);

                          // drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //  }

                      }
                      return;
                  }
                  if (event.getX() < 20) {
                      if (event.getY() < 44) {
                          //  if(drag.ifLine()) {
                          drag.getLine(20.0, 49.0, LinX, LinY);
                          drag.ChangeCoordinates(20.0, 49.0);

                          //  drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          // }
                      } else if (event.getY() > 880) {
                          //   if(drag.ifLine()) {
                          drag.getLine(20.0, 880.0, LinX, LinY);
                          drag.ChangeCoordinates(20.0, 880.0);

                          //  drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //  }
                      } else {
                          //           if(drag.ifLine()) {
                          drag.getLine(20.0, event.getY(), LinX, LinY);
                          drag.ChangeCoordinates(20.0, event.getY());

                          //  drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //       }

                      }

                      return;
                  } else if (event.getX() > 1180) {
                      if (event.getY() < 44) {
                          //      if(drag.ifLine()) {
                          drag.getLine(1180.0, 49.0, LinX, LinY);
                          drag.ChangeCoordinates(1180.0, 49.0);

                          // drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //   }

                      } else if (event.getY() > 880) {
                          //    if(drag.ifLine()) {
                          drag.getLine(1180.0, 880.0, LinX, LinY);
                          drag.ChangeCoordinates(1180.0, 880.0);

                          //   drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //   }

                      } else {
                          //   if(drag.ifLine()) {
                          drag.getLine(1180.0, event.getY(), LinX, LinY);
                          drag.ChangeCoordinates(1180.0, event.getY());

                          //   drag = head.FindElement(event.getX(), event.getY());
                          LinX = drag.return_CenterX(drag);
                          LinY = drag.return_CenterY(drag);
                          //  }
                      }
                      return;

                  } else {
                      //    if(drag.ifLine()) {
                      drag.getLine(event.getX(), event.getY(), LinX, LinY);
                      drag.ChangeCoordinates(event.getX(), event.getY());

                      // drag = head.FindElement(event.getX(), event.getY());
                      LinX = drag.return_CenterX(drag);
                      LinY = drag.return_CenterY(drag);
                      //    }

                  }
              }

          });
          layout2.setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  drag = null;
              }
          });
      }
     return move;
  }

  public void Create_rand_Graph(Integer vertex) {
      Double randX, randY;
      Integer index = 0;
      while (index < vertex) {
          randX = (double) (Math.random() * 1180 + 20);
          randY = (double) (Math.random() * 831 + 49);

          circle = new Circle(randX, randY, 20);
          if (index == 0) {
              head = new Node(randX, randY, cont_node, circle);
              graph.insertNode(head);
              node2 = head;
              head.AddNode(head, null, count);


              layout2.getChildren().addAll(circle);
              this.cont_node++;
              this.count++;
              this.move = false;
              index++;
          } else {
              node = new Node(randX, randY, cont_node, circle);
              graph.insertNode(node);

              head.AddNode(node2, node, count);
              node2 = node;

              layout2.getChildren().addAll(circle);
              this.cont_node++;
              this.count++;
              this.move = false;
              index++;
          }

      }

      if(vertex != 0){
          index = 0;

          while(index < vertex){





          }


      }

  }


>>>>>>> 752ece9dd2c578e75ac118b437679475fbd24c94

  public static void main(String[] args) {
      launch(args);
  }
}
