package Edit;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import File.ManageFile;
import Node.Edges;
import Node.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.LinkedList;

public class EditNode{
  private Stage InputStage = new Stage();
  private  Boolean isnot = true,delete = false;
  private BorderPane layout;
  private Line line;
  private Integer  count2 = 0, ind = 0,weight;
  private Boolean  Added = false;
  private Line tmpline;
  private ManageFile file;



  Edges edge_1;
  // private Desktop desktop = Desktop.getDesktop();


  public EditNode(){

  }

  public void SetLayout(BorderPane layout,Stage window,Scene scene){
    this.layout = layout;
  }

  public void SetManageFile(ManageFile file){
    this.file = file;
  }


  //Add Element

  public void AddElement(New_Node tmp,Boolean adding){
    TextField InputElement = new TextField();
    InputElement.setText("Inserisci l'elemento:");

    Button Choose = new Button("Choose");
    Button Cancel = new Button("Cancel");


    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputElement, Choose, Cancel);

    Scene scene3 = new Scene(newInput, 300, 250);
    file.getInputStage().setTitle("Inserisci nodo");

    if( file.getInputStage() != null){
      file.getInputStage().close();
    }



    file.getInputStage().setScene(scene3);
    file.getInputStage().setOnCloseRequest(e ->{
      if(!adding){
        for (New_Node node : file.getListNode()) {
          if(node.equals(tmp)){
            //tmp2 = tmp;
            delete = true;
            break;
          }
        }

        if(delete) {
          file.getListNode().remove(tmp);
        }
        file.getEvent().setIndex(file.getListNode().size());
        file.setVertex(file.getVertex() - 1);
        if(file.getVertex()<0){
          file.setVertex(0);
        }
      }
      Added = false;
    });

