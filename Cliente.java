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
        String txt2;

        Socket cliente = new Socket(ip, porta);

        Scanner input = new Scanner(System.in);

        msgInicial = new BufferedReader(new InputStreamReader(System.in));

        vaiPraServidor = new DataOutputStream(cliente.getOutputStream());

        veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        Thread t = new Cliente(cliente);
        t.start();

        while (true) {
            txt = msgInicial.readLine();
            System.out.println("TXT2 " + txt);
            vaiPraServidor.writeBytes(txt + '\n');

        }
        // cliente.close();

    }

    @Override
    public void run() {
        String txt2;
        DataOutputStream vaiPraServidor;
        BufferedReader veioDoServidor;

        try {
            vaiPraServidor = new DataOutputStream(cliente.getOutputStream());
            veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                txt2 = veioDoServidor.readLine();
                System.out.println("Veio do Servidor: " + txt2);
            }
        } catch (Exception e) {

        }
    }
}
