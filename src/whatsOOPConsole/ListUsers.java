package whatsOOPConsole;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.util.ArrayList;

public class ListUsers {

    public ArrayList<User> listUsers = new ArrayList<>();

    public void addNewUser(User newUser) {
        listUsers.add(newUser);
    }
}
