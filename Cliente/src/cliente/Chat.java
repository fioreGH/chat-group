package cliente;

import com.sun.glass.events.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.*;

public class Chat extends javax.swing.JFrame {

    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;

    //Contrutor    
    public Chat(String nome) {

        initComponents();

        this.nome = nome;
        identificacao.setText(nome + " esta conectado");

        try {
            s = new Socket("127.0.0.1", 5000);
        } catch (IOException e) {
            showMessageDialog(null, "Erro ao conectar com o servidor", "", ERROR_MESSAGE);
            System.exit(0);
        }
        Thread();

    }

    private void Thread() {

        Thread t = new Thread(new Runnable() {

            String mensagem;

            @Override
            public void run() {

                try {
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);

                    while ((mensagem = br.readLine()) != null) {

                        mensagemRecebida.setText(mensagemRecebida.getText() + mensagem + "\n");

                    }

                } catch (IOException e) {
                    showMessageDialog(null, "Erro na conexão com o servidor", "", ERROR_MESSAGE);
                }

            }

        });
        t.start(); //inicia a Thread
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemEnviada = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        identificacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chat");
        setResizable(false);

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        jScrollPane1.setViewportView(mensagemRecebida);

        mensagemEnviada.setColumns(20);
        mensagemEnviada.setRows(5);
        mensagemEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensagemEnviadaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(mensagemEnviada);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(identificacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(identificacao)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String mensagem = nome + " escreveu: ";

        try {

            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += mensagemEnviada.getText();
            ps.println(mensagem);
            ps.flush();

            mensagemEnviada.setText("");

        } catch (IOException e) {

            showMessageDialog(null, "Erro: mensagem não enviada", "", ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void mensagemEnviadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensagemEnviadaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String mensagem = nome + " escreveu: ";

            try {

                PrintStream ps = new PrintStream(s.getOutputStream());
                mensagem += mensagemEnviada.getText();
                ps.println(mensagem);
                ps.flush();

                mensagemEnviada.setText("");

            } catch (IOException e) {

                showMessageDialog(null, "Erro: mensagem não enviada", "", ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_mensagemEnviadaKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            s.close();
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel identificacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemEnviada;
    private javax.swing.JTextArea mensagemRecebida;
    // End of variables declaration//GEN-END:variables
}
