import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DistanceVector {

	static String local;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<node> graph = new ArrayList<node>();
	static node localNode = null;
	static int DVlength;

	public static void main(String[] args) throws IOException {
		String[] initNode = br.readLine().split("//s+");  //First line of input, all nodes in the graph
		DVlength = initNode.length;
		local = br.readLine();  //Second input, local node

		localNode = new node(local);

		for(int i=0; i<initNode.length; i++){  //Adds all non local nodes to the graph
			if(initNode[i] != local){
				node node = new node(initNode[i]);
				graph.add(node);
				node.setDist(-1);
			}
		}

		String input;
		while((input = br.readLine()) != null){
			String[] edgeParams = input.split("//s+");
			node currentNode = null;
			for(node n: graph){
				if(n.name == edgeParams[0]){
					currentNode = n;
					currentNode.setAdjacent(true);
					currentNode.setDist(Integer.parseInt(edgeParams[1]));
				}
			}
			localNode.edges.add(new edge(currentNode, Integer.parseInt(edgeParams[1])));
			currentNode.edges.add(new edge(localNode, Integer.parseInt(edgeParams[1])));
		}
		
		for(node neighbor: graph){
			if(neighbor.adjToLocal == true){
				
			}
		}
	}
}

class node {
	public String name;
	public List<edge> edges = new ArrayList<edge>();
	public boolean adjToLocal;
	public int distToLocal;
	public HashMap<String, Integer> DV = new HashMap<String, Integer>();
	
	public node(String n){
		name = n;
	}

	public void updateDV(){
		/**Bellman Ford
		For all nodes z in the graph that are not the local node
		The distance from local to z is the min(costofedge(x,y) + Dy(z))
		 **/
	}

	public void setAdjacent(boolean b){
		adjToLocal = b;
	}
	
	public void setDist(int i){
		distToLocal = i;
	}
}

class edge {
	public node target;
	public int cost;

	public edge(node t, int c){
		target = t;
		cost = c;
	}
}