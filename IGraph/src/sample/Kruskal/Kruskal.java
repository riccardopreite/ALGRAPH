package sample;

import com.sun.tools.javah.Util;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.File.ManageFile;
import sample.Node.DFS;
import sample.Node.Edges;
import sample.Node.New_Node;

import javafx.event.EventHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

import static javafx.scene.paint.Color.ORANGE;
import static javafx.scene.paint.Color.RED;

public class Kruskal {
    public  ManageFile file;
    Boolean SteCleaner = false;
    public Boolean found = false,get = false;
    public BorderPane layout2;
    public ArrayList<Edges> MinSpanningTree = new ArrayList<>();
    public int mst_cost = 0,j = 0,k = 0, ind = 0, x = 0;
    public Integer cont = 0,index = 0,aux_index;
    public UnionFind UF;
    public Edges front;
    public MenuBar menuBar = new MenuBar();




    public Kruskal(){

    }

    public void SetManageFile(ManageFile file) {
        this.file = file;
    }

    public void UsingKruskal(BorderPane layout,Scene scene) {

        try {
            menuBar.getMenus().clear();
        }
        catch (Exception e){}
        if(file.vertex < 0 || file.Kruskal_list.isEmpty()){
            file.event.SetError("Il grafo non Esiste!");
            return;

        }
        layout2 = layout;
        file.Kruskal_list.sort(Comparator.comparing(Edges::getWeight));
if(file.Kruskal_list.size() < file.vertex){
    file.event.SetError("Il numero di archi è minore del numero di nodi quindi non è possibile applicare Kruskal!");
    return;
}
        DFS dfs = new DFS();
        cont = dfs.ApplyDFS(file.Kruskal_list.getFirst().getNodeA().getArchi(),file.Kruskal_list.getFirst().getNodeA(),0);

System.out.println(cont);
if(cont != file.vertex){
    file.event.SetError("Il grafo non è connesso e non è possibile applicare Kruskal!");
    return;
}

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Premi invio per vedere ogni passaggio. Altrimenti in Option->Skip to End potrai vedere direttamente il risultato finale");
        alert.showAndWait();
        for (Edges edge : file.Kruskal_list) {
            edge.getLine().setStrokeWidth(0.2);
        }
        Menu File = new Menu("File");
        MenuItem Save = new MenuItem("Save as");
        Save.setOnAction(e ->{


                FileChooser filex = new FileChooser();
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
                } catch (FileNotFoundException t) {
                    t.printStackTrace();
                }
                Integer index = 0;
                stage.setOnCloseRequest(t -> stage.close());
                if (!name.equals(null)) {
                    try {
                        file.createNewFile();
                    } catch (IOException g) {
                        g.printStackTrace();
                    }

                    PrintStream WriteOnFIle = new PrintStream(fileTosave);
                    WriteOnFIle.println("Vertex:");
                    WriteOnFIle.println(cont);
                    index = 0;
                    WriteOnFIle.println("Root:");
                    WriteOnFIle.println(this.file.ListNode.getFirst().getElement());


                    WriteOnFIle.println("Elements:");
                    if(!this.file.ListNode.isEmpty()){
                        for (New_Node node : this.file.ListNode) {
                            if(!node.equals(this.file.ListNode.getFirst()))
                            WriteOnFIle.println(node.getElement());

                        }
                    }
                    WriteOnFIle.println("Edges:");
                    ind = 0;
                   while(ind < MinSpanningTree.size()){
                       if(MinSpanningTree.get(ind).equals(MinSpanningTree.get(0))) {
                           message = MinSpanningTree.get(0).getNodeA().getElement() + "->" + MinSpanningTree.get(0).getNodeB().getElement() + "("+MinSpanningTree.get(0).getWeight()+")";
                       }
                       else{
                           message = message + "->" + MinSpanningTree.get(ind).getNodeA().getElement() + "->" + MinSpanningTree.get(ind).getNodeB().getElement() + "("+MinSpanningTree.get(0).getWeight()+")";

                       }
                        ind++;
                    }
                    WriteOnFIle.println(message);
                    WriteOnFIle.println(";");
                    System.out.println("File saved!");

                }
//            file.SaveFile(MinSpanningTree.size());
        });

