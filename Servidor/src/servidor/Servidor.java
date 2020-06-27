package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.*;

import static javax.swing.JOptionPane.*;

public class Servidor {

    public static void main(String[] args) {

        //armazenar todos os participantes do chat (todas as conexões)
        ArrayList<PrintStream> clientes = new ArrayList<>();

        try {
            ServerSocket server = new ServerSocket(5000);
            showMessageDialog(null, "Servidor Conectado via porta 5000", "", INFORMATION_MESSAGE);
            Socket socket;

            while (true) {
                socket = server.accept();

                //guarda o endereço do cliente
                clientes.add(new PrintStream(socket.getOutputStream()));

                Mensagem mensagem = new Mensagem(socket, clientes);

            }

        } catch (IOException e) {
            showMessageDialog(null, "Erro: Problemas ao conectar o Servidor", "", ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

}
