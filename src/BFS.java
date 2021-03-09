import java.util.*;

public class BFS {
    static Node bfs(Node startNode, Node endNode) {

        Set<Node> visitedNodes = new HashSet<Node>();
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(startNode);
        visitedNodes.add(startNode);
        int nodeExplored = 0;
        while (!queue.isEmpty()) {

            Node curNode = queue.remove();
            // System.out.println(curNode);
            nodeExplored++;
            if (curNode.equals(endNode)) {
                // traceBack(curNode);
                System.out.print("| Pregledana vozlisca: " + nodeExplored + " ");
                return curNode;
            } else {
                for (Node node : curNode.getNeighbours()) {
                    if (!visitedNodes.contains(node)) {
                        visitedNodes.add(node);
                        queue.add(node);
                    }

                }

            }

        }

        return null;
    }

}
