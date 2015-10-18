/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author mary'sRose
 */
public class UniformCost extends Algorithm {

    public UniformCost(char[][] maze, String mazeName) {
        this.init(maze, mazeName);
        iter = 0;
        maxDepth = 0;
        maxFrontierSize = 0;
    }

    @Override
    public void solve() {
        Queue<Node> frontier = new LinkedList<>();
        List<Node> closed = new LinkedList<>();
        Stack<Node> solution = new Stack<>();
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

        int pathCost = solution.size() - 1;
        printSolution(solution);
        printMaze(endNode, frontier);

        System.out.println("Path Cost: " + pathCost);
        System.out.println("Nodes Expanded: " + closed.size());
        System.out.println("Max Depth: " + maxDepth);
        System.out.println("Max Frontier Size: " + maxFrontierSize);

    }

    @Override
    public double heuristic(Node node) {
        return 0;
    }

    @Override
    public double getCost(Node n) {
        double cost = -1;
        cost += heuristic(n);
        while (n != null) {
            cost += cost2(n);
            n = n.parent;
        }
        return cost;
    }

    public double cost1(Node n) {
        return 1 / (2 * n.x);
    }

    public double cost2(Node n) {
        return Math.pow(2, n.x);
    }

}
