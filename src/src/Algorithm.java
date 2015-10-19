/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import mazeui.Coordinate;
import mazeui.MazeState;
import mazeui.MazeUI;
import mazeui.TinyMazeUI;

/**
 *
 * @author MiriamMarie
 */
public abstract class Algorithm {

    protected Node startNode;
    protected Node endNode;
    protected char[][] maze;
    protected String mazeName;
    protected Stack<Node> ends = new Stack<>();
    
    protected int iter;
    protected int maxFrontierSize;
    protected int maxDepth;
    
    //Creates a TinyMaze GUI
    MazeUI mui;

    public void init(char[][] maze, String mazeName) {
        // look for start point
        this.mazeName = mazeName;
        startNode = null;
        endNode = null;
        this.maze = maze;
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                if (maze[x][y] == 'P') {
                    startNode = new Node(x, y, 'P');
                } else if (maze[x][y] == '.') {
                    endNode = new Node(x, y, '.');
                }
            }
            if (startNode != null && endNode != null) {
                break;
            }
        }
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
               if(maze[x][y]=='.'){
                   ends.add(new Node(x, y, '.'));
               }
            }
        }
        System.out.println("Stack: " + ends.size());
        //Initializes the GUI Maze by parsing the 2D Char array
        if(mazeName.matches("tinyMaze")){
            mui = new TinyMazeUI();
            mui.parse2DArray(maze);
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
            // set current node to C in map
//        if (!mazeName.matches("tinyMaze")){
//            return;
//        }
            maze[current.x][current.y] = 'C';
            System.out.println("Iteration #" + ++iter);
            System.out.println("Current Node: " + current);
            System.out.println("Frontier:");
            
            ArrayList<Coordinate> fs = new ArrayList<>();
            for (Node n : frontier) {
                System.out.println("  " + n + " F Value: " + getCost(n));
                fs.add(new Coordinate(n.x, n.y));
            }
            //Adds a maze state to the GUI maze for each iteration
            if(mazeName.matches("tinyMaze")){
                mui.addState(new MazeState(current, fs));
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
                maze[current.x][current.y] = 'P';
            } else {
                maze[current.x][current.y] = '=';
            }
//        }
    }
    
    protected void printSolution(Stack<Node> solution) {
        StringBuilder pathFound = new StringBuilder("Path Found: ");
        
        ArrayList<Coordinate> paths = new ArrayList<>();
        
        
        while (!solution.isEmpty()) {
            Node solutionNode = solution.pop();
            pathFound.append(solutionNode).append(" -> ");
            maze[solutionNode.x][solutionNode.y] = '/';
            paths.add(new Coordinate(solutionNode.x, solutionNode.y));
        }
        
        if(mazeName.matches("tinyMaze")){
            mui.setPath(paths);
        }
        
        pathFound.delete(pathFound.length() - 4, pathFound.length());
        System.out.println(pathFound);
    }
}
