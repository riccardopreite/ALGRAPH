import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import Edit.EditNode;
import File.ManageFile;
import Kruskal.*;
import MouseEvent.Event;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.MalformedURLException;


public class Main extends Application {
  private Integer count = 0;

  private Scene scene1, scene2;
  private Stage window;
  private Boolean open = false;

  private JFrame window2;
  private BorderPane layout, layout2;
  private ManageFile file = new ManageFile();
  private Kruskal krus = new Kruskal();
  private Event event = new Event();
  private EditNode edit = new EditNode();
  private File help ;
  private String HelpMenumessage ="",auxline;
  private MenuItem newfile,openfile,savefile,randomfile,exitfile;
  private Menu filemenu,editmenu,Kruskal,helpmenu;
  private MenuBar menuBar;


  public void start(Stage primaryStage){



    layout = new BorderPane();
    layout2 = new BorderPane();


    window = primaryStage;
    window.setTitle("WELCOME");

    //File menu


    filemenu = new Menu("File");
    window.setOnCloseRequest(e -> {

      e.consume();
      file.closeProgram();
    });


    newfile = new MenuItem("Nuovo");
    newfile.setOnAction(e -> {
              CloseStage();

      file.NewFile();
      window.setTitle("ALGRAPH");

      event.SetManageFile(file);
      edit.SetManageFile(file);
      event.SetEdit(edit);
      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(false);
      event.SetCombinationKey(scene2);

      if (count == 0) {
        filemenu.getItems().removeAll(newfile, openfile, randomfile, exitfile);
        filemenu.getItems().addAll(newfile, openfile, savefile, randomfile, exitfile);
        menuBar.getMenus().removeAll(filemenu,helpmenu);
        menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
        layout2.setTop(menuBar);
        count++;
        event.DragCircle();
        event.ViewMenu();
        event.DeleteNode();

      }
      else{
        try{
          menuBar.getMenus().clear();
          menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
          layout2.setTop(menuBar);
        }
        catch(Exception t){}
      }




    });


    openfile = new MenuItem("Apri");
    openfile.setOnAction(e -> {
      CloseStage();


      file.Openfile();
      window.setTitle("ALGRAPH");

      event.SetManageFile(file);
      edit.SetManageFile(file);
      event.SetEdit(edit);
      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(false);
      if (count == 0) {
        count++;
        filemenu.getItems().removeAll(newfile, openfile, randomfile, exitfile);
        filemenu.getItems().addAll(newfile, openfile, savefile, randomfile, exitfile);
        menuBar.getMenus().removeAll(filemenu,helpmenu);
        menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
        layout2.setTop(menuBar);
        event.DragCircle();
        event.ViewMenu();
        event.DeleteNode();

      }
      else{
        try{
          menuBar.getMenus().clear();
          menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
          layout2.setTop(menuBar);
        }
        catch(Exception t){}
      }
      event.SetCombinationKey(scene2);





    });

    savefile = new MenuItem("Salva come..");
    savefile.setOnAction(e -> {
      CloseStage();

      file.SaveFile(file.getVertex());
    });


    randomfile = new MenuItem("Genera random");
    randomfile.setOnAction(e -> {
      CloseStage();



      file.Random();
      window.setTitle("ALGRAPH");

      event.SetManageFile(file);
      edit.SetManageFile(file);
      event.SetEdit(edit);

      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(false);
      event.SetCombinationKey(scene2);


      if (count == 0) {
        count++;
        menuBar.getMenus().clear();
        filemenu.getItems().removeAll(newfile, openfile, randomfile, exitfile);
        filemenu.getItems().addAll(newfile, openfile, savefile, randomfile, exitfile);
        menuBar.getMenus().removeAll(filemenu, helpmenu);
        menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
        layout2.setTop(menuBar);
        event.DragCircle();
        event.ViewMenu();
        event.DeleteNode();

      }
      else{
        try{
          menuBar.getMenus().clear();
          menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
          layout2.setTop(menuBar);
        }
        catch(Exception t){}
      }
    });

    exitfile = new MenuItem("Esci");
    exitfile.setOnAction(e -> {

      e.consume();
      file.closeProgram();
      try {
        window2.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);
        window2.dispatchEvent(new WindowEvent(window2, WindowEvent.WINDOW_CLOSING));
      } catch (Exception t) {
      }


    });


