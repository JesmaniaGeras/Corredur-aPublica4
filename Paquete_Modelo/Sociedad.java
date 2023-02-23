//Angeles Gómez Jesús Gerardo
//Peñafiel Hernández José Manuel
//Ramírez Serna Brayan Emmanuel
package Paquete_Modelo;
import Paquete_Conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Sociedad 
{
    private PreparedStatement PS;
    private final Conexion CN;
    private ResultSet RS;
    private final String SQL_INSERT = "INSERT INTO Sociedad (denominacion, capital, objetoSocial, idRegimen, idActa) VALUES"
                                        + "(?, ?, ?, ?, ?)";
    private DefaultTableModel DTM;
    private final String SQL_SELECT = "SELECT * FROM SociedadConsultaForm";
    
    public Sociedad()
    {
        this.PS = null;
        this.CN = new Conexion();
    }
    
    public int consultarIdActa()
    {
        int ultimoIdActa = 0;
        String SQL_BUSQUEDA = "SELECT MAX(idActa) FROM ActaConstitutiva";
        
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_BUSQUEDA);
            RS = PS.executeQuery();
            while(RS.next())
            {
                ultimoIdActa = RS.getInt(1);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al buscar el registro pasado", "ERROR - Alta", 0);
        }
        finally
        {
            PS = null;
            RS = null;
            CN.close();
        }
        
        return ultimoIdActa;
        
    }
    
    public int insertarDatos(String denominacion, int capital, String objetoSocial, int idRegimen)
    {
        int res = 0;
        int idActa = consultarIdActa();
        
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_INSERT);
            PS.setString(1, denominacion);
            PS.setInt(2, capital);
            PS.setString(3, objetoSocial);
            PS.setInt(4, idRegimen);
            PS.setInt(5, idActa);
            res = PS.executeUpdate();
            
            if(res > 0)
                JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Alta", -1);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al guardar la información", "ERROR - Alta", 0);
        }
        finally
        {
            PS = null;
            CN.close(); 
        }
        
        return res;
    }
    
    public ArrayList<Paquete_Modelo.RegimenesSociales> generarLista()
    {
        ArrayList<Paquete_Modelo.RegimenesSociales> listaRegimenes = new ArrayList();
        String SQL = "SELECT * FROM listaRegimenesJuridicos";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            while (RS.next())
            {
                listaRegimenes.add(new RegimenesSociales (RS.getString("regimenJuridico")));
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al generar la consulta", "ERROR - ComboBox Régimen Jurídico", 0);
        }
        finally
        {
            PS = null;
            CN.close();
        }
        return listaRegimenes;
    }
    
    private DefaultTableModel setTitulos()
    {
        DTM = new DefaultTableModel();
        DTM.addColumn("Denominación");
        DTM.addColumn("Capital");
        DTM.addColumn("Objeto Social");
        DTM.addColumn("Régimen Jurídico");
        DTM.addColumn("No. de Póliza");
        return DTM;
    }
    
    public DefaultTableModel getDatos()
    {
        try
        {
            setTitulos();
            PS = CN.getConexion().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while(RS.next())
            {
                fila[0] = RS.getString(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                DTM.addRow(fila);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al consultar los datos", "ERROR - Alta", 0);         
        }
        finally
        {
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    
    public int actualizarDatos(String denominacion, int capital, String objetoSocial, int idRegimen)
    {
        int res = 0;
        String SQL_UPDATE = "UPDATE Sociedad SET capital = " + capital + ""
                + ", objetoSocial = '" + objetoSocial + "', idRegimen = " + idRegimen + " WHERE denominacion = '" + denominacion +"'";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_UPDATE);
           res = PS.executeUpdate();
           
           if (res > 0)
               JOptionPane.showMessageDialog(null, "Actualización Satisfactoria", "Alta", -1);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al guardar la información" + e, "ERROR - Alta", 0);
        }
        finally
        {
            PS = null;
            CN.close(); 
        }
        return res;
    }
    
}
