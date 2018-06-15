package sample.MouseEvent;

import com.sun.tools.doclets.formats.html.AllClassesFrameWriter;
import sample.Node.*;
import sample.File.ManageFile;
import  sample.Edit.EditNode;
import sample.Main;
import com.sun.java.swing.action.FileMenu;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Popup;
import javafx.stage.Stage;
import jdk.internal.cmm.SystemResourcePressureImpl;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.security.krb5.internal.crypto.Des;

import javax.management.ListenerNotFoundException;
import java.awt.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.paint.Color.*;





public class Event{


 public Stage DescritionStage = new Stage();
  public Stage popup_error = new Stage();
  Label label_error = new Label();
  Scene scene_error = new Scene(label_error,300,100);
  public Integer index = 0;
  public Integer count2 = 0;

  public  Boolean  setting = false,removed = false, adding = false, FoundEdge = false,wait = true, opened = false,ToAdd = false,TOweight, Elements = false;
  Double LinX, LinY;
  Scene  scene2;
  Stage window;
  BorderPane layout;
  Circle circle;
  public New_Node edgeF,edgeS;
  public New_Node drag = null;
  public New_Node ToChange;
  public Boolean clicked, complete;
  public Integer ctrl,count = 0;
  public New_Node tmp2, selected = new New_Node();
  public String message;
  public Scene scene4;
  public VBox Descrition;
  public ContextMenu context = new ContextMenu();
  public MenuItem delete = new MenuItem("Delete node");
  public  ManageFile file;
  public EditNode edit;

  // private Desktop desktop = Desktop.getDesktop();



   public Event(){

   }
  public void SetLayout(BorderPane layout, Stage window,Scene scene){
    this.layout = layout;
    this.window = window;
    this.scene2 = scene;
  }


  public void SetManageFile(ManageFile file){
     this.file = file;
  }

  public void SetEdit(EditNode edit){
    this.edit = edit;
  }

 public void SetError(String message){
 Alert error = new Alert(Alert.AlertType.ERROR);
 error.setHeaderText(message);
 error.showAndWait();

  }

