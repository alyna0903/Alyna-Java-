import java.awt.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        // Create the game board
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].addActionListener(e -> handleClick((JButton) e.getSource()));
                boardPanel.add(buttons[i][j]);
            }
        }

        // Add the board to the frame
        add(boardPanel, BorderLayout.CENTER);

        // Initialize the game
        currentPlayer = 'X';
        gameOver = false;

        setVisible(true);
    }

    private void handleClick(JButton button) {
        if (!gameOver && button.getText().isEmpty()) {
            button.setText(String.valueOf(currentPlayer));
            checkWinner();
            if (!gameOver) {
                togglePlayer();
            }
        }
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty() &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                showResult(buttons[i][0].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[0][i].getText().isEmpty() &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                showResult(buttons[0][i].getText());
                return;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            showResult(buttons[0][0].getText());
            return;
        }
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            showResult(buttons[0][2].getText());
            return;
        }

        // Check for a tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
            if (!tie) {
                break;
            }
        }
        if (tie) {
            showResult("Tie");
        }
    }

    private void showResult(String result) {
        gameOver = true;
        JOptionPane.showMessageDialog(this, "The winner is: " + result);
        resetGame();
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
        gameOver = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}