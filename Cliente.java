package outraTentativa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private OutputStream ou;
    private Writer ouw;
    private BufferedWriter bfw;

    public static void main(String[] args) throws IOException {

        Socket cliente = new Socket("127.0.0.1", 1);
        
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        Scanner msg = new Scanner(System.in);
        String texto;
        //texto = msg.nextLine();
        //saida.print(texto);
        while (msg.hasNextLine()) {
            saida.println(msg.nextLine());
        }
    }
    
    
}
