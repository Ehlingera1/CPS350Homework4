# Graph Traversal to Solve a Puzzle

## Assignment Overview

The purpose of this assignment is to use a breadth first search in order to correctly reconstructed a randomly messed up image in the form of a 3x3 puzzle. This is supposed to be accomplished by moving a white square up, down, left, and right.

## Goal State

The required final arrangement is:

```(Missing Space)
0 1 2
3 4 5
6 7 8
```

## How the Algorithm Works

1. Find the starting position of the blank tile.
2. Add the starting arrangement to the queue.
3. Remove the first arrangement from the queue.
4. Check whether the arrangement matches the goal state.
5. Generate all valid moves by moving the blank space up, down, left, or right.
6. Add arrangements that have not already been visited to the queue.
7. Repeat until the goal arrangement is found.
8. Reconstruct the solution path using parent references.

Because BFS explores arrangements in order of the number of moves, the first solution it finds uses the minimum number of moves.

## Project Files

- **Main.java:** Provides the puzzle interface and displays the puzzle movements.
- **Solution.java:** Contains the BFS implementation used to solve the puzzle.
- **MyQueue.java:** A queue implementation provided with the assignment.


## Conclusion

This assignment demonstrates the use of Breadth-First Search to find the shortest solution to a sliding puzzle. By exploring possible arrangements with a FIFO queue and preventing repeated states, the algorithm can systematically find a path from the starting arrangement to the required goal state.