package Sudoku;

/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #3
 * 1 - 5026221013 - Andika Cahya Sutisna
 * 2 - 5026221129 - Muhammad Ahdaf Amali
 * 3 - 5026221170 - Putu Panji Wiradharma
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serial warning
    private String playerName;
    private JPanel sudokuGrid = new JPanel();
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();
    private int mistake = 0;
    JPanel topbox = new JPanel();
    JPanel leftbox = new JPanel();
    JPanel rightbox = new JPanel();
    JPanel bottombox = new JPanel();
    JLabel Difficultylabel = new JLabel("Difficulty: ");
    JLabel mistakeslabel = new JLabel("Mistakes: " + mistake + "/3");

    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new BorderLayout()); // JPanel
        super.add(sudokuGrid);
        topbox.setOpaque(false);
        topbox.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        topbox.setPreferredSize(new Dimension(BOARD_WIDTH, 50));
        super.add(topbox, BorderLayout.NORTH);
        topbox.add(mistakeslabel);
        mistakeslabel.setFont(new Font("Poppins", Font.BOLD, 25));

        leftbox.setOpaque(false);
        leftbox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        leftbox.setPreferredSize(new Dimension(50, BOARD_HEIGHT));
        super.add(leftbox, BorderLayout.WEST);

        rightbox.setOpaque(false);
        rightbox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        rightbox.setPreferredSize(new Dimension(50, BOARD_HEIGHT));
        super.add(rightbox, BorderLayout.EAST);

        bottombox.setOpaque(false);
        bottombox.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottombox.setPreferredSize(new Dimension(BOARD_WIDTH, 50));
        super.add(bottombox, BorderLayout.SOUTH);
        sudokuGrid.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));
        bottombox.add(Difficultylabel);
        Difficultylabel.setFont(new Font("Poppins", Font.PLAIN, 15));

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                int value = 0;
                cells[row][col] = new Cell(row, col, value);
                sudokuGrid.add(cells[row][col]);

                // Add black borders to cells
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Add left, right, top, bottom borders for grid separation

                if (row % 3 == 0 && row > 0) {
                    Border border = new MatteBorder(1, 0, 0, 0, Color.BLACK);
                    cells[row][col].setBorder(new CompoundBorder(cells[row][col].getBorder(), border));
                }

                if ((row + 1) % 3 == 0 && row < SudokuConstants.GRID_SIZE - 1) {
                    Border border = new MatteBorder(0, 0, 3, 0, Color.BLACK);
                    cells[row][col].setBorder(new CompoundBorder(cells[row][col].getBorder(), border));
                }

                if (col % 3 == 0 && col > 0) {
                    Border border = new MatteBorder(0, 3, 0, 0, Color.BLACK);
                    cells[row][col].setBorder(new CompoundBorder(cells[row][col].getBorder(), border));
                }

                if ((col + 1) % 3 == 0 && col < SudokuConstants.GRID_SIZE - 1) {
                    Border border = new MatteBorder(0, 0, 0, 1, Color.BLACK);
                    cells[row][col].setBorder(new CompoundBorder(cells[row][col].getBorder(), border));
                }

                // [TODO 3 and 4] Declare the common listener and add it to all editable cells
                // Add action listener to editable cells
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(new CellInputListener());
                }
            }
        }
        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void SolveGame() {

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], true);
            }
        }
    }

    /**
     * Generate a new puzzle; and reset the gameboard of cells based on the puzzle.
     * You can call this method to start a new game.
     */
    public void NewGame(int pilihan) {
        if (pilihan == 0) {
            puzzle.generateEasyPuzzle();
            ;
            Difficultylabel.setText("Easy");
            mistake = 0;
            mistakeslabel.setText("Mistakes: " + mistake + "/3");
            // Initialize all the 9x9 cells, based on the puzzle.
            for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                    cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
                }
            }
        } else if (pilihan == 1) {
            puzzle.generateMediumPuzzle();
            Difficultylabel.setText("Medium");
            mistake = 0;
            mistakeslabel.setText("Mistakes: " + mistake + "/3");
            // Initialize all the 9x9 cells, based on the puzzle.
            for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                    cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
                }
            }
        } else if (pilihan == 2) {
            puzzle.generateHardPuzzle();
            ;
            Difficultylabel.setText("Hard");
            mistake = 0;
            mistakeslabel.setText("Mistakes: " + mistake + "/3");
            // Initialize all the 9x9 cells, based on the puzzle.
            for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                    cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
                }
            }
        }
    }

    /**
     * Return true if the puzzle is solved
     * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
     */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    // [TODO 2] Define a Listener Inner Class for all the editable Cells
    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell) e.getSource();

            int numberIn = 0;
            try {
                // Retrieve the int entered
                numberIn = Integer.parseInt(sourceCell.getText());
                // For debugging
                System.out.println("You entered " + numberIn);
                if (numberIn == 0) {
                    JOptionPane.showMessageDialog(null, "Masukan Hanya Angka 1-9");
                    System.out.println("Input tidak valid. Masukkan angka yang benar.");
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Masukan Hanya Angka 1-9");
                System.out.println("Input tidak valid. Masukkan angka yang benar.");
            }

            /*
             * [TODO 5] (later - after TODO 3 and 4)
             * Check the numberIn against sourceCell.number.
             * Update the cell status sourceCell.status,
             * and re-paint the cell via sourceCell.paint().
             */

            if (numberIn == sourceCell.number) {
                sourceCell.status = CellStatus.CORRECT_GUESS;
            } else {
                // Complete the code for incorrect guess
                sourceCell.status = CellStatus.WRONG_GUESS;
                mistake += 1;
            }
            mistakeslabel.setText("Mistakes: " + mistake + "/3");
            if (mistake == 3) {
                JOptionPane.showMessageDialog(null, "You Failed");
                SolveGame();
            }

            sourceCell.paint(); // re-paint this cell based on its status

            /*
             * [TODO 6] (later)
             * Check if the player has solved the puzzle after this move,
             * by calling isSolved(). Put up a congratulation JOptionPane if so.
             */
            if (isSolved()) {
                Object[] opsi = { "Yes", "No" };

                // Displays a dialog with options and gets their return values
                int pilihan = JOptionPane.showOptionDialog(
                        null, // Parent component (null for middle of screen dialog)
                        "Do you wanna play again?", // Dialog message
                        playerName + ", Play Again?", // Dialog title
                        JOptionPane.DEFAULT_OPTION, // Icon type (DEFAULT_OPTION for default icon)
                        JOptionPane.QUESTION_MESSAGE, // Message type (QUESTION_MESSAGE for question)
                        null, // Custom icon (null for default icon)
                        opsi, // Option list
                        opsi[0]); // Chosen default option

                // Uses the return value to determine the next action
                if (pilihan == JOptionPane.CLOSED_OPTION) {
                    System.out.println("Dialog ditutup tanpa pemilihan.");
                    System.exit(0);
                } else if (opsi[pilihan] == opsi[0]) {
                    Object[] opsiDiff = { "Easy", "Medium", "Hard" };

                    // Displays a dialog with options and gets their return values
                    int choice = JOptionPane.showOptionDialog(
                            null, // Parent component (null for middle of screen dialog)
                            "Select Difficulties", // Dialog message
                            "Difficulties ", // Dialog title
                            JOptionPane.DEFAULT_OPTION, // Icon type (DEFAULT_OPTION for default icon)
                            JOptionPane.QUESTION_MESSAGE, // Message type (QUESTION_MESSAGE for question)
                            null, // Custom icon (null for default icon)
                            opsiDiff, // Option list
                            opsiDiff[0]); // Chosen default option
                    if (opsiDiff[choice] == opsiDiff[0]) {

                        NewGame(choice);

                    } else if (opsiDiff[choice] == opsiDiff[1]) {

                        NewGame(choice);

                    } else if (opsiDiff[choice] == opsiDiff[2]) {

                        NewGame(choice);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Thank you for playing");
                    System.exit(0);
                }
            }
        }
    }
}
