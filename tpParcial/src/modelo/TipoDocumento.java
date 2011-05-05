package modelo;

public class TipoDocumento 
{
	private int idTipoDocumento;
	private String tipoDocumento;
	
	
	public TipoDocumento(int idTipoDocumento, String tipoDocumento)
	{
		this.setIdTipoDocumento(idTipoDocumento);
		this.setTipoDocumento(tipoDocumento);
	}
	
	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}


}
