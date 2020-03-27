/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsOOPGUI;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controle.ListUsers;

/**
 *
 * @author Romao
 */
public class Grupo extends javax.swing.JFrame {

    private static int porta;
    private static String ip;
    private Socket cliente;
    private BufferedReader veioDoServidor;
    private DataOutputStream vaiPraServidor;
    String  nomeUser;
    
    public Grupo() {
        initComponents();
        this.setLocationRelativeTo(null);
        jtxTela.setEditable(false);
        //Chama a função para iniciar o diálogo da conexão inicial.
        iniciar();
        //Thread para receber do servidor.
        ReceberDoServidor t = new ReceberDoServidor(cliente, jtxTela);
        t.start();
    }

    //Função para iniciar o diálogo da conexão inicial.
    public void iniciar() {
        JLabel lblMensagem = new JLabel("Conexão");
        JTextField ip = new JTextField("127.0.0.1");
        JTextField porta = new JTextField("1");
        JTextField nome = new JTextField("Cliente");
        
        Object[] texts = {lblMensagem, ip, porta, nome};
        JOptionPane.showMessageDialog(null, texts);
        
        jlUserCurrent.setText(nome.getText());
        this.porta = Integer.parseInt(porta.getText());
        this.ip = ip.getText();
        
        //Tenta criar o socket do cliente e captura o InputStream e OutputStream dele.
        try {
            this.cliente = new Socket(this.ip, this.porta);
            vaiPraServidor = new DataOutputStream(cliente.getOutputStream());
            vaiPraServidor.writeBytes(jlUserCurrent.getText() + '\n');
        } catch (IOException ex) {
            Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Função para enviar a mensagem e o servidor encaminhar para os outros.
    public void enviarMsg(){
        
        //Verifica se o JTextField está vazio, se tiver avisa ao cliente para digitar algo.
        if (jtfMsg.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite algo antes de enviar!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        //Se o cliente digitar algo, ele manda a mensagem para o servidor e atualiza o JTextArea.
        try {
            vaiPraServidor = new DataOutputStream(cliente.getOutputStream());
            vaiPraServidor.writeBytes(jtfMsg.getText() + '\n');
            jtxTela.append("> " + jtfMsg.getText() + "\n");
            
            //Se o cliente digitar "sair", desabilita o botao de enviar e o JTextField.
            if (jtfMsg.getText().equalsIgnoreCase("sair")) {
                jbSend.setEnabled(false);
                jtfMsg.setEnabled(false);
            }
            
            jtfMsg.setText("");
        } catch (IOException ex) {
            System.out.println("Erro ao enviar mensagem. LOG: ");
            Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxTela = new javax.swing.JTextArea();
        jtfMsg = new javax.swing.JTextField();
        jbSend = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlUserCurrent = new javax.swing.JLabel();
        jbSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WhatsOOP");
        setResizable(false);

        jtxTela.setColumns(20);
        jtxTela.setRows(5);
        jScrollPane1.setViewportView(jtxTela);

        jtfMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfMsgActionPerformed(evt);
            }
        });
        jtfMsg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfMsgKeyPressed(evt);
            }
        });

        jbSend.setText("Enviar");
        jbSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuário Atual:");

        jbSair.setText("Sair");
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        jLabel2.setText("OBS:");

        jLabel3.setText("1 - Mandando a mensagem \"sair\", você sairá do chat.");

        jLabel4.setText("2 - Pode-se enviar a mensagem apertando \"Enter\".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlUserCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtfMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jbSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbSend, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))))
                        .addGap(0, 21, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlUserCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSend, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed
        enviarMsg();
    }//GEN-LAST:event_jbSendActionPerformed

    private void jtfMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfMsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfMsgActionPerformed

    private void jtfMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfMsgKeyPressed
        //Permite que envie a mensagem teclando "Enter".
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                enviarMsg();
        }
    }//GEN-LAST:event_jtfMsgKeyPressed

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Grupo().setVisible(true);

            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSair;
    private javax.swing.JButton jbSend;
    private javax.swing.JLabel jlUserCurrent;
    private javax.swing.JTextField jtfMsg;
    private javax.swing.JTextArea jtxTela;
    // End of variables declaration//GEN-END:variables
}
