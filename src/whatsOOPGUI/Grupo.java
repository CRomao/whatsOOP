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
import whatsOOPConsole.ListUsers;

/**
 *
 * @author Romao
 */
public class Grupo extends javax.swing.JFrame {

    /**
     * Creates new form Grupo
     */
    private static int porta;
    private static String ip;
    private Socket cliente;
    private BufferedReader veioDoServidor;
    private BufferedReader msgInicial;
    private DataOutputStream vaiPraServidor;
    String txt;
    String txt2, nomeUser;
    UsuariosGUI usuarios = new UsuariosGUI();
    public Grupo() {
        initComponents();
        this.setLocationRelativeTo(null);
        jtxTela.setEditable(false);
        iniciar();
        Escutar t = new Escutar(cliente, jtxTela);
        t.start();
    }

    public void iniciar() {
        JLabel lblMessage = new JLabel("Conexão");
        JTextField ip = new JTextField("127.0.0.1");
        JTextField porta = new JTextField("12345");
        JTextField nome = new JTextField("Cliente");
        Object[] texts = {lblMessage, ip, porta, nome};
        JOptionPane.showMessageDialog(null, texts);
        jlUserCurrent.setText(nome.getText());
        this.porta = Integer.parseInt(porta.getText());
        this.ip = ip.getText();
        try {
            this.cliente = new Socket(this.ip, this.porta);
            vaiPraServidor = new DataOutputStream(cliente.getOutputStream());
            vaiPraServidor.writeBytes(jlUserCurrent.getText() + '\n');
        } catch (IOException ex) {
            Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }
    
    public void enviarMsg(){
        if (jtfMsg.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite algo antes de enviar!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            vaiPraServidor = new DataOutputStream(cliente.getOutputStream());
            vaiPraServidor.writeBytes(jtfMsg.getText() + '\n');
            jtxTela.append("> " + jtfMsg.getText() + "\n");
            if (jtfMsg.getText().equalsIgnoreCase("sair")) {
                jbSend.setEnabled(false);
                jtfMsg.setEnabled(false);
            }
            jtfMsg.setText("");
        } catch (IOException ex) {
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
        jbListUsers = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlUserCurrent = new javax.swing.JLabel();
        jbSair = new javax.swing.JButton();

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

        jbListUsers.setText("Listar Usuários Conectados");
        jbListUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListUsersActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuário Atual:");

        jbSair.setText("Sair");
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlUserCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbListUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbSend, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbListUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
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

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbSairActionPerformed

    private void jtfMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfMsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfMsgActionPerformed

    private void jtfMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfMsgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                enviarMsg();
        }
    }//GEN-LAST:event_jtfMsgKeyPressed

    private void jbListUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListUsersActionPerformed
        JOptionPane.showMessageDialog(null, usuarios.listarUsuarios());
        
        
    }//GEN-LAST:event_jbListUsersActionPerformed

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

    public void escutar() {
        String txt2;
        BufferedReader veioDoServidor;

        try {
            veioDoServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            System.out.println("veio do serfdfed");
            while (veioDoServidor.ready()) {
                txt2 = veioDoServidor.readLine();
                if (txt2.equalsIgnoreCase("sair")) {
                    break;
                }
                //System.out.println(txt2);
                jtxTela.append(txt2 + "\n");
            }
        } catch (Exception e) {
            System.out.println("Você saiu do chat.");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbListUsers;
    private javax.swing.JButton jbSair;
    private javax.swing.JButton jbSend;
    private javax.swing.JLabel jlUserCurrent;
    private javax.swing.JTextField jtfMsg;
    private javax.swing.JTextArea jtxTela;
    // End of variables declaration//GEN-END:variables
}