        MenuItem exitfile = new MenuItem("Exit");
        exitfile.setOnAction(e -> {

            e.consume();
            file.closeProgram();
        });
        MenuItem returntoGraph = new MenuItem("Torna al grafo");
        returntoGraph.setOnAction(e -> {

            file.ReprintGraph(layout);

        });
        File.getItems().addAll(exitfile);
        Menu Skip2 = new Menu("Option");
        MenuItem skip = new MenuItem("Skip To End");
        skip.setOnAction(e -> {
               SteCleaner = true;



             UF = new UnionFind(file.ListNode.size());
            for (Edges edge : file.Kruskal_list) {
                Edges front = edge;
                ind = 0;
                for (New_Node node : file.ListNode) {
                    if (node == edge.getNodeA()) {
                        k = ind;
                    } else if (node == edge.getNodeB()) {
                        j = ind;

                    }
                    ind++;

                }

                if (!UF.isSameSet(k, j)) {          // check
                    mst_cost += front.getWeight();            // add the weight of e to MST
                    UF.unionSet(k, j);// link them
                    System.out.println("Start node: " + edge.getNodeA().getElement() + " Arrive node: " + edge.getNodeB().getElement() + " Weight: " + edge.getWeight());
                    layout.getChildren().removeAll(edge.getLine(), edge.getNodeA().getCircle(), edge.getNodeB().getCircle(), edge.getNodeB().get_ElementBox(), edge.getNodeA().get_ElementBox());
                    edge.getLine().setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
                    edge.getLine().setFill(RED);
                    edge.getLine().setStroke(RED);
                    edge.getLine().setStrokeWidth(2.5);
                    layout.getChildren().addAll(edge.getLine(), edge.getNodeA().getCircle(), edge.getNodeB().getCircle(), edge.getNodeB().get_ElementBox(), edge.getNodeA().get_ElementBox());

                    MinSpanningTree.add(x, edge);
                    x++;
                }

            }
            Cleaner();
            File.getItems().clear();
            File.getItems().addAll(Save,returntoGraph,exitfile);
            menuBar.getMenus().removeAll(Skip2);
            layout.setTop(menuBar);
        });
        Skip2.getItems().addAll(skip);




            menuBar.getMenus().addAll(File,Skip2);
        layout.setTop(menuBar);

        UF = new UnionFind(file.ListNode.size());
        x = 0;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    //index = 0;

                    aux_index = index;
                    if (index < file.Kruskal_list.size()) {
                        if (!get) {

                            front = file.Kruskal_list.get(index);
                            front.getLine().setStyle("-fx-background-color: rgba(255, 165, 0, 0.5);");
                            //     front.getLine().setFill();
                            front.getLine().setStroke(ORANGE);
                            front.getLine().setStrokeWidth(2.5);
                            ind = 0;
                            for (New_Node node : file.ListNode) {
                                if (node == front.getNodeA()) {
                                    k = ind;
                                } else if (node == front.getNodeB()) {
                                    j = ind;

                                }
                                ind++;

                            }

                            get = true;
                        } else {
                            if (!UF.isSameSet(k, j)) {          // check
                                mst_cost += front.getWeight();            // add the weight of e to MST
                                UF.unionSet(k, j);// link them
                                System.out.println("Start node: " + front.getNodeA().getElement() + " Arrive node: " + front.getNodeB().getElement() + " Weight: " + front.getWeight());
                                layout.getChildren().removeAll(front.getLine(), front.getNodeA().getCircle(), front.getNodeB().getCircle(), front.getNodeB().get_ElementBox(), front.getNodeA().get_ElementBox());
                                front.getLine().setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
                                front.getLine().setFill(RED);
                                front.getLine().setStroke(RED);
                                front.getLine().setStrokeWidth(2.5);
                                layout.getChildren().addAll(front.getLine(), front.getNodeA().getCircle(), front.getNodeB().getCircle(), front.getNodeB().get_ElementBox(), front.getNodeA().get_ElementBox());
                                MinSpanningTree.add(x, front);
                                x++;

                            }
                            get = false;
                            index++;

                        }


                    } else {
                        Cleaner();
                        File.getItems().clear();
                        File.getItems().addAll(Save,returntoGraph,exitfile);
                        menuBar.getMenus().removeAll(Skip2);
                        layout.setTop(menuBar);
                    }
                }
            }
        });
        System.out.printf("MST cost = %d (Kruskal's)\n", mst_cost);



    }



    public void Cleaner(){
        for (Edges Edge:this.file.Kruskal_list) {

            for (Edges edge : MinSpanningTree) {
                if(Edge == edge){
                    found = true;
                    break;
                }


            }

            if (!found) {
                try {
                    file.layout.getChildren().remove(Edge.getLine());
                }
                catch (Exception e){

                }
            }
            found = false;

        }
    }
}
