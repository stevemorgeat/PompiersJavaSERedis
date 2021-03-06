/*
 Famille : Hash
 */
package fr.pb.securiteincendie;

//import fr.pb.general.Globale;
import fr.pb.global.Globale;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlElementDecl;
import jdk.nashorn.internal.objects.Global;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import redis.clients.jedis.Jedis;

/**
 *
 * @author pascal
 */
public class FamilleCRUDAvecJSON extends javax.swing.JFrame {

    private Jedis jedis;
    private DefaultTableModel idtm;
    // Pour le rollback
//    private String[][] t2DCauses;

    /**
     * Creates new form CauseCRUD
     */
    public FamilleCRUDAvecJSON() {
        initComponents();

        jedis =Globale.getConnexionRedis();

        remplirTable();

        setTitle("CRUD Famille");
        setLocationRelativeTo(null);
        setVisible(true);

    } /// Constructeur

    /**
     *
     */
    private void remplirTable() {

        String lsCle = "Famille";
        String jsonSerialise;
        String lsPrenom;
        String lsNom;
        String lsAge;
        String lsPseudo;
        String lsIsVIP;

        try {

            Object[] tLigne;

            idtm = (DefaultTableModel) jTableFamille.getModel();

            /*
             Affichage de la List stockée
             */
            // Nombre d'éléments
            long liElements = jedis.llen(lsCle);
            // Récupération du contenu de la List
            List<String> listeMembresFamille = jedis.lrange(lsCle, 0, liElements - 1);

            int liTaille = listeMembresFamille.size();

            // Boucle sur les éléments de la liste
            for (int i = 0; i < liTaille; i++) {
                jsonSerialise = listeMembresFamille.get(i);
//                System.out.println(jsonSerialise);
                Object objet = JSONValue.parse(jsonSerialise);
                // Object 2 JSONObject
                JSONObject objetJSON = (JSONObject) objet;
                lsPrenom = objetJSON.get("prenom").toString();
                lsNom = objetJSON.get("nom").toString();
                lsAge = objetJSON.get("age").toString();
                if (objetJSON.get("pseudo") == null) {
                    lsPseudo = "Pas de pseudo";
                } else {
                    lsPseudo = objetJSON.get("pseudo").toString();
                }

                if (objetJSON.get("is_vip") == null) {
                    lsIsVIP = " non ";
                } else {
                    lsIsVIP = " oui ";
                }
                System.out.println(lsPrenom);
                tLigne = new Object[6];
                tLigne[0] = "";
                tLigne[1] = lsPrenom;
                tLigne[2] = lsNom;
                tLigne[3] = lsAge;
                tLigne[4] = lsPseudo;
                tLigne[5] = lsIsVIP;

                idtm.addRow(tLigne);

                //System.out.println(lsPrenom + " " + lsNom + " a " + lsAge + " ans et son pseudo est  '" + lsPseudo + "' et il est VIP ? " + lsIsVIP);
                //System.out.println(listeMembresFamille.get(i));
            }
            jTableFamille.setModel(idtm);

            jLabelMessage.setText("Jusque là tout va bien !!!");

        } catch (Exception e) {
            jLabelMessage.setText(e.getMessage());
        }
    } /// remplirTable

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButtonAjouter = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();
        jTextFieldPrenom = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFamille = new javax.swing.JTable();
        jButtonCLS = new javax.swing.JButton();
        jButtonCommit = new javax.swing.JButton();
        jButtonRollback = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPseudo = new javax.swing.JTextField();
        jTextFieldAge = new javax.swing.JTextField();
        jTextFieldIsVip = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setText("Prénom");

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });

        jLabelMessage.setText("Message");

        jTextFieldPrenom.setText("Reporter");

        jTableFamille.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flag", "Prénom", "Nom", "Age", "Pseudo", "IS VIP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFamille.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFamilleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFamille);
        if (jTableFamille.getColumnModel().getColumnCount() > 0) {
            jTableFamille.getColumnModel().getColumn(0).setResizable(false);
        }

        jButtonCLS.setText("CLS");
        jButtonCLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCLSActionPerformed(evt);
            }
        });

        jButtonCommit.setText("Commit");
        jButtonCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCommitActionPerformed(evt);
            }
        });

        jButtonRollback.setText("Rollback");
        jButtonRollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRollbackActionPerformed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jLabel3.setText("Nom");

        jLabel4.setText("Age");

        jLabel5.setText("Pseudo");

        jLabel6.setText("IS VIP");

        jTextFieldNom.setText("Tintin");

        jTextFieldPseudo.setText("Tintin");

        jTextFieldAge.setText("33");

        jTextFieldIsVip.setText("oui");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMessage)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonCommit)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonRollback))
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonCLS)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(jButtonModifier)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonSupprimer))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldIsVip, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldIsVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCLS)
                    .addComponent(jButtonAjouter)
                    .addComponent(jButtonModifier)
                    .addComponent(jButtonSupprimer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCommit)
                    .addComponent(jButtonRollback))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMessage)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed

        String lsPrenom = jTextFieldPrenom.getText();
        String lsNom = jTextFieldPrenom.getText();
        String lsAge = jTextFieldPrenom.getText();
        String lsPseudo = jTextFieldPrenom.getText();
        String lsIsVip = jTextFieldPrenom.getText();

        jLabelMessage.setText("");

        if (lsPrenom.trim().isEmpty()) {
            jLabelMessage.setText("Saisies obligatoires");
        } else {
            String[] tLigne = new String[6];
            tLigne[0] = "+";
            tLigne[1] = lsPrenom;
            tLigne[2] = lsNom;
            tLigne[3] = lsAge;
            tLigne[4] = lsPseudo;
            tLigne[5] = lsIsVip;
            idtm.addRow(tLigne);
        }
    }//GEN-LAST:event_jButtonAjouterActionPerformed

    private void jButtonCLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCLSActionPerformed
        //
        jLabelMessage.setText("");
        jTextFieldPrenom.setText("");
    }//GEN-LAST:event_jButtonCLSActionPerformed

    private void jButtonCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCommitActionPerformed
        //
        jLabelMessage.setText("");
        try {
            int liRows = jTableFamille.getRowCount();
            String lsFlag;
            String lsPrenom;
            String lsNom;
            String lsAge;
            String lsPseudo;
            String lsIsVip;

            for (int i = liRows - 1; i >= 0; i--) {
                lsFlag = jTableFamille.getValueAt(i, 0).toString();
                lsPrenom = jTableFamille.getValueAt(i, 1).toString();

                if (lsFlag.equals("+")) {
                    // Ajout à droite
                    //jedis.sadd("Causes", lsCause);
                    idtm.setValueAt("", i, 0);
                }
                if (lsFlag.equals("-")) {
                    // Remove
                    //jedis.srem("Causes", lsCause);
                    idtm.removeRow(i);
                }
                /*
                 TODO
                 */
                if (lsFlag.equals("v")) {
                    // Ancienne valeur (a revoir)
                    //jedis.srem("Causes", lsCause);
                    // Nouvelle valeur
                    //jedis.sadd("Causes", lsCause);
                    idtm.setValueAt("", i, 0);
                }
            }
            jLabelMessage.setText("Validation définitive OK");
        } catch (Exception e) {
            jLabelMessage.setText(e.getMessage());
        }
    }//GEN-LAST:event_jButtonCommitActionPerformed

    private void jButtonRollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRollbackActionPerformed
        //
