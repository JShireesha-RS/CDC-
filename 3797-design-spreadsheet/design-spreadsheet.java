import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {
    private final int rows;
    private final int cols = 26; // A to Z
    private final int[][] grid;

    public Spreadsheet(int rows) {
        this.rows = rows;
        this.grid = new int[rows][cols]; // All initialized to 0
    }

    public void setCell(String cell, int value) {
        int[] pos = getCellPosition(cell);
        grid[pos[0]][pos[1]] = value;
    }

    public void resetCell(String cell) {
        int[] pos = getCellPosition(cell);
        grid[pos[0]][pos[1]] = 0;
    }

    public int getValue(String formula) {
        if (!formula.startsWith("=")) {
            throw new IllegalArgumentException("Formula must start with '='");
        }

        // Remove the '=' and split by '+'
        String[] parts = formula.substring(1).split("\\+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formula must be of the form '=X+Y'");
        }

        return getOperandValue(parts[0]) + getOperandValue(parts[1]);
    }

    // Helper to get cell position: returns int[]{rowIndex, colIndex}
    private int[] getCellPosition(String cell) {
        char colChar = cell.charAt(0);
        int col = colChar - 'A';

        int row = Integer.parseInt(cell.substring(1)) - 1; // 1-indexed to 0-indexed
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            throw new IllegalArgumentException("Invalid cell reference: " + cell);
        }
        return new int[]{row, col};
    }

    // Helper to get value from either integer literal or cell reference
    private int getOperandValue(String operand) {
        if (Character.isDigit(operand.charAt(0))) {
            return Integer.parseInt(operand);
        } else {
            int[] pos = getCellPosition(operand);
            return grid[pos[0]][pos[1]];
        }
    }
}
