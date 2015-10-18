/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author MiriamMarie
 */
public abstract class Algorithm {

    protected Node startNode;
    protected Node endNode;
    protected char[][] maze;
    protected String mazeName;
    
    protected int iter;
    protected int maxFrontierSize;
    protected int maxDepth;

    public void init(char[][] maze, String mazeName) {
        // look for start point
        this.mazeName = mazeName;
        startNode = null;
        endNode = null;
        this.maze = maze;
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                if (maze[x][y] == '.') {
                    startNode = new Node(x, y, '.');
                } else if (maze[x][y] == 'P') {
                    endNode = new Node(x, y, 'P');
                }
            }
            if (startNode != null && endNode != null) {
                break;
            }
        }
    }

    public abstract void solve();

    public abstract double heuristic(Node node);

    protected void populateChildren(Node node) {
        List<Node> children = new LinkedList<>();

        // add only walkable children
        Node[] possibleChildren
                = {new Node(node.x, node.y - 1, maze[node.x][node.y - 1]),
                    new Node(node.x + 1, node.y, maze[node.x + 1][node.y]),
                    new Node(node.x, node.y + 1, maze[node.x][node.y + 1]),
                    new Node(node.x - 1, node.y, maze[node.x - 1][node.y])};

        for (Node child : possibleChildren) {
            if (child.c != '%') {
                child.parent = node;
                children.add(child);
            }
        }
        node.children = children;
    }

    protected double getCost(Node n) {
        double cost = -1;
        cost += heuristic(n);
        while (n != null) {
            cost++;
            n = n.parent;
        }
        return cost;
    }
    
    protected void printMaze(Node current, Collection<Node> frontier) {
        // print maze on tinyMaze
//        if (mazeName.matches("tinyMaze")) {
            // set current node to C in map
            maze[current.x][current.y] = 'C';
            System.out.println("Iteration #" + ++iter);
            System.out.println("Current Node: " + current);
            System.out.println("Frontier:");
            for (Node n : frontier) {
                System.out.println("  " + n + " F Value: " + getCost(n));
            }
            String lines = Arrays.deepToString(maze);
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    System.out.print(maze[i][j]);
                }
                System.out.println("");
            }
            // reset how current node looks like after printing
            if (current.equals(startNode)) {
                maze[current.x][current.y] = '.';
            } else {
                maze[current.x][current.y] = '=';
            }
//        }
    }
    
    protected void printSolution(Stack<Node> solution) {
        StringBuilder pathFound = new StringBuilder("Path Found: ");
        
        while (!solution.isEmpty()) {
            Node solutionNode = solution.pop();
            pathFound.append(solutionNode).append(" -> ");
            maze[solutionNode.x][solutionNode.y] = '/';
        }
        pathFound.delete(pathFound.length() - 4, pathFound.length());
        System.out.println(pathFound);
    }
}
