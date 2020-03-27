package whatsOOPGUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

//Classe Responsável por receber do servidor e atualizar o JTextArea do cliente.
//Como ela estende de Thread, cada usuário terá uma thread dessa classe.

public class ReceberDoServidor extends Thread {

    private Socket cliente;
    private BufferedReader veioDoServidor;
    private JTextArea telaArea;

    //Recebe o cliente e o JTextArea dele, para saber de quem atualizar.
    public ReceberDoServidor(Socket cliente, JTextArea jtxTela) {
        this.cliente = cliente;
        this.telaArea = jtxTela;
    }

    public void run() {
        String txt;
        try {
            veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                txt = veioDoServidor.readLine();
                //Se o usuário digitar "sair", manda para o JTextArea dele que ele saiu.
                //Se não tiver digitado, manda a mensagem que veio do servidor.
                if (txt.equalsIgnoreCase("sair")){
                    telaArea.append("***Você saiu do Chat." + "\n");
                    break;
                }
                telaArea.append(txt + "\n");
            }
        } catch (Exception e) {
            System.out.println("Você saiu do chat.");
        }
    }
}
