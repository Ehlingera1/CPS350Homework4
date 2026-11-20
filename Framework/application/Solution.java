package application;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

public class Solution {

    // Represents one possible puzzle arrangement
    private static class Node {
        int[] state;
        int blankPosition;
        Node parent;

        Node(int[] state, int blankPosition, Node parent) {
            this.state = state;
            this.blankPosition = blankPosition;
            this.parent = parent;
        }
    }

    public static void breadthFirstSearch(int[] num, int m, Vector solution) {

        solution.clear();

        if (num == null || num.length != 9) {
            return;
        }

        // Find the starting position of the blank piece
        int startBlankPosition = -1;

        for (int i = 0; i < num.length; i++) {
            if (num[i] == m) {
                startBlankPosition = i;
                break;
            }
        }

        if (startBlankPosition == -1) {
            return;
        }

        // Goal arrangement
        int[] goal = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        // FIFO queue for BFS
        Queue<Node> queue = new ArrayDeque<>();

        // Store processed arrangements
        ArrayList<String> processed = new ArrayList<>();

        // Prevent duplicate arrangements
        Set<String> visited = new HashSet<>();

        // Add starting arrangement
        Node start = new Node(num.clone(), startBlankPosition, null);

        queue.add(start);
        visited.add(Arrays.toString(start.state));

        // Continue until queue is empty
        while (!queue.isEmpty()) {

            // Remove first arrangement from queue
            Node current = queue.remove();

            String currentKey = Arrays.toString(current.state);

            processed.add(currentKey);

            // Check if goal has been reached
            if (Arrays.equals(current.state, goal)) {

                // Reconstruct solution path
                ArrayList<Integer> reversePath = new ArrayList<>();

                Node step = current;

                while (step != null) {
                    reversePath.add(step.blankPosition);
                    step = step.parent;
                }

                // Add positions in correct order
                // Starting position is included for Main.java compatibility
                for (int i = reversePath.size() - 1; i >= 0; i--) {
                    solution.add(reversePath.get(i));
                }

                return;
            }

            // Find row and column of blank piece
            int blank = current.blankPosition;

            int row = blank / 3;
            int col = blank % 3;

            // Possible neighboring positions
            int[] neighbors = {
                blank - 3, // Up
                blank + 3, // Down
                blank - 1, // Left
                blank + 1  // Right
            };

            // Generate neighboring arrangements
            for (int nextBlank : neighbors) {

                // Check array boundaries
                if (nextBlank < 0 || nextBlank >= 9) {
                    continue;
                }

                int nextRow = nextBlank / 3;
                int nextCol = nextBlank % 3;

                // Make sure the positions are directly adjacent
                if (Math.abs(row - nextRow) + Math.abs(col - nextCol) != 1) {
                    continue;
                }

                // Create a copy of current arrangement
                int[] nextState = current.state.clone();

                // Swap blank with neighboring piece
                int temp = nextState[blank];

                nextState[blank] = nextState[nextBlank];

                nextState[nextBlank] = temp;

                String nextKey = Arrays.toString(nextState);

                // Check whether arrangement was already visited
                if (!visited.contains(nextKey)) {

                    visited.add(nextKey);

                    Node child = new Node(
                        nextState,
                        nextBlank,
                        current
                    );

                    queue.add(child);
                }
            }
        }
    }
}