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
	
	public static void main(String[] args) throws IOException {
		String[] initNode = br.readLine().split("//s+");
		local = br.readLine();
		for(int i=0; i<initNode.length; i++){
			if(initNode[i] == local){
				graph.add(new node(local));
			}
		}
		for(int i=0; i<initNode.length; i++){
			if(initNode[i] != local){
				graph.add(new node(initNode[i]));
			}
		}
		for(node n: graph){
			if(n.name == local){
				localNode = n;
			}
		}
		
		String input;
		while((input = br.readLine()) != null){
			node currentNode = null;
			for(node n: graph){
				if(n.name == input){
					currentNode = n;
				}
			}
			String[] edgeParams = input.split("//s+");
			localNode.edges.add(new edge(currentNode, Integer.parseInt(edgeParams[1])));
			currentNode.edges.add(new edge(localNode, Integer.parseInt(edgeParams[1])));
		}
	}
}

class node {
	public String name;
	public List<edge> edges = new ArrayList<edge>();
	
	public node(String n){
		name = n;
	}
}

class edge {
	node target;
	int cost;
	
	public edge(node t, int c){
		target = t;
		cost = c;
	}
}