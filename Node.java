import java.util.Set;

public interface Node<T> {
	
	double getHeuristic(T goal);
	
	double getTraversalCost(T neighbour);

	Set<T> getNeighbours();
	
	void markStar();
	
	void markVisited(int i, int j); 
}