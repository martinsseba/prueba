package modelo;

public class FacturaItem 
{
	private Articulo articulo;
	private int cantidad;
	private float precio;/*VER COMO SE SETEA ESTE PRECIO!!!!!!!!!!!!*/
	
	public FacturaItem(Articulo articulo, int cantidad, float precio)
	{
		this.setArticulo(articulo);
		this.setCantidad(cantidad);
		this.setPrecio(precio);
		
	}
	public FacturaItem(Articulo articulo, int cantidad)
	{
		this.setArticulo(articulo);
		this.setCantidad(cantidad);
		this.setPrecio();
		
	}
	
	public FacturaItem()
	{
		
	}
	
	
	public float totalItem()
	{
		return(this.getCantidad()*this.getArticulo().getPrecio());
	}
	
	
	public void setArticulo(Articulo articulo)
	{
		this.articulo = articulo;
	}
	public Articulo getArticulo() 
	{
		return articulo;
	}
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}
	public int getCantidad()
	{
		return cantidad;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public void setPrecio()
	{
		this.precio = this.getArticulo().getPrecio();
	}


	public float getPrecio() {
		return precio;
	}
	
	

}
