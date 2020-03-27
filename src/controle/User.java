package controle;

import java.io.BufferedReader;
import java.io.DataOutputStream;


//Classe para os usuários.

public class User {

private String name;
private BufferedReader inputClient;
private DataOutputStream outputClient;
    
    public User(String nomeUser, BufferedReader input, DataOutputStream output ){
        setName(nomeUser);
        setInputClient(input);
        setOutputClient(output);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedReader getInputClient() {
        return inputClient;
    }

    public void setInputClient(BufferedReader inputClient) {
        this.inputClient = inputClient;
    }

    public DataOutputStream getOutputClient() {
        return outputClient;
    }

    public void setOutputClient(DataOutputStream outputClient) {
        this.outputClient = outputClient;
    }



}