    //Edit menu
    editmenu = new Menu("Modifica");

    Menu Node = new Menu("Nodo");
    Menu Edge = new Menu("Arco");


    //Add Node
    MenuItem Addnode = new MenuItem("Aggiungi Nodo");
    Addnode.setOnAction(e -> {
      CloseStage();

      event.setAdding(true);
      event.setSetting(false);
      event.setFoundEdge(false);
      event.AddNode();


    });


    //Set Element
    MenuItem setElement = new MenuItem("Modifica Elemento");

    setElement.setOnAction(e -> {
      CloseStage();


      event.setAdding(false);
      event.setSetting(true);
      event.setFoundEdge(false);
      event.SetElement();
    });


    //Add Edge
    MenuItem Add_Edge = new MenuItem("Aggiungi Arco");
    Add_Edge.setOnAction(e -> {
      CloseStage();


      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(true);
      event.SetControl(0);
      event.GetEdge(0);

    });

    //Delete Edge

    MenuItem DeleteEdge = new MenuItem("Elimina Arco");
    DeleteEdge.setOnAction(e -> {
      CloseStage();

      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(true);
      event.SetControl(1);
      event.GetEdge(1);
    });

    //Change Weight
    MenuItem ChangeWeight = new MenuItem("Modifica Peso");
    ChangeWeight.setOnAction(e -> {
      CloseStage();


      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(true);
      event.SetControl(2);
      event.GetEdge(2);


    });
    editmenu.getItems().addAll(Node, Edge);
    Node.getItems().addAll(Addnode, setElement);
    Edge.getItems().addAll(Add_Edge, DeleteEdge, ChangeWeight);


    //Kruskal
    Kruskal = new Menu("Opzioni");
    MenuItem doKruskal = new MenuItem("Kruskal");
    doKruskal.setOnAction(e -> {

      CloseStage();
      window.setTitle("KRUSKAL");

      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(false);
      krus.SetManageFile(file);
      krus.UsingKruskal(file.getLayout(), scene2);

    });

    Kruskal.getItems().addAll(doKruskal);
    //Help menu
    helpmenu = new Menu("Aiuto");
    MenuItem Help = new MenuItem("Guida");
    Help.setOnAction(e -> {

      event.setAdding(false);
      event.setSetting(false);
      event.setFoundEdge(false);
      if(!open) {
        HelpMenu();
        open = true;
      }

      window2.toFront();
      window2.setVisible(true);
      //window2.show();

    });
    helpmenu.getItems().addAll(Help);


    //Main menu bar
    menuBar = new MenuBar();
    filemenu.getItems().addAll(newfile, openfile, randomfile, exitfile);

    menuBar.getMenus().addAll(filemenu, helpmenu);
    layout.setTop(menuBar);
    window.getIcons().add(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.png")));



    scene2 = new Scene(layout2, 1200, 900);
    layout2.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");

    file.SetLayout(layout2, window, scene2,menuBar,filemenu,editmenu,Kruskal,helpmenu);
    event.SetLayout(layout2, window,scene1,savefile,openfile,newfile,Help,randomfile,doKruskal);
    edit.SetLayout(layout2, window, scene2);

    layout.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");
    window.setX(150);
    window.setY(50);


    scene1 = new Scene(layout, 300, 350);
    window.setScene(scene1);
    window.show();


  }


  //Open Helpmenu
  public void HelpMenu(){
    help = new File("Guide.txt");
    try {
      FileReader is = new FileReader(help);
      BufferedReader br=new BufferedReader(is,100);
      auxline = br.readLine();

      do
      {
        HelpMenumessage = HelpMenumessage + auxline + '\n';
        auxline = br.readLine();
      }
      while (auxline!=null);
      is.close();
    }
    catch (MalformedURLException e)
    {
      System.out.println("Attenzione:" + e);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    window2 = new JFrame("Guida");
    final JTextArea textArea;
    textArea = new JTextArea(10, 20);
    JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    textArea.setText(HelpMenumessage);
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    window2.add(scroll);
    window2.setSize(630, 500);
    window2.setVisible(true);
    window2.setLocationRelativeTo(null);


  }



  public static void main(String[] args) {
    launch(args);
  }

  public void CloseStage(){

    if(edit.getInputStage() != null){
      edit.getInputStage().close();
    }
    if(event.getDescritionStage() != null){
      event.getDescritionStage().close();

    }

  }




}
