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
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {

    private Socket cliente;

    public Cliente(Socket s) {
        cliente = s;
    }

    public static void main(String[] args) throws IOException {
        String ip = "127.0.0.1";
        int porta = 1;
        BufferedReader msgInicial;
        DataOutputStream vaiPraServidor;
        BufferedReader veioDoServidor;
        String txt;
        String txt2, nomeUser;

        Socket cliente = new Socket(ip, porta);

        Scanner input = new Scanner(System.in);

        msgInicial = new BufferedReader(new InputStreamReader(System.in));

        vaiPraServidor = new DataOutputStream(cliente.getOutputStream());

        veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        System.out.print("Digite o seu nome: ");
        nomeUser = msgInicial.readLine();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        vaiPraServidor.writeBytes(nomeUser + '\n');

        Thread t = new Cliente(cliente);
        t.start();

        while (true) {
            txt = msgInicial.readLine();
            vaiPraServidor.writeBytes(txt + '\n');
            if (txt.equalsIgnoreCase("sair"))break;
        }
    }

    @Override
    public void run() {
        String txt2;
        BufferedReader veioDoServidor;

        try {
            veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                txt2 = veioDoServidor.readLine();
                if (txt2.equalsIgnoreCase("sair"))break;
                System.out.println(txt2);
            }
        } catch (Exception e) {
            System.out.println("Você saiu do chat.");
        }
    }
}
