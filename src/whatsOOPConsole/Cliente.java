package whatsOOPConsole;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


//Classe para o cliente.

public class Cliente extends Thread {

    private Socket cliente;

    public Cliente(Socket s) {
        cliente = s;
    }

    public static void main(String[] args) throws IOException {
        String ip;
        int porta;
        BufferedReader msgInicial;
        DataOutputStream vaiPraServidor;
        String txt;
        String txt2, nomeUser;
        
        //Obter o IP e porta do servidor.
        Scanner input = new Scanner(System.in);
        
        System.out.print("Informe o IP do servidor: ");
        ip = input.nextLine();
        
        System.out.print("Informe a porta do servidor: ");
        porta = input.nextInt();
        
        Socket cliente = new Socket(ip, porta);      

        msgInicial = new BufferedReader(new InputStreamReader(System.in));

        //Define o Output do cliente.
        vaiPraServidor = new DataOutputStream(cliente.getOutputStream());
        
        System.out.println("-=-=-=Bem vindo-=-=-=");

        System.out.print("Digite o seu nome: ");
        nomeUser = msgInicial.readLine();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("OBS:");
        System.out.println("1 - Mandando a mensagem 'sair', você sairá do chat.");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        
        //Manda o nome do cliente para o servidor registrar na lista.
        vaiPraServidor.writeBytes(nomeUser + '\n');

        //Cria-se uma noa thread com o cliente, para que ela fique verificando se o servidor mandou algo.
        Thread t = new Cliente(cliente);
        t.start();

        //Controle para mandar para o servidor.
        while (true) {
            System.out.print("> ");
            txt = msgInicial.readLine();
            vaiPraServidor.writeBytes(txt + '\n');
            if (txt.equalsIgnoreCase("sair"))break;
        }
    }

    //RUN para verificar se o servidor mandou algo.
    @Override
    public void run() {
        String txt2;
        BufferedReader veioDoServidor;

        try {
            veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                txt2 = veioDoServidor.readLine();
                if (txt2.equalsIgnoreCase("sair")){
                    txt2 = "Você saiu do chat.";
                    System.out.println(txt2);
                    break;
                }
                System.out.println();
                System.out.println(txt2);
                System.out.print("> ");
            }
        } catch (Exception e) {
            System.out.println("Erro ao receber mensagem do servidor.");
        }
    }
}
