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
public class BFS extends Algorithm {
    
    @Override
    public void solve() {
        Queue<Node> frontier = new LinkedList<>();
        List<Node> closed = new LinkedList<>();
        Stack<Node> solution = new Stack<>();
        int expandCount = 0;
        int maxDepth = 0;
        int maxFrontierSize = 0;
        frontier.add(startNode);
        Node current;
        do {
            int depth;
            current = frontier.remove();
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
                }
            }
            if (frontier.size() > maxFrontierSize) {
                maxFrontierSize = frontier.size();
            }
        } while (!frontier.isEmpty());
        StringBuilder pathFound = new StringBuilder("Path Found: ");
        int pathCost = solution.size();
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
