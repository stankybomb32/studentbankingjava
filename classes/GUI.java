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

    public static void displayAccount(Account account){
        JFrame frame = new JFrame(account.getName() + "'s Account");
        frame.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        JLabel balanceLabel = new JLabel("Balance: "+String.valueOf(account.getBalance()));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        infoPanel.add(balanceLabel);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        frame.setSize(350,400);

        frame.setVisible(true);
    }
}
