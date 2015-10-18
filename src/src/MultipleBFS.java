/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author MiriamMarie
 */
public class MultipleBFS extends MultipleGoalAlgorithm {

    public MultipleBFS(char[][] maze, String mazeName) {
        this.init(maze, mazeName);
        iter = 0;
        maxDepth = 0;
        maxFrontierSize = 0;
    }

    @Override
    public double heuristic(Node node) {
        return 0;
    }

    @Override
    public void solve() {
        Queue<Node> frontier = new LinkedList<>();
        List<Node> closed = new LinkedList<>();
        Queue<Node> solution = new LinkedList<>();
        frontier.add(startNode);
        solution.add(startNode);
        Node current = null;
        do {
            // get current node from queue
            current = frontier.remove();
            closed.add(current);

            // check if current node is goal
            if (endNodes.contains(current)) {
                solution.add(current);
                // "restart" search with current node as startNode 
                ((LinkedList) frontier).add(0, current);
                startNode = current;
                endNodes.remove(current);
            }
            // end loop if all goals reached
            if (endNodes.isEmpty()) {
                endNode = current;
                printMaze(endNode, frontier);
                break;
            }

            // add walkable neighbors to frontier
            populateChildren(current);
            for (Node child : current.children) {
                if (!frontier.contains(child) && !closed.contains(child)) {
                    frontier.add(child);
                    if (endNodes.contains(child)) {
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

        int pathCost = solution.size() - 1;
        printSolution(solution);
        printMaze(endNode, frontier);

        System.out.println("Path Cost: " + pathCost);
        System.out.println("Nodes Expanded: " + closed.size());
        System.out.println("Max Depth: " + maxDepth);
        System.out.println("Max Frontier Size: " + maxFrontierSize);

    }

}
