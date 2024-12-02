package classes;

import java.io.*;
import java.util.ArrayList;

public class FileHandling {
    public static ArrayList<Account> read() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            return (ArrayList<Account>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Account>();
        }
    }

    public static void write(ArrayList<Account> accounts){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
