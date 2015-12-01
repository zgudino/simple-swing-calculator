import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

public class Calculator implements ActionListener {

    private JPanel mainPanel;
    private JTextField operatorFieldOne, operatorFieldTwo;
    private JButton calculateButton;
    private JLabel resultLabel;

    public Calculator() {
        // Amarrar eventos y validadores
        operatorFieldOne.setInputVerifier(new IntVerifier());
        operatorFieldTwo.setInputVerifier(new IntVerifier());
        calculateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Puede implementar accion con tecla "enter"
        if (e.getSource() == calculateButton) {
            int a = Integer.parseInt(operatorFieldOne.getText());
            int b = Integer.parseInt(operatorFieldTwo.getText());

            resultLabel.setText(String.valueOf(a + b));
        }
    }

    private class IntVerifier extends InputVerifier {
        NumberFormat intFormat = NumberFormat.getIntegerInstance();

        @Override
        public boolean verify(JComponent input) {
            try {
                intFormat.parse(((JTextField) input).getText()).intValue();
            } catch (ParseException e) {
                Toolkit.getDefaultToolkit().beep(); // TODO Debe pitar y sobrear campo indicando error
                return false;
            }
            return true;
        }
    }

    public static void start() {
        JFrame frame = new JFrame("Sumando los enteros");
        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> start());
    }
}
