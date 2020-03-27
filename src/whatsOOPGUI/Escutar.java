package whatsOOPGUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

public class Escutar extends Thread {

    private Socket cliente;
    private BufferedReader veioDoServidor;
    private String txt = "";
    private JTextArea telaArea;

    public Escutar(Socket cliente, JTextArea jtxTela) {
        this.cliente = cliente;
        this.telaArea = jtxTela;
    }

    public void run() {
        String txt2;
        try {
            veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                txt2 = veioDoServidor.readLine();
                if (txt2.equalsIgnoreCase("sair"))break;
                telaArea.append(txt2 + "\n");
                adicionar(txt2);
            }
        } catch (Exception e) {
            System.out.println("Você saiu do chat.");
        }
    }

    public void adicionar(String txt) {
        this.txt = txt;
    }

}