  //Drag Circle
  public void DragCircle(){

    layout.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
     //     if (file.ListNode.isEmpty())
          if (file.vertex==0)
            drag = null;
          else {
            drag = file.ListNode.getFirst().FindElement( event.getX(), event.getY(),file.ListNode);

            if (drag != null) {

              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
            }
          }
        }


      }
    });

    layout.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (drag == null || drag.get_ElementBox() == null) return;
        if (event.getY() < 44) {
          if (event.getX() > 1180) {
            drag.Change_Line(1180.0, 49.0, LinX, LinY,drag);
            drag.ChangeCoordinates(1180.0, 49.0);
            drag.move_VBox(1180.0,49.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          } else if (event.getX() < 20) {
            drag.Change_Line(21.0, 49.0, LinX, LinY,drag);

            drag.ChangeCoordinates(21.0, 49.0);
            drag.move_VBox(21.0,49.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          } else {
            drag.Change_Line(event.getX(), 49.0, LinX, LinY,drag);
            drag.ChangeCoordinates(event.getX(), 49.0);
            drag.move_VBox(event.getX(),49.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          }
        } else if (event.getY() > 880) {
          if (event.getX() > 1180) {
            drag.Change_Line(1180.0, 880.0, LinX, LinY,drag);
            drag.ChangeCoordinates(1180.0, 880.0);
            drag.move_VBox(1180.0,880.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          } else if (event.getX() < 20) {
            // if(drag.ifLine()) {
            drag.Change_Line(21.0, 880.0, LinX, LinY,drag);
            drag.ChangeCoordinates(21.0, 880.0);
            drag.move_VBox(21.0,880.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          } else {
            drag.Change_Line(event.getX(), 880.0, LinX, LinY,drag);
            drag.ChangeCoordinates(event.getX(), 880.0);
            drag.move_VBox(event.getX(),880.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;
          }
        }
        if (event.getX() < 20) {
          if (event.getY() < 44) {
            drag.Change_Line(20.0, 49.0, LinX, LinY,drag);
            drag.ChangeCoordinates(20.0, 49.0);
            drag.move_VBox(20.0,49.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;
          } else if (event.getY() > 880) {
            drag.Change_Line(20.0, 880.0, LinX, LinY,drag);
            drag.ChangeCoordinates(20.0, 880.0);
            drag.move_VBox(20.0,880.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          } else {
            drag.Change_Line(20.0, event.getY(), LinX, LinY,drag);
            drag.ChangeCoordinates(20.0, event.getY());
            drag.move_VBox(20.0,event.getY());
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          }

        } else if (event.getX() > 1180) {
          if (event.getY() < 44) {
            drag.Change_Line(1180.0, 49.0, LinX, LinY,drag);
            drag.ChangeCoordinates(1180.0, 49.0);
            drag.move_VBox(1180.0,49.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;
          } else if (event.getY() > 880) {
            drag.Change_Line(window.getMaxWidth(), window.getMaxHeight(), LinX, LinY,drag);
            drag.ChangeCoordinates(1180.0, 880.0);
            drag.move_VBox(1180.0,880.0);
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;

          } else {
            drag.Change_Line(window.getMaxWidth() - 20, event.getY(), LinX, LinY,drag);
            drag.ChangeCoordinates(1180.0, event.getY());
            drag.move_VBox(1180.0,event.getY());
            LinX = drag.getCircleX();
            LinY = drag.getCircleY();
            return;
          }

        } else {
          drag.Change_Line(event.getX(), event.getY(), LinX, LinY,drag);
          drag.ChangeCoordinates(event.getX(), event.getY());
          drag.move_VBox(event.getX(), event.getY());
          LinX = drag.getCircleX();
          LinY = drag.getCircleY();
          return;

        }
      }

    });
    layout.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        drag = null;
      }
    });
    layout.setOnMouseDragReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        return;

      }
    });

    //End Of Drag Circle

  }


  //Select Node/Menu

  public void ViewMenu(){

    EventHandler<MouseEvent> select = mouseEvent -> {
      window.setFullScreen(false);
      if (window.isMaximized()) {
        window.setMaxWidth(1200);
        window.setMinWidth(1200);
        window.setMaxHeight(900);
        window.setMinHeight(900);
      }
      count2 = 0;
      if(file.vertex > 0){
      if (file.ListNode.isEmpty()) {
        System.out.println("Head null");

      } else {
        selected = file.ListNode.getFirst().FindElement(mouseEvent.getX(), mouseEvent.getY(), file.ListNode);
        if (selected != null) {
          //    selected = file.head.FindElement(mouseEvent.getX(), mouseEvent.getY(), file.tmphead);
          circle = selected.getCircle();
          circle.setFill(BLUE);
          if (!edit.Added) {
            message = file.ListNode.getFirst().GetAllEdges(selected, file.ListNode);
            // System.out.println(message);
            if (count2 == 0) {
              Label element = new Label();
              element.setText(message);
              Descrition = new VBox(10);
              Descrition.setPadding(new Insets(10, 10, 10, 10));
              Descrition.getChildren().add(element);
              scene4 = new Scene(Descrition, 150, 250);
              DescritionStage.setX(mouseEvent.getScreenX() + 13.0);
              DescritionStage.setY(mouseEvent.getScreenY() - 8.0);
              DescritionStage.setOpacity(0.8);
              DescritionStage.setScene(scene4);
              DescritionStage.show();
            }
            count2++;
          }
        } else {
          file.ListNode.getFirst().rePrint_Circle(file.ListNode);
          if (DescritionStage != null) {
            DescritionStage.close();
          }
          if (context != null) {
            context.hide();
          }

        }
      }
    }

    };
    layout.addEventHandler(MouseEvent.MOUSE_MOVED, select);
    //End of Menu
  }

  //Add Node


  public void AddNode(){
  //  if(adding) {
      EventHandler<MouseEvent> add = mouseEventadd -> {
        if(adding){
          FoundEdge = false;
          setting = false;
        if (mouseEventadd.getButton() == MouseButton.PRIMARY) {

          removed = false;
          clicked = true;
          complete = true;

          if (mouseEventadd.getY() < 45) {
            SetError("Il nodo è troppo vicino alla barra superiore");
            return;

          } else if (mouseEventadd.getY() > 880) {
            SetError("Il nodo è troppo vicino al fondo ");
            return;

          }
          if (mouseEventadd.getX() < 20) {
            SetError("Il nodo è troppo vicino al lato sinistro");
            return;


          } else if (mouseEventadd.getX() > 1180) {
            SetError("Il nodo è troppo vicino al lato destro");
            return;


          } else {
            circle = new Circle(mouseEventadd.getX(), mouseEventadd.getY(), 20);
          }

//          if (!file.ListNode.isEmpty()) {
            if(file.vertex>0){
            if (file.ListNode.getFirst().FindElement(mouseEventadd.getX(), mouseEventadd.getY(), file.ListNode) == null) {
              file.tmpnode = new New_Node();
              file.tmpnode.setCircleX(mouseEventadd.getX());
              file.tmpnode.setCircleY(mouseEventadd.getY());
              file.tmpnode.setCircle(circle);
              file.ListNode.add(index, file.tmpnode);
              edit.AddElement(file.ListNode.get(index), false);

              file.vertex++;

            } else {
              SetError("\tNon puoi disegnare sopra un altro nodo!");
            }
          } else {
            file.tmpnode = new New_Node();
            file.tmpnode.setCircleX(mouseEventadd.getX());
            file.tmpnode.setCircleY(mouseEventadd.getY());
            file.tmpnode.setCircle(circle);
            file.ListNode.add(0, file.tmpnode);
            edit.AddElement(file.ListNode.getFirst(), false);
              file.vertex++;

            }


            }

        }
        adding = false;

      };
      layout.addEventHandler(MouseEvent.MOUSE_PRESSED, add);




  }

  //Set Element
  public void SetElement(){
    EventHandler<MouseEvent> set = mouseEventset -> {
      if(setting) {
        adding = false;
        if (mouseEventset.getButton() == MouseButton.PRIMARY) {
          if (!file.ListNode.isEmpty()) {
            if(file.ListNode.getFirst().FindElement( mouseEventset.getX(), mouseEventset.getY(),file.ListNode) != null){
              ToChange = file.ListNode.getFirst().FindElement( mouseEventset.getX(),  mouseEventset.getY(),file.ListNode);
                if (ToChange.getElement() != null) {
//                  layout.getChildren().remove(ToChange.get_ElementBox());

                  edit.AddElement(ToChange, true);
                }
              }
          }
        }
        setting = false;
      }
    };
    layout.addEventHandler(MouseEvent.MOUSE_PRESSED, set);
  }

