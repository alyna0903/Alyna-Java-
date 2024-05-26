import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private double num1, num2;
    private char operator;

    public Calculator() {
        super("Calculator");

        // Create display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(display.getFont().deriveFont(20f));
        getContentPane().add(display, BorderLayout.NORTH);

        // Create buttons
        JButton[] buttons = new JButton[16];
        String[] buttonLabels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);

            // Set button background color to red
            buttons[i].setBackground(Color.GRAY);
        }

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Set background color of entire calculator frame to red
        getContentPane().setBackground(Color.getHSBColor(276.9f, 86.7f, 94.1f));

        setSize(300, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0))) {
            // Append digit to display
            display.setText(display.getText() + command);
        } else if (command.equals(".")) {
            // Check if decimal point already exists
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + command);
            }
        } else {
            // Handle operator and equal buttons
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }

        if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            calculate();
            display.setText("" + num1);
            num1 = num2 = 0; // Reset for next calculation
        }
    }

    private void calculate() {
        switch (operator) {
            case '+':
                num1 += num2;
                break;
            case '-':
                num1 -= num2;
                break;
            case '*':
                num1 *= num2;
                break;
            case '/':
                if (num2 == 0) {
                    display.setText("Error: Division by zero");
                } else {
                    num1 /= num2;
                }
                break;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
