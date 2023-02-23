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
import java.util.ArrayList;

public class Socio 
{
    private PreparedStatement PS;
    private final Conexion CN;
    private ResultSet RS;
    private final String SQL_INSERT = "INSERT INTO Cliente (nombre, edad, CURP, RFC, IDMEX, ocupacion, sexo, direccion, idNacionalidad, idRol)"
                                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM SociosConsultaForm";
    private DefaultTableModel DTM;
    
    public Socio()
    {
        this.PS = null;
        this.CN = new Conexion();
    }
    
    public int insertarDatos(String nombre, int edad, String curp, String rfc, String idmex, String ocupacion, String sexo, 
                            String direccion, int idNacionalidad, int idRol, String denominacion)
    {
        int res = 0;
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_INSERT);
            PS.setString(1, nombre);
            PS.setInt(2, edad);
            PS.setString(3, curp);
            PS.setString(4, rfc);
            PS.setString(5, idmex);
            PS.setString(6, ocupacion);
            PS.setString(7, sexo);
            PS.setString(8, direccion);
            PS.setInt(9, idNacionalidad);
            PS.setInt(10, idRol);
            res = PS.executeUpdate();
            
            if(res > 0)
                JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Alta", -1);
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
        
        int idCliente = consultarId(nombre);
        int idActa = consultarIdActa(denominacion);
        String SQL_INSERT_ACTACLIENTE = "INSERT INTO ActaCliente (idActa, idCliente) VALUES (?, ?)";
        try
        {
            res = 0;
            PS = CN.getConexion().prepareStatement(SQL_INSERT_ACTACLIENTE);
            PS.setInt(1, idActa);
            PS.setInt(2, idCliente);
            res = PS.executeUpdate();
            
            if(res > 0)
                JOptionPane.showMessageDialog(null, "Socio Registrado en Sociedad: " + denominacion, "Alta", -1);
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
                            
    public ArrayList<Paquete_Modelo.Nacionalidad> generarListaNacionalidad()
    {
        ArrayList<Paquete_Modelo.Nacionalidad> listaNacionalidades = new ArrayList();
        String SQL = "SELECT * FROM listaNacionalidades";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            while (RS.next())
            {
                listaNacionalidades.add(new Nacionalidad (RS.getString("nacionalidad")));
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
        return listaNacionalidades;
    }
    
    public ArrayList<Paquete_Modelo.Roles> generarListaRoles()
    {
        ArrayList<Paquete_Modelo.Roles> listaRoles = new ArrayList();
        String SQL =    "SELECT * FROM listaRoles";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            while (RS.next())
            {
                listaRoles.add(new Roles (RS.getString("rol")));
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
        return listaRoles;
    }
    
    public ArrayList<Paquete_Modelo.Denominaciones> generarListaDenominaciones()
    {
        ArrayList<Paquete_Modelo.Denominaciones> listaDenominaciones = new ArrayList();
        String SQL = "SELECT * FROM listaDenominaciones";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            while (RS.next())
            {
                listaDenominaciones.add(new Denominaciones (RS.getString("denominacion")));
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
        return listaDenominaciones;
    }
    
    private DefaultTableModel setTitulos()
    {
        DTM = new DefaultTableModel();
        DTM.addColumn("Nombre");
        DTM.addColumn("Edad");
        DTM.addColumn("CURP");
        DTM.addColumn("RFC");
        DTM.addColumn("IDMEX");
        DTM.addColumn("Ocupacion");
        DTM.addColumn("Sexo");
        DTM.addColumn("Direccion");
        DTM.addColumn("Nacionalidad");
        DTM.addColumn("Rol");
        return DTM;
    }
    
    public DefaultTableModel getDatos()
    {
        try
        {
            setTitulos();
            PS = CN.getConexion().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[10];
            while(RS.next())
            {
                fila[0] = RS.getString(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
                fila[6] = RS.getString(7);
                fila[7] = RS.getString(8);
                fila[8] = RS.getString(9);
                fila[9] = RS.getString(10);
                
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
    
    public int consultarId(String nombre)
    {
        int idEliminar = 0;
       
       String SQL = "SELECT idCliente FROM Cliente WHERE nombre = '" + nombre +"'";
       try
       {
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            while(RS.next())
                idEliminar = RS.getInt("idCliente");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al generar la consulta" + e, "ERROR - ComboBox Régimen Jurídico", 0);
        }
        finally
        {
            PS = null;
            CN.close();
        }
       
       return idEliminar;
    }
    
    public int consultarIdActa (String denominacion)
    {
        int idActa = 0;
        String SQL = "SELECT idActa FROM Sociedad WHERE denominacion = '" + denominacion +"'";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            while(RS.next())
                idActa = RS.getInt("idActa");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al generar la consulta" + e, "ERROR - ComboBox Régimen Jurídico", 0);
        }
        finally
        {
            PS = null;
            CN.close();
        }
        return idActa;
    }
    
    public int actualizarDatos(String nombre, int edad, String curp, String rfc, String idmex, String ocupacion, String sexo, 
                            String direccion, int idNacionalidad, int idRol, String denominacion)
    {
        int res = 0;
        String SQL_UPDATE = "UPDATE Cliente SET "
                            + "edad = " + edad +", "
                            + "CURP = '" + curp + "', "
                            + "RFC = '" + rfc + "', "
                            + "IDMEX = '" + idmex + "', "
                            + "ocupacion = '" + ocupacion + "', "
                            + "sexo= '" + sexo + "', " 
                            + "direccion = '" + direccion + "', "
                            + "idNacionalidad = " + idNacionalidad + ", "
                            + "idRol = " + idRol + 
                            " WHERE nombre = '" + nombre + "'";
        try
        {
            PS = CN.getConexion().prepareStatement(SQL_UPDATE);
           res = PS.executeUpdate();
           
           if (res > 0)
               JOptionPane.showMessageDialog(null, "Actualización Satisfactoria de cliente", "Alta", -1);
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
        
        int idActa = consultarIdActa(denominacion);
        int idCliente = consultarId(nombre);
        String SQL_UPDATEACTACLIENTE = "UPDATE ActaCliente SET "
                            + "idActa = " + idActa + 
                            " WHERE idCliente = " + idCliente;
        try
        {
            res = 0;
            PS = CN.getConexion().prepareStatement(SQL_UPDATE);
           res = PS.executeUpdate();
           
           if (res > 0)
               JOptionPane.showMessageDialog(null, "Actualización de Sociedad satisfactoria", "Alta", -1);
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
    
    public int eliminarRegistro (String nombre)
   {
       int res = 0;
       String SQL_DELETE = "DELETE FROM Cliente WHERE nombre= '" + nombre + "'";
       int idEliminar = consultarId(nombre);
       
       try
       {
           PS = CN.getConexion().prepareStatement("DELETE FROM ActaCliente WHERE idCliente = " + idEliminar);
           res = PS.executeUpdate();
       }
       catch (SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Registro NO Eliminado" + e, "Alta", 0);
       }
       finally
       {
           PS = null;
           CN.close();
       }
       
       try
       {
           PS = CN.getConexion().prepareStatement(SQL_DELETE);
           res = PS.executeUpdate();
           
           if(res > 0)
               JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar", -1);
       }
       catch (SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Registro NO Eliminado" + e, "Alta", 0);
       }
       finally
       {
           PS = null;
           CN.close();
       }
       return res;
   }
    
    
}
