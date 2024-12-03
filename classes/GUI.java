package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GUI {

    public static Account login(ArrayList<Account> accounts,String name, String pass) throws NullPointerException{
        for (Account account: accounts){
            if((account.getName()).equals(name) && account.checkPass(pass)){
                return account;
            }
        }
        throw new NullPointerException();

    }

    public static void signup(ArrayList<Account> accounts, String name, String pass) throws Exception{
        for (Account account: accounts){
            if((account.getName()).equals(name)){
                throw new Exception();
            }
        }
        accounts.add(new Account(name, pass, 0));
    }
    public static void displayLogin(ArrayList<Account> accounts){
        JFrame frame = new JFrame("Login Screen");
        JPanel panel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");
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
        buttonPanel.add(signupButton);

        frame.setVisible(true);


        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Account account = GUI.login(accounts, userText.getText(), passField.getText());
                    GUI.displayAccount(account, accounts);
                    frame.dispose(); //Logic to be added later
                }
                catch(Exception e1){
                    Dialog warning = new Dialog(frame,"Invalid Credentials!", false);
                    warning.setSize(300,150);
                    Button closeButton = new Button("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            warning.setVisible(false);  // Hides the dialog
                        }
                    });
                    warning.add(closeButton);
                    warning.setVisible(true);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    signup(accounts, userText.getText(), passField.getText());
                    Dialog confirmation = new Dialog(frame,"Account Created!", false);
                    confirmation.setSize(300,150);
                    Button closeButton = new Button("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            confirmation.dispose();
                        }
                    });
                    confirmation.add(closeButton);
                    confirmation.setVisible(true);

                }
                catch(Exception e1){
                    Dialog warning = new Dialog(frame,"Account already exists!", false);
                    warning.setSize(300,150);
                    Button closeButton = new Button("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            warning.dispose();
                        }
                    });
                    warning.add(closeButton);
                    warning.setVisible(true);
                }
            }
        });
    }

    public static void displayAccount(Account account, ArrayList<Account> accounts){
        JFrame frame = new JFrame(account.getName() + "'s Account");
        frame.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        JLabel balanceLabel = new JLabel("Balance: "+String.valueOf(account.getBalance()));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton tranHisButton = new JButton("View Transaction History");
        JButton finAdviceButton = new JButton("Financial Advice");
        JButton logoutButton = new JButton("Log Out");

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDeposit(frame,balanceLabel,account);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayWithdraw(frame,balanceLabel,account);

            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransferWindow(frame, balanceLabel,account);
            }
        });

        tranHisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTranHis(frame, account);
            }
        });

        finAdviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayInc(frame, account);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileHandling.write(accounts);
                frame.dispose();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileHandling.write(accounts); // Save accounts before closing
            }
        });
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        infoPanel.add(balanceLabel);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(tranHisButton);
        buttonPanel.add(finAdviceButton);
        buttonPanel.add(logoutButton);
        frame.setSize(350,400);

        frame.setVisible(true);
    }

    public static void displayWithdraw(JFrame frame, JLabel balanceLabel, Account account) {
        JDialog withdraw = new JDialog(frame, "Enter withdraw amount");
        withdraw.setSize(300, 150);
        JButton confirmButton = new JButton("Confirm");
        JLabel actionLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(20);

        withdraw.add(actionLabel, BorderLayout.NORTH);
        withdraw.add(amountField, BorderLayout.CENTER);
        withdraw.add(confirmButton, BorderLayout.SOUTH);

        withdraw.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.withdraw(amount);
                balanceLabel.setText("Balance: " + String.valueOf(account.getBalance()));
                withdraw.setVisible(false);
                withdraw.dispose();
            }

        });
    }

    public static void displayDeposit(JFrame frame, JLabel balanceLabel, Account account){
        JDialog deposit = new JDialog(frame,"Enter deposit amount");
        deposit.setSize(300,150);
        JButton confirmButton = new JButton("Confirm");
        JLabel actionLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(20);

        deposit.add(actionLabel, BorderLayout.NORTH);
        deposit.add(amountField, BorderLayout.CENTER);
        deposit.add(confirmButton, BorderLayout.SOUTH);

        deposit.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                balanceLabel.setText("Balance: "+String.valueOf(account.getBalance()));
                deposit.setVisible(false);
                deposit.dispose();
            }
        });

    }

    public static CashApp displayTransferWindow(JFrame frame, JLabel balanceLabel, Account account){
        JDialog transfer = new JDialog(frame,"Transfer Window");
        transfer.setSize(700,150);
        JPanel fieldPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton bkashButton = new JButton("Bkash");
        JButton nagadButton = new JButton("Nagad");
        JLabel mobileLabel = new JLabel("Enter Mobile No:");
        JTextField mobileText = new JTextField(20);
        JLabel actionLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(20);

        fieldPanel.add(mobileLabel);
        fieldPanel.add(mobileText);
        fieldPanel.add(actionLabel);
        fieldPanel.add(amountField);
        buttonPanel.add(bkashButton);
        buttonPanel.add(nagadButton);
        transfer.add(fieldPanel, BorderLayout.NORTH);
        transfer.add(buttonPanel, BorderLayout.SOUTH);

        transfer.setVisible(true);

        final CashApp[] cashAppHolder = {null};

        bkashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mobileNo = mobileText.getText();
                double amount = Double.parseDouble(amountField.getText());
                cashAppHolder[0] = new Bkash(mobileNo);
                cashAppHolder[0].transferIn(amount);
                account.transferOut("Bkash",amount);
                balanceLabel.setText("Balance: "+String.valueOf(account.getBalance()));
                transfer.dispose();

            }
        });

        nagadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mobileNo = mobileText.getText();
                double amount = Double.parseDouble(amountField.getText());
                cashAppHolder[0] = new Nagad(mobileNo);
                cashAppHolder[0].transferIn(amount);
                account.transferOut("Nagad",amount);
                balanceLabel.setText("Balance: "+String.valueOf(account.getBalance()));
                transfer.dispose();
            }
        });

        return cashAppHolder[0];



    }

    public static void displayInc(JFrame frame, Account account){
        JDialog incDialog = new JDialog(frame, "Enter monthly income:");
        incDialog.setSize(700,150);
        JButton confirmButton = new JButton("Confirm");
        JLabel actionLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(20);

        incDialog.add(actionLabel, BorderLayout.NORTH);
        incDialog.add(amountField, BorderLayout.CENTER);
        incDialog.add(confirmButton, BorderLayout.SOUTH);

        incDialog.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double monthlyIncome = Double.parseDouble(amountField.getText());
                displayFinAdvice(frame, account, monthlyIncome);
                incDialog.dispose();
            }
        });
    }

    public static void displayFinAdvice(JFrame frame, Account account, double monthlyIncome){

        JDialog FinDialog = new JDialog(frame, account.getName()+"s Financial Advice");
        JTextArea AdviceField = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(AdviceField);
        JButton exitButton = new JButton("Exit");
        JPanel buttonPanel = new JPanel(new FlowLayout());

        StringBuilder advice = new StringBuilder();
        advice.append(FinancialAdvisor.giveSavingAdvice(account)+"\n");
        advice.append(FinancialAdvisor.suggestSavingPlan(monthlyIncome, account)+"\n");
        advice.append(FinancialAdvisor.actionableSavingTips()+"\n");

        AdviceField.setText(advice.toString());
        AdviceField.setLineWrap(true);
        AdviceField.setWrapStyleWord(true);

        FinDialog.setSize(300,500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        buttonPanel.add(exitButton);
        FinDialog.add(AdviceField, BorderLayout.CENTER);
        FinDialog.add(buttonPanel, BorderLayout.SOUTH);


        FinDialog.setVisible(true);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinDialog.dispose();
            }
        });



    }

    public static void displayTranHis(JFrame frame, Account account){
        JDialog tranHisDialog = new JDialog(frame, account.getName()+"'s Transaction History");
        JTextArea infoField = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(infoField);
        JButton exitButton = new JButton("Exit");
        JPanel buttonPanel = new JPanel(new FlowLayout());

        StringBuilder content = new StringBuilder();
        for(TransactionEvent i: account.getTranHis()){
            content.append(i.getAmount()+"\t"+i.getType()+"\t"+i.getPlatform()).append("\n");
        }
        infoField.setText(content.toString());

        tranHisDialog.setSize(300,500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        buttonPanel.add(exitButton);
        tranHisDialog.add(infoField, BorderLayout.CENTER);
        tranHisDialog.add(buttonPanel, BorderLayout.SOUTH);


        tranHisDialog.setVisible(true);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tranHisDialog.dispose();
            }
        });

    }


}
