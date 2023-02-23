
package Paquete_Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion 
{
    private static Connection cnx;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD = "CorreduriaPublica";
    private static final String USER = "root";
    private static final String PASS = "Moderatto@2002";
    private static final String URL = "jdbc:mysql://localhost:3306/"+BD+
                                        "?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC&"
                                    + "characterEncoding=utf8&useSSL=false&useUnicode=true";
    
    public Conexion()
    {
        cnx = null;
    }
    
    public Connection getConexion()
    {
        cnx = null;
        try
        {
            Class.forName(DRIVER);
            cnx = (Connection)DriverManager.getConnection(URL, USER, PASS);
        }
        catch (ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "No se puede realizar la conversion de la clase " + e.getMessage(), "ERROR", 0);
            System.exit(0);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión " + e.getMessage(), "ERROR", 0);
        }
        return cnx;
    }
    
    public void close()
    {
        try
        {
            cnx.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión " + e.getMessage(), "ERROR", 0);
        }
        
    }
}
