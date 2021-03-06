/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view;

import rs.ac.bg.fon.np_project.client.controller.ControllerUI;
import java.awt.Frame;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.client.validation.ValidationException;
import rs.ac.bg.fon.np_project.client.validation.Validator;
import rs.ac.bg.fon.np_project.client.view.tableModel.TableModelGame;

/**
 *
 * @author Simona
 */
public class FrmGames extends javax.swing.JDialog {

    /**
     * Creates new form FrmGames
     */
    public FrmGames(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Igre");
        FrameCenter.CenteredFrame(this);
        try {
            prepareView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska pri inicijalizaciji" + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
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
        jLabel3 = new javax.swing.JLabel();
        cmbIzdavac = new javax.swing.JComboBox();
        cmbKategorija = new javax.swing.JComboBox();
        txtNaziv = new javax.swing.JTextField();
        btnPretraziIzdavac = new javax.swing.JButton();
        btnPretraziKategorija = new javax.swing.JButton();
        cmbPretraziNaziv = new javax.swing.JButton();
        btnPrikaziSve = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIgre = new javax.swing.JTable();
        btnUcitaj = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pretraga prema izdavacu:");

        jLabel2.setText("Pretraga prema kategoriji:");

        jLabel3.setText("Pretraga prema nazivu:");

        btnPretraziIzdavac.setText("Pretrazi");
        btnPretraziIzdavac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziIzdavacActionPerformed(evt);
            }
        });

        btnPretraziKategorija.setText("Pretrazi");
        btnPretraziKategorija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziKategorijaActionPerformed(evt);
            }
        });

        cmbPretraziNaziv.setText("Pretrazi");
        cmbPretraziNaziv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPretraziNazivActionPerformed(evt);
            }
        });

        btnPrikaziSve.setText("Prikazi sve");
        btnPrikaziSve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrikaziSveActionPerformed(evt);
            }
        });

        tblIgre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblIgre);

        btnUcitaj.setText("Ucitaj");
        btnUcitaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbIzdavac, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnPretraziIzdavac, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbKategorija, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnPretraziKategorija, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPretraziNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(112, 112, 112)
                        .addComponent(btnPrikaziSve, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUcitaj, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbIzdavac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPretraziIzdavac))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbKategorija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPretraziKategorija)
                    .addComponent(btnPrikaziSve))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPretraziNaziv))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUcitaj)
                    .addComponent(btnBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPretraziIzdavacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziIzdavacActionPerformed
        Publisher publisher = (Publisher) cmbIzdavac.getSelectedItem();
        try {
            if(publisher==null) return;
            String query = "SELECT * FROM igra WHERE izdavacid=" + publisher.getPublisherId();
            List<Game> games = ControllerUI.getInstance().getGamesByQuery(query);
            if (games.size() == 0) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje igre po zadatoj vrednosti!", "Greska", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronasao igre po zadatoj vrednosti!");
            }

            ((TableModelGame) tblIgre.getModel()).setIgre(games);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnPretraziIzdavacActionPerformed

    private void btnPretraziKategorijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziKategorijaActionPerformed
        GameCategory category = (GameCategory) cmbKategorija.getSelectedItem();
        try {
            Long kategorijaId = ControllerUI.getInstance().getGameCategoryId(category);

            String query = "SELECT * FROM igra WHERE kategorijaid=" + kategorijaId;
            List<Game> igre = (List<Game>) ControllerUI.getInstance().getGamesByQuery(query);
            if (igre.size() == 0) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje igre po zadatoj vrednosti!", "Greska", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronasao igre po zadatoj vrednosti!");
            }

            ((TableModelGame) tblIgre.getModel()).setIgre(igre);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPretraziKategorijaActionPerformed

    private void cmbPretraziNazivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPretraziNazivActionPerformed

        try {
            validateInput();
            String query = "SELECT * FROM igra WHERE naziv='" + txtNaziv.getText().trim() + "'";
            List<Game> igre = (List<Game>) ControllerUI.getInstance().getGamesByQuery(query);
            if (igre.size() == 0) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje igre po zadatoj vrednosti!", "Greska", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronasao igre po zadatoj vrednosti!");
            }

            ((TableModelGame) tblIgre.getModel()).setIgre(igre);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_cmbPretraziNazivActionPerformed

    private void btnPrikaziSveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrikaziSveActionPerformed
        List<Game> games;
        try {
            games = ControllerUI.getInstance().getGames();
            ((TableModelGame) tblIgre.getModel()).setIgre(games);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnPrikaziSveActionPerformed

    private void btnUcitajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajActionPerformed
        int selectedrow = tblIgre.getSelectedRow();
        if (selectedrow == -1) {
            return;
        }
        Game g = ((TableModelGame) tblIgre.getModel()).getIgra(selectedrow);
        FrmLoadGame frm = new FrmLoadGame((Frame) this.getParent(), true, g);
        frm.setTabelaIgra(this.tblIgre);
        frm.setCmbPublishers(this.cmbIzdavac);
        frm.setVisible(true);

    }//GEN-LAST:event_btnUcitajActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmGames dialog = new FrmGames(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnPretraziIzdavac;
    private javax.swing.JButton btnPretraziKategorija;
    private javax.swing.JButton btnPrikaziSve;
    private javax.swing.JButton btnUcitaj;
    private javax.swing.JComboBox cmbIzdavac;
    private javax.swing.JComboBox cmbKategorija;
    private javax.swing.JButton cmbPretraziNaziv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblIgre;
    private javax.swing.JTextField txtNaziv;
    // End of variables declaration//GEN-END:variables

    private void prepareView() throws Exception{

        loadPublishers();
        loadGameCategories();
        formatTable();

    }

    private void validateInput() throws ValidationException {

        Validator.startValidation().validateNotNullOrEmpty(txtNaziv.getText(), "Unesite vrednost pretrage!").throwIfInvalide();

    }

    private void loadPublishers() throws Exception{

        List<Publisher> publishers = ControllerUI.getInstance().getPublishers();
        for (Publisher publisher : publishers) {
            cmbIzdavac.addItem(publisher);

        }

    }

    private void loadGameCategories() throws Exception{

        GameCategory[] kategorije = (GameCategory[]) ControllerUI.getInstance().getGameCategories();
        for (GameCategory kategorijaIgara : kategorije) {
            cmbKategorija.addItem(kategorijaIgara);
        }
        
    }

    private void formatTable() throws Exception{

        List<Game> igre = loadGames();
        TableModelGame tm = new TableModelGame(igre);
        tblIgre.setModel(tm);

    }

    private List<Game> loadGames() throws Exception{

        List<Game> games = ControllerUI.getInstance().getGames();
        return games;

    }
}
