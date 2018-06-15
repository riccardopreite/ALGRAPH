package sample.File;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import sample.Edit.EditNode;
import sample.MouseEvent.Event;
import sample.Node.*;
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

import java.awt.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.paint.Color.*;



public class ManageFile{

  public Integer count2 = 0;

  public  Boolean edge = false, removed = false, add = false, delete_edge = false,continue_line = true, opened = false, Elements = false;
  Scene  scene2;
  Stage window;
  Circle circle;
  Line line;
  Integer   ind = 0,weight;
  public New_Node forFile = null,forFile2 = null;
  public New_Node tmphead = null,tmpnode = null;
  public Integer vertex = 0, count_line = 0;
  public Boolean answer = false;
  public LinkedList<Edges> Kruskal_list;
  public Stage InputStage = new Stage();
  public BorderPane layout;
  public Event event = new Event();
  public EditNode edit = new EditNode();
  public LinkedList<New_Node> ListNode = new LinkedList<>();
  public Menu filemenu,editmenu,Kruskal,helpmenu;
  public MenuBar menuBar;
  public FileChooser fileChooser = new FileChooser();
  public FileChooser filex = new FileChooser();


  java.io.File file;
  // private Desktop desktop = Desktop.getDesktop();

public ManageFile(){

}

  public void SetLayout(BorderPane layout, Stage window, Scene scene,MenuBar menubar,Menu filemenu, Menu editmenu,Menu kruskal,Menu helpmenu){

  this.layout = layout;
  this.window = window;
  this.scene2 = scene;
  this.menuBar = menubar;
  this.filemenu = filemenu;
  this.editmenu = editmenu;
  this.Kruskal = kruskal;
  this.helpmenu = helpmenu;

  }


  //Open file
  public void NewFile()  {
    if(vertex > 0){
      layout = ListNode.getFirst().clearLayout(layout,ListNode);
    }
    vertex = 0;
    count_line = 0;
    line = null;
    edge = false;
    opened = false;
    removed = false;
    add = false;
    ListNode = new LinkedList<>();
    Kruskal_list = new LinkedList();
    window.setMaxWidth(1200);
    window.setMinWidth(1200);
    window.setMaxHeight(900);
    window.setMinHeight(900);
    window.setFullScreen(false);
    window.setMaximized(false);
    window.setX(150);
    window.setY(50);
    window.setScene(scene2);


  }

  //Random

  public void Random(){
    if(vertex > 0){
      layout = ListNode.getFirst().clearLayout(layout,ListNode);
    }
    vertex = 0;
    count_line = 0;
    line = null;
    edge = false;
    opened = false;
    removed = false;
    add = false;
    ListNode = new LinkedList<>();
    Kruskal_list = new LinkedList();
    vertex = vertex();
    window.show();
    window.setMaxWidth(1200);
    window.setMinWidth(1200);
    window.setMaxHeight(900);
    window.setMinHeight(900);
    window.setFullScreen(false);
    window.setMaximized(false);
    window.setX(150);
    window.setY(50);
    window.setScene(scene2);


  }

