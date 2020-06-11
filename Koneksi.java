package Kasir;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Koneksi 
{
    public static Connection getKoneksi(String host, String port, String username, String password, String db)
    {
        String url    ="jdbc:mysql://"+ host + ":" + port + "/" + db;
        Connection koneksi = null;
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi     = (Connection) DriverManager.getConnection(url,username,password);
            System.out.println("Koneksi Berhasil");
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Database Error");
            koneksi     = null;
        }
        
        return koneksi;
    }   
}
