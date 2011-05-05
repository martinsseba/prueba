package modelo;

public class TipoFactura 
{
	private int idTipoFactura;
	private String tipoFactura;
	
	public TipoFactura(int idTipoFactura, String tipoFactura)
	{
		this.setIdTipoFactura(idTipoFactura);
		this.setTipoFactura(tipoFactura);
	}


	public void setIdTipoFactura(int idTipoFactura) {
		this.idTipoFactura = idTipoFactura;
	}

	public int getIdTipoFactura() {
		return idTipoFactura;
	}
	
	public void setTipoFactura(String tipoFactura)
	{
		this.tipoFactura = tipoFactura;
	}
	
	public String getTipoFactura()
	{
		return(this.tipoFactura);
	}


}
