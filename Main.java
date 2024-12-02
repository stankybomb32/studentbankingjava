import classes.*;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Account> accounts = new ArrayList<Account>();
    public static void main(String[] args) {
        accounts = FileHandling.read();
        GUI.displayLogin(accounts);

        FileHandling.write(accounts);
    }
}
