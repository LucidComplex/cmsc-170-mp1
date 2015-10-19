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
 * @author MiriamMarie
 */
public class PacmanDFS extends Algorithm{

    
    private int nodesExpanded, pc;
    
    public PacmanDFS(char[][] maze, String mazeName) {
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
        Stack<Node> frontier = new Stack<>();
        List<Node> closed = new LinkedList<>();
        Stack<Node> solution = new Stack<>();
        frontier.add(startNode);
        Node current = null;
        endNode = ends.pop();
        
        do {
            // get current node from queue
            current = frontier.pop();
            closed.add(current);
            
            nodesExpanded++;
            // check if current node is goal
            if (current.equals(endNode)) {
                startNode = current;
                while (true) {
                    solution.add(current);
                    if (current.parent != null) {
                        current = current.parent;
                    } else {
                        break;
                    }
                }
                printMaze(endNode, frontier);
                
                closed.clear();
                //solution.clear();
                frontier.clear();
                if(!ends.isEmpty()){
                    solve();
                }
                
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
        } while (!frontier.isEmpty() && !ends.isEmpty());
        
        int pathCost = solution.size() - 1;
        pc = pc + pathCost;
        printSolution(solution);
        printMaze(endNode, frontier);

        System.out.println("Path Cost: " + pc);
        System.out.println("Nodes Expanded: " + nodesExpanded);
        System.out.println("Max Depth: " + maxDepth);
        System.out.println("Max Frontier Size: " + maxFrontierSize);
    }
    
}
