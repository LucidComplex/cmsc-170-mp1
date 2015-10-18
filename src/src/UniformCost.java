/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
        List<Node> closed = new LinkedList<>();
        Stack<Node> solution = new Stack<>();
        Node current = null;
        UniformCost that = this;

        //Used to choose the node with the best/lowest cost
        AbstractQueue<Node> frontier
                = new PriorityQueue<>(new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        double cost1 = that.getCost(o1);
                        double cost2 = that.getCost(o2);

                        if (cost1 > cost2) {
                            return 1;
                        } else if (cost1 < cost2) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });

        frontier.add(startNode);
        do {
            // grab best node from frontier
            current = frontier.remove();
            closed.add(current);

            // check if current node is goal node: Builds up stack used for tracing
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

            // populate frontier with walkable nodes
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

            //Set frontier size
            if (frontier.size() > maxFrontierSize) {
                maxFrontierSize = frontier.size();
            }

            printMaze(current, frontier);

            // Search max depth
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
    protected double getCost(Node n) {
        return cost1(n);
    }

    public double cost1(Node n) {
        return Math.pow(0.5, n.y);
    }

    public double cost2(Node n) {
        return Math.pow(2, n.y);
    }

    @Override
    public double heuristic(Node node) {
        return 0;
    }

}
