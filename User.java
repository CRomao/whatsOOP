package outraTentativa;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public class User {

private String name;
private boolean online = false;
private BufferedReader inputClient;
private DataOutputStream outputClient;

    public User(String name, boolean online, BufferedReader input, DataOutputStream output ){
        setName(name);
        setOnline(online);
        setInputClient(inputClient);
        setOutputClient(outputClient);
    }
    
    public User(BufferedReader input, DataOutputStream output ){
        setInputClient(input);
        setOutputClient(output);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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
