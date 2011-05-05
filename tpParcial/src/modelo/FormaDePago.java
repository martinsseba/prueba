package modelo;

public class FormaDePago
{
	private int idFormaDePago;
	private String formaDePago;
	private float condicion;
	private float descuentoInteres;
	//Tiene el porcentaje de desc o recargo que se hace
	private int cantidadCuotas;
	//Tiene la cant de coutas q se va a hacer el descuento o rec
	
	public FormaDePago(int idFormaDePago,String formaDePago,float condicion,float descuentoInteres,int cantidadCuotas)
	{
		this.setIdFormaDePago(idFormaDePago);
		this.setFormaDePago(formaDePago);
		this.setCondicion(condicion);
		this.setDescuentoInteres(descuentoInteres);
		this.setCantidadCuotas(cantidadCuotas);
		
	}
	
	public void setFormaDePago(String formaDePago)
	{
		this.formaDePago = formaDePago;
	}
	public String getFormaDePago()
	{
		return formaDePago;
	}
	public void setIdFormaDePago(int idFormaDePago) 
	{
		this.idFormaDePago = idFormaDePago;
	}
	public int getIdFormaDePago()
	{
		return idFormaDePago;
	}
	public void setCantidadCuotas(int cantidadCuotas) 
	{
		this.cantidadCuotas = cantidadCuotas;
	}
	public int getCantidadCuotas() 
	{
		return cantidadCuotas;
	}
	public void setDescuentoInteres(float descuentoInteres) 
	{
		this.descuentoInteres = descuentoInteres;
	}
	public float getDescuentoInteres() 
	{
		return descuentoInteres;
	}
	public void setCondicion(float condicion) 
	{
		this.condicion = condicion;
	}
	public float getCondicion() 
	{
		return condicion;
	}
	

}
