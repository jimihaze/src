import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DistanceVector {

	static String local;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<node> graph = new ArrayList<node>();
	static node localNode = null;
	static Map<String, Integer> localDV = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws IOException {
		String[] initNode = br.readLine().split("//s+");  //First line of input, all nodes in the graph
		local = br.readLine();  //Second input, local node

		localNode = new node(local);
		localNode.DV.put(localNode.name, 0);

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
			int cost = 0;
			for(node n: graph){
				if(n.name == edgeParams[0]){
					currentNode = n;
					cost = Integer.parseInt(edgeParams[1]);
					currentNode.setAdjacent(true);
					currentNode.setDist(Integer.parseInt(edgeParams[1]));
					localNode.edges.add(new edge(currentNode, cost));
					currentNode.edges.add(new edge(localNode, cost));
					localNode.DV.put(currentNode.name, cost);
				}
			}
		}
		
		for(node neighbor: graph){
			if(neighbor.adjToLocal == true){
				for(node destination: graph){
					neighbor.Destinations.put(destination.name, -1);
				}
				neighbor.DV = localNode.DV;
			}
		}
		
		Iterator<String> it = localNode.DV.keySet().iterator();
		while((input = br.readLine()) != null){
			for(node n: graph){
				int min = Integer.MAX_VALUE;
				if(n.adjToLocal == true){
					if(localNode.DV.get(n.name) < min){
						min = localNode.DV.get(n.name);
					}
				}
			}
			
			
			if(!localDV.equals(localNode.DV)){
				System.out.println(localNode.name);
				while(it.hasNext()){
					String key = it.next();
					System.out.println(key + " " + localNode.DV.get(key));
					System.out.print('\n');
				}
			}
		}
	}
}

class node {
	public String name;
	public List<edge> edges = new ArrayList<edge>();
	public boolean adjToLocal;
	public int distToLocal;
	public Map<String, Integer> DV = new HashMap<String, Integer>();
	public Map<String, Integer> Destinations = new HashMap<String, Integer>();
	
	public node(String n){
		name = n;
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