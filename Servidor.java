package outraTentativa;

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

public class Servidor extends Thread {

    private Socket cliente;
    private static ListUsers users = new ListUsers();

    public Servidor(Socket novoCliente) {
        this.cliente = novoCliente;
    }

    @Override
    public void run() {

        BufferedReader veioDoCliente;
        DataOutputStream vaiPraCliente;
        try {
            String msgCliente;
            String enviarMsg;
            veioDoCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            vaiPraCliente = new DataOutputStream(cliente.getOutputStream());
            User newUser = new User(veioDoCliente, vaiPraCliente);
            users.addNewUser(newUser);
            while (true) {
                msgCliente = veioDoCliente.readLine();
                System.out.println(msgCliente);
                mandarPraTodos(msgCliente);
                //vaiPraCliente.writeBytes(msgCliente + '\n');
            }
        } catch (IOException ex) {
            System.out.println("Erro ao criar cliente e obter INPUT e OUTPUT");
        }

//        msgCliente = veioDoCliente.readLine();
//        System.out.println(msgCliente);
//        vaiPraCliente.writeBytes(msgCliente + '\n');
    }

    public void mandarPraTodos(String txt) {
        System.out.println(users.listUsers.size());
        try {
            for (int i = 0; i < users.listUsers.size(); i++) {
                System.out.println(users.listUsers.get(i).getOutputClient());
                users.listUsers.get(i).getOutputClient().writeBytes(txt + '\n');
            }
        } catch (IOException ex) {
            System.out.println("Erro ao enviar mensagem para todos...");
        }
    }

    public static void main(String[] args) throws IOException {

        String msgCliente;
        String enviarMsg;

        ServerSocket servidor = new ServerSocket(1);
        //Socket cliente = servidor.accept();
        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("conectou");
            new Servidor(cliente).start();

//            BufferedReader veioDoCliente
//                    = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
//
//            DataOutputStream vaiPraCliente
//                    = new DataOutputStream(cliente.getOutputStream());
//
//            msgCliente = veioDoCliente.readLine();
//            System.out.println(msgCliente);
//            vaiPraCliente.writeBytes(msgCliente + '\n');
        }

    }
}
