/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.view;

import rs.ac.bg.fon.np_project.client.controller.ControllerUI;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.client.view.tableModel.TableModelGame;

/**
 *
 * @author Simona
 */
public class FrmLoadGame extends javax.swing.JDialog {

    private Game game;
    private JTable table;
    private JComboBox<Publisher> cmbPublishers;
    
    public void setTabelaIgra(JTable t) {
        table = t;
    }
    
    public void setCmbPublishers(JComboBox<Publisher> cmbPublishers) {
        this.cmbPublishers = cmbPublishers;
    } 
    
    /**
     * Creates new form FrmLoadGame
     */
    public FrmLoadGame(java.awt.Frame parent, boolean modal, Game g) {
        super(parent, modal);
        initComponents();
        game = g;
        setTitle("Informacije o igri");

        FrameCenter.CenteredFrame(this);
        try {
            prepareView();
            JOptionPane.showMessageDialog(this, "Sistem je ucitao igru.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne mo�e da ucita igru.", "Gre�ka", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblNaziv = new javax.swing.JLabel();
        lblNaziv1 = new javax.swing.JLabel();
        lblNaziv2 = new javax.swing.JLabel();
        lblNaziv3 = new javax.swing.JLabel();
        lblNaziv4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNaziv� = new javax.swing.JTextField();
        txtBrIgraca = new javax.swing.JTextField();
        txtIzdavac = new javax.swing.JTextField();
        txtKategorija = new javax.swing.JTextField();
        txtKolicina = new javax.swing.JTextField();
        btnIzbrisi = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID");

        lblNaziv.setText("Naziv");

        lblNaziv1.setText("Max broj igraca");

        lblNaziv2.setText("Izdavac");

        lblNaziv3.setText("Kategorija");

        lblNaziv4.setText("Kolicina");

        txtId.setEditable(false);

        txtNaziv�.setEditable(false);
        txtNaziv�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNaziv�ActionPerformed(evt);
            }
        });

        txtBrIgraca.setEditable(false);
        txtBrIgraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrIgracaActionPerformed(evt);
            }
        });

        txtIzdavac.setEditable(false);
        txtIzdavac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIzdavacActionPerformed(evt);
            }
        });

        txtKategorija.setEditable(false);
        txtKategorija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKategorijaActionPerformed(evt);
            }
        });

        txtKolicina.setEditable(false);
        txtKolicina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKolicinaActionPerformed(evt);
            }
        });

        btnIzbrisi.setText("Izbri�i");
        btnIzbrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzbrisiActionPerformed(evt);
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblNaziv, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblNaziv1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(lblNaziv2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(lblNaziv3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(lblNaziv4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBrIgraca, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNaziv�)
                                    .addComponent(txtId)
                                    .addComponent(txtKategorija, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addComponent(txtKolicina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                            .addComponent(txtIzdavac))
                        .addGap(70, 70, 70))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIzbrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNaziv�, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtBrIgraca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(txtKategorija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblNaziv)
                        .addGap(24, 24, 24)
                        .addComponent(lblNaziv1)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNaziv2)
                            .addComponent(txtIzdavac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(lblNaziv3)
                        .addGap(24, 24, 24)
                        .addComponent(lblNaziv4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzbrisi)
                    .addComponent(btnBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void txtNaziv�ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void txtBrIgracaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void txtIzdavacActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtKategorijaActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void txtKolicinaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnIzbrisiActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            ControllerUI.getInstance().deleteGame(game);
            ((TableModelGame) table.getModel()).deleteIgra(game);
            refreshComboBox();
            JOptionPane.showMessageDialog(this, "Sistem je obrisao igru!");
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }                                          

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        

        this.dispose();

    }                                       

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnIzbrisi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNaziv;
    private javax.swing.JLabel lblNaziv1;
    private javax.swing.JLabel lblNaziv2;
    private javax.swing.JLabel lblNaziv3;
    private javax.swing.JLabel lblNaziv4;
    private javax.swing.JTextField txtBrIgraca;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIzdavac;
    private javax.swing.JTextField txtKategorija;
    private javax.swing.JTextField txtKolicina;
    private javax.swing.JTextField txtNaziv�;
    // End of variables declaration                   

    private void prepareView() {

        txtId.setText(game.getGameid().toString());
        txtNaziv�.setText(game.getGameName());
        txtBrIgraca.setText(game.getNumPlayers().toString());
        txtIzdavac.setText(game.getPublisher().toString());
        txtKolicina.setText(game.getNumberInStock().toString());
        txtKategorija.setText(game.getGameCategory().toString());

    }

    private void refreshComboBox() throws Exception {

        cmbPublishers.removeAllItems();
        List<Publisher> publishers = ControllerUI.getInstance().getPublishers();
        for (Publisher publisher : publishers) {
            System.out.println(publisher);
            cmbPublishers.addItem(publisher);

        }

    }
}