  public void Openfile() {
    if(vertex > 0){
      layout = ListNode.getFirst().clearLayout(layout,ListNode);
    }
    vertex = 0;
    count_line = 0;
    line = null;
    edge = false;
    opened = false;

    removed = false;
    add = false;
    ListNode = new LinkedList<>();
    Kruskal_list = new LinkedList();
    window.setX(150);
    window.setY(50);
    window.setMaxWidth(1200);
    window.setMinWidth(1200);
    window.setMaxHeight(900);
    window.setMinHeight(900);
    window.setFullScreen(false);
    window.setMaximized(false);
    window.setScene(scene2);
    Stage stage = new Stage();

    fileChooser.setTitle("Open Txt File");
    fileChooser.setInitialDirectory(
    new File(System.getProperty("user.home"))
    );
    fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("Txt", "*.txt")
    );
    file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      try {
        //      desktop.open(file);
        FileReader is = new FileReader(file);
        BufferedReader br = new BufferedReader(is, 100);
        int index = 0;
        int word;
        String edge1, edge2;
        String aux_edge = "";
        String line_file = "";
        do {
          line_file = "";
          count_line++;
          line_file = br.readLine();
          System.out.println(line_file);
          if (line_file != null) {
            if (line_file.equals("Vertex:")) {
              //Aggiungi vertex
              //prendi numero da riga successiva
              //chiama random graph fino ai nodi
              count_line++;
              line_file = br.readLine();
              try {
                vertex = Integer.parseInt(line_file);
                if(vertex < 0){
                  event.SetError("Error!\nAlla linea" + count_line + ". Inserito numero vertici < 0 ");
                  return;
                }
              }
              catch (Exception t){
                event.SetError("Error!\nAlla linea: " + count_line + " c'è un errore(richesto intero, inserita stringa");
                return;
              }

              opened = true;
              Create_rand_Graph(vertex);
              System.out.println("Vertici aggiunti");
            }

            else if(line_file.equals("Root:")){
              ListNode.getFirst().clearLayout(layout,ListNode);
              event.SetError("Stai cercado di aprire il File di un albero!");
          //    OpenTree();
            }
            else if (line_file.equals("Elements:")) {
              Elements = true;
              index = 0;
              line_file = br.readLine();
              count_line++;
              while (index < vertex && (!line_file.equals("Edges:"))) {
                //Prende i numeri dei nodi e aggiunge gli elementi
                if (!line_file.equals("")){
                  if (line_file.equals("NE")) {
                    ListNode.get(index).setElement("NE");
                  } else {
                    if(!ListNode.getFirst().IsElement(ListNode,line_file)) {
                      ListNode.get(index).setElement(line_file);
                    }
                    else{
                      ListNode.get(index).setElement("NE");
                      event.SetError("L'elemento " + line_file + " esiste già, verrà sostituito da NE.\n(E' possibile cambiarlo dopo)");
                    }


                  }
                ListNode.get(index).set_ElementBox();
                layout.getChildren().add(ListNode.get(index).get_ElementBox());
                index++;
              }
              line_file = br.readLine();
              count_line++;

              }
              //while col numero di nodi preso prima, ignora le restanti righe fino agli archi
              //Inserisce l'elemento per il nodo corrispettivo, nell'ordine scritto.
              //Controllo se int, double o string
              //inserisce l'elemento nel nodo
            }  if (line_file.equals("Edges:")) {

              if(Elements){
                if (!ListNode.isEmpty()) {
                  for (New_Node node: ListNode) {
                    if(node.getElement().equals(""))
                      node.setElement("NE");
                  }
                }
              }
            else  if(!Elements){
                for (New_Node node : ListNode) {
                  node.setElement("NE");
                }
             //   break;
              }
              do {
                word = br.read();
                System.out.println((char)word);
                if ((char) word != ';' && word != -1) {
                  if ((char) word != '\n') {
                    aux_edge = "";
                    while ((char) word != '-') {
                      if((char)word != ' ') {
                        aux_edge = aux_edge + (char) word;
                      }
                      word = br.read();
                    }
                    if((char)word != '-'){
                      event.SetError("Error!\nAlla linea " + count_line + " il carattere '-' non è attaccato fra i due nodi, la linea non verrà creata");
                      continue_line = false;
                    }
                    if(continue_line){
                    edge1 = aux_edge;
                    if ((char) word != '\n') {
                      aux_edge = "";
                      word = br.read();
                      while ((char) word != '/' && (char) word != '\n') {
                        if((char)word != ' ') {
                          aux_edge = aux_edge + (char) word;
                        }
                        word = br.read();
                      }
                      edge2 = aux_edge;
                      aux_edge = "";
                      if ((char) word == '/') {
                        word = br.read();
                        while ((char) word != '\n') {
                          aux_edge = aux_edge + (char) word;
                          word = br.read();
                        }
                        weight = Integer.parseInt(aux_edge);
                      } else {
                        Alert warn = new Alert(Alert.AlertType.WARNING);
                        warn.setHeaderText("Warning!\nAlla linea " + count_line + "il peso non è presente.\nSarà geherato random. ");
                        warn.showAndWait();
                        weight = (int) Math.random() * 20 + 1;
                      }
                      if ((ListNode.getFirst().IsElement(ListNode, edge1)) && (ListNode.getFirst().IsElement(ListNode, edge2)) && edge1 != edge2 && (!edge1.equals("NE")) && (!edge2.equals("NE"))) {
                        System.out.println("edge1: " + edge1 + " edge2: " + edge2 + " W: " + weight);
                        forFile = ListNode.getFirst().returnNode_element(edge1, ListNode);
                        forFile2 = ListNode.getFirst().returnNode_element(edge2, ListNode);
                        System.out.println("PESO : " + aux_edge);
                        ListNode.getFirst().File_line(forFile, forFile2, layout, weight, Kruskal_list);
                      } else {
                        event.SetError("L'arco scelto tra " + edge1 + "<->" + edge2 + " non può essere creato");
                      }
                    }
                    }
                    continue_line = true;
                  }
                }
                count_line++;

              }
              while ((char) word != ';' && word != -1);
              //Prende i numeri dei nodi e crea un arco fra loro, se un arco già esiste non lo ricrea
              System.out.println("Edges added");
            }
          }
        }
        while (line_file != null);
      //  event.SetHead(this.head, this.tmphead, this.vertex);

        // event.SetHead(this.tmphead,this.head);
      }
      catch (MalformedURLException e)
      {
        System.out.println("Attenzione:" + e);
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
    //End of Openfile
  }




  public Integer vertex(){
    TextField InputVertex = new TextField();
//    InputVertex.setPromptText("Insert Number of Vertex");
    InputVertex.setText("Inserisci il numero di Vertici:");
    Button button = new Button("Inserisci");
    button.setOnAction(e -> {
      if(edit.isInt(InputVertex, InputVertex.getText(),InputStage,true)){
        vertex = Integer.parseInt(InputVertex.getText());

        System.out.println(vertex);

        if(vertex <= 10 && vertex > 0){
          InputStage.close();
          Create_rand_Graph(vertex);
        }

        else{
          event.SetError("Per la generazione random inserire da 1 10 nodi!\nSarà comunque possibile modificarlo dopo.");
        }
      }
    });
    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputVertex, button);
    if(InputStage != null){
      InputStage.close();
    }
    Scene scene3 = new Scene(newInput, 300, 250);

