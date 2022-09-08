package Fitur;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class formLogin1 extends javax.swing.JFrame {

    public formLogin1() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid_karyawan = new javax.swing.JTextField();
        btnmasuk = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnlupa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtid_karyawan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtid_karyawan.setBorder(null);
        txtid_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_karyawanActionPerformed(evt);
            }
        });
        getContentPane().add(txtid_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 290, 30));

        btnmasuk.setBackground(new java.awt.Color(245, 150, 92));
        btnmasuk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnmasuk.setForeground(new java.awt.Color(255, 255, 255));
        btnmasuk.setText("MASUK");
        btnmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmasukActionPerformed(evt);
            }
        });
        getContentPane().add(btnmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 370, 50));

        txtpassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtpassword.setBorder(null);
        getContentPane().add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 410, 290, 30));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBox1.setText("Show Password");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 460, 130, 40));

        btnlupa.setOpaque(false);
        btnlupa.setContentAreaFilled(false);
        btnlupa.setBorderPainted(false);
        btnlupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlupaActionPerformed(evt);
            }
        });
        getContentPane().add(btnlupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 220, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Login.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnlupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlupaActionPerformed
        this.setVisible(false);
        new lupapassword().setVisible(true);
    }//GEN-LAST:event_btnlupaActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
        txtpassword.setEchoChar((char)0);
        else
        txtpassword.setEchoChar('*');
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmasukActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM user WHERE id_user='"+txtid_karyawan.getText()
            +"'AND password='"+txtpassword.getText()+"'";
            java.sql.Connection conn=(Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()){

                if (txtid_karyawan.getText().equals(rs.getString("id_user"))
                    && txtpassword.getText().equals(rs.getString("password"))){
                    JOptionPane.showMessageDialog(null, "BERHASIL LOGIN");
                    if(rs.getString("level").equals("Admin")){
                        this.setVisible(false);
                        new Home(rs.getString("id_user")).setVisible(true);
                        JOptionPane.showMessageDialog(null, "HAI ADMIN!");
                    } else if(rs.getString("level").equals("Karyawan")){
                        this.setVisible(false);
                        new Dasboard(rs.getString("id_user")).setVisible(true);
                        JOptionPane.showMessageDialog(null, "HAI KARYAWAN!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,
                    "USERNAME ATAU PASSWORD SALAH!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnmasukActionPerformed

    private void txtid_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_karyawanActionPerformed

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
            java.util.logging.Logger.getLogger(formLogin1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formLogin1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formLogin1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formLogin1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formLogin1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlupa;
    private javax.swing.JButton btnmasuk;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtid_karyawan;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
