package Fitur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import laporan.pilihlaporanawalkrywn;

public class Dasboard extends javax.swing.JFrame {

    public Dasboard(String id_user) {
        initComponents();
        barangKeluar();
        stok();
        total_transaksi();
        pendapatan();
        tampil_tanggal();
        tampil_waktu();
        setName(id_user);
    }
    
    public void setName(String user) {
        txt_username.setText(user);
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
        txt_barangkeluar.setText("0");
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
                txt_barangkeluar.setText(String.valueOf(total));
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
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_pendapatan = new javax.swing.JLabel();
        txt_stok = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        txt_transaksi = new javax.swing.JLabel();
        txt_barangkeluar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_pendapatan.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_pendapatan.setForeground(new java.awt.Color(245, 150, 92));
        txt_pendapatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_pendapatan.setText("0");
        getContentPane().add(txt_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 490, 240, -1));

        txt_stok.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_stok.setForeground(new java.awt.Color(245, 150, 92));
        txt_stok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_stok.setText("0");
        getContentPane().add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 170, -1));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_transaksi.setForeground(new java.awt.Color(245, 150, 92));
        txt_transaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_transaksi.setText("0");
        getContentPane().add(txt_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, 170, -1));

        txt_barangkeluar.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        txt_barangkeluar.setForeground(new java.awt.Color(245, 150, 92));
        txt_barangkeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_barangkeluar.setText("0");
        getContentPane().add(txt_barangkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 140, -1));

        jButton1.setOpaque(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 200, 50));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Username");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        jButton6.setOpaque(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, 190, 40));

        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 190, 50));

        jButton2.setOpaque(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, 40));

        jButton4.setOpaque(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 190, 50));

        jButton5.setOpaque(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 190, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Dashboard REVISI.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new Profil_1(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int respons = JOptionPane.showConfirmDialog(this, "Yakin Logout ?", "Log Out", JOptionPane.
        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new formLogin1().setVisible(true); 
            JOptionPane.showMessageDialog(null, "Anda berhasil Log Out");
        } else {
        }          
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new suppliek(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new formBarang1(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.setVisible(false);
        new BarangMasukKaryawan(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        new cashier_1(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dasboard(txt_username.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txt_barangkeluar;
    private javax.swing.JLabel txt_pendapatan;
    private javax.swing.JLabel txt_stok;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JLabel txt_transaksi;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
