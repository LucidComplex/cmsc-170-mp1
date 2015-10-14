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
    
    public BFS(char[][] maze, String mazeName) {
        this.init(maze, mazeName);
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
        int expandCount = 0;
        int maxDepth = 0;
        int maxFrontierSize = 0;
        frontier.add(startNode);
        Node current = null;
        int iter = 0;
        do {
            if (mazeName.matches("tinyMaze")) {
                System.out.println("Iteration #" + ++iter);
                System.out.println("Current Node: " + (current != null ? current : "None"));
                StringBuilder sb = new StringBuilder("Frontier: ");
                for (Node n : frontier) {
                    sb.append(n).append(" F Value: ").append(getCost(n)).append("; ");
                }
                sb.delete(sb.length() - 2, sb.length());
                System.out.println(sb);
                String lines = Arrays.deepToString(maze);
                
                for (int i = 0; i<maze.length;i++){
                    for(int j = 0; j<maze[i].length;j++){
                        System.out.print(maze[i][j]);
                    }
                    System.out.println("");
                }
            }
            int depth;
            if (current != null) {
                maze[current.x][current.y] = ' ';
            }
            current = frontier.remove();
            if (mazeName.matches("tinyMaze")) {
                maze[current.x][current.y] = 'C';
            }
            closed.add(current);
            expandCount++;
            if (current.c == 'P') {
                while (true) {
                    solution.add(current);
                    if (current.parent != null) {
                        current = current.parent;
                    } else {
                        break;
                    }
                }
                break;
            }
            
            // check depth
            Node temp = current;
            depth = 0;
            while (temp != null) {
                depth++;
                temp = temp.parent;
            }
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            populateChildren(current);
            for (Node child : current.children) {
                if (!frontier.contains(child) && !closed.contains(child)) {
                    frontier.add(child);
                    maze[child.x][child.y] = 'F';
                }
            }
            if (frontier.size() > maxFrontierSize) {
                maxFrontierSize = frontier.size();
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
        System.out.println("Nodes Expanded: " + expandCount);
        System.out.println("Max Depth: " + maxDepth);
        System.out.println("Max Frontier Size: " + maxFrontierSize);
        
    }
}
