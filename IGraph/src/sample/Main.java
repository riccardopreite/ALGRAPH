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
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;
import jdk.internal.cmm.SystemResourcePressureImpl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;


public class Main extends Application {
  Boolean edge1 = false, edge2= false, removed = false, add = false,move = false;
  Double startLineX, startLineY, endLineX, endLineY, LinX, LinY;
  Scene scene1, scene2;
  Stage window;
  BorderPane layout, layout2;
  Circle circle;
  Line line;
  Integer cont_node=0, count = 0;
  Node node;
  Node pd = null,pd2;
  Node head = null;
  Node node2;
  public Node drag;
  Graph graph = new Graph();

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

      });

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


      MenuItem exitfile = new MenuItem("Exit");
      exitfile.setOnAction(e -> {

          e.consume();
          closeProgram();
      });


      filemenu.getItems().addAll(newfile, openfile, randomfile, exitfile);

      //Edit menu
      Menu editmenu = new Menu("Edit");
      MenuItem Deletenode = new MenuItem("Delete Node");
      Deletenode.setOnAction(e -> {
          this.removed = true;

          EventHandler<MouseEvent> delete = mouseEvent -> removed = Removenode(mouseEvent, removed);
          layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, delete);
      });

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
      if (this.count == 0) {
        head = null;
        head = new Node(mouseEvent.getX(), mouseEvent.getY(), cont_node, circle);
        graph.insertNode(head);
        node2 = head;
        head.AddNode(head, null, count);


        layout2.getChildren().addAll(circle);
        this.cont_node++;
        this.count++;
        this.move = false;
      } else {
        node = new Node(mouseEvent.getX(), mouseEvent.getY(), cont_node, circle);
        graph.insertNode(node);

        head.AddNode(node2, node,count);
        node2 = node;

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
    Node delete;
    if(removed) {
      //  this.edge1 = false;
       // this.edge2 = false;
      //  this.add = false;
        if(count == 0) {
            return false;
        }

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
        delete = head.FindElement(mouseEvent.getX(), mouseEvent.getY());


        if (delete != null) {
          circle = head.CircleToDelete(delete);
          this.count = head.DeleteElement(delete, this.layout2,count);
          graph.deleteNode(delete);
          System.out.println("Element Deleted.");
          // graph.print();
        }
        head.print();
        this.move = false;

      }
      else return false;

    }
    return false;

  }

  public Node AddStart(MouseEvent mouseEvent, Node start, Boolean Edge1){
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

        start = head.FindElement(mouseEvent.getX(), mouseEvent.getY());
        this.startLineX = mouseEvent.getX();
        this.startLineY = mouseEvent.getY();

        this.edge1 = false;
        this.move = false;

        return start;


      }
      else {
            this.move = false;
            this.edge1 = false;
        }


    }
      this.move = false;
      return start;

  }

  public void AddEnd(MouseEvent mouseEvent2, Node start, Boolean Edge2) {

    Node finish = null;

    if(Edge2) {
        //this.edge1 = false;
      //  this.removed = false;
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

        this.edge2 = false;
        this.move = false;

            graph.print();
      }
      else this.edge2 = false;


    }

  }

  public Boolean MoveCircle(Boolean move){

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



  public static void main(String[] args) {
      launch(args);
  }
}
