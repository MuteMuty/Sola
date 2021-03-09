import java.util.*;
public class DFS {
    static Node dfs(Node startNode, Node endNode) {

        Set<Node> visitedNodes = new HashSet<Node>();
        java.util.Stack<Node> stack = new java.util.Stack<Node>();

        stack.add(startNode);
        int nodeExplored = 0;
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            // System.out.println(curNode);
            nodeExplored++;
            if (curNode.equals(endNode)) {
                //traceBack(curNode);
                System.out.print("| Pregledana vozlisca: " + nodeExplored + " ");
                return curNode;
            } else {
                if (!visitedNodes.contains(curNode)) {
                    visitedNodes.add(curNode);
                    for (Node node : curNode.getNeighbours()) {
                        if (!visitedNodes.contains(node))
                            stack.add(node);
                    }
                }
            }
        }
        return null;
    }
}
