/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Stevie K. Halprin
// 22/3/2024
public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells

        // ArrayList that holds the cells that are part of the solution to the maze
        ArrayList<MazeCell> solCells = new ArrayList<>();
        // Stack that holds the parent cells to every solution cell
        Stack<MazeCell> parCells = new Stack<MazeCell>();
        // Makes the cell that will be started at the end cell of the maze
        MazeCell current = maze.getEndCell();

        // While the given cell isn't the start of the maze...
        while (current != maze.getStartCell()) {
            // Add the current cell to the Stack of parent cells
            parCells.push(current);
            // Make the new current cell the parent of the current cell
            current = current.getParent();
        }
        // Finally, add the start cell to the Stack of parent cells
        parCells.push(maze.getStartCell());

        // While there are still cells in parCells, add the top element to ArrayList solCells
        while (!parCells.empty()) {
            solCells.add(parCells.pop());
        }

        // Return the newly filled ArrayList of solution cells
        return solCells;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Makes the cell currently being explored the starting cell
        MazeCell current = maze.getStartCell();
        // Creates new stack to which the cells to be explored are added
        Stack<MazeCell> path = new Stack<>();
        // Adds the start cell to the solution
        path.push(maze.getStartCell());

        // While the cell currently being explored isn't the end cell, continue
        while (current != maze.getEndCell()) {
            // Set the current cell to the top element in path, then remove that element
            current = path.pop();
            // If cell to the North of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow() - 1, current.getCol())) {
                maze.getCell(current.getRow() - 1, current.getCol()).setParent(current);
                maze.getCell(current.getRow() - 1, current.getCol()).setExplored(true);
                path.push(maze.getCell(current.getRow() - 1, current.getCol()));
            }
            // If cell to the East of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow(), current.getCol() + 1)) {
                maze.getCell(current.getRow(), current.getCol() + 1).setParent(current);
                maze.getCell(current.getRow(), current.getCol() + 1).setExplored(true);
                path.push(maze.getCell(current.getRow(), current.getCol() + 1));
            }
            // If cell to the South of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow() + 1, current.getCol())) {
                maze.getCell(current.getRow() + 1, current.getCol()).setParent(current);
                maze.getCell(current.getRow() + 1, current.getCol()).setExplored(true);
                path.push(maze.getCell(current.getRow() + 1, current.getCol()));
            }
            // If cell to the West of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow(), current.getCol() - 1)) {
                maze.getCell(current.getRow(), current.getCol() - 1).setParent(current);
                maze.getCell(current.getRow(), current.getCol() - 1).setExplored(true);
                path.push(maze.getCell(current.getRow(), current.getCol() - 1));
            }
        }
        // Return ArrayList of solution by running getSolution()
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Sets the current cell to the starting cell of the maze
        MazeCell current = maze.getStartCell();
        // Creates new Queue to which the cells to be explored are added
        Queue<MazeCell> path = new LinkedList<>();
        // Adds the start cell to the solution
        path.add(maze.getStartCell());

        while (current != maze.getEndCell()) {
            // Set the current cell to the next element in path, then remove that element
            current = path.remove();
            // If cell to the North of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow() - 1, current.getCol())) {
                maze.getCell(current.getRow() - 1, current.getCol()).setParent(current);
                maze.getCell(current.getRow() - 1, current.getCol()).setExplored(true);
                path.add(maze.getCell(current.getRow() - 1, current.getCol()));
            }
            // If cell to the East of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow(), current.getCol() + 1)) {
                maze.getCell(current.getRow(), current.getCol() + 1).setParent(current);
                maze.getCell(current.getRow(), current.getCol() + 1).setExplored(true);
                path.add(maze.getCell(current.getRow(), current.getCol() + 1));
            }
            // If cell to the South of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow() + 1, current.getCol())) {
                maze.getCell(current.getRow() + 1, current.getCol()).setParent(current);
                maze.getCell(current.getRow() + 1, current.getCol()).setExplored(true);
                path.add(maze.getCell(current.getRow() + 1, current.getCol()));
            }
            // If cell to the West of current cell can be explored, add it to path
            // Also set the parent of that cell to the current cell, and make the cell explored
            if (maze.isValidCell(current.getRow(), current.getCol() - 1)) {
                maze.getCell(current.getRow(), current.getCol() - 1).setParent(current);
                maze.getCell(current.getRow(), current.getCol() - 1).setExplored(true);
                path.add(maze.getCell(current.getRow(), current.getCol() - 1));
            }
        }
        // Return ArrayList of solution by running getSolution()
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
