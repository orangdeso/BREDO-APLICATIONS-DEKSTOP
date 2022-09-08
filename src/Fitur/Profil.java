package Fitur;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class Profil extends javax.swing.JFrame {

    public Profil(String id_user) {
        initComponents();
        setNama(id_user);
        tampilProfil();
    }
    
    public void setNama(String user) {
        profil_nama.setText(user);
    }
    
    private void tombolEdit2() {
        txt_nama.setEditable(true);
        txt_tanggallahir.setEditable(true);
        txt_telepon.setEditable(true);
        txt_jabatan.setEditable(true);
        txt_alamat.setEditable(true); 
    }
    
    private void tombolEdit() {
        txt_nama.setEditable(false);
        txt_tanggallahir.setEditable(false);
        txt_telepon.setEditable(false);
        txt_jabatan.setEditable(false);
        txt_alamat.setEditable(false);
    }
    
    private void tampilProfil() {
        try {
            String sql = "SELECT * FROM user WHERE id_user = '"+profil_nama.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                txt_nama.setText(res.getString(2));
                txt_tanggallahir.setText(res.getString(3));
                txt_telepon.setText(res.getString(4));
                txt_alamat.setText(res.getString(5));
                txt_jabatan.setText(res.getString(7));

                
            }
        } catch (Exception e) {
        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txt_jabatan = new javax.swing.JTextField();
        txt_tanggallahir = new javax.swing.JTextField();
        txt_telepon = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Telepon.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, -1, 60));

        profil_nama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        profil_nama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profil_nama.setText("Username");
        getContentPane().add(profil_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 80, -1));

        jLabel4.setBackground(new java.awt.Color(255, 51, 51));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Nama1.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 50, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Tanggal_lahir.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 50, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Alamat.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, -1, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Jabatan.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 50, 50));

        btn_simpan.setBackground(new java.awt.Color(245, 150, 92));
        btn_simpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/diskette.png"))); // NOI18N
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, 40, 40));

        btn_edit.setBackground(new java.awt.Color(245, 150, 92));
        btn_edit.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/edit.png"))); // NOI18N
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 40, 40));

        btn_hapus.setBackground(new java.awt.Color(245, 150, 92));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("HAPUS AKUN");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 580, 320, 50));

        jButton4.setBackground(new java.awt.Color(245, 150, 92));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/home.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 720, 50, 40));

        jPanel1.setBackground(new java.awt.Color(214, 130, 79));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 50, 50));

        jPanel4.setBackground(new java.awt.Color(214, 130, 79));
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 50, 50));

        jPanel2.setBackground(new java.awt.Color(214, 130, 79));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 50, 50));

        jPanel3.setBackground(new java.awt.Color(214, 130, 79));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 50, 50));

        jPanel5.setBackground(new java.awt.Color(214, 130, 79));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 50, 50));

        txt_jabatan.setEditable(false);
        txt_jabatan.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        getContentPane().add(txt_jabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 260, 50));

        txt_tanggallahir.setEditable(false);
        txt_tanggallahir.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        getContentPane().add(txt_tanggallahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 260, 50));

        txt_telepon.setEditable(false);
        txt_telepon.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txt_telepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_teleponActionPerformed(evt);
            }
        });
        getContentPane().add(txt_telepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, 260, 50));

        txt_nama.setEditable(false);
        txt_nama.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 260, 50));

        txt_alamat.setEditable(false);
        txt_alamat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 260, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Profil.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_teleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_teleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_teleponActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        tombolEdit2();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        try {
            String sql = "UPDATE user SET nama = '"+txt_nama.getText()+"', tanggal_lahir = '"+txt_tanggallahir.getText()
                    +"', no_telp = '"+txt_telepon.getText()+"', alamat = '"+txt_alamat.getText()+"', level = '"+txt_jabatan.getText()
                    +"' WHERE id_user = '"+profil_nama.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil SIMPAN");
        } catch (Exception e) {
             e.printStackTrace();
        }
        tombolEdit();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int pop = JOptionPane.showConfirmDialog(this, "Pastikan Semua Data telah ke Backup!", "Pengajuan Penghapusan Akun", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pop == JOptionPane.YES_OPTION) { 
        
            try {
                String hapus = "DELETE FROM user WHERE id_user = '"+profil_nama.getText()+"'";
                System.out.println(hapus);
                java.sql.Connection conn = (Connection) db_koneksi.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(hapus);
                pst.execute();
                
                this.setVisible(false);
                new formLogin1().setVisible(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new Home(profil_nama.getText()).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profil(profil_nama.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static final javax.swing.JLabel profil_nama = new javax.swing.JLabel();
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_jabatan;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_tanggallahir;
    private javax.swing.JTextField txt_telepon;
    // End of variables declaration//GEN-END:variables
}
