package whatsOOPGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import whatsOOPConsole.ListUsers;
import whatsOOPConsole.User;

public class ServidorGUI extends Thread {

    private Socket cliente;
    private static ListUsers users = new ListUsers();

    public ServidorGUI(Socket novoCliente) {
        this.cliente = novoCliente;
    }

    @Override
    public void run() {

        BufferedReader veioDoCliente;
        DataOutputStream vaiPraCliente;
        String nomeUser;

        try {

            String msgCliente;
            String enviarMsg;
            veioDoCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            vaiPraCliente = new DataOutputStream(cliente.getOutputStream());
            nomeUser = veioDoCliente.readLine();
            User newUser = new User(veioDoCliente, vaiPraCliente);
            newUser.setName(nomeUser);
            users.addNewUser(newUser);

            while (true) {
                msgCliente = veioDoCliente.readLine();
                mandarPraTodos(msgCliente, nomeUser);
            }

        } catch (IOException ex) {
            // System.out.println("Erro ao criar cliente e obter INPUT e OUTPUT");
            System.out.println(ex);
        }
    }

    public void mandarPraTodos(String txt, String nomeUser) {
        boolean saiu = false;
        int user = -1;
        if (txt.equalsIgnoreCase("sair")) {
            txt = "*** " + nomeUser + " saiu do chat";
            saiu = true;
        } else {
            txt = nomeUser + ": " + txt;
        }

        try {
            for (int i = 0; i < users.listUsers.size(); i++) {
                if (!(nomeUser == users.listUsers.get(i).getName())) {
                    users.listUsers.get(i).getOutputClient().writeBytes(txt + '\n');
                }
                if (nomeUser == users.listUsers.get(i).getName() && saiu == true) {
                    users.listUsers.get(i).getOutputClient().writeBytes("sair" + '\n');
                    user = i;
                }
            }
            if (saiu) {
                users.listUsers.remove(user);
                cliente.close();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao enviar mensagem para todos...");
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(1);

        while (true) {
            System.out.print("Esperando conexão...");
            Socket cliente = servidor.accept();
            System.out.println("conectou!");
            new ServidorGUI(cliente).start();
        }

    }
}
