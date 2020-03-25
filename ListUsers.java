package outraTentativa;

import java.net.Socket;
import outroCanal.*;
import java.util.ArrayList;

public class ListUsers{

    ArrayList<Socket> listUsers = new ArrayList<>();
    
    public void addUser(Socket newUser){
        listUsers.add(newUser);
    }    
    
}
