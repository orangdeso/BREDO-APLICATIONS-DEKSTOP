package Fitur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import laporan.pilihlaporanawaladmin;

public class Home extends javax.swing.JFrame {

    public Home(String id_user) {
        initComponents();
        stok();
        total_transaksi();
        pendapatan();
        karyawan();
        barangKeluar();
        tampil_tanggal();
        tampil_waktu();
        pengeluaran();
        setNama(id_user);
    }
    
    public void setNama(String user) {
        txt_username2.setText(user);
    }
      
    private void tampil_tanggal(){
        java.util.Date tglsekaran = new java.util.Date();
        SimpleDateFormat oi = new SimpleDateFormat("EEEE dd MMMM YYYY", Locale.getDefault());
        String tanggal = oi.format(tglsekaran);
        txt_tanggal.setText(tanggal);
    }
    
    private void tampil_waktu(){
        ActionListener taskPerformer = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";
            
                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();
            
                if(nilai_jam <= 9) nol_jam = "0";
                if(nilai_menit <= 9) nol_jam = "0";
                if(nilai_detik <= 9) nol_jam = "0";
            
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
            
                txt_waktu.setText(jam + ":" + menit + ":" + detik + "");
            }      
        };
        new Timer(1000, taskPerformer).start(); 
    }
    
    private void barangKeluar(){
        txt_barangKeluar.setText("0");
        int total =0;
        try {
            String sql = "SELECT SUM(dtl_transaksi.qty) AS TOTAL, (transaksi.tanggal_transaksi)"
                    + "FROM dtl_transaksi JOIN transaksi ON dtl_transaksi.id_transaksi=transaksi.id_transaksi" 
                    + " WHERE date(transaksi.tanggal_transaksi) = CURDATE()" 
                    + " GROUP BY transaksi.tanggal_transaksi";
            System.out.println(sql);
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                total += Integer.parseInt(res.getString("TOTAL"));
                txt_barangKeluar.setText(String.valueOf(total));
            }
        
        } catch (Exception e) {
    }
    }

    private void stok(){
        txt_stok.setText(null);
        int total =0;
        try {
            String sql = "SELECT *, SUM(stok) AS TOTAL FROM roti";
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                total += Integer.parseInt(res.getString("TOTAL"));
                txt_stok.setText(String.valueOf(total));
            }
        } catch (Exception e) {
        }
    }
    
    private void total_transaksi() {
        txt_transaksi.setText(null);
        int total =0;
        try {
            String sql = "SELECT *, COUNT(id_transaksi) AS TOTAL_TRANSAKSI FROM transaksi WHERE date(tanggal_transaksi) = CURDATE()";
            System.out.println(sql);
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                total += Integer.parseInt(res.getString("TOTAL_TRANSAKSI"));
                txt_transaksi.setText(String.valueOf(total));
            } 
        } catch (Exception e) {
        }
    }
    
    private void pendapatan() {
        txt_pendapatan.setText("0");
        int total =0;
        try {
            String sql = "SELECT *, SUM(tot_bayar) AS PENDAPATAN FROM transaksi WHERE date(tanggal_transaksi) = CURDATE()";
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                total += Integer.parseInt(res.getString("PENDAPATAN"));
                txt_pendapatan.setText(String.valueOf(total));
            }
            String mataUang = txt_pendapatan.getText().replaceAll("\\.", "");
            double dbbyr = Double.parseDouble(mataUang);
            DecimalFormat df = new DecimalFormat("#,###,##0");
            if(dbbyr>999) {
                txt_pendapatan.setText(df.format(dbbyr));
                
            }
        } catch (Exception e) {
        }
    }
    
    private void karyawan(){
        txt_karyawan.setText(null);
        int total =0;
        try {
            String sql = "SELECT*, COUNT(nama) AS TOTAL FROM user WHERE level = 'karyawan'";
            System.out.println(sql);
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                total += Integer.parseInt(res.getString("TOTAL"));
                txt_karyawan.setText(String.valueOf(total));
            }
        } catch (Exception e) {
        }
    }
    
    private void pengeluaran() {
        txt_pengeluaran.setText("0");
        int total =0;
        try {
            String sql = "SELECT *, SUM(harga_total) AS PENGELUARAN FROM pembelian WHERE date(tgl_pembelian) = CURDATE()";
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                total += Integer.parseInt(res.getString("PENGELUARAN"));
                txt_pengeluaran.setText(String.valueOf(total));
            }
            String mataUang = txt_pengeluaran.getText().replaceAll("\\.", "");
            double dbbyr = Double.parseDouble(mataUang);
            DecimalFormat df = new DecimalFormat("#,###,##0");
            if(dbbyr>999) {
                txt_pengeluaran.setText(df.format(dbbyr));
                
            }
        } catch (Exception e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_transaksi = new javax.swing.JLabel();
        txt_pendapatan = new javax.swing.JLabel();
        txt_karyawan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txt_waktu = new javax.swing.JLabel();
        txt_barangKeluar = new javax.swing.JLabel();
        txt_pengeluaran = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txt_stok = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txt_tanggal = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_transaksi.setForeground(new java.awt.Color(245, 150, 92));
        txt_transaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_transaksi.setText("0");
        getContentPane().add(txt_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 180, -1));

        txt_pendapatan.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        txt_pendapatan.setForeground(new java.awt.Color(245, 150, 92));
        txt_pendapatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_pendapatan.setText("0");
        getContentPane().add(txt_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 610, 240, 70));

        txt_karyawan.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_karyawan.setForeground(new java.awt.Color(245, 150, 92));
        txt_karyawan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_karyawan.setText("0");
        getContentPane().add(txt_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 140, -1));

        jButton1.setOpaque(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 180, 40));

        jButton8.setOpaque(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 230, 50));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        txt_username2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username2.setText("Username");
        getContentPane().add(txt_username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        txt_barangKeluar.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        txt_barangKeluar.setForeground(new java.awt.Color(245, 150, 92));
        txt_barangKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_barangKeluar.setText("0");
        getContentPane().add(txt_barangKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 170, 70));

        txt_pengeluaran.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        txt_pengeluaran.setForeground(new java.awt.Color(245, 150, 92));
        txt_pengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_pengeluaran.setText("0");
        getContentPane().add(txt_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 230, 70));

        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 180, 40));

        txt_stok.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_stok.setForeground(new java.awt.Color(245, 150, 92));
        txt_stok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_stok.setText("0");
        getContentPane().add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 600, 140, -1));

        jButton2.setOpaque(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 180, 40));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        jButton3.setOpaque(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 180, 40));

        jButton4.setOpaque(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 180, 30));

        jButton5.setOpaque(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 180, 40));

        jButton6.setOpaque(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 140, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Home REVISI.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int respons = JOptionPane.showConfirmDialog(this, "KELUAR DARI HALAMAN ?", "LOGOUT", JOptionPane.
        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new formLogin1().setVisible(true); 
            JOptionPane.showMessageDialog(null, "ANDA BERHASIL LOGOUT");
        } else {
        }  
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new Karyawan(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.setVisible(false);
        new Profil(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        new supplier(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        new cashier(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new BarangMasuk(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.setVisible(false);
        new formBarang(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new pilihlaporanawaladmin(txt_username2.getText()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(txt_username2.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txt_barangKeluar;
    private javax.swing.JLabel txt_karyawan;
    private javax.swing.JLabel txt_pendapatan;
    private javax.swing.JLabel txt_pengeluaran;
    private javax.swing.JLabel txt_stok;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JLabel txt_transaksi;
    public static final javax.swing.JLabel txt_username2 = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
