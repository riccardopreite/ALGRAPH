package sample.Node;

import java.util.LinkedList;

public class DFS {
    public DFS() {



    }
    public Integer ApplyDFS(LinkedList<Edges>list, New_Node ToBeExamined,Integer cont){

        ToBeExamined.setConnect(true);
        cont++;

        System.out.println(list.size());
       for (Edges edge : list) {
            System.out.println(" Element: " + edge.getNodeB().getElement());
            if (!edge.getNodeB().getConnect()) {

                System.out.println("RECALL " + cont);
                System.out.println("Element examed set true " +edge.getNodeB().getElement() + " Size of list ");

                 cont =  ApplyDFS(edge.getNodeB().getArchi(),edge.getNodeB(),cont);
            }

        }

return cont;
    }

}
