package Fitur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import laporan.pilihlaporanawaladmin;

public class BarangMasuk extends javax.swing.JFrame {

    private String total;

    public BarangMasuk(String id_user) {
        initComponents();
        load_table();
        otonamaBarang();
//        otonamaSupp();
        otopanggilBarang();
//        otopanggilSupp();
        autonumber();
        kosong();
        setName(id_user);
        tampil_tanggal();
        tampil_waktu();
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
    
    public void setName(String user) {
        txt_username.setText(user);
    }
    
    private Statement st;
    private ResultSet rs;
    
    private void otonamaBarang() {
        try {
            st = db_koneksi.configDB().createStatement();
            rs = st.executeQuery("SELECT * FROM roti");
            while (rs.next()) {
                CBNamaBarang.addItem(rs.getString("nama"));
            }
        } catch (SQLException e) {
        }
    }
    
    private void kosong() {
        txtIdPembelian.setText(null);
        txtIdBarang.setText(null);
        txtIdSupp.setText(null);
        CBNamaSupplier.setText(null);
        txtIdSupp.setText(null);
        txtHargaTotal.setText(null);
        txtStok.setText(null);
        txtHargaMasuk.setText(null);
        CBNamaBarang.setSelectedItem(null);
        txtJumlah.setText(null);
        autonumber();
    }
    
   private void otopanggilBarang() {
        try {
            st = db_koneksi.configDB().createStatement();
            rs = st.executeQuery("SELECT * FROM roti JOIN supplier ON roti.id_supp = supplier.id_supp WHERE nama = '" 
                    + CBNamaBarang.getSelectedItem() + "'");
            while (rs.next()) {
                String a = rs.getString("id_roti");
                String b = rs.getString("nama_supp");
                String c = rs.getString("stok");
                String d = rs.getString("id_supp");
                txtIdBarang.setText(a);
                CBNamaSupplier.setText(b);
                txtStok.setText(c);
                txtIdSupp.setText(d);
                }
            } catch (Exception e) {
        }
    }
    
    private void tampiltotal() {
        int jumlah, harga_masuk, total;
        
        jumlah = Integer.valueOf(txtJumlah.getText());
        harga_masuk = Integer.valueOf(txtHargaMasuk.getText());
        total = jumlah * harga_masuk;
        
        txtHargaTotal.setText(String.valueOf(total));
    }
    
    private void autonumber() {
       try {
           String sql = "SELECT MAX(id_pembelian) AS ID FROM pembelian";
           java.sql.Connection conn = (Connection)db_koneksi.configDB();
           java.sql.Statement pst = conn.createStatement();
           java.sql.ResultSet rs = pst.executeQuery(sql);
           while(rs.next()) {
               Object[] obj = new Object[1];
               obj[0] = rs.getString("ID");
               if(obj[0] == null) {
                   obj[0] = "PM00000000";
               }
               
               String str_id = (String) obj[0];
               String kd = str_id.substring(2, 10);
               int int_code = Integer.parseInt(kd);
               int_code++;
               String a = String.format("%08d", int_code);
               String b = "PM" + a;
               txtIdPembelian.setText(b);
               txtIdPembelian.setEditable(false);
               txtIdBarang.setEditable(false);
               txtIdSupp.setEditable(false);
                }
           } catch (NumberFormatException | SQLException e) {      
       }
    }
    
    private void load_table(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID Pembelian");
        model.addColumn("ID Barang");
        model.addColumn("ID Supplier");
        model.addColumn("Nama Barang");
        model.addColumn("Nama Supplier");
        model.addColumn("Jumlah");
        model.addColumn("Harga Masuk");
        model.addColumn("Harga Total");                                                                                                                                                                                                                                                         
        model.addColumn("Stok");

        try {
            int no = 1;
            String sql = "SELECT pembelian.id_pembelian, roti.id_roti, supplier.id_supp, roti.nama, supplier.nama_supp, detail_pembelian.jumlah, "
                            + " detail_pembelian.harga_masuk, pembelian.harga_total,  roti.stok" +
                            " FROM roti" +
                            " JOIN supplier ON roti.id_supp = supplier.id_supp" +
                            " JOIN detail_pembelian ON roti.id_roti = detail_pembelian.id_roti" +
                            " JOIN pembelian ON pembelian.id_pembelian = detail_pembelian.id_pembelian";

            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()){
                model.addRow(new Object[]{no++, 
                    res.getString(1), 
                    res.getString(2), 
                    res.getString(3), 
                    res.getString(4),
                    res.getString(5), 
                    res.getString(6),
                    res.getString(7),
                    res.getString(8),
                    res.getString(9)});   
            }
            jTable1.setModel(model);
            } catch (SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdPembelian = new javax.swing.JTextField();
        txtIdBarang = new javax.swing.JTextField();
        txtIdSupp = new javax.swing.JTextField();
        txtHargaTotal = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtHargaMasuk = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        CBNamaBarang = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        txtJumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        txt_tanggal = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        CBNamaSupplier = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdPembelian.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtIdPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPembelianActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 145, 30));