//        jLabelMessage.setText("");
//        int liRows = jTableFamille.getRowCount();
//        String lsFlag;
//        for (int i = liRows - 1; i >= 0; i--) {
//            lsFlag = jTableFamille.getValueAt(i, 0).toString();
//            if (lsFlag.equals("+")) {
//                idtm.removeRow(i);
//            }
//            if (lsFlag.equals("-")) {
//                idtm.setValueAt("", i, 0);
//            }
//            if (lsFlag.equals("v")) {
//
//                for (int j = 0; j < t2DCauses.length; j++) {
//                    String[] tLigne = t2DCauses[j];
//                    if (tLigne[1].equals(jTableFamille.getValueAt(i, 1).toString())) {
//                        idtm.setValueAt(tLigne[0], i, 1);
//                    }
//                }
//                idtm.setValueAt("", i, 0);
//            }
//        }
    }//GEN-LAST:event_jButtonRollbackActionPerformed

    private void jTableFamilleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFamilleMouseClicked
        //
        jLabelMessage.setText("");

        jTextFieldPrenom.setText(jTableFamille.getValueAt(jTableFamille.getSelectedRow(), 1).toString());
        jTextFieldNom.setText(jTableFamille.getValueAt(jTableFamille.getSelectedRow(), 2).toString());
        jTextFieldAge.setText(jTableFamille.getValueAt(jTableFamille.getSelectedRow(), 3).toString());
        jTextFieldPseudo.setText(jTableFamille.getValueAt(jTableFamille.getSelectedRow(), 4).toString());
        jTextFieldIsVip.setText(jTableFamille.getValueAt(jTableFamille.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_jTableFamilleMouseClicked

    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
        //
//        jLabelMessage.setText("");
//        int liRow = jTableFamille.getSelectedRow();
//        if (liRow >= 0) {
//            if (jTableFamille.getValueAt(liRow, 0).toString().equals("") || jTableFamille.getValueAt(liRow, 0).toString().equals("-")) {
//                t2DCauses[liRow][1] = jTextFieldCause.getText();
//                jTableFamille.setValueAt("v", liRow, 0);
//                jTableFamille.setValueAt(jTextFieldCause.getText(), liRow, 1);
//            }
//        } else {
//            jLabelMessage.setText("Vous devez sélectionner une ligne !");
//        }

    }//GEN-LAST:event_jButtonModifierActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        //
        jLabelMessage.setText("");
        int liRow = jTableFamille.getSelectedRow();
        if (liRow >= 0) {
            if (jTableFamille.getValueAt(liRow, 0).toString().equals("+")) {
                idtm.removeRow(liRow);
            } else {
                idtm.setValueAt("-", liRow, 0);
            }
        } else {
            jLabelMessage.setText("Vous devez sélectionner une ligne !");
        }
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        JFrame f = Globale.getFenetre();
//        f.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FamilleCRUDAvecJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FamilleCRUDAvecJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FamilleCRUDAvecJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FamilleCRUDAvecJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FamilleCRUDAvecJSON().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonCLS;
    private javax.swing.JButton jButtonCommit;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonRollback;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFamille;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldIsVip;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldPseudo;
    // End of variables declaration//GEN-END:variables
}
