//Angeles Gómez Jesús Gerardo
//Peñafiel Hernández José Manuel
//Ramírez Serna Brayan Emmanuel
package Paquete_Modelo;

public class SocioConsultas 
{
    private int idCliente;
    private String nombre;
    private int edad;
    private String CURP;     
    private String RFC;
    private float IDMEX;
    private String ocupacion;        
    private String sexo;       
    private String direccion;      
    private String idNacionalidad;
    private String idRol;

    public SocioConsultas(int idCliente, String nombre, int edad, String CURP, String RFC, float IDMEX, String ocupacion, String sexo, String direccion, String idNacionalidad, String idRol) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.edad = edad;
        this.CURP = CURP;
        this.RFC = RFC;
        this.IDMEX = IDMEX;
        this.ocupacion = ocupacion;
        this.sexo = sexo;
        this.direccion = direccion;
        this.idNacionalidad = idNacionalidad;
        this.idRol = idRol;
    }

    public SocioConsultas() {
        this.idCliente = 0;
        this.nombre = null;
        this.edad = 0;
        this.CURP = null;
        this.RFC = null;
        this.IDMEX = 0.0f;
        this.ocupacion = null;
        this.sexo = null;
        this.direccion = null;
        this.idNacionalidad = null;
        this.idRol = null;              
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public void setIDMEX(float IDMEX) {
        this.IDMEX = IDMEX;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setIdNacionalidad(String idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCURP() {
        return CURP;
    }

    public String getRFC() {
        return RFC;
    }

    public float getIDMEX() {
        return IDMEX;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdNacionalidad() {
        return idNacionalidad;
    }

    public String getIdRol() {
        return idRol;
    }
    
}
