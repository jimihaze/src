
public class DistanceVector {

	static node local;
	
	public static void main(String[] args) {

	}

}

public class node {
	String name;
	List<edge> edges = new ArrayList<edge>();
}

public class edge {
	String target;
	int cost;
}