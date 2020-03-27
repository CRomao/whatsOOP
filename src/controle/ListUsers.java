package controle;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.util.ArrayList;

//Classe para a lista de usuários.

public class ListUsers {

    public ArrayList<User> listUsers = new ArrayList<>();

    public void addNewUser(User newUser) {
        listUsers.add(newUser);
    }
}
