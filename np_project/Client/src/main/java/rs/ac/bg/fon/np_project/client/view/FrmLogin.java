/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view;

import rs.ac.bg.fon.np_project.commonlibrary.communication.CurrentUser;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Response;
import rs.ac.bg.fon.np_project.commonlibrary.communication.ResponseType;
import rs.ac.bg.fon.np_project.client.controller.ControllerUI;
import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.client.validation.ValidationException;
import rs.ac.bg.fon.np_project.client.validation.Validator;

/**
 *
 * @author Simona
 */
public class FrmLogin extends javax.swing.JFrame {

    private static Worker currentUser;
    private FrmMain frmMain;
    
    public FrmMain getFrmMain() {
        return frmMain;
    }
    
    /**
     * Creates new form FrmLogin
     */
    public FrmLogin() {
        initComponents();
        FrameCenter.CenteredFrame(this);
        setTitle("Prijava");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        txtUsername.setText("simi");

        txtPassword.setText("simi123456");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        try {
            validateForm();
            String user = txtUsername.getText().trim();
            String password = String.valueOf(txtPassword.getPassword());
            currentUser = ControllerUI.getInstance().login(user, password);
            CurrentUser.setCurrentUser(currentUser);
            JOptionPane.showMessageDialog(this, "Dobrodošli, " + currentUser.getUsername());
            this.dispose();
            frmMain=new FrmMain(currentUser);
            frmMain.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Greska: " + "Server prestao sa radom.", "Greska", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greska: " + e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private void validateForm() throws ValidationException {

         Validator.startValidation().validateNotNullOrEmpty(txtUsername.getText(), "Morate uneti korisnicko ime!")
                .validateNotNullOrEmpty(String.valueOf(txtPassword.getPassword()), "Morate uneti sifru!")
                .throwIfInvalide();

    }
    
    public void login(Response response) {
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            JOptionPane.showMessageDialog(this, "Greska: " + response.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            currentUser = (Worker) response.getResult();
            CurrentUser.setCurrentUser(currentUser);
            JOptionPane.showMessageDialog(this, "Dobrodošli, " + currentUser.getUsername());
            this.dispose();
            FrmMain frm = new FrmMain(currentUser);

            frm.setVisible(true);
        }

    }
}
