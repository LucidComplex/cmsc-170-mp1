/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author MiriamMarie
 */
public class BFS extends Algorithm {
	private int iter;
    
    public BFS(char[][] maze, String mazeName) {
        this.init(maze, mazeName);
		iter = 0;
    }
    
    @Override
    public double heuristic(Node node) {
        return 0;
    }
    
    @Override
    public void solve() {
        Queue<Node> frontier = new LinkedList<>();
        List<Node> closed = new LinkedList<>();
        Stack<Node> solution = new Stack<>();
        int maxDepth = 0;
        int maxFrontierSize = 0;
        frontier.add(startNode);
        Node current = null;
        do {
			// get current node from queue
            current = frontier.remove();
            closed.add(current);

			// check if current node is goal
            if (current.equals(endNode)) {
                while (true) {
                    solution.add(current);
                    if (current.parent != null) {
                        current = current.parent;
                    } else {
                        break;
                    }
                }
				printMaze(endNode, frontier);
                break;
            }

			// add walkable neighbors to frontier
            populateChildren(current);
            for (Node child : current.children) {
                if (!frontier.contains(child) && !closed.contains(child)) {
                    frontier.add(child);
					if (child.equals(endNode)) {
						maze[child.x][child.y] = 'f';
					} else {
						maze[child.x][child.y] = 'F';
					}
                }
            }
            if (frontier.size() > maxFrontierSize) {
                maxFrontierSize = frontier.size();
            }

			printMaze(current, frontier);
            
            // check depth
            int depth;
            Node temp = current;
            depth = 0;
            while (temp != null) {
                depth++;
                temp = temp.parent;
            }
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        } while (!frontier.isEmpty());
        StringBuilder pathFound = new StringBuilder("Path Found: ");
        int pathCost = solution.size() - 1;
        while (!solution.isEmpty()) {
            pathFound.append(solution.pop()).append(" -> ");
        }
        pathFound.delete(pathFound.length() - 4, pathFound.length());
        System.out.println(pathFound);
        System.out.println("Path Cost: " + pathCost);
        System.out.println("Nodes Expanded: " + closed.size());
        System.out.println("Max Depth: " + maxDepth);
        System.out.println("Max Frontier Size: " + maxFrontierSize);
        
    }

	private void printMaze(Node current, Queue<Node> frontier) {
		// print maze on tinyMaze
		if (mazeName.matches("tinyMaze")) {
			// set current node to C in map
			maze[current.x][current.y] = 'C';
			System.out.println("Iteration #" + ++iter);
			System.out.println("Current Node: " + current);
			System.out.println("Frontier:");
			for (Node n : frontier) {
				System.out.println("  " + n + " F Value: " + getCost(n));
			}
			String lines = Arrays.deepToString(maze);
			for (int i = 0; i<maze.length;i++){
				for(int j = 0; j<maze[i].length;j++){
					System.out.print(maze[i][j]);
				}
				System.out.println("");
			}
			// reset how current node looks like after printing
			if (current.equals(startNode)) {
				maze[current.x][current.y] = '.';
			} else {
				maze[current.x][current.y] = ' ';
			}
		}
	}
}
