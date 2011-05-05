package modelo;

public class Ciudad 
{
	private int idCiudad;
	private String ciudad;
	
	
	public Ciudad(int idCiudad, String ciudad)
	{
		this.setIdCiudad(idCiudad);
		this.setCiudad(ciudad);
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	
	


}
