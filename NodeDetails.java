import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NodeDetails {
	private final ArrayList<ArrayList<String>> map;

	public class MapNode implements Node<MapNode> {
		private final int x, y;

		public MapNode(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double getHeuristic(MapNode goal) {			
				return Math.abs(x - goal.x) + Math.abs(y - goal.y);
		}

		public double getTraversalCost(MapNode neighbour) {
			return 1;
		}

		public Set<MapNode> getNeighbours() {
			Set<MapNode> neighbours = new HashSet<MapNode>();

			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if ((i == x && j == y) || i < 0 || j < 0 || (i==x+1 && j==y+1) || (i==x-1 && j==y+1) || (i==x-1 && j==y-1) || (i==x+1 && j==y-1)
							|| i > map.size()-1 || j > map.get(i).size()-1
							 || map.get(i).get(j).equalsIgnoreCase("W")) {
						continue;
					}

					// TODO: create cache instead of recreation
					neighbours.add(new MapNode(i, j));
					markVisited(i, j);
				}
			}

			return neighbours;
		}
		
		public void markStar() {
			if(!map.get(this.x).get(this.y).equalsIgnoreCase("S") && !map.get(this.x).get(this.y).equalsIgnoreCase("E"))
				map.get(this.x).set(this.y, "*");
		}
		
		@Override
		public void markVisited(int i, int j) {
			if(!map.get(i).get(j).equalsIgnoreCase("S") && !map.get(i).get(j).equalsIgnoreCase("E"))
				map.get(i).set(j, "\"");
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MapNode other = (MapNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private NodeDetails getOuterType() {
			return NodeDetails.this;
		}
	}

	public NodeDetails(ArrayList<ArrayList<String>> map) {
		this.map = map;
	}

	public List<MapNode> findPath(int xStart, int yStart, int xGoal, int yGoal) {
		return PathFinder.doAStar(new MapNode(xStart, yStart), new MapNode(
				xGoal, yGoal));
	}

}