package cliente;

import static javax.swing.JOptionPane.*;

public class Cliente {

    public static void main(String[] args) {

        String nome = showInputDialog(null, "Entre com seu nome: ", "", PLAIN_MESSAGE);

        Chat chat = new Chat(nome); //instancia a classe Chat e o inicia com o nome digitado pelo usuário
        chat.setVisible(true);

    }

}
