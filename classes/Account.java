package classes;

import java.util.LinkedList;

public class Account {
    private String name;
    private String passHash;
    private double balance;
    LinkedList<TransactionEvent> TranHis;

    Account(String name, String password, double balance ){
        this.name = name;
        this.passHash = password; // Replace with Hashing later
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPass(String pass) {
        this.passHash = pass; //Replace with hashing later
    }

    public double getBalance() {
        return balance;
    }

    void deposit(double amount){
        // To be added later
    }

    void withdraw(double amount){
        // To be added later
    }

    void transfer(String platform, double amount){
        // To be added later
    }

    boolean checkPass(String pass){
        return true; // To be added later
    }

}
