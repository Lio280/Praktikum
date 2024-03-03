import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Testr {
    private JFrame frame;
    private JPanel panel;
    private JTextField display;
    private JButton[] buttons;
    private JList<String> historyList;
    private DefaultListModel<String> historyModel;

    private double result;
    private String operator;
    private boolean isOperatorClicked;
    private String firstNumber; // Track the first number in the expression

    private ArrayList<String> history;

    public Testr() {
        frame = new JFrame("Praktikums Rechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        panel.setBackground(new Color(225, 225, 225));

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Source Sans Pro", Font.PLAIN, 40));

        buttons = new JButton[20];

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "Bin", "Frac", "C"
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 30));
            buttons[i].setBackground(new Color(225, 225, 225));
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
            buttons[i].addActionListener(new ButtonClickListener());
            panel.add(buttons[i]);
        }

        // Create history list
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        JScrollPane historyScrollPane = new JScrollPane(historyList);
        historyScrollPane.setPreferredSize(new Dimension(200, 0));

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(display, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(historyScrollPane, BorderLayout.EAST);

        frame.setSize(600, 500);
        frame.setVisible(true);

        // Initialize history
        history = new ArrayList<>();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();

            switch (buttonText) {
                case "C":
                    clear();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    processOperator(buttonText);
                    break;
                case "=":
                    calculate();
                    break;
                case "√":
                    calculateSquareRoot();
                    break;
                case "Bin":
                    convertToBinary();
                    break;
                case "Frac":
                    convertToFraction();
                    break;
                default:
                    processNumber(buttonText);
                    break;
            }
        }

        private void clear() {
            display.setText("");
            result = 0;
            operator = "";
            firstNumber = ""; // Clear first number when clearing
            isOperatorClicked = false;
        }

        private void processOperator(String op) {
            if (!isOperatorClicked) {
                firstNumber = display.getText(); // Store the first number
                result = Double.parseDouble(firstNumber);
                operator = op;
                isOperatorClicked = true;
                display.setText("");
            }
        }

        private void calculate() {
            if (isOperatorClicked) {
                double secondOperand = Double.parseDouble(display.getText());
                String expression = firstNumber + operator + display.getText(); // Capture the full expression
                switch (operator) {
                    case "+":
                        result += secondOperand;
                        break;
                    case "-":
                        result -= secondOperand;
                        break;
                    case "*":
                        result *= secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result /= secondOperand;
                        } else {
                            display.setText("Error: Division by zero");
                            return;
                        }
                        break;
                }
                display.setText("");
                isOperatorClicked = false;
                addToHistory(expression + "=" + formatResult(result));
            }
        }

        private void calculateSquareRoot() {
            double number = Double.parseDouble(display.getText());
            if (number >= 0) {
                result = Math.sqrt(number);
                display.setText(formatResult(result));
                addToHistory("√(" + number + ")=" + formatResult(result));
            } else {
                display.setText("Error: Invalid input");
            }
        }

        private void convertToBinary() {
            int number = Integer.parseInt(display.getText());
            display.setText(Integer.toBinaryString(number));
            addToHistory("Bin(" + number + ")=" + display.getText());
        }

        private void convertToFraction() {
            double number = Double.parseDouble(display.getText());
            display.setText(convertToFractionString(number));
            addToHistory("Frac(" + number + ")=" + display.getText());
        }

        private void processNumber(String number) {
            // if (isOperatorClicked) {
            //     display.setText("");
            //     isOperatorClicked = true;
            // }
            display.setText(display.getText() + number);
        }

        private String formatResult(double result) {
            DecimalFormat df = new DecimalFormat("#.######");
            return df.format(result);
        }

        private String convertToFractionString(double number) {
            int wholePart = (int) number;
            double fractionalPart = number - wholePart;
            int denominator = 1000000;
            int numerator = (int) (fractionalPart * denominator);

            int gcd = findGCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;

            String result = wholePart > 0 ? wholePart + " " : "";
            result += numerator + "/" + denominator;
            return result;
        }

        private int findGCD(int a, int b) {
            if (b == 0) return a;
            return findGCD(b, a % b);
        }

        private void addToHistory(String entry) {
            history.add(0, entry);
            if (history.size() > 5) {
                history.remove(5);
            }
            refreshHistoryList();
        }

        private void refreshHistoryList() {
            historyModel.clear();
            for (String entry : history) {
                historyModel.addElement(entry);
            }
        }
    }

    public static void main(String[] args) {
        new Testr();
    }
}