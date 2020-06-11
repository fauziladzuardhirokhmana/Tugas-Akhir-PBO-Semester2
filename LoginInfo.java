package Kasir;

import com.mysql.jdbc.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class LoginInfo extends javax.swing.JFrame 
{
    private Connection kon;
    private Statement stmt;
    private ResultSet rs;
    
    private static String ID_Login;
    private String Nama;
    private String Telepon;
    private String Email;
    private String Alamat;
    
    public static void setID(String ID)
    { ID_Login = ID; }
    public static String getID()
    { return ID_Login; }
    
    public void ShowData()
    {
        try
        {
            Statement stmt = kon.createStatement();
            String query = "SELECT*FROM login_info WHERE ID_Login = '"+getID()+"'";
            rs = stmt.executeQuery(query);
            
            if(rs.next())
            {
                Nama = rs.getString("Nama_Admin");
                Telepon = rs.getString("No_Telepon");
                Email = rs.getString("Email");
                Alamat = rs.getString("Alamat");
                
                TBX_Nama.setText(Nama);
                TBX_Telepon.setText(Telepon);
                TBX_Email.setText(Email);
                TBX_Alamat.setText(Alamat);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public LoginInfo() 
    {
        initComponents();
        kon = Koneksi.getKoneksi("localhost", "3306", "root", "", "pbo_kasir");
        ShowData();
        
        ImageIcon ig = new ImageIcon("Logo.png");
        setIconImage(ig.getImage());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LBL_Info = new javax.swing.JLabel();
        LBL_Nama = new javax.swing.JLabel();
        LBL_Telepon = new javax.swing.JLabel();
        LBL_Email = new javax.swing.JLabel();
        LBL_Alamat = new javax.swing.JLabel();
        TBX_Telepon = new javax.swing.JTextField();
        TBX_Email = new javax.swing.JTextField();
        TBX_Nama = new javax.swing.JTextField();
        TBX_Alamat = new javax.swing.JTextField();
        BTN_Lanjut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBL_Info.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_Info.setText("Login Info");
        LBL_Info.setName("LBL_Info"); // NOI18N
        getContentPane().add(LBL_Info, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 13, -1, -1));

        LBL_Nama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        LBL_Nama.setText("Nama");
        LBL_Nama.setName("LBL_Nama"); // NOI18N
        getContentPane().add(LBL_Nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 77, -1, -1));

        LBL_Telepon.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        LBL_Telepon.setText("No Telepon");
        LBL_Telepon.setName("LBL_Telepon"); // NOI18N
        getContentPane().add(LBL_Telepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 126, -1, -1));

        LBL_Email.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        LBL_Email.setText("Email");
        LBL_Email.setName("LBL_Email"); // NOI18N
        getContentPane().add(LBL_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 175, -1, -1));

        LBL_Alamat.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        LBL_Alamat.setText("Alamat");
        LBL_Alamat.setName("LBL_Alamat"); // NOI18N
        getContentPane().add(LBL_Alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 221, -1, -1));

        TBX_Telepon.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TBX_Telepon.setEnabled(false);
        TBX_Telepon.setName("TBX_Telepon"); // NOI18N
        getContentPane().add(TBX_Telepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 123, 256, -1));

        TBX_Email.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TBX_Email.setEnabled(false);
        TBX_Email.setName("TBX_Email"); // NOI18N
        getContentPane().add(TBX_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 172, 256, -1));

        TBX_Nama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TBX_Nama.setEnabled(false);
        TBX_Nama.setName("TBX_Nama"); // NOI18N
        getContentPane().add(TBX_Nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 74, 256, -1));

        TBX_Alamat.setEnabled(false);
        TBX_Alamat.setName("TBX_Alamat"); // NOI18N
        TBX_Alamat.setPreferredSize(new java.awt.Dimension(6, 31));
        getContentPane().add(TBX_Alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 221, 256, -1));

        BTN_Lanjut.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        BTN_Lanjut.setText("Lanjutkan");
        BTN_Lanjut.setName("BTN_Lanjut"); // NOI18N
        BTN_Lanjut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_LanjutActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Lanjut, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 310, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Rekayasa Perangkat Lunak SMK Negeri 4 Bandung\\Pemrograman Berorientasi Objek\\Tugas-Tugas\\Coding\\Kasir\\Logo.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 3, 400, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_LanjutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_LanjutActionPerformed
        dispose();
        InputData id = new InputData();
        id.setVisible(true);
    }//GEN-LAST:event_BTN_LanjutActionPerformed

    
    public static void main(String args[]) 
    {
        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new LoginInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Lanjut;
    private javax.swing.JLabel LBL_Alamat;
    private javax.swing.JLabel LBL_Email;
    private javax.swing.JLabel LBL_Info;
    private javax.swing.JLabel LBL_Nama;
    private javax.swing.JLabel LBL_Telepon;
    private javax.swing.JTextField TBX_Alamat;
    private javax.swing.JTextField TBX_Email;
    private javax.swing.JTextField TBX_Nama;
    private javax.swing.JTextField TBX_Telepon;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
