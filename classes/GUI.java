package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    public static void displayLogin(ArrayList<Account> accounts){
        JFrame frame = new JFrame("Login Screen");
        JPanel panel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(350,400);


        JLabel userLabel = new JLabel("Username: ");
        JTextField userText = new JTextField(20);
        panel.add(userLabel);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password: ");
        JPasswordField passField = new JPasswordField(20);
        panel.add(passLabel);
        panel.add(passField);

        buttonPanel.add(loginButton);

        frame.setVisible(true);


        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //Logic to be added later
            }
        });
    }
}
