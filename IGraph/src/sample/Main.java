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
  Boolean edge = false, removed = false, add = false,move = false, delete_edge = false;
  Double startLineX, startLineY, endLineX, endLineY, LinX, LinY;
  Scene scene1, scene2;
  Stage window, window2;
  BorderPane layout, layout2;
  Circle circle;
  Line line;
  Integer  count = 0;
  Node node;
  New_Node pd = null;
  Node head = null;
  public New_Node tmphead = null,tmpnode = null;
  public New_Node edgeF,edgeS,edgeFN,edgeSN;
  public New_Node drag;
  public Boolean clicked, complete,clicked2, complete2;
  public Integer vertex = 0;
  public Boolean answer = false;
  public New_Node tmp2;

  File file;



  Graph graph = new Graph();
  TextField nameinput;
  TextField priceinput;
  TextField quantityinput;
  TableView table;
  Stage secondStage,InputStage;
  VBox vbox;
 // private Desktop desktop = Desktop.getDesktop();


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
      if(count > 0){
        layout2 = head.clearLayout(layout2,tmphead);

      }
      if(secondStage != null) {
        secondStage.close();
      }
      set_View();
      count = 0;
      line = null;
      edge = false;
      removed = false;
      add = false;
      move = false;
      head = null;
      graph = new Graph();

      window.setX(600);
      window.setY(50);
      window.setScene(scene2);

    });

    MenuItem openfile = new MenuItem("Open");
    openfile.setOnAction(e -> {
      if(count > 0){
        layout2 = head.clearLayout(layout2,tmphead);

      }
      if(secondStage != null) {
        secondStage.close();
      }
      set_View();
      count = 0;
      line = null;
      edge = false;
      removed = false;
      add = false;
      move = false;
      head = null;
      graph = new Graph();

      Openfile();
      window.setX(600);
      window.setY(50);
      window.setScene(scene2);

    });


    MenuItem randomfile = new MenuItem("Generate random");
    randomfile.setOnAction(e -> {
      if(count > 0){
        layout2 = head.clearLayout(layout2,tmphead);

      }
      if(secondStage != null) {
        secondStage.close();
      }

      count = 0;
      line = null;
      edge = false;
      removed = false;
      add = false;
      move = false;
      head = null;
      graph = new Graph();
      vertex = vertex();

      window.setX(600);
      window.setY(50);
    });


    MenuItem exitfile = new MenuItem("Exit");
    exitfile.setOnAction(e -> {

      e.consume();
      closeProgram();
    });

    EventHandler<MouseEvent> select = mouseEvent -> {
      New_Node selected;
      if(head != null) {
        if ((selected = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead)) != null) {

          circle = selected.getCircle();
          circle.setFill(BLUE);

        }
        else{
          head.rePrint_Circle(tmphead);
        }
      }

    };
    layout2.addEventHandler(MouseEvent.MOUSE_MOVED, select);

    filemenu.getItems().addAll(newfile, openfile, randomfile, exitfile);

    //Edit menu
    Menu editmenu = new Menu("Edit");
    MenuItem Deletenode = new MenuItem("Delete Node");
    Deletenode.setOnAction(e -> {
      removed = true;
      add = false;
      edge = false;
      move = false;
      delete_edge = false;
      clicked = true;
      complete = false;
      clicked2 = true;
      complete2 = false;

      EventHandler<MouseEvent> delete = mouseEvent -> removed = Removenode(mouseEvent, removed);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, delete);

    });

    MenuItem Addnode = new MenuItem("Add Node");
    Addnode.setOnAction(e -> {
      add = true;
      edge = false;
      removed = false;
      move = false;
      delete_edge = false;
      clicked = true;
      complete = false;
      clicked2 = true;
      complete2 = false;
      EventHandler<MouseEvent> AddNode = mouseEvent -> this.add = addCircle(mouseEvent, add);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, AddNode);


    });

    MenuItem Add_Edge = new MenuItem("Add Edge");
    Add_Edge.setOnAction(e -> {
      this.edge = true;
      clicked2 = false;
      complete2 = false;
      EventHandler<MouseEvent> open = mouseEvent -> {
        if (count < 2) {
          System.out.println("poecodio minore di due finish");
          return ;
        }
        else if ((mouseEvent.getButton() == MouseButton.PRIMARY) && clicked2 == false) {
/*
                  if(!clicked){
                   this.edgeS = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                   if(edgeS != null){
                     System.out.println("edgeS " + this.edgeS);


                  }
                  }
                  else{
                   this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                    System.out.println("edgeF " + this.edgeF);

                    if(edgeF == edgeS){
                     this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);

                   }
                   if(edgeF != null){
                     System.out.println("edgeF " + this.edgeF);

                     complete = true;
                  }
                  }
                  if(complete){
                  delete_edge = Delete_edge(delete_edge);
                  complete = false;
                  clicked = false;


                  }

*/

          this.edgeSN = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
          if(edgeSN != null) {
            clicked2 = true;
          }


        }

        if (clicked2) {
          if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            this.edgeFN = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
            if (edgeFN == edgeSN) {
              this.edgeFN = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);

            } else if (edgeFN != null) {
              //  this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
              System.out.println("edgeF " + this.edgeFN);
              System.out.println("ciao");
              complete2 = true;


            }

          }
        }
        if (complete2) {
          clicked2 = false;
          edge = AddEdge(edge);
          complete2 = false;

        }
        //


      };
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, open);
    });
    /*
    MenuItem EndEdge = new MenuItem("Choose End Node");
    EndEdge.setOnAction(e -> {
      this.edge2 = true;
      EventHandler<MouseEvent> end = mouseEvent2 -> AddEnd(mouseEvent2, pd, this.edge2);
      layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, end);

    });
*/
    MenuItem MoveNode = new MenuItem("Move The Node");
    MoveNode.setOnAction(e -> {
      move = true;
      move = MoveCircle(move);

    });

    MenuItem DeleteEdge = new MenuItem("Delete Edge");
    DeleteEdge.setOnAction(e -> {
              delete_edge = true;
              clicked = false;
              complete = false;
              EventHandler<MouseEvent> setfirst = mouseEvent -> {
                if (count < 2) {
                  System.out.println("poecodio minore di due finish");
                  return ;
                }
                else if ((mouseEvent.getButton() == MouseButton.PRIMARY) && clicked == false) {
/*
                  if(!clicked){
                   this.edgeS = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                   if(edgeS != null){
                     System.out.println("edgeS " + this.edgeS);


                  }
                  }
                  else{
                   this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                    System.out.println("edgeF " + this.edgeF);

                    if(edgeF == edgeS){
                     this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);

                   }
                   if(edgeF != null){
                     System.out.println("edgeF " + this.edgeF);

                     complete = true;
                  }
                  }
                  if(complete){
                  delete_edge = Delete_edge(delete_edge);
                  complete = false;
                  clicked = false;


                  }

*/


                  this.edgeS = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                  if(edgeS != null) {
                    clicked = true;
                  }


                }

                if (clicked) {
                  if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                    if(edgeF == edgeS){
                      this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);

                    }
                    else if (edgeF != null) {
                    //  this.edgeF = head.FindElement(mouseEvent.getX(), mouseEvent.getY(), tmphead);
                      System.out.println("edgeF " + this.edgeF);
                      System.out.println("ciao");
                      complete = true;



                    }
                    if (complete){
                      clicked = false;
                      delete_edge = Delete_edge(delete_edge);
                      complete = false;

                    }
                  }

                }
                //


              };
              layout2.addEventHandler(MouseEvent.MOUSE_PRESSED, setfirst);
            });


    editmenu.getItems().addAll(Addnode, Add_Edge, Deletenode,MoveNode,DeleteEdge);


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
    window.setX(600);
    window.setY(50);
  }

  private void openFile(File file) {
    URL path = null;

    try {
//      desktop.open(file);

      path = file.toURL();
      InputStream is = path.openStream();
      BufferedReader br=new BufferedReader(new InputStreamReader(is));
      int i;
      String line_file;
    //  do
   //  {
        line_file = "";
      do{
        line_file = br.readLine();
        if(line_file == "Vertex:"){

          //Aggiungi vertex
          System.out.println("Vertici aggiunti");
        }
        System.out.println(line_file);

        i = br.read();

      }
      while(i != -1);

   //   }
   //   while (i!=-1);
      /*
      do
      {
        i=br.read();
        System.out.print((char)i);
      }
      while (i!=-1);
      System.out.println("\n");
      is.close();
*/
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
            new FileChooser.ExtensionFilter("Txt", "*.txt")
    );
    file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      openFile(file);
    }

  }

  private void closeProgram() {
     answer = ConfirmBox.display("Fuck God!!!", "Are you sure you want to exit?");

    if (answer) {
      if(secondStage != null) {
        secondStage.close();
      }

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
      edge = false;
      removed = false;
      move = false;
      delete_edge = false;
      clicked = true;
      complete = true;

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
       tmp2 = new New_Node();
      TextField InputVertex = new TextField();
      ToggleGroup group = new ToggleGroup();

      RadioButton box1 = new RadioButton("Integer");
      RadioButton box2 = new RadioButton("Double");
      RadioButton box3 = new RadioButton("String");
      box1.setToggleGroup(group);
      box2.setToggleGroup(group);
      box3.setToggleGroup(group);

      box1.setSelected(true);

      Button button = new Button("Choose");


      VBox newInput = new VBox(10);

      newInput.setPadding(new Insets(10,10,10,10));
      newInput.getChildren().addAll(InputVertex, button, box1, box2, box3);

      Scene scene3 = new Scene(newInput, 300, 250);
      InputStage = new Stage();
      InputStage.setScene(scene3);
      InputStage.show();
      if (this.count == 0) {

        tmphead = new New_Node();

        button.setOnAction(e -> {
          if (box1.isSelected()) {
            try {
              int vertex = Integer.parseInt(InputVertex.getText());
              tmphead.setElement(vertex);

              InputStage.close();




            } catch (NumberFormatException t) {
              System.out.println("Error: " + InputVertex.getText() + " is a String");

            }
          } else if (box2.isSelected()) {
            try {
              Double vertex = Double.parseDouble(InputVertex.getText());
              tmphead.setElement(vertex);

              InputStage.close();

            } catch (NumberFormatException t) {
              System.out.println("Error: " + InputVertex.getText() + " is a String");

            }
          } else if (box3.isSelected()) {
            tmphead.setElement(InputVertex.getText());
            InputStage.close();


          }
          head = null;
          head = new Node();
          System.out.println("DENTRO");
          tmphead.setCircleX(mouseEvent.getX());
          tmphead.setCircleY(mouseEvent.getY());
          tmphead.setCircle(circle);
          graph.insertNode(tmphead);

          head.AddNode(tmphead, null, count, tmphead);

          tmp2 = tmphead;


          layout2.getChildren().addAll(circle);
          this.count++;
          this.move = false;
        });
        }
       else {
        node = new Node();
        tmpnode = new New_Node();

      button.setOnAction(e -> {
        if (box1.isSelected()) {
          try {
            int vertex = Integer.parseInt(InputVertex.getText());
            tmpnode.setElement(vertex);

            InputStage.close();


          } catch (NumberFormatException t) {
            System.out.println("Error: " + InputVertex.getText() + " is a String");

          }
        } else if (box2.isSelected()) {
          try {
            Double vertex = Double.parseDouble(InputVertex.getText());
            tmpnode.setElement(vertex);

            InputStage.close();

          } catch (NumberFormatException t) {
            System.out.println("Error: " + InputVertex.getText() + " is a String");

          }
        } else if (box3.isSelected()) {
          tmpnode.setElement(InputVertex.getText());
          InputStage.close();


        }
        tmpnode.setCircleX(mouseEvent.getX());
        tmpnode.setCircleY(mouseEvent.getY());
        tmpnode.setCircle(circle);

        graph.insertNode(tmpnode);

        head.AddNode(tmp2, tmpnode, count, tmphead);
        tmp2 = tmpnode;

        layout2.getChildren().addAll(circle);
        this.count++;
        this.move = false;
      });
        }


      }

    return false;
  }


  public Boolean Removenode(MouseEvent mouseEvent, Boolean removed){
    New_Node delete,next = null;

    int equal = 0;
    if(removed) {
      edge = false;
      add = false;
      move = false;
      delete_edge = false;
      clicked = true;
      complete = true;

      if(count == 0) {
        return false;
      }

      if (mouseEvent.getButton() == MouseButton.PRIMARY) {
        delete = head.FindElement(mouseEvent.getX(), mouseEvent.getY(),tmphead);


        if (delete != null) {
          circle = head.CircleToDelete(delete);
          if(delete == tmphead){
            next = tmphead.getNext();
             equal = 1;


          }
          this.count = head.DeleteElement(delete, this.layout2,count,this.tmphead);
          if (this.count == 0){
            head = null;
          }
          else if(equal == 1){
            tmphead = next;
          }
          /*
          if (delete == tmphead){
            head = null;
          */
          graph.deleteNode(delete);
          System.out.println("Element Deleted.");
          graph.print();
        }
        if(head != null) {
          head.print(tmphead);
        }
        this.move = false;

      }
      else return false;

    }
    return false;

  }

  public Boolean AddEdge(Boolean edge) {


    if(edge) {
      removed = false;
      add = false;
      move = false;
      delete_edge = false;
      clicked = true;
      complete = true;
      if(count < 2) {
        System.out.println("poecodio minore di due finish");
        this.move = false;
        return false;
      }

        line = new Line();
        line.setStartX(this.edgeSN.getCircleX());
        line.setStartY(this.edgeSN.getCircleY());
        line.setEndX(this.edgeFN.getCircleX());
        line.setEndY(this.edgeFN.getCircleY());

        //    System.out.println("Startx: " + this.startLineX + " Starty: " + this.startLineY + " Endx: " + endLineX + " Endy: " + endLineY  );
        edgeSN.setLine(line);
        edgeFN.setLine(line);
        layout2.getChildren().addAll(line);

        graph.insertEdge(edgeSN, edgeFN);
        //   this.pd = null;

        this.edge = false;
        this.move = false;

        graph.print();
      }
      else this.edge = false;

return false;
    }
  
  public Boolean Delete_edge(Boolean delete_edge) {

    if (delete_edge) {
      clicked = true;
      complete = true;

      System.out.println("Diversi");
      System.out.println("EDGES: " + this.edgeS + " EDGEF " + this.edgeF);
          tmphead.Remove_line(this.edgeS, this.edgeF, layout2);




        return false;

      }



    return false;


  }

  public Boolean MoveCircle(Boolean move){

    if(move) {
      edge = false;
      removed = false;
      add = false;
      delete_edge = false;
      clicked = true;
      complete = true;
      this.move = false;
      layout2.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          New_Node delete = null;
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
              drag.Change_Line(1180.0, 49.0, LinX, LinY);
              drag.ChangeCoordinates(1180.0, 49.0);
              //  if(drag.ifLine()) {
              // drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }

            } else if (event.getX() < 20) {
              drag.Change_Line(21.0, 49.0, LinX, LinY);

              drag.ChangeCoordinates(21.0, 49.0);
              //   if(drag.ifLine()) {
              //   drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }

            } else {
              drag.Change_Line(event.getX(), 49.0, LinX, LinY);

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
              drag.Change_Line(1180.0, 880.0, LinX, LinY);

              drag.ChangeCoordinates(1180.0, 880.0);
              //  if(drag.ifLine()) {
              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //   }

            } else if (event.getX() < 20) {
              // if(drag.ifLine()) {
              drag.Change_Line(21.0, 880.0, LinX, LinY);
              drag.ChangeCoordinates(21.0, 880.0);

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              // }

            } else {
              //  if(drag.ifLine()) {
              drag.Change_Line(event.getX(), 880.0, LinX, LinY);
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
              drag.Change_Line(20.0, 49.0, LinX, LinY);
              drag.ChangeCoordinates(20.0, 49.0);

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              // }
            } else if (event.getY() > 880) {
              //   if(drag.ifLine()) {
              drag.Change_Line(20.0, 880.0, LinX, LinY);
              drag.ChangeCoordinates(20.0, 880.0);

              //  drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }
            } else {
              //           if(drag.ifLine()) {
              drag.Change_Line(20.0, event.getY(), LinX, LinY);
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
              drag.Change_Line(1180.0, 49.0, LinX, LinY);
              drag.ChangeCoordinates(1180.0, 49.0);

              // drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //   }

            } else if (event.getY() > 880) {
              //    if(drag.ifLine()) {
              drag.Change_Line(1180.0, 880.0, LinX, LinY);
              drag.ChangeCoordinates(1180.0, 880.0);

              //   drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //   }

            } else {
              //   if(drag.ifLine()) {
              drag.Change_Line(1180.0, event.getY(), LinX, LinY);
              drag.ChangeCoordinates(1180.0, event.getY());

              //   drag = head.FindElement(event.getX(), event.getY());
              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              //  }
            }
            return;

          } else {
            //    if(drag.ifLine()) {
            drag.Change_Line(event.getX(), event.getY(), LinX, LinY);
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

  public Integer vertex(){
    TextField InputVertex = new TextField();

    Button button = new Button("OK");

    button.setOnAction(e -> {
      if(isInt(InputVertex, InputVertex.getText())){
        vertex = Integer.parseInt(InputVertex.getText());
        System.out.println(vertex);
        if(vertex <= 20){
          set_View();
          Create_rand_Graph(vertex, table);
          window.setScene(scene2);

        }
      }
    });




    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputVertex, button);

    Scene scene3 = new Scene(newInput, 300, 250);
    InputStage = new Stage();
    InputStage.setScene(scene3);
    InputStage.show();
return vertex;
  }
  public Boolean Set_element(New_Node tmp){
    TextField InputVertex = new TextField();
    ToggleGroup group = new ToggleGroup();

    RadioButton box1 = new RadioButton("Integer");
    RadioButton box2 = new RadioButton("Double");
    RadioButton box3 = new RadioButton("String");
    box1.setToggleGroup(group);
    box2.setToggleGroup(group);
    box3.setToggleGroup(group);

    box1.setSelected(true);






    Button button = new Button("Choose");

    button.setOnAction(e -> {
      if (box1.isSelected()) {
        try {
          int vertex = Integer.parseInt(InputVertex.getText());
          if (tmp == tmphead) {
            tmphead.setElement(vertex);
            System.out.println(tmphead.getElement());


          }
          else{
            tmpnode.setElement(vertex);
            System.out.println(tmpnode.getElement());

          }

          InputStage.close();




        } catch (NumberFormatException t) {
          System.out.println("Error: " + InputVertex.getText() + " is a String");

        }
      } else if (box2.isSelected()) {
        try {
          Double vertex = Double.parseDouble(InputVertex.getText());
          if (tmp == tmphead) {
            tmphead.setElement(vertex);
            System.out.println(tmphead.getElement());


          }
          else{
            tmpnode.setElement(vertex);
            System.out.println(tmpnode.getElement());

          }
          InputStage.close();
          System.out.println(tmp.getElement());





        } catch (NumberFormatException t) {
          System.out.println("Error: " + InputVertex.getText() + " is a String");

        }
      } else if (box3.isSelected()) {
        if (tmp == tmphead) {
          tmphead.setElement(InputVertex.getText());
          System.out.println(tmphead.getElement());


        }
        else{
          tmpnode.setElement(InputVertex.getText());
          System.out.println(tmpnode.getElement());

        }
        InputStage.close();
        System.out.println(tmp.getElement());


      }

    }
    );

    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputVertex, button, box1, box2, box3);

    Scene scene3 = new Scene(newInput, 300, 250);
    InputStage = new Stage();
    InputStage.setScene(scene3);
    InputStage.show();

    return false;
  }

  public void Create_rand_Graph(Integer vertex, TableView table) {

    Double randX, randY;
    Integer index = 0;

    if(vertex != 0){
      System.out.println("coap");
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


          ObservableList<New_Node> nodes = FXCollections.observableArrayList();
          nodes.add(new New_Node("Dell", 1049.00, 30.0));

           nodes.add(new New_Node("A",10.0,10.0));
           nodes.add(new New_Node("B",randX,randY));

             tmphead.setElement("A");
           //  tmphead.setCircleX(Double.parseDouble(priceinput.getText()));
           //  tmphead.setCircleY(Double.parseDouble(quantityinput.getText()));

           // table.setItems(nodes);
          table.setItems(nodes);

          head.AddNode(tmphead, null, count, tmphead);

          tmp2 = tmphead;


          layout2.getChildren().addAll(circle);
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



    }


  }
  public void set_View() {
    TableColumn<New_Node, String> namecolumn = new TableColumn<>("Name");
    namecolumn.setMinWidth(100);
    namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    //Price column
    TableColumn<New_Node, Double> pricecolumn = new TableColumn<>("Price");
    pricecolumn.setMinWidth(100);
    pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    //Quantity column
    TableColumn<New_Node, Double> quantitycolumn = new TableColumn<>("Quantity");
    quantitycolumn.setMinWidth(100);
    quantitycolumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    Button addbutton = new Button("Add");
    addbutton.setOnAction(e -> {
      this.add = true;
      tmpnode = new New_Node();
      tmpnode.setElement(nameinput.getText());
      tmpnode.setCircleX(Double.parseDouble(priceinput.getText()));
      tmpnode.setCircleY(Double.parseDouble(quantityinput.getText()));
      circle = new Circle(Double.parseDouble(priceinput.getText()), Double.parseDouble(quantityinput.getText()), 20);
      layout2.getChildren().add(circle);

      table.getItems().add(tmpnode);
      nameinput.clear();
      priceinput.clear();
      quantityinput.clear();


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
    Scene scene4 = new Scene(vbox, 500, 400);
    secondStage = new Stage();
    secondStage.setScene(scene4);
    secondStage.setX(50);
    secondStage.setY(50);
    secondStage.show();


  }

  private boolean isInt(TextField input, String message){
    try{
      int InputVertex = Integer.parseInt(input.getText());
      if(InputVertex <= 20 ) {
        InputStage.close();
        return true;
      }
      else return false;
    }catch(NumberFormatException e){
      System.out.println("Error: " + message + " is not a number");
      return false;
    }
  }
  public static void main(String[] args) {
    launch(args);
  }
}
