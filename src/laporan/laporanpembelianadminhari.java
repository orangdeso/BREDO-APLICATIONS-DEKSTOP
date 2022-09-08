
package laporan;

//import static Home.txt_username2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class laporanpembelianadminhari extends javax.swing.JFrame {

    /**
     * Creates new form laporanpembelianadminhari
     */
    public laporanpembelianadminhari(String id_user) {
        initComponents();
        load_table();
        setNama(id_user);
        tampil_tanggal();
        tampil_waktu();
        kosong();
    }
    
     private void kosong(){
        TXT_TGL.setText(null);
        TXT_BLN.setText(null);
        TXT_THN.setText(null);
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
    private void load_table() {
    DefaultTableModel  tbl = new DefaultTableModel();
    tbl.addColumn("No.");
    tbl.addColumn("Id Pembelian");
    tbl.addColumn("Tanggal");
    tbl.addColumn("Jumlah");
    tbl.addColumn("Pengeluaran");
    tbl.addColumn("Supplier");

    String caridata = TXT_CARI.getText();
    try {
     int  no = 1;
     int harga_total = 0;
     int total_barang = 0;
     String sql = "select pembelian.id_pembelian, pembelian.tgl_pembelian, "
             + "sum(detail_pembelian.jumlah), pembelian.harga_total, pembelian.id_supp "
             + " from pembelian join detail_pembelian on pembelian.id_pembelian = detail_pembelian.id_pembelian "
             + " WHERE pembelian.id_pembelian LIKE'%"+caridata+"%'"
             + "or "+"tgl_pembelian like'%"+caridata+"%' or "+"jumlah like'%"+caridata+"%' "
             + "or "+"harga_total like'%"+caridata+"%' or "+"id_supp like '%"+caridata+"%' group by pembelian.id_pembelian";
     System.out.println(sql);
     java.sql.Connection conn = (Connection)db_koneksi.configDB();
     java.sql.Statement stm = conn.createStatement();
     java.sql.ResultSet rs = stm.executeQuery(sql);
   
     while (rs.next()) {
         tbl.addRow(new Object [ ] {
             no,
             rs.getString(1),
             rs.getString(2),
             rs.getString(3),
             rs.getString(4),
             rs.getString(5)
             
       
     });
         total_barang += Integer.parseInt(rs.getString(3));
        txt_terbeli.setText(String.valueOf(total_barang));
         harga_total += Integer.parseInt(rs.getString("harga_total"));
         txt_PENGELUARAN.setText(String.valueOf(harga_total));
         jTable1.setModel(tbl);
         no++;
     }
    }catch(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    private void cari() {
    DefaultTableModel  tbl = new DefaultTableModel();
    tbl.addColumn("No.");
    tbl.addColumn("Id Pembelian");
    tbl.addColumn("Tanggal");
    tbl.addColumn("Jumlah");
    tbl.addColumn("Pengeluaran");
    tbl.addColumn("Supplier");

    String caridata = TXT_CARI.getText();
    try {
     int  no = 1;
     int harga_total = 0;
     int total_barang = 0;
     String tampilan = "yyyy-MM-dd";
     SimpleDateFormat fm = new SimpleDateFormat(tampilan);
     String tgll1 = String.valueOf(fm.format(tgl1.getDate()));
     String tgll2 = String.valueOf(fm.format(tgl2.getDate()));
     String sql = "select pembelian.id_pembelian, pembelian.tgl_pembelian, sum(detail_pembelian.jumlah), pembelian.harga_total, pembelian.id_supp  from pembelian join detail_pembelian on pembelian.id_pembelian = detail_pembelian.id_pembelian  WHERE DATE(tgl_pembelian) BETWEEN '"+tgll1+"' AND '"+tgll2+"' group by pembelian.id_pembelian" ;
     System.out.println(sql);
     java.sql.Connection conn = (Connection)db_koneksi.configDB();
     java.sql.Statement stm = conn.createStatement();
     java.sql.ResultSet rs = stm.executeQuery(sql);
   
     while (rs.next()) {
         tbl.addRow(new Object [ ] {
             no,
             rs.getString(1),
             rs.getString(2),
             rs.getString(3),
             rs.getString(4),
             rs.getString(5)
             
       
     });
         total_barang += Integer.parseInt(rs.getString(3));
        txt_terbeli.setText(String.valueOf(total_barang));
         harga_total += Integer.parseInt(rs.getString("harga_total"));
         txt_PENGELUARAN.setText(String.valueOf(harga_total));
         jTable1.setModel(tbl);
         no++;
     }
    }catch(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_terbeli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_PENGELUARAN = new javax.swing.JTextField();
        btn_kembali = new javax.swing.JButton();
        BTN_REFRESH = new javax.swing.JButton();
        BTN_CARI = new javax.swing.JButton();
        TXT_THN = new javax.swing.JTextField();
        TXT_BLN = new javax.swing.JTextField();
        TXT_TGL = new javax.swing.JTextField();
        TXT_CARI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BTN_CETAK = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        tgl1 = new com.toedter.calendar.JDateChooser();
        tgl2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 780, 370));

        txt_terbeli.setEditable(false);
        txt_terbeli.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txt_terbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_terbeliActionPerformed(evt);
            }
        });
        getContentPane().add(txt_terbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 660, 90, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Barang Terbeli");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, 160, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Pengeluaran");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 710, 150, 40));

        txt_PENGELUARAN.setEditable(false);
        txt_PENGELUARAN.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        getContentPane().add(txt_PENGELUARAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 710, 170, 40));

        btn_kembali.setBackground(new java.awt.Color(245, 150, 92));
        btn_kembali.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_kembali.setForeground(new java.awt.Color(255, 255, 255));
        btn_kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/back.png"))); // NOI18N
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 720, 60, 40));

        BTN_REFRESH.setBackground(new java.awt.Color(245, 150, 92));
        BTN_REFRESH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTN_REFRESH.setForeground(new java.awt.Color(255, 255, 255));
        BTN_REFRESH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/refresh.png"))); // NOI18N
        BTN_REFRESH.setText("REFRESH");
        BTN_REFRESH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_REFRESHActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_REFRESH, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 130, 40));

        BTN_CARI.setBackground(new java.awt.Color(245, 150, 92));
        BTN_CARI.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTN_CARI.setForeground(new java.awt.Color(255, 255, 255));
        BTN_CARI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/magnifier.png"))); // NOI18N
        BTN_CARI.setText("CARI");
        BTN_CARI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_CARI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BTN_CARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CARIActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_CARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 100, 40));

        TXT_THN.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(TXT_THN, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 70, 40));

        TXT_BLN.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(TXT_BLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 70, 40));

        TXT_TGL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TXT_TGL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_TGLActionPerformed(evt);
            }
        });
        getContentPane().add(TXT_TGL, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 70, 40));

        TXT_CARI.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TXT_CARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_CARIActionPerformed(evt);
            }
        });
        TXT_CARI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_CARIKeyTyped(evt);
            }
        });
        getContentPane().add(TXT_CARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 150, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Cari Data");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 90, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tanggal");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 80, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Bulan");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, 50));

        BTN_CETAK.setBackground(new java.awt.Color(245, 150, 92));
        BTN_CETAK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTN_CETAK.setForeground(new java.awt.Color(255, 255, 255));
        BTN_CETAK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/printing.png"))); // NOI18N
        BTN_CETAK.setText("CETAK");
        BTN_CETAK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTN_CETAK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BTN_CETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CETAKActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_CETAK, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 660, 110, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Tahun");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, 70, 50));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Username");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));
        getContentPane().add(tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));
        getContentPane().add(tgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambarlaporanadmin/Laporan pembelianhari Admin REVISI.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_terbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_terbeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_terbeliActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.setVisible(false);
        new laporanadminpembelian(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void BTN_REFRESHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_REFRESHActionPerformed
        load_table();
        kosong();
    }//GEN-LAST:event_BTN_REFRESHActionPerformed

    private void BTN_CARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CARIActionPerformed
        load_table();
        cari();
        //        cari_hari();
    }//GEN-LAST:event_BTN_CARIActionPerformed

    private void TXT_TGLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_TGLActionPerformed

    }//GEN-LAST:event_TXT_TGLActionPerformed

    private void TXT_CARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_CARIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_CARIActionPerformed

    private void TXT_CARIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_CARIKeyTyped
        load_table();
    }//GEN-LAST:event_TXT_CARIKeyTyped

    private void BTN_CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CETAKActionPerformed
        // TODO add your handling code here:
        cetak();
    }//GEN-LAST:event_BTN_CETAKActionPerformed
private void cetak() {
            try {
                jTable1.print();
                
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null,  "Maaf Data Table Anda Kosong");
            }
        }
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
            java.util.logging.Logger.getLogger(laporanpembelianadminhari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporanpembelianadminhari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporanpembelianadminhari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporanpembelianadminhari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporanpembelianadminhari(txt_username.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_CARI;
    private javax.swing.JButton BTN_CETAK;
    private javax.swing.JButton BTN_REFRESH;
    private javax.swing.JTextField TXT_BLN;
    private javax.swing.JTextField TXT_CARI;
    private javax.swing.JTextField TXT_TGL;
    private javax.swing.JTextField TXT_THN;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser tgl1;
    private com.toedter.calendar.JDateChooser tgl2;
    private javax.swing.JTextField txt_PENGELUARAN;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JTextField txt_terbeli;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
