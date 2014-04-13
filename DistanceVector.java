import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DistanceVector {

	static String local;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<node> graph = new ArrayList<node>();
	static node localNode = null;
	static node fauxLocal = null;
	static int DVlength;

	public static void main(String[] args) throws IOException {
		String[] initNode = br.readLine().split("//s+");  //First line of input, all nodes in the graph
		DVlength = initNode.length;
		local = br.readLine();  //Second input, local node

		localNode = new node(local, DVlength);
		graph.add(localNode);

		for(int i=0; i<initNode.length; i++){  //Adds all non local nodes to the graph
			if(initNode[i] != local){
				graph.add(new node(initNode[i], DVlength));
			}
		}

		fauxLocal = localNode;  //Faux local node to add edges between nodes, starts at local
		String input;
		while((input = br.readLine()) != null){  //Loop on read line, to take in more edges
			if(input.isEmpty()){
				continue;
			}
			String[] edgeParams = input.split("//s+");
			if(edgeParams.length == 1){  //Determines new faux local to add edges to that node
				for(node n: graph){
					if(n.name == edgeParams[0]){
						fauxLocal = n;
					}
				}
			}
			else{  //Actually adds edges between nodes given costs
				node currentNode = null;
				for(node n: graph){
					if(n.name == edgeParams[0]){
						currentNode = n;
					}
				}
				fauxLocal.edges.add(new edge(currentNode, Integer.parseInt(edgeParams[1])));
				currentNode.edges.add(new edge(localNode, Integer.parseInt(edgeParams[1])));
			}
		}
	}
}

class node {
	public String name;
	public List<edge> edges = new ArrayList<edge>();
	public int[] DV;
	
	public node(String n, int l){
		name = n;
		DV = new int[l];
	}
	
	public void updateDV(){
		/**Bellman Ford
		For all nodes z in the graph that are not the local node
		The distance from local to z is the min(costofedge(x,y) + Dy(z))
		**/
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