    Added = true;
    file.getInputStage().show();
    scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
          Choose.fire();
        }
      }
    });
    scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
          Cancel.fire();
        }
      }

    });
    // Choose.fire();
    Choose.setOnAction(e -> {

      for (New_Node node : file.getListNode()) {


        if (node.getElement().equals(InputElement.getText())) {
          file.getEvent().SetError("Questo elemento esiste gia'!");
          isnot = false;
          break;
        }
        isnot = true;

      }
      if(isnot && !InputElement.getText().isEmpty()){
        try{
          layout.getChildren().remove(tmp.get_ElementBox());
        }
        catch (Exception t){}
          tmp.setElement(InputElement.getText());
          tmp.set_ElementBox();
          if (!adding) {
            file.getEvent().setIndex(file.getEvent().getIndex() + 1 );
            layout.getChildren().add(tmp.getCircle());

          }

          layout.getChildren().add(tmp.get_ElementBox());

          Added = false;
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
          file.getInputStage().close();
        }
      });
      Cancel.setOnAction(e -> {
        if(!adding){
          for (New_Node node : file.getListNode()) {
            if(node.equals(tmp)){
              file.getListNode().remove(node);
            }

          }
          file.getEvent().setIndex(file.getListNode().size());
          file.setVertex(file.getVertex() - 1);
          if(file.getVertex()<0){
            file.setVertex(0);
          }

        }
        Added = false;
        file.getInputStage().close();

      });
      count2 = 1;

    }


    //Add Edge

    public void Add_Edge(New_Node edgeSN, New_Node edgeFN){

      TextField InputWeight = new TextField();
      if(file.getVertex() < 2) {
        file.getEvent().SetError("Ci sono meno di due nodi, non e' possibile creare un arco!");
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
        return;
      }
      if(!file.getListNode().getFirst().ifLine(edgeSN,edgeFN))
      {
        file.getEvent().SetError("Esiste gia' un arco fra " + edgeSN.getElement() + "<->" +edgeFN.getElement() + ". \nNon e' possibile crearne un altro uguale! ");
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
        return;
      }
      InputWeight.setText("Inserisci il peso:");

      Button Add = new Button("Inserisci");
      Button Cancel = new Button("Annulla");
      VBox newInput = new VBox(10);
      newInput.setPadding(new Insets(10,10,10,10));
      newInput.getChildren().addAll(InputWeight, Add, Cancel);
      Scene scene3 = new Scene(newInput, 300, 250);
      file.getInputStage().setTitle("Aggiungi arco");
      count2 = 1;
      if( file.getInputStage() != null){
        file.getInputStage().close();
      }    file.getInputStage().setScene(scene3);
      file.getInputStage().show();
      scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          if (event.getCode() == KeyCode.ENTER) {
            Add.fire();
          }
        }
      });
      scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          if (event.getCode() == KeyCode.ESCAPE) {
            Cancel.fire();
          }
        }

      });
      Add.setOnAction(e -> {
        if (isInt(InputWeight, InputWeight.getText(), file.getInputStage(), false)) {
          weight = Integer.parseInt(InputWeight.getText());
          if (weight > 0) {
            line = new Line();
            line.setStartX(edgeSN.getCircleX());
            line.setStartY(edgeSN.getCircleY());
            line.setEndX(edgeFN.getCircleX());
            line.setEndY(edgeFN.getCircleY());

            //weight chiesto in input, se non inserito non disegna la linea
            edgeSN.setLine(line, weight,edgeSN,edgeFN);
            edgeFN.setLine(line, weight,edgeSN,edgeFN);
            edge_1 = edgeSN.getEdges(line, weight);
            layout.getChildren().removeAll(edgeSN.get_ElementBox(), edgeFN.get_ElementBox());
            layout.getChildren().addAll(line);
            layout.getChildren().addAll(edgeSN.get_ElementBox(), edgeFN.get_ElementBox());
            ind = 0;
            file.getKruskal_list().add(edge_1);

            file.getInputStage().close();
          }
          else{
            file.getEvent().SetError("Error!\n Non puoi inserire un peso <= 0.");
          }

        }

      });
      Cancel.setOnAction(e -> {
        file.getInputStage().close();
      });
    }



    //Delete edges

    public void Delete_Edge(New_Node edgeS, New_Node edgeF){
      Edges edge1;
      LinkedList<Edges> tmplist = file.getKruskal_list();
      if(file.getVertex() < 2) {
        file.getEvent().SetError("Ci sono meno di due nodi, non e' possibile creare un arco!");
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
        return;
      }
      if(file.getListNode().getFirst().ifLine(edgeS,edgeF))
      {
        file.getEvent().SetError("Non esiste un arco fra " + edgeS.getElement() + "<->" +edgeF.getElement() + ". \nNon e' possibile terminare l'eliminazione.");
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
        return;
      }

      line = file.getListNode().getFirst().FoundLine(edgeS, edgeF);
      for (Edges edges :tmplist) {
        if(line == edges.getLine()){
          file.getKruskal_list().remove(edges);
          break;
        }
      }
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText("Arco fra " + edgeS.getElement() + "<->" + edgeF.getElement());
      file.getListNode().getFirst().Remove_line(edgeS, edgeF, layout);

    }



    //Change Weight


    public void Change_Weight(New_Node edge1, New_Node edge2){
      if(file.getVertex() < 2) {
        file.getEvent().SetError("Ci sono meno di due nodi, non e' possibile modificare un arco!");
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
        return;
      }
      if(file.getListNode().getFirst().ifLine(edge1,edge2))
      {
        file.getEvent().SetError("Non esiste un arco fra " + edge1.getElement() + "<->" +edge2.getElement() + ". \nNon e' possibile modificare il peso.");
        file.getEvent().setFoundEdge(false);
        file.getEvent().setAdding(false);
        file.getEvent().setSetting(false);
        return;
      }
      TextField InputWeight = new TextField();
      InputWeight.setText("Inserisci il peso:");

      Button Add = new Button("Aggiungi");
      Button Cancel = new Button("Annulla");


      VBox newInput = new VBox(10);

      newInput.setPadding(new Insets(10, 10, 10, 10));
      newInput.getChildren().addAll(InputWeight, Add, Cancel);

      Scene scene3 = new Scene(newInput, 300, 250);
      file.getInputStage().setTitle("Modifica peso");
      if( file.getInputStage() != null){
        file.getInputStage().close();
      }
      file.getInputStage().setScene(scene3);
      file.getInputStage().show();

      scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          if (event.getCode() == KeyCode.ENTER) {
            Add.fire();
          }
        }

      });
      scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          if (event.getCode() == KeyCode.ESCAPE) {
            Cancel.fire();
          }
        }

      });

      Add.setOnAction(e -> {
        if (isInt(InputWeight, InputWeight.getText(),file.getInputStage(),false)) {
          weight = Integer.parseInt(InputWeight.getText());
          if (weight > 0) {
            ind = 0;
            tmpline = file.getListNode().getFirst().FoundLine(edge1,edge2);
            edge1.setLine(tmpline, weight,edge1,edge2);
            edge2.setLine(tmpline, weight,edge2,edge1);
            edge_1 = edge1.getEdges(line, weight);
            for (Object edges : file.getKruskal_list()) {
              if (file.getKruskal_list().get(ind) == edge_1) {
                file.getKruskal_list().get(ind).setWeight(weight);
                break;
              }
              ind++;
            }
          }
          else{
            file.getEvent().SetError("Error!\n Non puoi inserire un peso <= 0.");
          }
        }
        file.getInputStage().close();
      });

      Cancel.setOnAction(e -> {
        file.getInputStage().close();
      });
    }



    public boolean isInt(TextField input, String message,Stage InputStage, Boolean graph){
      try{
        int InputVertex = Integer.parseInt(input.getText());

        return  true;

      }catch(NumberFormatException e){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error!\n" + message + "non e' un numero");
        error.showAndWait();
        return false;
      }
    }


    public Boolean getAdded() {
      return Added;
    }

    public void setAdded(Boolean added) {
      Added = added;
    }

    public Stage getInputStage() {
      return InputStage;
    }

    public void setInputStage(Stage inputStage) {
      InputStage = inputStage;
    }
  }
