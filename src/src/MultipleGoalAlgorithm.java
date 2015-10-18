package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import mazeui.Coordinate;
import mazeui.TinyMazeUI;

public abstract class MultipleGoalAlgorithm extends Algorithm {

    protected List<Node> endNodes;

    @Override
    public void init(char[][] maze, String mazeName) {
        this.mazeName = mazeName;
        this.maze = maze;
        startNode = null;
        endNodes = new LinkedList<>();
        mui = new TinyMazeUI();

        // populate start and goal nodes
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                if (maze[x][y] == 'P') {
                    startNode = new Node(x, y, 'P');
                } else if (maze[x][y] == '.') {
                    endNodes.add(new Node(x, y, '.'));
                }
            }
        }

        if (mazeName.matches("tinyMaze")) {
            mui.parse2DArray(maze);
        }
    }

    public void printSolution(Queue<Node> solution) {
        StringBuilder pathFound = new StringBuilder("Path Found: ");

        ArrayList<Coordinate> paths = new ArrayList<>();

        while (!solution.isEmpty()) {
            Node solutionNode = solution.remove();
            pathFound.append(solutionNode).append(" -> ");
            maze[solutionNode.x][solutionNode.y] = '/';
            paths.add(new Coordinate(solutionNode.x, solutionNode.y));
        }

        if (mazeName.matches("tinyMaze")) {
            mui.setPath(paths);
        }

        pathFound.delete(pathFound.length() - 4, pathFound.length());
        System.out.println(pathFound);
    }
}
