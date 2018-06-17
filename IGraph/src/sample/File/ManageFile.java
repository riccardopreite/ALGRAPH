package sample.File;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Edit.EditNode;
import sample.MouseEvent.Event;
import sample.Node.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.Optional;
import static javafx.scene.paint.Color.*;



public class ManageFile{

  private Integer count2 = 0;

  private  Boolean edge = false, removed = false, add = false, delete_edge = false,continue_line = true, opened = false, Elements = false;
  private Scene  scene2;
  private Stage window;
  private Circle circle;
  private Line line;
  private Integer   ind = 0,weight;
  private New_Node forFile = null,forFile2 = null;
  private New_Node tmpnode = null;
  private Integer vertex = 0, count_line = 0;
  private Boolean answer = false;
  private LinkedList<Edges> Kruskal_list;
  private Stage InputStage = new Stage();
  private BorderPane layout;
  private Event event = new Event();
  private EditNode edit = new EditNode();
  private LinkedList<New_Node> ListNode = new LinkedList<>();
  private Menu filemenu,editmenu,Kruskal,helpmenu;
  private MenuBar menuBar;
  private FileChooser fileChooser = new FileChooser();
  private FileChooser filex = new FileChooser();


  private File file;
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
                event.SetError("Error!\nAlla linea: " + count_line + " c'e' un errore(richesto intero, inserita stringa");
                return;
              }

              opened = true;
              Create_rand_Graph(vertex,0);
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
                      event.SetError("L'elemento " + line_file + " esiste gia', verra' sostituito da NE.\n(E' possibile cambiarlo dopo)");
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
                      event.SetError("Error!\nAlla linea " + count_line + " il carattere '-' non e' attaccato fra i due nodi, la linea non verra' creata");
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
                          warn.setHeaderText("Warning!\nAlla linea " + count_line + "il peso non e' presente.\nSara' generato random. ");
                          warn.showAndWait();
                          weight = (int) Math.random() * 20 + 1;
                        }
                        if ((ListNode.getFirst().IsElement(ListNode, edge1)) && (ListNode.getFirst().IsElement(ListNode, edge2)) && edge1 != edge2 && (!edge1.equals("NE")) && (!edge2.equals("NE"))) {
                          forFile = ListNode.getFirst().returnNode_element(edge1, ListNode);
                          forFile2 = ListNode.getFirst().returnNode_element(edge2, ListNode);
                          ListNode.getFirst().File_line(forFile, forFile2, layout, weight, Kruskal_list);
                        } else {
                          event.SetError("L'arco scelto tra " + edge1 + "<->" + edge2 + " non puo' essere creato");
                        }
                      }
                    }
                    continue_line = true;
                  }
                }
                count_line++;

              }
              while ((char) word != ';' && word != -1);
              //Prende i numeri dei nodi e crea un arco fra loro, se un arco gia' esiste non lo ricrea
            }
          }
        }
        while (line_file != null);

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
    TextField MaxWeight = new TextField();
    //    InputVertex.setPromptText("Insert Number of Vertex");
    InputVertex.setText("Inserisci il numero di Vertici:(Max 10)");
    MaxWeight.setText("Inserisci il peso massimo:(Max 100)");

    Button Insert = new Button("Inserisci");
    Button Cancel = new Button("Annulla");


    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputVertex,MaxWeight, Insert,Cancel);
    if(InputStage != null){
      InputStage.close();
    }
    Scene scene3 = new Scene(newInput, 300, 250);
    scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
          Insert.fire();
        }
      }
    });

    Insert.setOnAction(e -> {
      if(edit.isInt(InputVertex, InputVertex.getText(),InputStage,true)) {
        vertex = Integer.parseInt(InputVertex.getText());
        if(edit.isInt(MaxWeight, MaxWeight.getText(),InputStage,true)) {
          weight = Integer.parseInt(MaxWeight.getText());
          if (vertex <= 10 && vertex > 0) {
            if(weight > 0 && weight <= 100 ) {
              opened = false;
              Create_rand_Graph(vertex, weight);
              InputStage.close();

            }
            else{
              event.SetError("Inserire un peso > 0 e <= 100!");
            }
          }
          else{event.SetError("Per la generazione random inserire da 1 10 nodi!\nSara' comunque possibile modificarlo dopo.");
          }
        }
      }
    });

    Cancel.setOnAction(e -> {
      InputStage.close();
    });

    InputStage.setScene(scene3);
    InputStage.showAndWait();
    return vertex;
  }


  public void Create_rand_Graph(Integer vertex,Integer MaxWeight) {

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
          ListNode.getFirst().random_graph(layout, index, control, ListNode,Kruskal_list,MaxWeight);
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
      if (!ListNode.isEmpty()) {
        for (New_Node node : ListNode) {
          WriteOnFIle.println(node.getElement());

        }
      }
      WriteOnFIle.println("Edges:");
      ind = 0;
      if(!Kruskal_list.isEmpty()){
        for (Edges edge : Kruskal_list) {
          message = message + edge.getNodeA().getElement() + "-" + edge.getNodeB().getElement() + "/" + edge.getWeight() + "\n";
        }
      }
      WriteOnFIle.println(message);
      WriteOnFIle.println(";");

    }
  }





  public void ReprintGraph(BorderPane layout) {
    LinkedList<Edges> EDGE;
    window.setTitle("ALGRAPH");

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
    answer = ConfirmBox.display("Attenzione!!!", "Vuoi salvare il File?(Invio:Si/Esc:no)");


    if (answer) {

      SaveFile(vertex);


      window.close();

    }
    else{
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("Sei Sicuro di voler Uscire?");

      Optional<ButtonType> result = alert.showAndWait();
      if(result.get() == ButtonType.OK) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        window.close();
      }
      else return;
    }


  }


  public LinkedList<New_Node> deleteElement(New_Node node){
    LinkedList<Edges> tmplist;
    Integer index = 0;
    for (New_Node Node:ListNode) {
      if(!Node.equals(node)){
        tmplist = Node.getArchi();
        index = 0;
        while(index < tmplist.size()){
          if(tmplist.get(index).getNodeB().equals(node)){
            Node.getArchi().remove(tmplist.get(index));

          }
          index++;
        }

      }

    }
    ListNode.remove(node);
    return ListNode;
  }
  public LinkedList<Edges> deleteEdge(New_Node selected) {
    LinkedList<Edges> tmplist = Kruskal_list;
    Integer index = 0;
    while(index < tmplist.size()){
      if(tmplist.get(index).getNodeB().equals(selected) || tmplist.get(index).getNodeA().equals(selected)) {

        Kruskal_list.remove(tmplist.get(index));
      }
      index++;
    }
    //  Kruskal_list = tmplist;
return Kruskal_list;
  }


  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Integer getVertex() {
    return vertex;
  }

  public void setVertex(Integer vertex) {
    this.vertex = vertex;
  }

  public LinkedList<Edges> getKruskal_list() {
    return Kruskal_list;
  }

  public void setKruskal_list(LinkedList<Edges> kruskal_list) {
    Kruskal_list = kruskal_list;
  }

  public Stage getInputStage() {
    return InputStage;
  }

  public void setInputStage(Stage inputStage) {
    InputStage = inputStage;
  }

  public BorderPane getLayout() {
    return layout;
  }

  public void setLayout(BorderPane layout) {
    this.layout = layout;
  }

  public LinkedList<New_Node> getListNode() {
    return ListNode;
  }

  public void setListNode(LinkedList<New_Node> listNode) {
    ListNode = listNode;
  }


  public Stage getWindow() {
    return window;
  }

  public void setWindow(Stage window) {
    this.window = window;
  }
}
