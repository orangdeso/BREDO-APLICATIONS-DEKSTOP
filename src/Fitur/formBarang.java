package Fitur;

import com.barcodelib.barcode.Linear;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import laporan.pilihlaporanawaladmin;

public class formBarang extends javax.swing.JFrame {
    
//    private void generate (String kode) throws Exception {
//    EAN13 barcode = new EAN13();
//    
//    barcode.setData(kode);
//    
//    barcode.setUom(IBarcode.UOM_PIXEL);
//    
//    barcode.setX(3f);
//    
//    barcode.setY(75f);
//    
//    barcode.setLeftMargin(0f);
//    barcode.setRightMargin(0f);
//    barcode.setTopMargin(0f);
//    barcode.setBottomMargin(0f);
//    
//    barcode.setResolution(72);
//    
//    barcode.setShowText(true);
//    
//    barcode.setTextFont(new Font("Arial", 0, 12));
//    
//    barcode.setTextMargin(6);
//    
//    barcode.setRotate(IBarcode.ROTATE_0);
      private void cetak () {
    try {
        Linear barcode = new Linear();
        barcode.setType(Linear.CODE128B);
        barcode.setData(txt_barcode.getText());
        barcode.setI(12.0f);
        barcode.renderBarcode("C://Netbenas 8.2//WSIBD_WEEK7//src//img//" + txt_barcode.getText() + ".png");
        barcode.renderBarcode("C://Netbenas 8.2//WSIBD_WEEK7//src//barcode_cetak//" + txtnama.getText() + ".png");
    
    }catch(Exception e){}
    } 
//       barcode.drawBarcode("src" + "/" + "img" + "/barcode/" + kode + ".gif");
//    barcode.drawBarcode("C://Netbenas 8.2//WSIBD_WEEK7//src//img//" + kode + ".png");
//    barcode.drawBarcode(%%SpecCodeAA + txtnama.getText() + ".png");       
//   }
   
   public static String getRandomNumberString(){
       Random rnd = new Random();
       int number = rnd.nextInt(99999999);
       return String.format("%012d", number);
   }
    
