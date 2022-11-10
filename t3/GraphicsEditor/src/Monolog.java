import functions.AnyFunctions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Monolog extends JDialog {
    private JPanel contentPane;
    private JTextField textField;
    private JButton buildButton;
    private JButton buttonHermit;
    private JButton buttonSplines;
    private JButton buttonBezier;
    private JButton buttonClear;
    public static Map<String, Double> params;
    public static ArrayList<AnyFunctions> anyFunctions;
    public static ArrayList<AnyFunctions> besierFunctions;
    public static ArrayList<AnyFunctions> hermitFunctions;
    public static ArrayList<AnyFunctions> splineFunctions;

    private DrawPanel dp = new DrawPanel();

    public Monolog() {
        setContentPane(contentPane);
        params = new HashMap<>();

        anyFunctions = new ArrayList<>();
        besierFunctions = new ArrayList<>();
        hermitFunctions = new ArrayList<>();
        splineFunctions = new ArrayList<>();

        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText() != ""){
                    AnyFunctions f = new AnyFunctions(textField.getText());
                    anyFunctions.add(f);

                }
            }
        });


        buttonBezier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText() != ""){
                    AnyFunctions f = new AnyFunctions(textField.getText());
                    besierFunctions.add(f);

                }
            }
        });
        buttonHermit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText() != ""){
                    AnyFunctions f = new AnyFunctions(textField.getText());
                    hermitFunctions.add(f);

                }
            }
        });
        buttonSplines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText() != ""){
                    AnyFunctions f = new AnyFunctions(textField.getText());
                    splineFunctions.add(f);

                }
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anyFunctions.clear();
                besierFunctions.clear();
                hermitFunctions.clear();
                splineFunctions.clear();
            }
        });

        setModal(true);
    }

    public static void main(String[] args) {
        Monolog dialog = new Monolog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
