package enity.creature;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.swing.Timer;

public class AStarSearch {

	private static Timer timer;
	static boolean isTimeOut = false;

	public static void initTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isTimeOut)
					System.out.println("Time out");
				isTimeOut = true;
			}
		});
	}

	public static LinkedList<Point> findPathAStar(Point start, Point goal) {
		isTimeOut = false;
		timer.start();

		AStarNode goalNode = new AStarNode(goal);
		goalNode.estimatedCostToGoal = 0;

		AStarNode startNode = new AStarNode(start);
		startNode.pathParent = null;
		startNode.costFromStart = 0;
		startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);

		Comparator<AStarNode> comp = new Comparator<AStarNode>() {
			@Override
			public int compare(AStarNode a, AStarNode b) {

				return (int) (a.getCost() - b.getCost());
			}
		};

		PriorityQueue<AStarNode> open = new PriorityQueue<>(comp);
		open.add(startNode);

		ArrayList<AStarNode> closed = new ArrayList<>();

		while (!open.isEmpty()) {
			if (isTimeOut) {
				timer.stop();
				return null;
			}

			AStarNode curr = open.remove();
			if (curr.coord.equals(goal)) {
				return curr.buildPath();
			} else {
				for (AStarNode neighbor : curr.getNeighbors()) {
					if (isTimeOut) {
						timer.stop();
						return null;
					}

					double costFromStart = curr.costFromStart + curr.getCost(neighbor);
					if (!open.contains(neighbor) && !closed.contains(neighbor)
							|| neighbor.costFromStart >costFromStart ) {

						neighbor.pathParent = curr;
						neighbor.costFromStart = costFromStart;
						neighbor.estimatedCostToGoal = neighbor.getEstimatedCost(goalNode);
						if (closed.contains(neighbor)) {
							closed.remove(neighbor);
						}
						if (!open.contains(neighbor)) {
							open.add(neighbor);
						}
					}
				}
			}
			closed.add(curr);
		}
		return null;
	}
}

class AStarNode {

	Point coord;
	AStarNode pathParent;
	double costFromStart;
	double estimatedCostToGoal;

	public AStarNode(int x, int y) {
		this.coord = new Point(x, y);
	}

	public AStarNode(Point p) {
		this.coord = p;
	}

	public double getCost() {
		return this.costFromStart + this.estimatedCostToGoal;
	}

	public int compareTo(Object other) {
		double thisValue = this.getCost();
		double otherValue = ((AStarNode) other).getCost();

		double v = thisValue - otherValue;
		return (v > 0) ? 1 : (v < 0) ? -1 : 0; // sign function
	}

	public double getCost(AStarNode neighbor) {
		return this.coord.distance(neighbor.coord);
	}

	public double getEstimatedCost(AStarNode goalNode) {
		return this.coord.distance(goalNode.coord);
	}

	public ArrayList<AStarNode> getNeighbors() {
		ArrayList<AStarNode> list = new ArrayList<>();
		list.add(new AStarNode(this.coord.x - 1, this.coord.y + 0)); // left
		list.add(new AStarNode(this.coord.x - 1, this.coord.y + 0)); // right

		list.add(new AStarNode(this.coord.x - 0, this.coord.y - 1)); // up
		list.add(new AStarNode(this.coord.x - 0, this.coord.y + 1)); // down

		// list.add(new AStarNode(this.coord.x - 1, this.coord.y - 1)); // left up
		// list.add(new AStarNode(this.coord.x + 1, this.coord.y - 1)); // right up
		//
		// list.add(new AStarNode(this.coord.x - 1, this.coord.y + 1)); // left down
		// list.add(new AStarNode(this.coord.x + 1, this.coord.y + 1)); // right, down
		return list;
	}

	public LinkedList<Point> buildPath() {
		LinkedList<Point> path = new LinkedList<>();
		AStarNode current = this;
		while (current.pathParent != null) {
			path.addFirst(current.coord);
			current = current.pathParent;
		}
		path.addFirst(current.coord);
		return path;
	}
}