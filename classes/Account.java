package classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Account implements Serializable {
    private String name;
    private String passHash;
    private double balance;
    LinkedList<TransactionEvent> tranHis;

    Account(String name, String password, double balance ){
        this.name = name;
        this.passHash = PasswordHashing.hashPassword(password);
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
        this.passHash = PasswordHashing.hashPassword(pass);
    }

    public double getBalance() {
        return balance;
    }

    void deposit(double amount){
        this.balance += amount;
        tranHis.add(new TransactionEvent("deposit",amount,"bank"));
    }

    void withdraw(double amount){
        try{
            if(balance - amount < 0){
                throw new Exception("Balance can't be less than zero");
            }
            this.balance -= amount;
            tranHis.add(new TransactionEvent("withdraw",amount,"bank"));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void transferOut(String platform, double amount){
        try{
            if(balance - amount < 0){
                throw new Exception("Balance can't be less than zero.");
            }
            this.balance -= amount;
            tranHis.add(new TransactionEvent("transfer",amount,platform));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public LinkedList<TransactionEvent> getTranHis() {
        return tranHis;
    }

    boolean checkPass(String pass){
        return (PasswordHashing.hashPassword(pass)).equals(passHash);
    }

}