  private void tampilan(){
DefaultTableModel model = new DefaultTableModel();
     model.addColumn("No");
     model.addColumn("KODE BARANG");
     model.addColumn("BARCODE");
     model.addColumn("NAMA BARANG");
     model.addColumn("HARGA JUAL");
     model.addColumn("HARGA BELI");
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
         jTable2.setModel(model);
         } catch (SQLException e){
         }  
    }
  private void load_table(){
     DefaultTableModel model = new DefaultTableModel();
     model.addColumn("No");
     model.addColumn("KODE BARANG");
     model.addColumn("KODE BARCODE");
     model.addColumn("NAMA BARANG");
     model.addColumn("HARGA JUAL");
     model.addColumn("HARGA BELI");
     model.addColumn("STOK");
     model.addColumn("ID SUPPLIER");
     model.addColumn("NAMA SUPPLIER");                                                                                                                                                                                                                                                         
     model.addColumn("TELP SUPPLIER");
     model.addColumn("ALAMAT SUPPLIER");
     
    String cari = txtcari.getText();
     try {
         int no=1;
         String sql = "SELECT * FROM roti JOIN supplier ON roti.id_supp = supplier.id_supp WHERE nama LIKE '%"+cari+"%'";
         java.sql.Connection conn=(Connection)db_koneksi.configDB();
         java.sql.Statement stm=conn.createStatement();
         java.sql.ResultSet res=stm.executeQuery(sql);
         while (res.next()){
             model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4),
             res.getString(5), res.getString(6), res.getString(8), res.getString(9), res.getString(10), res.getString(11)});   
         }
         jTable2.setModel(model);
         } catch (SQLException e){
         }   
    }                                  
          
    private void autonumber() {
       try {
           String sql = "SELECT MAX(id_roti) AS ID FROM roti";
           java.sql.Connection conn = (Connection)db_koneksi.configDB();
           java.sql.Statement pst = conn.createStatement();
           java.sql.ResultSet rs = pst.executeQuery(sql);
           while(rs.next()) {
               Object[] obj = new Object[1];
               obj[0] = rs.getString("ID");
               if(obj[0] == null) {
                   obj[0] = "BR00000000";
               }
               String str_kd = (String) obj[0];
               String kd = str_kd.substring(2, 10);
               int int_code = Integer.parseInt(kd);
               int_code++;
               String a = String.format("%08d", int_code);
               String b = "BR" + a;
               txtid.setText(b);
               txtid.setEditable(false);
           }
       } catch (Exception e) {      
       }
       txt_barcode.setText(getRandomNumberString());
    }
    
    private Statement st;
    private ResultSet rs;
    
    private void otoid() {
        try {
            st = db_koneksi.configDB().createStatement();
            rs = st.executeQuery("SELECT * FROM supplier");
            while (rs.next()) {
                cbidsupp.addItem(rs.getString("id_supp"));
            }
        } catch (Exception e) {
        }
    }
    
    private void otoidsupp() {
        try {
            st = db_koneksi.configDB().createStatement();
            rs = st.executeQuery("SELECT * FROM supplier WHERE id_supp = '" 
                    + cbidsupp.getSelectedItem() + "'");
           while (rs.next()) {
                String a = rs.getString("id_supp");
                String b = rs.getString("nama_supp");
                String c = rs.getString("telp_supp");
                String d = rs.getString("alamat");
                txtnama1.setText(b);
                txttelp.setText(c);
                txtalamat.setText(d);
           }
        } catch (Exception e) {
        }
    }
  
      
    private void kosong(){
    txtid.disable();
//    txtid.setText(null);
    txtnama.setText(null);
    txthargabeli.setText(null);
    txthargajual.setText(null);
    txtstok.setText(null);
    cbidsupp.setSelectedItem(null);
    txtnama1.setText(null);
    txttelp.setText(null);
    txtalamat.setText(null);
    txt_barcode.setText(null);
    barcode.setIcon(null);
    txtcari.setText(null);
}
     
    public formBarang(String id_user) {
        initComponents();
        kosong();
        tampilan();
        load_table();
        otoid();
        autonumber();
        setNama(id_user);
        tampil_tanggal();
        tampil_waktu();
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnedit = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btncari = new javax.swing.JButton();
        cbidsupp = new javax.swing.JComboBox<>();
        txtstok = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txthargajual = new javax.swing.JTextField();
        txtnama1 = new javax.swing.JTextField();
        txthargabeli = new javax.swing.JTextField();
        txttelp = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        txt_barcode = new javax.swing.JTextField();
        barcode = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_tanggal = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        btnhome = new javax.swing.JButton();
        btnkasir = new javax.swing.JButton();
        btnlaporan = new javax.swing.JButton();
        btnbarang = new javax.swing.JButton();
        btnsupplier = new javax.swing.JButton();
        btnbrgmasuk = new javax.swing.JButton();
        btnkaryawan = new javax.swing.JButton();
        btnkaryawan1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setBackground(new java.awt.Color(255, 204, 153));
        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.setRowHeight(35);
        jTable2.setSelectionBackground(new java.awt.Color(245, 150, 92));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 780, 200));

        btnedit.setBackground(new java.awt.Color(245, 150, 92));
        btnedit.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnedit.setForeground(new java.awt.Color(255, 255, 255));
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/edit.png"))); // NOI18N
        btnedit.setText("EDIT");
        btnedit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        getContentPane().add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, 90, 40));

        btnclear.setBackground(new java.awt.Color(245, 150, 92));
        btnclear.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/brush.png"))); // NOI18N
        btnclear.setText("CLEAR");
        btnclear.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        getContentPane().add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(831, 500, -1, 40));

        btnhapus.setBackground(new java.awt.Color(245, 150, 92));
        btnhapus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnhapus.setForeground(new java.awt.Color(255, 255, 255));
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/bin.png"))); // NOI18N
        btnhapus.setText("HAPUS");
        btnhapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 500, 110, 40));

        btncari.setBackground(new java.awt.Color(245, 150, 92));
        btncari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btncari.setForeground(new java.awt.Color(255, 255, 255));
        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/magnifier.png"))); // NOI18N
        btncari.setText("CARI");
        btncari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        getContentPane().add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, 90, 40));

        cbidsupp.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbidsupp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  " }));
        cbidsupp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbidsuppItemStateChanged(evt);
            }
        });
        cbidsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidsuppActionPerformed(evt);
            }
        });
        getContentPane().add(cbidsupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 190, 40));

        txtstok.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 190, 40));

        txtnama.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 190, 40));

        txthargajual.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txthargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 190, 40));

        txtnama1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtnama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 190, 40));

        txthargabeli.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txthargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 190, 40));

        txttelp.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txttelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, 190, 40));

        txtalamat.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 290, 190, 40));

        txtid.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 190, 40));

        btntambah.setBackground(new java.awt.Color(245, 150, 92));
        btntambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btntambah.setForeground(new java.awt.Color(255, 255, 255));
        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/add1.png"))); // NOI18N
        btntambah.setText("TAMBAH");
        btntambah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        getContentPane().add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, 120, 40));

        txtcari.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 150, 40));

        txt_barcode.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_barcodeActionPerformed(evt);
            }
        });
        txt_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_barcodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_barcodeKeyTyped(evt);
            }
        });
        getContentPane().add(txt_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 350, 190, 40));
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 640, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("TELP SUPPLIER");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 130, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("HARGA JUAL");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 130, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("STOK");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 130, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("ID BARANG");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 130, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("ID SUPPLIER");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 130, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("NAMA SUPPLIER");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 130, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("NAMA BARANG");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 130, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("BARCODE");
        jLabel9.setToolTipText("");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 140, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("HARGA BELI");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 130, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("ALAMAT");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 130, 40));

        jButton1.setBackground(new java.awt.Color(245, 150, 92));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/refresh.png"))); // NOI18N
        jButton1.setText("REFRESH");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 445, 120, 40));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Username");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        btnhome.setOpaque(false);
        btnhome.setContentAreaFilled(false);
        btnhome.setBorderPainted(false);
        btnhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, 50));

        btnkasir.setOpaque(false);
        btnkasir.setContentAreaFilled(false);
        btnkasir.setBorderPainted(false);
        btnkasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkasirActionPerformed(evt);
            }
        });
        getContentPane().add(btnkasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, 50));

        btnlaporan.setOpaque(false);
        btnlaporan.setContentAreaFilled(false);
        btnlaporan.setBorderPainted(false);
        btnlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(btnlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 190, 50));

        btnbarang.setOpaque(false);
        btnbarang.setContentAreaFilled(false);
        btnbarang.setBorderPainted(false);
        btnbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbarangActionPerformed(evt);
            }
        });
        getContentPane().add(btnbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 200, 50));

        btnsupplier.setOpaque(false);
        btnsupplier.setContentAreaFilled(false);
        btnsupplier.setBorderPainted(false);
        btnsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btnsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 190, 40));

        btnbrgmasuk.setOpaque(false);
        btnbrgmasuk.setContentAreaFilled(false);
        btnbrgmasuk.setBorderPainted(false);
        btnbrgmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbrgmasukActionPerformed(evt);
            }
        });
        getContentPane().add(btnbrgmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 190, 50));

        btnkaryawan.setOpaque(false);
        btnkaryawan.setContentAreaFilled(false);
        btnkaryawan.setBorderPainted(false);
        btnkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkaryawanActionPerformed(evt);
            }
        });
        getContentPane().add(btnkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, 130, 50));

        btnkaryawan1.setOpaque(false);
        btnkaryawan1.setContentAreaFilled(false);
        btnkaryawan1.setBorderPainted(false);
        btnkaryawan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkaryawan1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnkaryawan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 190, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang Admin REVISI.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE roti INNER JOIN supplier ON supplier.id_supp = roti.id_supp "
                    + "SET nama = '"+txtnama.getText()+"', harga_beli = '"+
                    txthargabeli.getText()+"', harga_jual = '"+txthargajual.getText()+"', stok = '"+txtstok.getText()
                    +"', nama_supp = '"+txtnama1.getText()+"', telp_supp = '"+txttelp.getText()+
                    "', alamat = '"+txtalamat.getText()+"' WHERE roti.id_roti = '"+txtid.getText()+"'";                  
            java.sql.Connection conn=(Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI EDIT");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PERUBAHAN DATA GAGAL"
                        +e.getMessage());
        }
        load_table();
        kosong();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        kosong();
        autonumber();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        try {
            String sql  = "DELETE FROM roti WHERE id_roti='"+txtid.getText()+"'";
            java.sql.Connection conn=(Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "BERHASIL DI HAPUS");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "GAGAL TERHAPUS");
        }
        load_table();
        kosong();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        load_table();
    }//GEN-LAST:event_btncariActionPerformed

    private void btnhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhomeActionPerformed
       this.setVisible(false);
       new Home(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_btnhomeActionPerformed

    private void btnkasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkasirActionPerformed
       this.setVisible(false);
       new cashier(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_btnkasirActionPerformed
                                    
    
    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
         try {
            String sql1 = "INSERT INTO roti VALUES ('"+txtid.getText()+"','"+txt_barcode.getText()+"','"
            +txtnama.getText()+"','"+txthargabeli.getText()+"','"+txthargajual.getText()+"','"
            +txtstok.getText()+"','"+cbidsupp.getSelectedItem()+"')";           
            java.sql.Connection conn=(java.sql.Connection)(Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
            pst1.execute();
            cetak();
//            this.setVisible(false);
//            new formBarang(txt_username.getText()).setVisible(true);
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception ex) {
           Logger.getLogger(formBarang.class.getName()).log(Level.SEVERE, null, ex);
       }
        load_table();
        kosong();
        autonumber();
    }//GEN-LAST:event_btntambahActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
                // TODO add your handling code here:
        int baris = jTable2.rowAtPoint(evt.getPoint());
        String barang = jTable2.getValueAt(baris,1).toString();
        txtid.setText(barang);
        txtid.disable();
        if (jTable2.getValueAt(baris, 2)==null){
            txt_barcode.setText("");
        } else {
            txt_barcode.setText(jTable2.getValueAt(baris, 2).toString());
            ImageIcon imgThisImg = new ImageIcon("C://Netbenas 8.2//WSIBD_WEEK7//src//img//"
                    + txt_barcode.getText() + ".png");
                    barcode.setIcon(imgThisImg);
        }
        if (jTable2.getValueAt(baris, 3)==null){
            txtnama.setText("");
        } else {
            txtnama.setText(jTable2.getValueAt(baris, 3).toString());
        }
        if (jTable2.getValueAt(baris, 5)==null){
            txthargabeli.setText("");
        } else {
            txthargabeli.setText(jTable2.getValueAt(baris, 5).toString());
        }
        if (jTable2.getValueAt(baris, 4)==null){
            txthargajual.setText("");
        } else {
            txthargajual.setText(jTable2.getValueAt(baris, 4).toString());
        }
        if (jTable2.getValueAt(baris, 6)==null){
            txtstok.setText("");
        } else {
            txtstok.setText(jTable2.getValueAt(baris, 6).toString());
        if (jTable2.getValueAt(baris, 7)==null){
            cbidsupp.setSelectedItem("");
        } else {
            cbidsupp.setSelectedItem(jTable2.getValueAt(baris, 7).toString());
        if (jTable2.getValueAt(baris, 8)==null){
            txtnama1.setText("");
        } else {
            txtnama1.setText(jTable2.getValueAt(baris, 8).toString());
        if (jTable2.getValueAt(baris, 9)==null){
            txttelp.setText("");
        } else {
            txttelp.setText(jTable2.getValueAt(baris, 9).toString());
        if (jTable2.getValueAt(baris, 10)==null){
            txtalamat.setText("");
        } else {
            txtalamat.setText(jTable2.getValueAt(baris, 10).toString());
        }
        }
        }
        }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void cbidsuppItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbidsuppItemStateChanged
        // TODO add your handling code here:
        otoidsupp();
    }//GEN-LAST:event_cbidsuppItemStateChanged

    private void btnbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbarangActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Profil(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_btnbarangActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void cbidsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidsuppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbidsuppActionPerformed

    private void txtidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtidKeyPressed

    private void txtidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtidKeyTyped

    private void txt_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_barcodeActionPerformed

    private void txt_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_barcodeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("No");
        model.addColumn("KODE BARANG");
        model.addColumn("KODE BARCODE");
        model.addColumn("NAMA BARANG");
        model.addColumn("HARGA BELI");
        model.addColumn("HARGA JUAL");
        model.addColumn("STOK");
        model.addColumn("ID SUPPLIER");
        model.addColumn("NAMA SUPPLIER");                                                                                                                                                                                                                                                         
        model.addColumn("TELP SUPPLIER");
        model.addColumn("ALAMAT SUPPLIER");
        
        try
        {
            int no=1;
            String sql = "SELECT * FROM roti JOIN supplier ON roti.id_supp = supplier.id_supp WHERE code='"+txtid.getText()+"'";
            java.sql.Connection conn=(Connection)db_koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()){
             model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4),
             res.getString(5), res.getString(6), res.getString(8), res.getString(9), res.getString(10), res.getString(11)
             });                      
            }
            jTable2.setModel(model);
        }
        catch(Exception e){
            e.printStackTrace();
    }
    }   
    }//GEN-LAST:event_txt_barcodeKeyPressed

    private void txt_barcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_barcodeKeyTyped
        // TODO add your handling code here:
        if("".equals(txt_barcode.getText())){
            load_table();
        }
    }//GEN-LAST:event_txt_barcodeKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tampilan();
        kosong();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnbrgmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbrgmasukActionPerformed
        this.setVisible(false);
        new BarangMasuk(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_btnbrgmasukActionPerformed

    private void btnkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkaryawanActionPerformed
        int respons = JOptionPane.showConfirmDialog(this, "Yakin Logout ?", "Log Out", JOptionPane.
        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new formLogin1().setVisible(true); 
            JOptionPane.showMessageDialog(null, "Anda berhasil Log Out");
        } else {
        }  
    }//GEN-LAST:event_btnkaryawanActionPerformed

    private void btnlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaporanActionPerformed
       this.setVisible(false);
       new pilihlaporanawaladmin(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_btnlaporanActionPerformed

    private void btnsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupplierActionPerformed
       this.setVisible(false);
       new supplier(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_btnsupplierActionPerformed

    private void btnkaryawan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkaryawan1ActionPerformed
       this.setVisible(false);
       new Karyawan(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_btnkaryawan1ActionPerformed

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
            java.util.logging.Logger.getLogger(formBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formBarang(txt_username.getText()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode;
    private javax.swing.JButton btnbarang;
    private javax.swing.JButton btnbrgmasuk;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnhome;
    private javax.swing.JButton btnkaryawan;
    private javax.swing.JButton btnkaryawan1;
    private javax.swing.JButton btnkasir;
    private javax.swing.JButton btnlaporan;
    private javax.swing.JButton btnsupplier;
    private javax.swing.JButton btntambah;
    private javax.swing.JComboBox<String> cbidsupp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_barcode;
    private javax.swing.JLabel txt_tanggal;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txthargabeli;
    private javax.swing.JTextField txthargajual;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnama1;
    private javax.swing.JTextField txtstok;
    private javax.swing.JTextField txttelp;
    // End of variables declaration//GEN-END:variables

}
