package Fitur;

/**
 *
 * @author Us
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import laporan.pilihlaporanawalkrywn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public class cashier_1 extends javax.swing.JFrame {

     private DefaultTableModel model;
    // cashier() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    public void totalBiaya(){
        int jumlahBaris = jTable1.getSelectedRow();
        int totalBiaya = 0;
        int totBiaya = Integer.parseInt(String.valueOf(jTable1.getValueAt(jumlahBaris, 5)).trim());
        int tagi = Integer.parseInt(String.valueOf(txt_totHarga.getText().toString()));
        int totx = tagi - totBiaya;
       txt_tagihan.setText(IDbarang);
        //int jumlahBarang, hargaBarang;
        /*
        for (int i = 0 ; i<jumlahBaris ; i++){
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
            totalBiaya +=  jumlahBarang * hargaBarang;  
        }
        */
        
        txt_totHarga.setText(String.valueOf(totx));
        txt_tagihan.setText("Rp"+totx+",-");     
    }
    
     private void autoNumber() {
       try {
           String sql = "SELECT MAX(id_transaksi) AS ID FROM transaksi";
           java.sql.Connection conn = (Connection)db_koneksi.configDB();
           java.sql.Statement pst = conn.createStatement();
           java.sql.ResultSet rs = pst.executeQuery(sql);
           while(rs.next()) {
               Object[] obj = new Object[1];
               obj[0] = rs.getString("ID");
               if(obj[0] == null) {
                   obj[0] = "TR00000000";
               }
               String str_kd = (String) obj[0];
               String kd = str_kd.substring(2, 10);
               int int_code = Integer.parseInt(kd);
               int_code++;
               String a = String.format("%08d", int_code);
               String b = "TR" + a;
               txt_idTrs.setText(b);
               txt_idTrs.setEditable(false);
           }
       } catch (Exception e) {      
       }
    }
    
    public void utama() {
        txt_idTrs.setText("");
        txt_NamaBarang.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
        autoNumber();
    }
     public void clear(){
        txt_barcode.setText("");
        txt_totHarga.setText("");
        txt_Bayar.setText("");
        txt_kembalian.setText("");
        txt_tagihan.setText("0");
    }
    
    public void clear2() {
        txt_barcode.setText("");
        txt_idBrg.setText("");
        txt_NamaBarang.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");   
    }
    
    int totalJumlah;
    int totalHarga;
    String IDbarang;
    public void LoadData(){
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.addRow(new Object[]{
        txt_idTrs.getText(), // Sing Tak Tambahi
        txt_idBrg.getText(),
//        txt_barcode.getText(),
        txt_NamaBarang.getText(),
        txt_jumlah.getText(),
        txt_harga.getText(),
        txt_totHarga.getText()
        });
    }
    
     public void kosong(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
    }
      
    public void getTransaction(){
         String barcode1 = this.txt_barcode.getText();
         String query = "SELECT * FROM `roti` WHERE `code` = '" + barcode1 + "';";
         try {
             java.sql.Connection conn = (Connection) db_koneksi.configDB();
             java.sql.PreparedStatement prs = conn.prepareStatement(query);
             java.sql.ResultSet rs = prs.executeQuery();
             while(rs.next()){
                 this.txt_idBrg.setText(rs.getString("id_roti"));
                 this.txt_NamaBarang.setText(rs.getString("nama"));
                 this.txt_harga.setText(rs.getString("harga_jual"));
             }
         } catch (SQLException ex) {
             Logger.getLogger(cashier.class.getName()).log(Level.SEVERE, null, ex);
         }
    } 
    
    public void tambahTransaksi(){
        int jumlah,harga,total;
        
        jumlah = Integer.valueOf(txt_jumlah.getText());
        harga= Integer.valueOf(txt_harga.getText());
        total = jumlah * harga ;
        txt_tagihan.setText(String.valueOf(total));
        
        LoadData();
        totalBiaya();
        txt_idBrg.requestFocus();
    }
    
