package modelo;

public class Articulo 
{
	
	private int idArticulo;
	private String codigoArticulo;
	private String articulo;
	private float precio;
	
	public Articulo(int idArticulo, String codigoArticulo, String articulo, float precio)
	{
		this.setIdArticulo(idArticulo);
		this.setCodigoArticulo(codigoArticulo);
		this.setArticulo(articulo);
		this.setPrecio(precio);
	}
	
	public Articulo()
	{
		
	}
	
	
	public void setIdArticulo(int idArticulo) 
	{
		this.idArticulo = idArticulo;
	}
	public int getIdArticulo() 
	{
		return idArticulo;
	}
	public void setPrecio(float precio) 
	{
		this.precio = precio;
	}
	public float getPrecio() 
	{
		return precio;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	public String getCodigoArticulo() {
		return codigoArticulo;
	}

}
