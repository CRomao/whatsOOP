package outraTentativa;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread {

    private Socket cliente;
    private static ListUsers users = new ListUsers();

    public Servidor(Socket novoCliente) {
        this.cliente = novoCliente;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(1);

        try {
            while (true) {
                System.out.println("Aguardando cliente");
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado...");
                users.addUser(cliente);
//                for(int i=0; i<users.size(); i++){
//                    System.out.println(users.get(i));
//                }
                new Servidor(cliente).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            InputStream in = cliente.getInputStream();
            Scanner entrada = new Scanner(cliente.getInputStream());
            while (entrada.hasNextLine()) {
                System.out.println(entrada.nextLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
