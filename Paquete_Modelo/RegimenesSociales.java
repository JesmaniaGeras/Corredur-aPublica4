//Angeles Gómez Jesús Gerardo
//Peñafiel Hernández José Manuel
//Ramírez Serna Brayan Emmanuel
package Paquete_Modelo;

public class RegimenesSociales 
{
    private int idRegimen;
    private String regimenJuridico;
    
    public RegimenesSociales()
    {
        this.idRegimen = 0;
        this.regimenJuridico = null;
    }
    
    public RegimenesSociales(String regimenJuridico)
    {
        this.regimenJuridico = regimenJuridico;
    }
    
    public int getIdRegimen()
    {
        return idRegimen;
    }
    
    public String getRegimenJuridico()
    {
        return regimenJuridico;
    }
    
}
