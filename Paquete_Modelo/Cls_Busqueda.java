//Angeles Gómez Jesús Gerardo
//Peñafiel Hernández José Manuel
//Ramírez Serna Brayan Emmanuel

package Paquete_Modelo;

import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import Paquete_Conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cls_Busqueda {
    private PreparedStatement PS;
    private DefaultTableModel DTM;
    private final String SQL_SELECT = "SELECT * FROM actaConstitutiva";
    private final Conexion CN;
    private ResultSet RS;
    
    public Cls_Busqueda(){
        this.PS = null;
        this.CN = new Conexion();
    }
    
    //Funcion para crear los titulos de la tabla de la consulta de datos
    
    private DefaultTableModel setTitulosActa(){
        DTM = new DefaultTableModel();
        DTM.addColumn("ID del Acta");
        DTM.addColumn("No. de Póliza");
        DTM.addColumn("No. de Hojas");
        DTM.addColumn("Fecha del acta");
        DTM.addColumn("No. del Libro");
        DTM.addColumn("ID del Licenciado");
        DTM.addColumn("ID del Corredor");
        return DTM;
    }
    private DefaultTableModel setTitulosSocio(){
        DTM = new DefaultTableModel();
        DTM.addColumn("ID del CLiente");
        DTM.addColumn("Nombre del cliente");
        DTM.addColumn("Edad");
        DTM.addColumn("CURP");
        DTM.addColumn("RFC");
        DTM.addColumn("IDMEX");
        DTM.addColumn("Ocupación");
        DTM.addColumn("Sexo");
        DTM.addColumn("Dirección");
        DTM.addColumn("Nacionalidad");
        DTM.addColumn("Rol");
        return DTM;
    }
    
    private DefaultTableModel setTitulosSociedades(){
        DTM = new DefaultTableModel();
        DTM.addColumn("ID de la Sociedad"); 
        DTM.addColumn("Denominación");
        DTM.addColumn("Capital");
        DTM.addColumn("Objeto Social");
        DTM.addColumn("Régimen Jurídico");
        DTM.addColumn("Acta");        
        return DTM;
    }
    // Funcion para la muestra de la tabla con informacion 
    public DefaultTableModel getDatos(){
        try{
            setTitulosActa();
            PS = CN.getConexion().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getDate(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                DTM.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consultar los datos","ERROR", 0);            
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    
    //buscar o hacer consultas del acta constitutiva
    public DefaultTableModel buscarActa(String parametro){
        String SQL;
        SQL = "SELECT * FROM actaConstitutiva WHERE idActa =" + parametro;
        try{
            setTitulosActa();
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                DTM.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consultar el registro de Actas","ERROR", 0);
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public String consultaBuscarSocioId(String parametro){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" c.idCliente, c.nombre, ");
        sb.append("c.edad, c.CURP, ");
        sb.append("c.RFC, c.IDMEX,");
        sb.append("c.ocupacion, c.sexo, ");
        sb.append("c.direccion, n.nacionalidad, r.rol ");
        sb.append("FROM cliente c ");
        sb.append("inner join nacionalidades n on n.idNacionalidad = c.idNacionalidad ");
        sb.append(" inner join roles r on r.idRol = c.idRol  ");
        sb.append("WHERE idCliente ");
        sb.append(parametro);
                  
         return sb.toString();
    }
    public String consultaBuscarSocioLike(String parametro){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" c.idCliente, c.nombre, ");
        sb.append("c.edad, c.CURP, ");
        sb.append("c.RFC, c.IDMEX,");
        sb.append("c.ocupacion, c.sexo, ");
        sb.append("c.direccion, n.nacionalidad, r.rol ");
        sb.append("FROM cliente c ");
        sb.append("inner join nacionalidades n on n.idNacionalidad = c.idNacionalidad ");
        sb.append(" inner join roles r on r.idRol = c.idRol  ");
        sb.append("WHERE nombre like");
        sb.append(parametro);
        return sb.toString();        
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    
    public DefaultTableModel buscarSocio(int criterio, String parametro){
        String SQL;
        
        if(criterio == 0)
            SQL = consultaBuscarSocioId("=" + parametro);
        else
            SQL = consultaBuscarSocioLike("'" + parametro + "%'");
        
        try{
            setTitulosSocio();
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[11];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getFloat(6);
                fila[6] = RS.getString(7);
                fila[7] = RS.getString(8);
                fila[8] = RS.getString(9);
                fila[9] = RS.getString(10);
                fila[10] = RS.getString(11);
                DTM.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consultar el registro de Socios","ERROR", 0);
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String consultaBuscarSociedadesId(String parametro){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" s.idSociedad, s.denominacion, ");
        sb.append(" s.capital, s.objetoSocial, ");
        sb.append(" r.regimenJuridico, a.idActa");
        sb.append(" FROM sociedad s ");
        sb.append(" inner join regimenJuridico r on r.idRegimen = s.idRegimen ");
        sb.append(" inner join actaConstitutiva a on a.idActa = s.idActa ");
        sb.append(" WHERE idSociedad ");        
        sb.append(parametro);                  
         return sb.toString();
    }
    public String consultaBuscarSociedadesLike(String parametro){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" s.idSociedad, s.denominacion, ");
        sb.append(" s.capital, s.objetoSocial, ");
        sb.append(" r.regimenJuridico, a.idActa");
        sb.append(" FROM sociedad s ");
        sb.append(" inner join regimenJuridico r on r.idRegimen = s.idRegimen ");
        sb.append(" inner join actaConstitutiva a on a.idActa = s.idActa ");
        sb.append(" WHERE denominacion like ");        
        sb.append(parametro);                  
         return sb.toString();
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    
    public DefaultTableModel buscarSociedades(int criterio, String parametro){
        String SQL;
        if(criterio == 0)
              SQL = consultaBuscarSociedadesId("=" + parametro);
        else
            SQL = consultaBuscarSociedadesLike("'" + parametro + "%'");
        try{
            setTitulosSociedades();
            PS = CN.getConexion().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getInt(6);
                DTM.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consultar el registro de Sociedades","ERROR", 0);
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //Metodo pára llenar combo socios
    public ArrayList<Paquete_Modelo.SocioConsultas> generarListadeSocios(){
        ArrayList<Paquete_Modelo.SocioConsultas> listaSocios = new ArrayList();
        String SQL = "SELECT c.idCliente, c.nombre, c.edad, c.CURP, c.RFC, c.IDMEX, c.ocupacion, c.sexo,c.direccion, n.nacionalidad, r.rol FROM cliente c inner join nacionalidades n on n.idNacionalidad = c.idNacionalidad inner join roles r on r.idRol = c.idRol ORDER BY idCliente";
         try{
             PS = CN.getConexion().prepareStatement(SQL);
             RS = PS.executeQuery();
             while(RS.next()){
                 listaSocios.add(new SocioConsultas(RS.getInt("idCliente"),
                                     RS.getString("nombre"),
                                     RS.getInt("edad"),
                                     RS.getString("CURP"),
                                     RS.getString("RFC"),
                                     RS.getFloat("IDMEX"),
                                     RS.getString("ocupacion"),
                                     RS.getString("sexo"),
                                     RS.getString("direccion"),
                                     RS.getString("Nacionalidad"),
                                     RS.getString("rol")));
             }
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error al consultar los ID de base de datos","ERROR", 0);
         }finally{
             PS = null;
             RS = null;
             CN.close();             
         }
         return listaSocios;
    }
    
    ////////////////////////////////////////
    //metodo para llenar combo Sociedades
     public ArrayList<Paquete_Modelo.SociedadesConsultas> generarListadeSociedades(){
        ArrayList<Paquete_Modelo.SociedadesConsultas> listaSociedades = new ArrayList();
        String SQL = "SELECT s.idSociedad, s.denominacion, s.capital, s.objetoSocial, r.regimenJuridico, a.idActa FROM sociedad s inner join regimenJuridico r on r.idRegimen = s.idRegimen inner join actaConstitutiva a on a.idActa = s.idActa ORDER BY idSociedad";
         try{
             PS = CN.getConexion().prepareStatement(SQL);
             RS = PS.executeQuery();
             while(RS.next()){
                 listaSociedades.add(new SociedadesConsultas(RS.getInt("idSociedad"),
                                     RS.getString("denominacion"),
                                     RS.getInt("capital"),
                                     RS.getString("objetoSocial"),
                                     RS.getString("regimenJuridico"),                                    
                                     RS.getInt("idActa")));
             }
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error al consultar los ID de base de datos","ERROR", 0);
         }finally{
             PS = null;
             RS = null;
             CN.close();             
         }
         return listaSociedades;
    }    
    
}
