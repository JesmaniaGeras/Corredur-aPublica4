
package Paquete_Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionEmail 
{
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public login log(String email, String password)
    {
        login l = new login();
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";        
        try
        {
            conexion = cn.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                l.setIdUsuarios(rs.getInt("idUsuario"));
                l.setNombre(rs.getString("nombre"));
                l.setEmail(rs.getString("email"));
                l.setPassword(rs.getString("password"));
            }
            
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return l;
    }
}