        txtIdBarang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtIdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBarangActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 145, 30));

        txtIdSupp.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtIdSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSuppActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdSupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 145, 30));

        txtHargaTotal.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        txtHargaTotal.setForeground(new java.awt.Color(245, 150, 92));
        txtHargaTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHargaTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaTotalActionPerformed(evt);
            }
        });
        getContentPane().add(txtHargaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 180, 70));

        txtStok.setEditable(false);
        txtStok.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStokActionPerformed(evt);
            }
        });
        getContentPane().add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, 150, 30));

        txtHargaMasuk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtHargaMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaMasukActionPerformed(evt);
            }
        });
        getContentPane().add(txtHargaMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 150, 30));

        jButton1.setBackground(new java.awt.Color(245, 150, 92));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/calculator.png"))); // NOI18N
        jButton1.setText("HITUNG");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 430, 120, 40));

        jButton2.setBackground(new java.awt.Color(245, 150, 92));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/bin.png"))); // NOI18N
        jButton2.setText("HAPUS");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 430, 100, 40));

        jButton3.setBackground(new java.awt.Color(245, 150, 92));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/diskette.png"))); // NOI18N
        jButton3.setText("SIMPAN");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 110, 40));

        CBNamaBarang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        CBNamaBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        CBNamaBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBNamaBarangItemStateChanged(evt);
            }
        });
        CBNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBNamaBarangActionPerformed(evt);
            }
        });
        getContentPane().add(CBNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, 150, 30));

        jButton11.setOpaque(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 210, 50));

        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_username.setText("Usernama");
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 120, 130, 40));

        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 145, 30));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 780, 270));

        jButton4.setOpaque(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, 140, 50));

        jButton5.setOpaque(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 220, 50));

        jButton6.setOpaque(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 220, 50));

        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 220, 50));

        jButton8.setOpaque(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 220, 50));

        jButton9.setOpaque(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 220, 50));

        jButton10.setOpaque(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 210, 50));

        txt_tanggal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, 40));

        txt_waktu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_waktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 80, 40));

        jButton12.setBackground(new java.awt.Color(245, 150, 92));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikon/brush.png"))); // NOI18N
        jButton12.setText("CLEAR");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, 100, 40));

        CBNamaSupplier.setEditable(false);
        CBNamaSupplier.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        getContentPane().add(CBNamaSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 220, 150, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang masuk Admin REVISI.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int pop = JOptionPane.showConfirmDialog(this, "HAPUS DATA?", "HAPUS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pop == JOptionPane.YES_OPTION) {                
            
        try {
            String sql = "DELETE FROM pembelian WHERE id_pembelian = '" + txtIdPembelian.getText()+"'";
            java.sql.Connection conn = (Connection)db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            int row = jTable1.getSelectedRow();
            model.removeRow(row);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            }
        kosong();
        }
    
        load_table();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {        
            String sql1 = "INSERT INTO pembelian VALUES ("+"'"+txtIdPembelian.getText()+"',now(),'"+txtHargaTotal.getText() +"','"+ txtIdSupp.getText()+"')";
            String sql2 = "INSERT INTO detail_pembelian (id_roti,id_pembelian,jumlah,harga_masuk) VALUES ('" + txtIdBarang.getText() + "','" + txtIdPembelian.getText() + "','" + txtJumlah.getText() + "','" + txtHargaMasuk.getText() + "')";
            String sql3 = "UPDATE roti SET harga_beli = '"+txtHargaMasuk.getText()+"' WHERE id_roti = '"+txtIdBarang.getText()+"'";
            java.sql.Connection conn = (Connection) db_koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql1);
            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql2);
            java.sql.PreparedStatement pst2 = conn.prepareStatement(sql3);
            pst.execute();
            pst1.execute();
            pst2.execute();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DITAMBAHKAN");          
            
            } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        load_table();
        kosong();
    }//GEN-LAST:event_jButton3ActionPerformed
   
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new Home(txt_username.getText()).setVisible(true);        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.setVisible(false);
        new cashier(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.setVisible(false);
        new pilihlaporanawaladmin(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.setVisible(false);
        new formBarang(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.setVisible(false);
        new supplier(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.setVisible(false);
        new Karyawan(txt_username.getText()).setVisible(true); 
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int respons = JOptionPane.showConfirmDialog(this, "KELUAR DARI HALAMAN ?", "LOGOUT", JOptionPane.
        YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respons==JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new formLogin1().setVisible(true); 
            JOptionPane.showMessageDialog(null, "ANDA BERHASIL LOGOUT");
        } else {
        }  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtIdPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPembelianActionPerformed

    private void txtIdSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSuppActionPerformed
        
    }//GEN-LAST:event_txtIdSuppActionPerformed

    private void CBNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBNamaBarangActionPerformed

    private void CBNamaBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBNamaBarangItemStateChanged
        otopanggilBarang();
    }//GEN-LAST:event_CBNamaBarangItemStateChanged

    private void txtIdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBarangActionPerformed

    private void txtStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStokActionPerformed

    private void txtHargaTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaTotalActionPerformed
        
    }//GEN-LAST:event_txtHargaTotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tampiltotal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtHargaMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaMasukActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String kode_barang = jTable1.getValueAt(baris, 1).toString();
        txtIdPembelian.setText(kode_barang);
        
        if (jTable1.getValueAt(baris, 2)==null) {
            txtIdBarang.setText("");
        } else {
            txtIdBarang.setText(jTable1.getValueAt(baris, 2).toString());
        }
        if (jTable1.getValueAt(baris, 4)==null) {
            CBNamaBarang.setSelectedItem("");
        } else {
            CBNamaBarang.setSelectedItem(jTable1.getValueAt(baris, 4).toString());
        }
        if (jTable1.getValueAt(baris, 5)==null) {
            CBNamaSupplier.setText("");
        } else {
            CBNamaSupplier.setText(jTable1.getValueAt(baris, 5).toString());
        }
        if (jTable1.getValueAt(baris, 3)==null) {
            txtIdSupp.setText("");
        } else {
            txtIdSupp.setText(jTable1.getValueAt(baris, 3).toString());
        }
        if (jTable1.getValueAt(baris, 6)==null) {
            txtJumlah.setText("");
        } else {
            txtJumlah.setText(jTable1.getValueAt(baris, 6).toString());
        }
        if (jTable1.getValueAt(baris, 7)==null) {
            txtHargaMasuk.setText("");
        } else {
            txtHargaMasuk.setText(jTable1.getValueAt(baris, 7).toString());
        }
        if (jTable1.getValueAt(baris, 9)==null) {
            txtStok.setText("");
        } else {
            txtStok.setText(jTable1.getValueAt(baris, 9).toString());
        }
        if (jTable1.getValueAt(baris, 8)==null) {
            txtHargaTotal.setText("");
        } else {
            txtHargaTotal.setText(jTable1.getValueAt(baris, 8).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        this.setVisible(false);
        new Profil(txt_username.getText()).setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        kosong();
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(BarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BarangMasuk(txt_username.getText()).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBNamaBarang;
    private javax.swing.JTextField CBNamaSupplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtHargaMasuk;
    private javax.swing.JTextField txtHargaTotal;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtIdPembelian;
    private javax.swing.JTextField txtIdSupp;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtStok;
    private javax.swing.JLabel txt_tanggal;
    public static final javax.swing.JLabel txt_username = new javax.swing.JLabel();
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