public void DeleteNode(){

  context.getItems().addAll(delete);

  EventHandler<MouseEvent> MENU = mouseEventdelete -> {
    adding = false;
    setting = false;
    if (!file.ListNode.isEmpty() && file.vertex > 0){
      if ((selected = file.ListNode.getFirst().FindElement( mouseEventdelete.getX(), mouseEventdelete.getY(),file.ListNode)) != null) {
        if (mouseEventdelete.getButton() == MouseButton.SECONDARY) {


          delete.setOnAction(e -> {
            removed = true;
            adding = false;
            clicked = true;
            complete = false;
            New_Node next = null;
            int equal = 0;
            clicked = true;
            complete = true;

            if(file.vertex == 0) {
              file.ListNode = new LinkedList<>();
              return;
            }
            if (selected != null) {
              for (New_Node node : file.ListNode) {
                if (node.equals(selected)){
                  file.ListNode.remove(node);
                  file.vertex--;
                  break;
                }
              }
              circle = selected.getCircle();
              selected.remove_element(file.layout);
             file.layout.getChildren().remove(circle);
              System.out.println("Element Deleted.");
            }


          });
          if (DescritionStage != null) {
            DescritionStage.close();
          }

          context.show(window, mouseEventdelete.getScreenX(), mouseEventdelete.getScreenY());

        }
      }
    }
  };

  layout.addEventHandler(MouseEvent.MOUSE_CLICKED, MENU);

  //EndOF delete Node
}

//Delete Edge

public void GetEdge(Integer control){

edgeS = null;
edgeF = null;
  EventHandler<MouseEvent> edgeset = mouseEvent2 -> {
    if(FoundEdge) {
      adding = false;
      setting = false;

      System.out.println("Control " + control);
      clicked = false;
      complete = false;
      if (file.vertex < 2) {
        SetError("Ci sono meno di due nodi, non è possibile creare un arco!");
        return;
      }
      if (edgeS != null) {
        clicked = true;
      }
       else if ((mouseEvent2.getButton() == MouseButton.PRIMARY) && clicked == false) {
        if(edgeS == null)
        edgeS = file.ListNode.getFirst().FindElement( mouseEvent2.getX(), mouseEvent2.getY(),file.ListNode);

      }

     if (clicked) {
        if (mouseEvent2.getButton() == MouseButton.PRIMARY) {
          edgeF = file.ListNode.getFirst().FindElement( mouseEvent2.getX(), mouseEvent2.getY(),file.ListNode);

          if (edgeF.equals(edgeS)) {
            edgeF = file.ListNode.getFirst().FindElement( mouseEvent2.getX(), mouseEvent2.getY(),file.ListNode);


          } else if (!edgeF.equals(null)) {
            System.out.println("END TROVATO " + edgeF);
            System.out.println("edgeF " + this.edgeF);
            System.out.println("ciao");
            complete = true;
          }
          if (complete) {
            if(this.ctrl != null){
            if (this.ctrl == 0) {
              System.out.println("Added");
              //Call add edge passo coppia di nodi
              edit.Add_Edge(edgeS, edgeF);
            } else if (this.ctrl == 1) {
              //call delete edge passo coppia di nodi
              System.out.println("Delete");
              edit.Delete_Edge(edgeS, edgeF);

            } else if (this.ctrl == 2) {
              //call change weight passo coppia di nodi
              System.out.println("Change");
              edit.Change_Weight(edgeS, edgeF);

            }
          }
            ctrl = null;
            FoundEdge = false;
            clicked = true;
            complete = false;

          }
        }

      }
    }
  };
  layout.addEventHandler(MouseEvent.MOUSE_CLICKED, edgeset);
}

public void SetControl(Integer count){
     this.ctrl = count;
     edgeS = null;
     edgeF = null;
}


}//ENDOFCLASS
