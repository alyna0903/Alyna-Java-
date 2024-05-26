import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    private JFrame frame;
    private JComboBox<String> shapeComboBox;
    private JTextField inputField1;
    private JTextField inputField2;
    private JTextField inputField3;
    private JButton calculateButton;
    private JLabel resultLabel;

    public App() {
        frame = new JFrame("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(8, 2, 5, 5));

        JLabel shapeLabel = new JLabel("Select a shape or function:");
        frame.add(shapeLabel);

        String[] shapes = {"Square", "Rectangle", "Triangle", "Circle", "Sphere", "Cube", "Cuboid", "Sin", "Cos", "Tan", "Pythagorean", "Quadratic"};
        shapeComboBox = new JComboBox<>(shapes);
        frame.add(shapeComboBox);

        JLabel inputLabel1 = new JLabel("Input 1:");
        frame.add(inputLabel1);

        inputField1 = new JTextField();
        frame.add(inputField1);

        JLabel inputLabel2 = new JLabel("Input 2:");
        frame.add(inputLabel2);

        inputField2 = new JTextField();
        frame.add(inputField2);

        JLabel inputLabel3 = new JLabel("Input 3:");
        frame.add(inputLabel3);

        inputField3 = new JTextField();
        frame.add(inputField3);

        calculateButton = new JButton("Calculate");
        frame.add(calculateButton);

        resultLabel = new JLabel("Result:");
        frame.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String shape = (String) shapeComboBox.getSelectedItem();
                double result = 0.0;

                try {
                    double input1 = Double.parseDouble(inputField1.getText());
                    double input2 = Double.parseDouble(inputField2.getText());
                    double input3 = Double.parseDouble(inputField3.getText());

                    switch (shape) {
                        case "Square":
                            result = input1 * input1;
                            resultLabel.setText("Area: " + result + ", Perimeter: " + (4 * input1) + ", Volume: N/A");
                            break;
                        case "Rectangle":
                            result = input1 * input2;
                            resultLabel.setText("Area: " + result + ", Perimeter: " + (2 * (input1 + input2)) + ", Volume: N/A");
                            break;
                        case "Triangle":
                            result = 0.5 * input1 * input2;
                            resultLabel.setText("Area: " + result + ", Perimeter: " + (input1 + input2 + input3) + ", Volume: N/A");
                            result = Math.PI * input1 * input1;
                            resultLabel.setText("Area: " + result + ", Perimeter: " + (2 * Math.PI * input1) + ", Volume: N/A");
                            break;
                        case "Sphere":
                            double surfaceArea = 4 * Math.PI * input1 * input1;
                            result = (4.0 / 3.0) * Math.PI * input1 * input1 * input1;
                            resultLabel.setText("Surface Area: " + surfaceArea + ", Volume: " + result);
                            break;
                        case "Cube":
                            double cubeSurfaceArea = 6 * input1 * input1;
                            result = input1 * input1 * input1;
                            resultLabel.setText("Surface Area: " + cubeSurfaceArea + ", Volume: " + result);
                            break;
                        case "Cuboid":
                            double cuboidSurfaceArea = 2 * (input1 * input2 + input1 * input3 + input2 * input3);
                            result = input1 * input2 * input3;
                            resultLabel.setText("Surface Area: " + cuboidSurfaceArea + ", Volume: " + result);
                            break;
                        case "Sin":
                            result = Math.sin(Math.toRadians(input1));
                            resultLabel.setText("Sin(" + input1 + "): " + result );
                            break;
                        case "Cos":
                            result = Math.cos(Math.toRadians(input1));
                            resultLabel.setText("Cos(" + input1 + "): " + result);
                            break;
                        case "Tan":
                            result = Math.tan(Math.toRadians(input1));
                            resultLabel.setText("Tan(" + input1 + "): " + result );
                            break;
                        case "Pythagorean":
                            result = Math.sqrt(input1 * input1 + input2 * input2);
                            resultLabel.setText("Hypotenuse: " + result );
                            break;
                        case "Quadratic":
                            double a = input1;
                            double b = input2;
                            double c = input3;
                            double discriminant = b * b - 4 * a * c;
                            if (discriminant >= 0) {
                                double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                                double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                                resultLabel.setText("Roots: x1 = " + x1 + ", x2 = " + x2 );
                            } else {
                                resultLabel.setText("No real roots");
                            }
                            break;
                    }

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
