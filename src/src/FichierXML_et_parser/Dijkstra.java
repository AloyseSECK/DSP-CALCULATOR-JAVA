package src.FichierXML_et_parser;

import java.io.*;
import java.util.*;
 
public class Dijkstra {
   private static final Graph.Edge[] GRAPH = {
      new Graph.Edge("Dakar", "Mbour", 85),
      new Graph.Edge("Mbour", "Dakar", 85),
      new Graph.Edge("Dakar", "Thies", 70),
      new Graph.Edge("Thies", "Dakar", 70),
      new Graph.Edge("Dakar", "Saint-Louis", 264),
      new Graph.Edge("Saint-Louis", "Dakar", 264),
      new Graph.Edge("Mbour", "Fatick", 72),
      new Graph.Edge("Fatick", "MBour", 72),
      new Graph.Edge("Mbour", "Thies", 54),
      new Graph.Edge("Thies", "Louga", 133),  
      new Graph.Edge("Thies", "Mbour", 54), 
      new Graph.Edge("Thies", "Diourbel", 76),
      new Graph.Edge("Diourbel", "Thies", 76), 
      new Graph.Edge("Saint-Louis", "Louga", 61),
      new Graph.Edge("Saint-Louis", "Matam", 429),
      new Graph.Edge("Matam", "Saint-Louis", 429), 
      new Graph.Edge("Louga", "Saint-Louis", 61),
      new Graph.Edge("Louga", "Thies", 133),
      new Graph.Edge("Kaolack", "Fatick", 37), 
      new Graph.Edge("Fatick", "Kaolack", 37),
      new Graph.Edge("Diourbel", "Touba", 42),
      new Graph.Edge("Touba", "Diourbel", 42),
      new Graph.Edge("Diourbel", "Kaolack", 64),
      new Graph.Edge("Kaolack", "Diourbel", 64),
      new Graph.Edge("Diourbel", "Banjul", 178),
      new Graph.Edge("Banjul", "Diourbel", 178),
      new Graph.Edge("Kaolack", "Banjul", 113),
      new Graph.Edge("Banjul", "Kaolack", 113),
      new Graph.Edge("Touba", "Louga", 138),
      new Graph.Edge("Louga", "Touba", 138),
      new Graph.Edge("Touba", "Banjul", 220),
      new Graph.Edge("Banjul", "Touba", 220),
      new Graph.Edge("Banjul", "Tamba", 388),
      new Graph.Edge("Tamba", "Banjul", 388),
      new Graph.Edge("Banjul", "Kolda", 389),
      new Graph.Edge("Kolda", "Banjul", 389),
      
   };
   
 
   public static void main(String[] args) {
	   Scanner sc =  new Scanner(System.in);
	   String[] region = {"Dakar", "Mbour", "Thies", "Fatick",
				  "Saint-Louis", "Diourbel", "Touba", "Banjul",
				  "Kaolack", "Tamba", "Kolda", "Matam", "Lougga"};
	   System.out.println("Bonjour veuillez choisir la ville de départ et la ville de destination\n");
	    System.out.println("*************");
	    for(int i=0; i< region.length; i++) {
	    	System.out.println( "\t "+region[i]+" ==> Code "+i);
	    }
	    System.out.println("************\n");
	    
	   System.out.println("Ville de départ : ");
	   		int depart = sc.nextInt();
	   System.out.println("Ville de destination");
	   		int destination = sc.nextInt();
	  
	   String Depart = null,Destination = null;
	   Depart = region[depart];
	   Destination = region[destination];
	   
      Graph g = new Graph(GRAPH);
      
      String START = Depart;
      String END = Destination;
      System.out.println("Le chemin a prendre est : ");
      g.dijkstra(START);
      g.printPath(END);
     
   }
}
 
class Graph {
   private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
 
   /** One edge of the graph (only used by Graph constructor) */
   public static class Edge {
      public final String v1, v2;
      public final int dist;
      public Edge(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }
 
   /** One vertex of the graph, complete with mappings to neighbouring vertices */
  public static class Vertex implements Comparable<Vertex>{
	public final String name;
	public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	public Vertex previous = null;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();
 
	public Vertex(String name)
	{
		this.name = name;
	}
 
	private void printPath()
	{
		if (this == this.previous)
		{
			System.out.printf("%s", this.name);
		}
		else if (this.previous == null)
		{
			System.out.printf("%s(unreached)", this.name);
		}
		else
		{
			this.previous.printPath();
			System.out.printf(" -> %s(%d)", this.name, this.dist);
		}
	}
 
	public int compareTo(Vertex other)
	{
		if (dist == other.dist)
			return name.compareTo(other.name);
 
		return Integer.compare(dist, other.dist);
	}
 
	@Override public String toString()
	{
		return "(" + name + ", " + dist + ")";
	}
}
 
   /** Builds a graph from a set of edges */
   public Graph(Edge[] edges) {
      graph = new HashMap<>(edges.length);
 
      //one pass to find all vertices
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
 
      //another pass to set neighbouring vertices
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
      }
   }
 
   /** Runs dijkstra using a specified source vertex */ 
   public void dijkstra(String startName) {
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();
 
      // set-up vertices
      for (Vertex v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
 
      dijkstra(q);
   }
 
   /** Implementation of dijkstra's algorithm using a binary heap. */
   private void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
 
         //look at distances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey(); //the neighbour in this iteration
 
            final int alternateDist = u.dist + a.getValue();
            if (alternateDist < v.dist) { // shorter path to neighbour found
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /** Prints a path from the source to the specified vertex */
   public void printPath(String endName) {
      if (!graph.containsKey(endName)) {
         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
         return;
      }
 
      graph.get(endName).printPath();
      System.out.println();
   }
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() {
      for (Vertex v : graph.values()) {
         v.printPath();
         System.out.println();
      }
   }
}