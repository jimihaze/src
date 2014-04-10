import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DistanceVector {

	static node local;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {

	}

}

class node {
	String name;
	List<edge> edges = new ArrayList<edge>();
}

class edge {
	String target;
	int cost;
}