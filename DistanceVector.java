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
		String[] initNode = br.readLine().split("//s+");
		DVlength = initNode.length;
		local = br.readLine();
		for(int i=0; i<initNode.length; i++){
			if(initNode[i] == local){
				localNode = new node(local);
				graph.add(localNode);
			}
		}
		for(int i=0; i<initNode.length; i++){
			if(initNode[i] != local){
				graph.add(new node(initNode[i]));
			}
		}

		fauxLocal = localNode;
		String input;
		while((input = br.readLine()) != null){
			if(input.isEmpty()){
				continue;
			}
			String[] edgeParams = input.split("//s+");
			if(edgeParams.length == 1){
				for(node n: graph){
					if(n.name == edgeParams[0]){
						fauxLocal = n;
					}
				}
			}
			else{
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
	
	public node(String n){
		name = n;
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