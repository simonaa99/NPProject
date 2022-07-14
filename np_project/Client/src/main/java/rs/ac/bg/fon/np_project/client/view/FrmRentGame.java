/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view;

import rs.ac.bg.fon.np_project.client.controller.ControllerUI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.client.validation.ValidationException;
import rs.ac.bg.fon.np_project.client.validation.Validator;
import rs.ac.bg.fon.np_project.client.view.tableModel.TableModelGame;
import rs.ac.bg.fon.np_project.client.view.tableModel.TableModelUser;

/**
 *
 * @author Simona
 */
public class FrmRentGame extends javax.swing.JDialog {

    /**
     * Creates new form FrmRentGame
     */
    public FrmRentGame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepareForm();
        FrameCenter.CenteredFrame(this);
        setTitle("Iznajmljivanje igre");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClanskakarta = new javax.swing.JLabel();
        txtClanskaKarta = new javax.swing.JTextField();
        lblClanskakarta1 = new javax.swing.JLabel();
        txtImePrezime = new javax.swing.JTextField();
        btnFindUser = new javax.swing.JButton();
        btnFindByName = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        lblNazivIgre = new javax.swing.JLabel();
        txtNazivIgre = new javax.swing.JTextField();
        btnFindGame = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGames = new javax.swing.JTable();
        btnIznajmi = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblClanskakarta.setText("Pretrazi broj clanske karte");

        lblClanskakarta1.setText("Pretrazi ime i prezime");

        btnFindUser.setText("Pronadji korisnika");
        btnFindUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindUserActionPerformed(evt);
            }
        });

        btnFindByName.setText("Pronadji korisnika");
        btnFindByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindByNameActionPerformed(evt);
            }
        });

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Ime", "Prezime", "Telefon", "Adresa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblUser);

        lblNazivIgre.setText("Unesite naziv igre");

        btnFindGame.setText("Pronadji igru");
        btnFindGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindGameActionPerformed(evt);
            }
        });

        tblGames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Naziv", "Izdavac", "Na stanju"
            }
        ));
        jScrollPane3.setViewportView(tblGames);

        btnIznajmi.setText("Iznajmi");
        btnIznajmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIznajmiActionPerformed(evt);
            }
        });

        btnBack.setText("Nazad");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFindGame, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNazivIgre, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNazivIgre, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnIznajmi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnFindUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblClanskakarta, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addComponent(txtClanskaKarta, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnFindByName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblClanskakarta1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblClanskakarta)
                            .addComponent(txtClanskaKarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindUser))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblClanskakarta1)
                            .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindByName)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNazivIgre, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNazivIgre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFindGame)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIznajmi)
                    .addComponent(btnBack))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindUserActionPerformed
        try {
            validateInput(txtClanskaKarta);
            String cardNumber = txtClanskaKarta.getText().trim();
            List<User> users = ControllerUI.getInstance().getUsersByUsersCard(cardNumber);
            if (users == null || users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clana po zadatoj vrednosti.", "Greska", JOptionPane.ERROR_MESSAGE);
                ((TableModelUser) tblUser.getModel()).setUsers(new ArrayList<>());
                ((TableModelGame) tblGames.getModel()).setIgre(new ArrayList<>());
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronasao clana.");
                ((TableModelUser) tblUser.getModel()).setUsers(users);
            }
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFindUserActionPerformed

    private void btnFindByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindByNameActionPerformed
        try {
            validateInput(txtImePrezime);
            String name = txtImePrezime.getText().trim();
            List<User> users = ControllerUI.getInstance().getUsersByName(name);
            if (users == null || users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clana po zadatoj vrednosti.", "Greska", JOptionPane.ERROR_MESSAGE);
                ((TableModelUser) tblUser.getModel()).setUsers(new ArrayList<>());
                ((TableModelGame) tblGames.getModel()).setIgre(new ArrayList<>());
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronasao clana.");
                ((TableModelUser) tblUser.getModel()).setUsers(users);
            }
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnFindByNameActionPerformed

    private void btnFindGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindGameActionPerformed
        try {
            validateInput(txtNazivIgre);
            String name = txtNazivIgre.getText().trim();
            String query = "SELECT * FROM igra WHERE naziv='" + name + "'";
            List<Game> games = (List<Game>) ControllerUI.getInstance().getGamesByQuery(query);
            if (games.size() == 0) {
                throw new Exception("Sistem ne moze da pronadje igre po zadatoj vrednosti!");
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronašao igre po zadatoj vrednosti.");
            }
            ((TableModelGame) tblGames.getModel()).setIgre(games);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            ((TableModelGame) tblGames.getModel()).setIgre(new ArrayList<>());
        }
    }//GEN-LAST:event_btnFindGameActionPerformed

    private void btnIznajmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIznajmiActionPerformed
        int selectedrowB = tblGames.getSelectedRow();
        int selectedRowU = tblUser.getSelectedRow();
        if (selectedrowB != -1 && selectedRowU != -1) {
            Game selectedGame = ((TableModelGame) tblGames.getModel()).getIgra(selectedrowB);
            User selectedUser = ((TableModelUser) tblUser.getModel()).getUser(selectedRowU);
            try {
                rentAGame(selectedUser, selectedGame);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);

            }

        }
    }//GEN-LAST:event_btnIznajmiActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        this.dispose();

    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRentGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRentGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRentGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRentGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmRentGame dialog = new FrmRentGame(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFindByName;
    private javax.swing.JButton btnFindGame;
    private javax.swing.JButton btnFindUser;
    private javax.swing.JButton btnIznajmi;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblClanskakarta;
    private javax.swing.JLabel lblClanskakarta1;
    private javax.swing.JLabel lblNazivIgre;
    private javax.swing.JTable tblGames;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtClanskaKarta;
    private javax.swing.JTextField txtImePrezime;
    private javax.swing.JTextField txtNazivIgre;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {

        TableModelUser tmu = new TableModelUser();
        tmu.setUsers(new ArrayList<>());
        tblUser.setModel(tmu);
        TableModelGame tmg = new TableModelGame(new ArrayList<>());
        tblGames.setModel(tmg);
        
    }

    private void validateInput(JTextField input) throws ValidationException{

        Validator.startValidation().validateNotNullOrEmpty(input.getText(), "Unesite vrednost pretrage!").throwIfInvalide();

    }

    private void rentAGame(User selectedUser, Game selectedGame) {

        try {

            ControllerUI.getInstance().rentGame(selectedUser, selectedGame);
            int num = ((TableModelGame) tblGames.getModel()).getIgra(tblGames.getSelectedRow()).getNumberInStock();
            ((TableModelGame) tblGames.getModel()).getIgra(tblGames.getSelectedRow()).setNumberInStock(num - 1);
            ((TableModelGame) tblGames.getModel()).fireTableDataChanged();
            String iznajmljivanje = "Sistem je uspesno izvrsio iznajmljivanje igre:\nKorisnik_id: " + selectedUser.getUsercard() + "\nIgra_id: " + selectedGame.getGameid();
            JOptionPane.showMessageDialog(this, iznajmljivanje);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }
}
