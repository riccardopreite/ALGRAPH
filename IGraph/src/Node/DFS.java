package Node;

import java.util.LinkedList;

public class DFS {
    public DFS() {



    }
    public Integer ApplyDFS(LinkedList<Edges>list, New_Node ToBeExamined,Integer cont){

        ToBeExamined.setConnect(true);
        cont++;

        System.out.println(list.size());
       for (Edges edge : list) {

           if (!edge.getNodeB().getConnect()) {

               cont = ApplyDFS(edge.getNodeB().getArchi(), edge.getNodeB(), cont);
           }

        }

return cont;
    }

}