    InputStage.setScene(scene3);
    InputStage.showAndWait();
    return vertex;
  }


  public void Create_rand_Graph(Integer vertex) {

    Double randX, randY;
    Integer index = 0;

    if(vertex != 0){
      while (index < vertex) {
        if (!ListNode.isEmpty()){
          do {
            do {
              randX = (Math.random() * 1180 + 20);
              randY = (Math.random() * 831 + 49);
            }while((((randX < 20) || (randX > 1180)) || ((randY < 45) || (randY > 880))));
          }
          //Aggiungere controllo per i bordi
          while (ListNode.getFirst().FindElement(randX, randY, ListNode) != null );

            circle = new Circle(randX, randY, 20);
            tmpnode = new New_Node();
            ListNode.add(index, tmpnode);
            ListNode.get(index).setCircleX(randX);
            ListNode.get(index).setCircleY(randY);
            ListNode.get(index).setCircle(circle);


            if (!opened) {
              ListNode.get(index).setElement(ListNode.get(index).setRandELement(ListNode));
              ListNode.get(index).set_ElementBox();
              layout.getChildren().addAll(circle, ListNode.get(index).get_ElementBox());
            } else layout.getChildren().addAll(circle);
            index++;
          }
          else{
          randX = (Math.random() * 1180 + 20);
          randY = (Math.random() * 831 + 49);
          circle = new Circle(randX, randY, 20);
          tmpnode = new New_Node();
          ListNode.add(index, tmpnode);
          ListNode.get(index).setCircleX(randX);
          ListNode.get(index).setCircleY(randY);
          ListNode.get(index).setCircle(circle);


          if (!opened) {
            ListNode.get(index).setElement(ListNode.get(index).setRandELement(ListNode));
            ListNode.get(index).set_ElementBox();
            layout.getChildren().addAll(circle, ListNode.get(index).get_ElementBox());
          } else layout.getChildren().addAll(circle);
          index++;
        }
      }
      if(!opened) {
        index = 0;
        Double control = 100 - (vertex * 4.75);
        while (index < vertex) {
          ListNode.getFirst().random_graph(layout, index, control, ListNode,Kruskal_list);
          index++;
        }
      }
    }
   // event.SetHead(this.head, this.tmphead, this.vertex);
    //Endo Of Rand Graph
  }

  //Save File

  public void SaveFile(Integer vertex) {
     Stage stage = new Stage();
    // filex.showSaveDialog(stage);
    String message = "";
    String name = null;
    filex.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Txt", "*.txt")
    );
    File file = new File(String.valueOf(filex.showSaveDialog(stage)));

    FileOutputStream fileTosave = null;
    try {
      if (!file.equals(null)){
        fileTosave = new FileOutputStream(file);
        name = file.getName();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Integer index = 0;
    stage.setOnCloseRequest(e -> stage.close());
    if (!name.equals(null)) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }

      PrintStream WriteOnFIle = new PrintStream(fileTosave);
      WriteOnFIle.println("Vertex:");
      WriteOnFIle.println(vertex);
      index = 0;
      WriteOnFIle.println("Elements:");
      if(!ListNode.isEmpty()){
      for (New_Node node : ListNode) {
        WriteOnFIle.println(node.getElement());

      }
    }
      WriteOnFIle.println("Edges:");
      ind = 0;
      for (Edges edge : Kruskal_list) {
        message = message + edge.getNodeA().getElement() + "-" + edge.getNodeB().getElement() + "/" + edge.getWeight() + "\n";
      }
      WriteOnFIle.println(message);
      WriteOnFIle.println(";");
      System.out.println("File saved!");

    }
  }





  public void ReprintGraph(BorderPane layout) {
    LinkedList<Edges> EDGE;
    for (New_Node node : ListNode) {
      node.setConnect(false);

      EDGE = node.getArchi();
      for (Edges Doline : EDGE) {

        try {
          layout.getChildren().remove(Doline.getLine());
          Doline.getLine().setStroke(BLACK);
          Doline.getLine().setStrokeWidth(1);
          layout.getChildren().add(Doline.getLine());

        } catch (Exception e) {
        }

      }
      layout.getChildren().removeAll(node.getCircle(), node.get_ElementBox());
      layout.getChildren().addAll(node.getCircle(), node.get_ElementBox());


    }

    layout.getChildren().removeAll(menuBar);
    menuBar.getMenus().removeAll(filemenu,helpmenu,editmenu,Kruskal);
    menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
    layout.setTop(menuBar);
  }



  //Close Program


  public  void closeProgram() {
    answer = ConfirmBox.display("Warning!!!", "Do you want to save the file?");


    if (answer) {

      SaveFile(vertex);


      window.close();

    }
    else{

      window.close();
    }


  }
}
