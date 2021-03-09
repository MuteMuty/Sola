import java.util.*;

public class ASTAR {
    static Node astar(Node startNode, Node endNode) {
        int nodeExplored = 0;
        // Stack<Node> toReturn = new Stack<>();
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        Set<Node> visited = new HashSet<Node>();

        queue.add(startNode);
        visited.add(startNode);
        while (!queue.isEmpty()) {
            nodeExplored++;
            // Updates view every 100 000 nodes explored

            Node current = queue.poll();
            if (current.equals(endNode)) {
                // traceBack(current);
                System.out.print("| Pregledana vozlisca: " + nodeExplored + " ");
                return current;
            }
            List<Node> nextStates = current.getNeighbours();
            for (Node state : nextStates) {
                state.h = state.hevristic(endNode);
                state.f = state.g + state.h;

                int tentative_gScore = current.g + (current.g-state.g);

                // check if distance smaller
                if (tentative_gScore < state.g) {

                    state.parent = current;
                    state.g = tentative_gScore;
                    state.f = state.g + state.h;

                    if (!visited.contains(state)) {
                        queue.add(state);
                        visited.add(state);
                    }
                }
            }

        }
        System.out.print("| Pregledana vozlisca: " + nodeExplored + " ");
        return null;

    }
}
