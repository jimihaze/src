import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DistanceVector {

	static String local;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<node> graph = new ArrayList<node>();
	
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
	}

}

class node {
	String name;
	List<edge> edges = new ArrayList<edge>();
	
	public node(String n){
		name = n;
	}
}

class edge {
	String target;
	int cost;
}