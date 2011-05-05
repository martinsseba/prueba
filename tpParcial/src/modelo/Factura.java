package modelo;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class Factura 
{
	private int idFactura;
	private String fecha; /*ver como trabajar con la fecha*/
	private Cliente cliente;
	private FormaDePago formaDePago;
	private ArrayList<FacturaItem> facturaItem;
	private TipoFactura tipoFactura;
	private float descuentoInteres;
	
	//Faltaria de ser necesario un constructor con descuentoInteres
	
	//constructor con todos los atributos menos descuentoInteres.
	public Factura(int idFactura,String fecha,Cliente cliente,FormaDePago formaDePago,ArrayList<FacturaItem> facturaItem, TipoFactura tipoFactura)
	{
		this.setIdFactura(idFactura);
		this.setFecha(fecha);
		this.setCliente(cliente);
		this.setFormaDePago(formaDePago);
		this.setFacturaItem(facturaItem);
		this.setTipoFactura(tipoFactura);
	}
	public Factura(Cliente cliente,ArrayList<FacturaItem> facturaItem,TipoFactura tipoFactura )
	{
		this.setIdFactura(new DAOFactura().proxId());
		this.setFecha();
		this.setFacturaItem(facturaItem);
		this.setTipoFactura(tipoFactura);
	}
	
	public Factura()
	{
		this.setIdFactura(new DAOFactura().proxId());
		this.setFecha();
		
	}
	
	public void addItem(FacturaItem facturaItem)
	{
		this.getFacturaItem().set(this.getFacturaItem().size(),facturaItem);
	}
	
	public float sumarPrecioItems()
	{
		float subTotal = 0;
		
		for(FacturaItem item: facturaItem)
		{
			subTotal = subTotal + item.totalItem();
		}
		
		return subTotal;
	}
	
	
	public float CalcularDescuentoORecargo()
	{
		return (this.sumarPrecioItems()* (this.getDescuentoInteres()/100));
	}
	
	public abstract float calcularTotal();
	
	

	public abstract float calcularSubTotal();

	
	public abstract float calcularIva();


	
	
	public float calcularCuota()
	{
		float couta = 0;
		if(this.getFormaDePago().getCantidadCuotas() != 0)
			couta = this.calcularTotal()/this.getFormaDePago().getCantidadCuotas();
		else
			couta = this.calcularTotal();
		return couta;
	}
	
	
	/*
	 * estructura el numero de la factura que en vez de ser el id, sea
	 * de 6 digitos, completando a izq del id con ceros.
	 * Ej:
	 * la factura id 1, queda: 000001
	 */
	public String formatearNroFactura()
	{
		String numFacturaFormat = "000000";
		String numFactura = numFacturaFormat + Integer.toString(this.getIdFactura());
		
		return(numFactura.substring(numFactura.length()- numFacturaFormat.length()));
		
	}
	


	
	public void setIdFactura(int idFactura) 
	{
		this.idFactura = idFactura;
	}
	public int getIdFactura() 
	{
		return idFactura;
	}
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	public Cliente getCliente() 
	{
		return cliente;
	}
	public void setFormaDePago(FormaDePago formaDePago) 
	{
		this.formaDePago = formaDePago;
	}
	public FormaDePago getFormaDePago() 
	{
		return formaDePago;
	}
	public void setFacturaItem(ArrayList<FacturaItem> facturaItem)
	{
		this.facturaItem = facturaItem;
	}
	public ArrayList<FacturaItem> getFacturaItem() 
	{
		return facturaItem;
	}
	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}
	
	public void setFecha()//recargamos el metodo setFecha()
	{
		GregorianCalendar gregCal = new GregorianCalendar();
		this.fecha = gregCal.get(Calendar.YEAR)+"-"+(gregCal.get(Calendar.MONTH)+1)+"-"+gregCal.get(Calendar.DATE);
	}
	public String getFecha() 
	{
		return fecha;
	}
	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}
	public void setDescuentoInteres(float descuentoInteres) {
		this.descuentoInteres = descuentoInteres;
	}
	public float getDescuentoInteres() {
		return descuentoInteres;
	}

}