//     private void tampil_combo1() {
//        try {
//            String sql = "SELECT * FROM roti";
//            System.out.println(sql);
//            java.sql.Connection conn = (Connection)db_koneksi.configDB();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            java.sql.ResultSet rs = pst.executeQuery();
//            while(rs.next()) {
//            }
//            rs.last();
//            int jumlahdata = rs.getRow();
//        } catch (Exception e) {           
//        }
//    }
     
    public cashier_1(String id_user) {
     initComponents();
//        this.setResizable(false);
        setTitle("Kasir");
        autoNumber();
//        tampil_combo1();
        setName(id_user);
        tampil_tanggal();
        tampil_waktu();
        
        
        //Create Table
        model = new DefaultTableModel();
        
        jTable1.setModel(model);
        
        model.addColumn("ID Transaksi");
//        model.addColumn("Barcode");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total");
       
        utama();
        
        txt_totHarga.setText("0");
        txt_Bayar.setText("");
        txt_kembalian.setText("");
        txt_idTrs.requestFocus(); 
       
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_NamaBarang = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_idTrs = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        txt_tagihan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_idBrg = new javax.swing.JTextField();
        txt_Bayar = new javax.swing.JTextField();
        txt_totHarga = new javax.swing.JTextField();
        btn_hapus = new javax.swing.JButton();
        btn_hitung = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_customer = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_barcode = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_tanggal = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        BackgroundCs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_NamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NamaBarangKeyTyped(evt);
            }
        });
        getContentPane().add(txt_NamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 250, 30));
        getContentPane().add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 250, 30));

        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyReleased(evt);
            }
        });
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 70, 30));

        txt_idTrs.setEnabled(false);
        txt_idTrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idTrsActionPerformed(evt);
            }
        });
        getContentPane().add(txt_idTrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 250, 30));

        btn_tambah.setBackground(new java.awt.Color(245, 150, 92));
        btn_tambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 90, 30));

        btn_cari.setBackground(new java.awt.Color(245, 150, 92));
        btn_cari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_cari.setForeground(new java.awt.Color(255, 255, 255));
        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 70, 30));

        txt_tagihan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txt_tagihan.setForeground(new java.awt.Color(245, 150, 92));
        txt_tagihan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tagihanActionPerformed(evt);
            }
        });
        getContentPane().add(txt_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 350, 130));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Total Harga");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 660, 120, 40));

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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 810, 150));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Bayar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 710, 120, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Data Penjualan");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, -1, -1));

        txt_idBrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idBrgKeyTyped(evt);
            }
        });
        getContentPane().add(txt_idBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 250, 30));

        txt_Bayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BayarActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 710, 170, 40));

        txt_totHarga.setEditable(false);
        txt_totHarga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_totHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totHargaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_totHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 660, 170, 40));

        btn_hapus.setBackground(new java.awt.Color(245, 150, 92));
        btn_hapus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/bin.png"))); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, 120, 40));

        btn_hitung.setBackground(new java.awt.Color(245, 150, 92));
        btn_hitung.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_hitung.setForeground(new java.awt.Color(255, 255, 255));
        btn_hitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/calculator.png"))); // NOI18N
        btn_hitung.setText("HITUNG");
        btn_hitung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitungActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 610, 120, 40));

        btn_simpan.setBackground(new java.awt.Color(245, 150, 92));
        btn_simpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/printing.png"))); // NOI18N
        btn_simpan.setText("CETAK");
        btn_simpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 610, 120, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("ID Barang");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        txt_kembalian.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        txt_kembalian.setForeground(new java.awt.Color(0, 204, 51));
        txt_kembalian.setBorder(null);
        getContentPane().add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 670, 290, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Kembalian");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 690, 140, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nama Barang");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Harga");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Jumlah");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Nama Customer");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Username");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));
        getContentPane().add(txt_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 250, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Barcode");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("ID Transaksi");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        txt_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_barcodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_barcodeKeyTyped(evt);
            }
        });
        getContentPane().add(txt_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 250, 30));

        jButton1.setOpaque(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 190, 50));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        jButton2.setOpaque(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, 50));

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
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 190, 50));

        jButton6.setOpaque(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 190, 50));

        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, 140, 50));

        BackgroundCs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Kasir Karyawan REVISI.png"))); // NOI18N
        getContentPane().add(BackgroundCs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 static int totalHargaVar = 0;
    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
//        int qty = Integer.parseInt(txt_jumlah.getText());
//         
//         try {
//            String sql = "SELECT stok FROM roti WHERE id_roti = '"+txt_idBrg.getText()+"'";
//            java.sql.Connection conn = (Connection)db_koneksi.configDB();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            java.sql.ResultSet rs = pst.executeQuery(sql);
//            
//            if(rs.next()) {
//                int stok = rs.getInt(1);
//                if (stok < qty) {
//                    JOptionPane.showMessageDialog(null, "Maaf Stok kurang");
//                    clear2();
//                } else {
//                    tambahTransaksi();
//                    clear2();
//                }
//            }
//        } catch (Exception e) {
//        }
                
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
          if(txt_jumlah.getText().trim().equals("") || txt_NamaBarang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data Yang Dimasukkan Belum Lengkap");
        }else{
        model.addRow(new Object[]{txt_idTrs.getText(), txt_idBrg.getText(),txt_NamaBarang.getText(),txt_harga.getText(), // Tak tambahi
        txt_jumlah.getText(),String.valueOf(Integer.parseInt(txt_harga.getText()) * Integer.parseInt(txt_jumlah.getText()))});
            totalHargaVar += Integer.parseInt(txt_harga.getText()) * Integer.parseInt(txt_jumlah.getText());
            this.txt_totHarga.setText(String.valueOf(totalHargaVar));
            this.txt_tagihan.setText(String.valueOf(totalHargaVar));
        }
        clear2();

    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        try {
            String sql = "SELECT nama, harga_jual FROM roti WHERE id_roti = '"+this.txt_idBrg.getText()+"'";
            java.sql.Connection c = (Connection)db_koneksi.configDB();
            java.sql.PreparedStatement p = c.prepareStatement(sql);
            java.sql.ResultSet r = p.executeQuery(sql);
            
            if(r.next()) {
                txt_NamaBarang.setText(r.getString(1));
                txt_harga.setText(r.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        txt_jumlah.setEditable(true);     
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tagihanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tagihanActionPerformed

    private void txt_BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BayarActionPerformed
          int total, bayar, kembalian;

        total = Integer.valueOf(txt_totHarga.getText());
        bayar = Integer.valueOf(txt_Bayar.getText());
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txt_kembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txt_BayarActionPerformed

    private void txt_totHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totHargaActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:int respons = JOptionPane.showConfirmDialog(this, "Yakin Ingin Menghapus ?", "Hapus Barang", JOptionPane.
        int respons = JOptionPane.showConfirmDialog(this, "Yakin Ingin Menghapus ?", "Hapus Barang", JOptionPane.
            YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int row = jTable1.getSelectedRow();
            totalBiaya();
            model.removeRow(row);
        }
        
        int total, bayar, kembalian;
        
        total = Integer.valueOf(txt_totHarga.getText());
        bayar = Integer.valueOf(txt_Bayar.getText());
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txt_kembalian.setText(String.valueOf(kembalian));
        }
        
        
        
    
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
       
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        try {
            String sql = "INSERT INTO transaksi VALUES ("+"'"+txt_idTrs.getText()+"',now(),'"+txt_totHarga.getText()+"', "
                    + "'"+txt_Bayar.getText()+"', '"+txt_kembalian.getText()+"', '"+txt_customer.getText()+"', '"+txt_username.getText()+"')";
            System.out.println(sql);
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        try {
            int baris = jTable1.getRowCount();
            
            for (int i=0; i < baris; i++) {
                String sql = "INSERT INTO dtl_transaksi(qty, sub_total, id_transaksi, id_roti) VALUES ('"
                        +jTable1.getValueAt(i, 4)+"','"+jTable1.getValueAt(i, 5)+"','"+jTable1.getValueAt(i, 0)
                        +"','"+jTable1.getValueAt(i, 1)+"')";
                System.out.println(sql);
                java.sql.Connection c = (Connection)db_koneksi.configDB();
                java.sql.PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        try {
            String NamaFile = "C:\\Netbenas 8.2\\WSIBD_WEEK7\\src\\Fitur\\report2.jasper";

            HashMap parameter = new HashMap () ;
            parameter.put ("tampil_idTransaksi", txt_idTrs.getText()) ;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_baker","root","");
            File file = new File (NamaFile) ;

            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter,con);

            JasperViewer.viewReport( jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak"
            +"\n"+e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        
        clear();
        utama();
        autoNumber();
        kosong();
        txt_tagihan.setText("Rp. 0");
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_idTrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idTrsActionPerformed
       
    }//GEN-LAST:event_txt_idTrsActionPerformed

    private void btn_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitungActionPerformed
        int total, bayar, kembalian;
        
        total = Integer.valueOf(txt_totHarga.getText());
        bayar = Integer.valueOf(txt_Bayar.getText());
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txt_kembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_btn_hitungActionPerformed

    private void txt_NamaBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NamaBarangKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_NamaBarangKeyTyped

    private void txt_idBrgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idBrgKeyTyped
        // TODO add your handling code here:
        this.getTransaction();
    }//GEN-LAST:event_txt_idBrgKeyTyped

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void txt_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_barcodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_barcodeKeyPressed

    private void txt_barcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_barcodeKeyTyped
        // TODO add your handling code here:
        this.getTransaction();
    }//GEN-LAST:event_txt_barcodeKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new Profil_1(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        new Dasboard(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new formBarang1(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new BarangMasukKaryawan(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.setVisible(false);
        new suppliek(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int respons = JOptionPane.showConfirmDialog(this, "Yakin Logout ?", "Log Out", JOptionPane.
        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new formLogin1().setVisible(true); 
            JOptionPane.showMessageDialog(null, "Anda berhasil Log Out");
        } else {
        }  
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyReleased
        int qty = Integer.parseInt(txt_jumlah.getText()); 
        try {
            String sql = "SELECT stok FROM roti WHERE id_roti = '"+txt_idBrg.getText()+"'";
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            
            if(rs.next()) {
                int stok = rs.getInt(1);
                if (stok < qty) {
                    JOptionPane.showMessageDialog(null, "Maaf Stok kurang");
                    clear2();
                } 
                
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_jumlahKeyReleased

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
            java.util.logging.Logger.getLogger(cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cashier(txt_username.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundCs;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_Bayar;
    private javax.swing.JTextField txt_NamaBarang;
    private javax.swing.JTextField txt_barcode;
    private javax.swing.JTextField txt_customer;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_idBrg;
    private javax.swing.JTextField txt_idTrs;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_tagihan;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JTextField txt_totHarga;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
