package sample.MouseEvent;

import javafx.scene.input.*;
import sample.Node.*;
import sample.File.ManageFile;
import  sample.Edit.EditNode;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.LinkedList;
import static javafx.scene.paint.Color.*;

public class Event{

  private Stage DescritionStage = new Stage();
  private VBox Descrition = new VBox(10);
  private  Scene scene4 = new Scene(Descrition, 150, 250),scene;
  private Integer index = 0;
  private Integer count2 = 0;
  private  Boolean  setting = false,removed = false, adding = false, FoundEdge = false,addmessage = true;
  private Double LinX, LinY,oldX,oldY;
  private Stage window;
  private BorderPane layout;
  private Circle circle;
  private New_Node edgeF,edgeS;
  private New_Node drag = null;
  private New_Node ToChange;
  private Boolean clicked, complete;
  private Integer ctrl;
  private New_Node tmpnode, selected = new New_Node();
  private String message;
  private MenuItem save,open,newfile,help,random,kruskal;
  private ContextMenu context = new ContextMenu();
  private MenuItem delete = new MenuItem("Eliminia nodo");
  private  ManageFile file;
  private EditNode edit;

  // private Desktop desktop = Desktop.getDesktop();



  public Event(){

  }
  public void SetLayout(BorderPane layout, Stage window,Scene scene,MenuItem save,MenuItem open, MenuItem newfile,MenuItem help,MenuItem random,MenuItem kruskal){
    this.layout = layout;
    this.window = window;
    this.scene = scene;
    this.save = save;
    this.open = open;
    this.newfile = newfile;
    this.help = help;
    this.random = random;
    this.kruskal = kruskal;
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
          //     if (file.getListNode().isEmpty())
          if (file.getVertex()==0)
          drag = null;
          else {
            drag = file.getListNode().getFirst().FindElement( event.getX(), event.getY(),file.getListNode());

            if (drag != null) {

              LinX = drag.getCircleX();
              LinY = drag.getCircleY();
              oldX = LinX;
              oldY = LinY;

            }
          }
        }


      }
    });

    layout.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (drag == null || drag.get_ElementBox() == null) return;
        Integer index = 0;
        while(index < file.getListNode().size()){
          if(!file.getListNode().get(index).equals(drag)){
            if(drag.exist(event.getX(),event.getY(),file.getListNode().get(index))){

              SetError("Error!\nStai cercando di posizionare un nodo sopra un altro nodo! Verra' riportato nella sua vecchia posizione");
              drag.Change_Line(oldX, oldY, LinX, LinY,drag);
              drag.ChangeCoordinates(oldX, oldY);
              drag.move_VBox(oldX,oldY);
              return;
            }
          }
          index++;
        }
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
      if(file.getVertex() > 0){
        if (file.getListNode().isEmpty()) {

        } else {
          selected = file.getListNode().getFirst().FindElement(mouseEvent.getX(), mouseEvent.getY(), file.getListNode());
          if (selected != null) {
            circle = selected.getCircle();
            circle.setFill(BLUE);
            if (!edit.getAdded()) {

              if (count2 == 0) {

                DescritionStage.setX(mouseEvent.getScreenX() + 13.0);
                DescritionStage.setY(mouseEvent.getScreenY() - 8.0);
                DescritionStage.setOpacity(0.8);
                if(addmessage) {
                  message = file.getListNode().getFirst().GetAllEdges(selected, file.getListNode());
                  addmessage = false;
                  Label element = new Label();
                  element.setText(message);
                  Descrition.setPadding(new Insets(10, 10, 10, 10));
                  Descrition.getChildren().add(element);
                  scene4.setRoot(Descrition);

                  //  scene4 = new Scene(Descrition, 150, 250);
                  DescritionStage.setScene(scene4);
                  DescritionStage.setResizable(false);
                  DescritionStage.show();
                }

              }
              count2++;
            }
          } else {
            file.getListNode().getFirst().rePrint_Circle(file.getListNode());
            addmessage = true;
            Descrition = new VBox(10);

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
            SetError("Il nodo e' troppo vicino alla barra superiore");
            return;

          } else if (mouseEventadd.getY() > 880) {
            SetError("Il nodo e' troppo vicino al fondo ");
            return;

          }
          if (mouseEventadd.getX() < 20) {
            SetError("Il nodo e' troppo vicino al lato sinistro");
            return;


          } else if (mouseEventadd.getX() > 1180) {
            SetError("Il nodo e' troppo vicino al lato destro");
            return;


          } else {
            circle = new Circle(mouseEventadd.getX(), mouseEventadd.getY(), 20);
          }

          //          if (!file.getListNode().isEmpty()) {
          if(file.getVertex()>0){
            if (file.getListNode().getFirst().FindElement(mouseEventadd.getX(), mouseEventadd.getY(), file.getListNode()) == null) {
              tmpnode = new New_Node();
              tmpnode.setCircleX(mouseEventadd.getX());
              tmpnode.setCircleY(mouseEventadd.getY());
              tmpnode.setCircle(circle);
              file.getListNode().add(index, tmpnode);
              edit.AddElement(file.getListNode().get(index), false);

              file.setVertex(file.getVertex() + 1);

            } else {
              SetError("\tNon puoi disegnare sopra un altro nodo!");
            }
          } else {
            tmpnode = new New_Node();
            tmpnode.setCircleX(mouseEventadd.getX());
            tmpnode.setCircleY(mouseEventadd.getY());
            tmpnode.setCircle(circle);
            file.getListNode().add(0, tmpnode);
            edit.AddElement(file.getListNode().getFirst(), false);
            file.setVertex(file.getVertex() +1);

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
          if (!file.getListNode().isEmpty()) {
            if(file.getListNode().getFirst().FindElement( mouseEventset.getX(), mouseEventset.getY(),file.getListNode()) != null){
              ToChange = file.getListNode().getFirst().FindElement( mouseEventset.getX(),  mouseEventset.getY(),file.getListNode());
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
      if (!file.getListNode().isEmpty() && file.getVertex() > 0){
        if ((selected = file.getListNode().getFirst().FindElement( mouseEventdelete.getX(), mouseEventdelete.getY(),file.getListNode())) != null) {
          if (mouseEventdelete.getButton() == MouseButton.SECONDARY) {


            delete.setOnAction(e -> {
              removed = true;
              adding = false;
              clicked = true;
              complete = false;
              New_Node next = null;
              int equal = 0;
              int index = 0;
              clicked = true;
              complete = true;

              if(file.getVertex() == 0) {
                file.setListNode(new LinkedList<>());
                return;
              }
              if (selected != null) {
                selected.remove_element(file.getLayout());
                file.setKruskal_list(file.deleteEdge(selected));

                file.setListNode(file.deleteElement(selected));
                file.setVertex(file.getVertex() -1);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Elemento Eliminato.");
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

        clicked = false;
        complete = false;
        if (file.getVertex() < 2) {
          SetError("Ci sono meno di due nodi, non e' possibile creare un arco!");
          return;
        }
        if (edgeS != null) {
          clicked = true;
        }
        else if ((mouseEvent2.getButton() == MouseButton.PRIMARY) && clicked == false) {
          if(edgeS == null)
          edgeS = file.getListNode().getFirst().FindElement( mouseEvent2.getX(), mouseEvent2.getY(),file.getListNode());

        }

        if (clicked) {
          if (mouseEvent2.getButton() == MouseButton.PRIMARY) {
            edgeF = file.getListNode().getFirst().FindElement( mouseEvent2.getX(), mouseEvent2.getY(),file.getListNode());

            if (edgeF.equals(edgeS)) {
              edgeF = file.getListNode().getFirst().FindElement( mouseEvent2.getX(), mouseEvent2.getY(),file.getListNode());


            } else if (!edgeF.equals(null)) {
              complete = true;
            }
            if (complete) {
              if(this.ctrl != null){
                if (this.ctrl == 0) {
                  //Call add edge passo coppia di nodi
                  edit.Add_Edge(edgeS, edgeF);
                } else if (this.ctrl == 1) {
                  //call delete edge passo coppia di nodi

                  edit.Delete_Edge(edgeS, edgeF);

                } else if (this.ctrl == 2) {
                  //call change weight passo coppia di nodi
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


  public Boolean getSetting() {
    return setting;
  }

  public void setSetting(Boolean setting) {
    this.setting = setting;
  }

  public Boolean getAdding() {
    return adding;
  }

  public void setAdding(Boolean adding) {
    this.adding = adding;
  }

  public Boolean getFoundEdge() {
    return FoundEdge;
  }

  public void setFoundEdge(Boolean foundEdge) {
    FoundEdge = foundEdge;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Stage getDescritionStage() {
    return DescritionStage;
  }

  public void setDescritionStage(Stage descritionStage) {
    DescritionStage = descritionStage;
  }

  public void SetCombinationKey(){
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.G, KeyCombination.META_ANY), () -> help.fire());


  }
  public void SetCombinationKey(Scene scene){
    this.scene = scene;
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.META_ANY), () ->save.fire());
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCombination.META_ANY), () -> newfile.fire());
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCombination.META_ANY), () -> open.fire());
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.G, KeyCombination.META_ANY), () -> help.fire());
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.META_ANY), () -> random.fire());
    scene.getAccelerators().put(new KeyCodeCombination(KeyCode.K, KeyCombination.META_ANY), () -> kruskal.fire());
  }
}

//ENDOFCLASS
