package Fitur;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import laporan.pilihlaporanawalkrywn;

public class formBarang1 extends javax.swing.JFrame {
    
    private void tampilan(){
DefaultTableModel model = new DefaultTableModel();
     model.addColumn("No");
     model.addColumn("KODE BARANG");
     model.addColumn("BARCODE");
     model.addColumn("NAMA BARANG");
     model.addColumn("HARGA BELI");
     model.addColumn("HARGA JUAL");
     model.addColumn("STOK");
     model.addColumn("ID SUPPLIER");
     model.addColumn("NAMA SUPPLIER");                                                                                                                                                                                                                                                         
     model.addColumn("TELP SUPPLIER");
     model.addColumn("ALAMAT SUPPLIER");
     
     try {
         int no=1;
         String sql = "SELECT * FROM roti JOIN supplier ON roti.id_supp = supplier.id_supp";
         java.sql.Connection conn=(Connection)db_koneksi.configDB();
         java.sql.Statement stm=conn.createStatement();
         java.sql.ResultSet res=stm.executeQuery(sql);
         while (res.next()){
             model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4),
             res.getString(5), res.getString(6), res.getString(8), res.getString(9), res.getString(10), res.getString(11)});   
         }
         jTable1.setModel(model);
         } catch (SQLException e){
         }  
    }
    private void load_table(){
     DefaultTableModel model = new DefaultTableModel();
     model.addColumn("No");
     model.addColumn("KODE BARANG");
     model.addColumn("BARCODE");
     model.addColumn("NAMA BARANG");
     model.addColumn("HARGA BELI");
     model.addColumn("HARGA JUAL");
     model.addColumn("STOK");
     model.addColumn("ID SUPPLIER");
     model.addColumn("NAMA SUPPLIER");                                                                                                                                                                                                                                                         
     model.addColumn("TELP SUPPLIER");
     model.addColumn("ALAMAT SUPPLIER");
     
    String cari = txtcari.getText();
     try {
         int no=1;
         String sql = "SELECT * FROM roti JOIN supplier ON roti.id_supp = supplier.id_supp WHERE id_roti LIKE '%"+cari+"%' or nama LIKE '%"+cari+"%' or alamat LIKE '%"+cari+"%' or 'telp_supp' '%"+cari+"%'";
         java.sql.Connection conn=(Connection)db_koneksi.configDB();
         java.sql.Statement stm=conn.createStatement();
         java.sql.ResultSet res=stm.executeQuery(sql);
         while (res.next()){
             model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4),
             res.getString(5), res.getString(6), res.getString(8), res.getString(9), res.getString(10), res.getString(11)});   
         }
         jTable1.setModel(model);
         } catch (SQLException e){
         }  
    }
    
    
    public formBarang1(String id_user) {
        initComponents();
        tampilan();
        load_table();
        setNama(id_user);
        tampil_tanggal();
        tampil_waktu();
        kosong();
        
    }
    
    private void kosong(){
        txtcari.setText(null);
    }
    
    public void setNama(String user) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txt_tanggal = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(245, 150, 92));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/magnifier.png"))); // NOI18N
        jButton1.setText("CARI");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 90, 40));

        jTable1.setBackground(new java.awt.Color(255, 204, 153));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setRowHeight(35);
        jTable1.setSelectionBackground(new java.awt.Color(245, 150, 92));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 780, 560));

        txtcari.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 270, 40));

        jButton2.setBackground(new java.awt.Color(245, 150, 92));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/refresh.png"))); // NOI18N
        jButton2.setText("REFRESH");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 120, 40));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Username");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        jButton3.setOpaque(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 200, 60));

        jButton4.setOpaque(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, 50));

        jButton5.setOpaque(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, 50));

        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 190, 50));

        jButton8.setOpaque(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 190, 50));

        jButton9.setOpaque(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, 130, 50));

        jButton10.setBackground(new java.awt.Color(245, 150, 92));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/warning.png"))); // NOI18N
        jButton10.setText("LAPORKAN");
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 130, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang Karyawan REVISI.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        load_table();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tampilan();
        kosong();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int respons = JOptionPane.showConfirmDialog(this, "Yakin Logout ?", "Log Out", JOptionPane.
        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new formLogin1().setVisible(true); 
            JOptionPane.showMessageDialog(null, "Anda berhasil Log Out");
        } else {
        }  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        new Profil_1(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new Dasboard(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new cashier_1(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.setVisible(false);
        new suppliek(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.setVisible(false);
        new BarangMasukKaryawan(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try{ Desktop.getDesktop().browse(new URL ("https://wa.wizard.id/d69664").toURI());
        }catch(IOException | URISyntaxException ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(formBarang1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formBarang1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formBarang1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formBarang1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formBarang1(txt_username.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txt_tanggal;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
