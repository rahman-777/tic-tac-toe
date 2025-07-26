import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private int turn = 0;

    public TicTacToeGame() {
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setTitle("Tic-Tac-Toe Game");
        setSize(400, 400);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton clickedButton = (JButton) ae.getSource();
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(turn % 2 == 0 ? "X" : "O");
            turn++;
            checkForWin();
        }
    }

    private void checkForWin() {
        String[][] board = new String[3][3];
        boolean allFilled = true;

        // Fill the board array and check if all buttons are filled
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
            if (board[i / 3][i % 3].equals("")) {
                allFilled = false;
            }
        }

        // Check for horizontal and vertical wins
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                displayWinner(board[i][0]);
                return;
            }
            if (!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                displayWinner(board[0][i]);
                return;
            }
        }

        // Check for diagonal wins
        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            displayWinner(board[0][0]);
            return;
        }
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            displayWinner(board[0][2]);
            return;
        }

        // Check for a draw
        if (allFilled) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            System.exit(0);
        }
    }

    private void displayWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        System.exit(0);
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
