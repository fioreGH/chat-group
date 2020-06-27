package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mensagem {

    private Socket s;
    private ArrayList<PrintStream> clientes;

    public Mensagem(Socket s, ArrayList<PrintStream> clientes) {

        this.s = s;
        this.clientes = clientes;
        Thread(); //permite varias tarefas
    }

    //permite realizar a conexão com todos (varios processos)
    private void Thread() {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                String mensagem = "";

                try {

                    InputStreamReader isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while ((mensagem = br.readLine()) != null) {

                        enviarMensagem(mensagem);

                    }

                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

        });

        t.start();
    }

    //envia a mensagem para todos os usarios conectados
    private void enviarMensagem(String mensagem) {

        for (int a = 0; a < clientes.size(); a++) {

            clientes.get(a).println(mensagem);
            clientes.get(a).flush();
        }

    }
}
