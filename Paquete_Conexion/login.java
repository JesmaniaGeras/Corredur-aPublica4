package Paquete_Conexion;

public class login 
{
    private int idUsuarios;
    private String nombre;
    private String email;
    private String password;
    
    public login()
    {}
    
    public login(int idUsuarios, String nombre, String email, String password)
    {
        this.idUsuarios = idUsuarios;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
