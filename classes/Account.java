package classes;

import java.util.HashMap;
import java.util.LinkedList;

public class Account {
    private String name;
    private String passHash;
    private double balance;
    LinkedList<TransactionEvent> tranHis;

    Account(String name, String password, double balance ){
        this.name = name;
        this.passHash = password; // Replace with Hashing later
        this.balance = balance;
        tranHis = new LinkedList<TransactionEvent>();
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
        this.balance += amount;
        tranHis.add(new TransactionEvent("deposit",amount,"bank"));
    }

    void withdraw(double amount){
        this.balance -= amount;
        tranHis.add(new TransactionEvent("withdraw",amount,"bank"));
    }

    void transferOut(String platform, double amount){
        this.balance -= amount;
        tranHis.add(new TransactionEvent("transfer",amount,platform));
    }

    boolean checkPass(String pass){
        return true; // To be added later
    }

}
