package Fitur;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class lupapassword extends javax.swing.JFrame {

    public lupapassword() {
        this.setResizable(false);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtusername = new javax.swing.JTextField();
        btnKonfirmasi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        C2 = new javax.swing.JCheckBox();
        C1 = new javax.swing.JCheckBox();
        txt_password1 = new javax.swing.JPasswordField();
        txt_password2 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtusername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtusername.setBorder(null);
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, 260, 50));

        btnKonfirmasi.setBackground(new java.awt.Color(245, 150, 92));
        btnKonfirmasi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setText("KONFIRMASI");
        btnKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiActionPerformed(evt);
            }
        });
        getContentPane().add(btnKonfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, 220, 50));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Kembali.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 680, 80, 70));

        btnKembali.setBackground(new java.awt.Color(245, 150, 92));
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(255, 255, 255));
        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/back.png"))); // NOI18N
        btnKembali.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 720, 60, 40));

        C2.setText("Show Password");
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });
        getContentPane().add(C2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 460, -1, 30));

        C1.setText("Show Password");
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });
        getContentPane().add(C1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, -1, 30));

        txt_password1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password1.setBorder(null);
        getContentPane().add(txt_password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 260, 50));

        txt_password2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_password2.setBorder(null);
        getContentPane().add(txt_password2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 270, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Lupa Password.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        try {
            String sql = "UPDATE user SET password ='"+txt_password2.getText()+"'WHERE id_user ='" + txtusername.getText() +"' ";
            java.sql.Connection conn = (Connection) db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Yeyyy Password Berhasil Di Rubah");
            this.setVisible(false);
            new formLogin1().setVisible(true);

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKonfirmasiActionPerformed

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.setVisible(false);
        new formLogin1().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        if(C1.isSelected()){
            txt_password1.setEchoChar((char)0);
        } else {
            txt_password1.setEchoChar('*');
        }
    }//GEN-LAST:event_C1ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
         if(C2.isSelected()){
            txt_password2.setEchoChar((char)0);
        } else {
            txt_password2.setEchoChar('*');
        }
    }//GEN-LAST:event_C2ActionPerformed

    
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
            java.util.logging.Logger.getLogger(lupapassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lupapassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lupapassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lupapassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lupapassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox C1;
    private javax.swing.JCheckBox C2;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKonfirmasi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txt_password1;
    private javax.swing.JPasswordField txt_password2;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
