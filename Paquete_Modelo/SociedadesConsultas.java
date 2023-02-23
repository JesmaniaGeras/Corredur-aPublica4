//Angeles Gómez Jesús Gerardo
//Peñafiel Hernández José Manuel
//Ramírez Serna Brayan Emmanuel

package Paquete_Modelo;

public class SociedadesConsultas 
{
    private int idSociedad;
    private String denominacion;
    private int capital;
    private String objetoSocial;
    private String idRegimen;
    private int idActa;

    public SociedadesConsultas(int idSociedad, String denominacion, int capital, String objetoSocial, String idRegimen, int idActa) {
        this.idSociedad = idSociedad;
        this.denominacion = denominacion;
        this.capital = capital;
        this.objetoSocial = objetoSocial;
        this.idRegimen = idRegimen;
        this.idActa = idActa;
    }

    public SociedadesConsultas() {
        this.idSociedad = 0;
        this.denominacion = null;
        this.capital = 0;
        this.objetoSocial = null;
        this.idRegimen = null;
        this.idActa = 0;
    }

    public void setIdSociedad(int idSociedad) {
        this.idSociedad = idSociedad;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public void setObjetoSocial(String objetoSocial) {
        this.objetoSocial = objetoSocial;
    }

    public void setIdRegimen(String idRegimen) {
        this.idRegimen = idRegimen;
    }

    public void setIdActa(int idActa) {
        this.idActa = idActa;
    }

    public int getIdSociedad() {
        return idSociedad;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public int getCapital() {
        return capital;
    }

    public String getObjetoSocial() {
        return objetoSocial;
    }

    public String getIdRegimen() {
        return idRegimen;
    }

    public int getIdActa() {
        return idActa;
    }
}
