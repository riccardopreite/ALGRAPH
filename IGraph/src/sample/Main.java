package sample;
import sample.Edit.EditNode;
import sample.File.ConfirmBox;
import sample.File.ManageFile;
import sample.Kruskal;
import sample.MouseEvent.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.Node.DFS;
import sample.Node.Edges;
import sample.Node.New_Node;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class Main extends Application {
  public Integer count = 0;

  Scene scene1, scene2;
  Stage window;
  public Boolean open = false;

  JFrame window2;
  public BorderPane layout, layout2;
   ManageFile file = new ManageFile();
  Kruskal krus = new Kruskal();
  Event event = new Event();
  EditNode edit = new EditNode();
  File help ;
  String HelpMenumessage ="",auxline;
  public MenuItem newfile,openfile,savefile,randomfile,exitfile;
  public Menu filemenu,editmenu,Kruskal,helpmenu;
  public MenuBar menuBar;


  public void start(Stage primaryStage) throws Exception {
    System.out.println("sono vivo");

    layout = new BorderPane();
    layout2 = new BorderPane();


    window = primaryStage;
    window.setTitle("making Menux");

    //File menu


     filemenu = new Menu("File");
    window.setOnCloseRequest(e -> {

      e.consume();
      file.closeProgram();
    });


     newfile = new MenuItem("New");
    newfile.setOnAction(e -> {
      CloseStage();
      file.NewFile();
      event.SetManageFile(file);
      event.file.tmphead = file.tmphead;
      edit.SetManageFile(file);
      event.SetEdit(edit);
      event.adding = false;
      event.setting = false;
      event.FoundEdge = false;
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




    });


     openfile = new MenuItem("Open");
    openfile.setOnAction(e -> {
      CloseStage();
      file.Openfile();
      event.SetManageFile(file);
      event.file.tmphead = file.tmphead;
      edit.SetManageFile(file);
      event.SetEdit(edit);
      event.adding = false;
      event.setting = false;
      event.FoundEdge = false;
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




    });

     savefile = new MenuItem("Save As");
    savefile.setOnAction(e -> {
      CloseStage();

      file.SaveFile(file.vertex);
    });


     randomfile = new MenuItem("Generate random");
    randomfile.setOnAction(e -> {
      CloseStage();
      file.Random();
      event.SetManageFile(file);
      event.file.tmphead = file.tmphead;
      edit.SetManageFile(file);
      event.SetEdit(edit);
      event.adding = false;
      event.setting = false;
      event.FoundEdge = false;
      if (count == 0) {
        count++;
        filemenu.getItems().removeAll(newfile, openfile, randomfile, exitfile);
        filemenu.getItems().addAll(newfile, openfile, savefile, randomfile, exitfile);
        menuBar.getMenus().removeAll(filemenu, helpmenu);
        menuBar.getMenus().addAll(filemenu, editmenu, Kruskal, helpmenu);
        layout2.setTop(menuBar);
        event.DragCircle();
        event.ViewMenu();
        event.DeleteNode();

      }
    });

     exitfile = new MenuItem("Exit");
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
     editmenu = new Menu("Edit");

    Menu Node = new Menu("Node");
    Menu Edge = new Menu("Edge");


    //Add Node
    MenuItem Addnode = new MenuItem("Add Node");
    Addnode.setOnAction(e -> {
      CloseStage();
      event.file.tmphead = file.tmphead;
      event.adding = true;
      event.setting = false;
      event.FoundEdge = false;
      event.AddNode();


    });


    //Set Element
    MenuItem setElement = new MenuItem("Add/Change Element");

    setElement.setOnAction(e -> {
      CloseStage();

      event.file.tmphead = file.tmphead;
      event.setting = true;
      event.FoundEdge = false;
      event.adding = false;
      event.SetElement();
    });


    //Add Edge
    MenuItem Add_Edge = new MenuItem("Add Edge");
    Add_Edge.setOnAction(e -> {
      CloseStage();

      event.file.tmphead = file.tmphead;
      event.FoundEdge = true;
      event.adding = false;
      event.setting = false;
      event.SetControl(0);
      event.GetEdge(0);

    });


    MenuItem DeleteEdge = new MenuItem("Delete Edge");
    DeleteEdge.setOnAction(e -> {
      CloseStage();

      event.file.tmphead = file.tmphead;
      event.FoundEdge = true;
      event.adding = false;
      event.setting = false;
      event.SetControl(1);
      event.GetEdge(1);
    });
    MenuItem ChangeWeight = new MenuItem("Change Weight");
    ChangeWeight.setOnAction(e -> {
      CloseStage();

      event.file.tmphead = file.tmphead;
      event.FoundEdge = true;
      event.adding = false;
      event.setting = false;
      event.SetControl(2);
      event.GetEdge(2);


    });
    editmenu.getItems().addAll(Node, Edge);
    Node.getItems().addAll(Addnode, setElement);
    Edge.getItems().addAll(Add_Edge, DeleteEdge, ChangeWeight);

     Kruskal = new Menu("Option");
    MenuItem doKruskal = new MenuItem("Kruskal");
    doKruskal.setOnAction(e -> {

      CloseStage();
      event.adding = false;
      event.setting = false;
      event.FoundEdge = false;
      krus.SetManageFile(file);
      krus.UsingKruskal(file.layout, scene2);

    });

    Kruskal.getItems().addAll(doKruskal);
    //Help menu
     helpmenu = new Menu("Help");
    MenuItem Help = new MenuItem("Show Help Menu");
    Help.setOnAction(e -> {
      if(!open) {
        HelpMenu();
        open = true;
      }

      window2.toFront();
      window2.show();

    });
    helpmenu.getItems().addAll(Help);


    //Main menu bar
     menuBar = new MenuBar();
    filemenu.getItems().addAll(newfile, openfile, randomfile, exitfile);

    menuBar.getMenus().addAll(filemenu, helpmenu);
    layout.setTop(menuBar);

    scene1 = new Scene(layout, 300, 350);
    window.setScene(scene1);

    scene2 = new Scene(layout2, 1200, 900);
    layout2.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");

    file.SetLayout(layout2, window, scene2,menuBar,filemenu,editmenu,Kruskal,helpmenu);
    event.SetLayout(layout2, window, scene2);
    edit.SetLayout(layout2, window, scene2);


    layout.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");

    window.show();


  }

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
          window2 = new JFrame("Help");
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

    if(edit.InputStage != null){
      edit.InputStage.close();
    }
    if(event.DescritionStage != null){
      event.DescritionStage.close();

    }
    if(event.popup_error != null){
      event.popup_error.close();
    }
    if(file.fileChooser != null){
    }
  }

}


