package Kasir;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class login extends javax.swing.JFrame 
{
    
    private Connection koneksi;
    private ResultSet rs;
    private Statement stmt;
    
    public login() 
    {
        initComponents();
        koneksi = Koneksi.getKoneksi("localhost", "3306", "root", "", "pbo_kasir");
        ImageIcon ig = new ImageIcon("Logo.png");
        setIconImage(ig.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LBL_Login = new javax.swing.JLabel();
        LBL_Username = new javax.swing.JLabel();
        LBL_Password = new javax.swing.JLabel();
        BTN_OK = new javax.swing.JButton();
        BTN_EXIT = new javax.swing.JButton();
        TBX_Username = new javax.swing.JTextField();
        PBX_Password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBL_Login.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_Login.setText("Login");
        LBL_Login.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LBL_Login.setName("LBL_Login"); // NOI18N
        getContentPane().add(LBL_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 36, -1, -1));

        LBL_Username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBL_Username.setText("Username");
        LBL_Username.setName("LBL_Username"); // NOI18N
        getContentPane().add(LBL_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 94, -1));

        LBL_Password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBL_Password.setText("Password");
        LBL_Password.setName("LBL_Password"); // NOI18N
        getContentPane().add(LBL_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 94, -1));

        BTN_OK.setText("OK");
        BTN_OK.setName("BTN_OK"); // NOI18N
        BTN_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_OKActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_OK, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 55, -1));

        BTN_EXIT.setText("EXIT");
        BTN_EXIT.setName("BTN_EXIT"); // NOI18N
        BTN_EXIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXITActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_EXIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        TBX_Username.setName("TBX_Username"); // NOI18N
        getContentPane().add(TBX_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 136, -1));
        getContentPane().add(PBX_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 158, 136, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Rekayasa Perangkat Lunak SMK Negeri 4 Bandung\\Pemrograman Berorientasi Objek\\Tugas-Tugas\\Coding\\Kasir\\Logo.png")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_OKActionPerformed
        String Username = TBX_Username.getText().toString();
        String Password = PBX_Password.getText().toString();
        String ID;
        
        try 
        {
            Password = sha1(Password);
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT*FROM login_username WHERE Username = '"+Username+"' AND Password = '"+Password+"'";
            rs = stmt.executeQuery(query);
            
            if(rs.next())
            {
                ID = rs.getString("ID_Login");
                LoginInfo.setID(ID);
        
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                dispose();
                
                LoginInfo LI = new LoginInfo();
                LI.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Username Atau Password Salah");
                TBX_Username.setText("");
                PBX_Password.setText("");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BTN_OKActionPerformed

    private void BTN_EXITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXITActionPerformed
        dispose();
    }//GEN-LAST:event_BTN_EXITActionPerformed

    
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_EXIT;
    private javax.swing.JButton BTN_OK;
    private javax.swing.JLabel LBL_Login;
    private javax.swing.JLabel LBL_Password;
    private javax.swing.JLabel LBL_Username;
    private javax.swing.JPasswordField PBX_Password;
    private javax.swing.JTextField TBX_Username;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    static String sha1(String input) throws NoSuchAlgorithmException 
    {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < result.length; i++) 
        {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }         
        return sb.toString();
    }
}
