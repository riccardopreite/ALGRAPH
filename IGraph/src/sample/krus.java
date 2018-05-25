package sample;
import java.util.*;

public class krus {
	int father[] = new int[100]; //max or equal nodes that i have
	
	int find(int x) {
		if(father[x]==x) {
			return x;
		}
		return find(father[x]);
	}
	
	void unite(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		father[fx] = fy;
	}
	
	public static void main(String args[]) {
		Scanner stdin= new Scanner(System.in);
		//richiede l'importazione delle java.util
		//dichiarazione dell'oggetto sample di kruskal
		krus sample = new krus();
		//inizializzazione dei father con ciclo for
		for(int i=0; i < 100; i++) {
			sample.father[i] = i;
			//each node described with the father itself
		}
		//dichiarazione delle variabili per caricare l'input
		int n, m;
		int a, b, w;
		
		//Array List per la creazione degli edges. 
		ArrayList<pair3> edges = new ArrayList<pair3>(); //Default constructor for array list edges
		
		//caricamento dell'input
		n = stdin.nextInt();
		m = stdin.nextInt();
		for(int i=0; i < m; i++) {
			a = stdin.nextInt();
			b = stdin.nextInt();
			w = stdin.nextInt();
			edges.add(new pair3(w, a, b)); //richiama il costruttore di pair3
		}
		
		System.out.println(); //separa l'input dall'output
		
		int mst_weight = 0;
		int mst_edges = 0;
		int mst_ni = 0; //riguarda l'indice che servirà per il ciclo while sottostante
		
		//STEP 1: Sorting della array list degli archi 
		Collections.sort(edges, new Comparator<pair3>() {
			//Comparator è un'interfaccia che serve per applicare ordinamento.
			//In pratica crea una nuova classe effettivamente
			@Override public int compare(pair3 p1, pair3 p2) {
				//Override viene messo per fare appunto l'override del metodo esistente
				//infatti all'interno inserisce un nuovo metodo con delle variabili "nuove" di tipo pair3
				return p1.w - p2.w;
			}
		});
		//STEP 2 - 3;
		while((mst_edges < n-1) || (mst_ni < m)) {
		//we brake the edges into three integers they describe it
			a = edges.get(mst_ni).a;
			b = edges.get(mst_ni).b;
			w = edges.get(mst_ni).w;
			
		//controllo if se i nodi sono già connessi
			if(sample.find(a) != sample.find(b)) {
				//unisco
				sample.unite(a, b);
				//aggiungo il peso dell'arco
				mst_weight += w;
				//stampo
				System.out.println(a + " " + b + " " + w);
				mst_edges++;
			}
				mst_ni++; //cambia l'indice per scorrere la lista
		}
				System.out.println("Il peso della MST è: " + mst_weight);
				
				//finito
				
	}
}
		
	

