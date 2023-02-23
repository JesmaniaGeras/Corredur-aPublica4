//Angeles Gómez Jesús Gerardo
//Peñafiel Hernández José Manuel
//Ramírez Serna Brayan Emmanuel

package Paquete_Modelo;

import Paquete_Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;


public class RegistroActasConstitutivas 
{
    private PreparedStatement PS;
    private final Conexion CN;
    private ResultSet RS;
    private final String SQL_INSERT = "INSERT INTO ActaConstitutiva (noDePoliza, noDeFojas, noLibro, idLicenciado, idCorredor)"
                                    + "VALUES (?, ?, ?, ?, ?)";
    private final String SQL_SELECT = "select * from ConsultaActasFormulario";
    private DefaultTableModel DTM;
    
    public RegistroActasConstitutivas()
    {
        this.PS = null;
        this.CN = new Conexion();
    }
    
    public int insertarDatos(int noDePoliza, int noDeFojas,  int noLibro, int idLicenciado, int idCorredor)
    {
        int res = 0;
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_INSERT);
            PS.setInt(1, noDePoliza);
            PS.setInt(2, noDeFojas);
            PS.setInt(3, noLibro);
            PS.setInt(4, idLicenciado);
            PS.setInt(5, idCorredor);
                
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
    
    public int actualizarDatos(int noDePoliza, int noDeFojas, int noLibro, int idLicenciado, int idCorredor)
    {
        int res = 0;
        String SQL_UPDATE = "UPDATE ActaConstitutiva SET noDeFojas = " + noDeFojas + ", noLibro = " + noLibro + ""
                + ", idLicenciado = " + idLicenciado + ", idCorredor = " + idCorredor + " WHERE noDePoliza = " + noDePoliza;
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_UPDATE);
           res = PS.executeUpdate();
           
           if (res > 0)
               JOptionPane.showMessageDialog(null, "Actualización Satisfactoria", "Alta", -1);
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
    
    private DefaultTableModel setTitulos()
    {
        DTM = new DefaultTableModel();
        DTM.addColumn("No. de Póliza");
        DTM.addColumn("No. de Fojas");
        DTM.addColumn("No. de Libro");
        DTM.addColumn("Licenciado");
        DTM.addColumn("Corredor");
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
                fila[0] = RS.getInt(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
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
}
