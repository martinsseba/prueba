package modelo;

import java.util.ArrayList;

public class FacturaA extends Factura
{
	public FacturaA(int idFactura,String fecha,Cliente cliente,FormaDePago formaDePago,ArrayList<FacturaItem> facturaItem, TipoFactura tipoFactura)
	{
		super(idFactura,fecha,cliente,formaDePago,facturaItem,tipoFactura);
	}
	
	public FacturaA(Cliente cliente,ArrayList<FacturaItem> facturaItem,TipoFactura tipoFactura )
	{
		super(cliente,facturaItem,tipoFactura);
	}
	
	public FacturaA()
	{
		super();
	}
	

	
	public float sumarPrecioItems()
	{
		return (super.sumarPrecioItems()/(121/100));
	}
	
	public float calcularTotal()
	{
		return (this.calcularSubTotal()+this.calcularIva());
	}
	
	public float calcularSubTotal()
	{
		return(this.sumarPrecioItems()+this.CalcularDescuentoORecargo());
	}
	
	public float calcularIva()
	{
		float precio = (this.sumarPrecioItems()+this.CalcularDescuentoORecargo())*21/100;
		return precio;
	}
	

	

	//Esto le saca el iva a cada uno de los item de la lista de la factura.
	/*
	public void setPrecioListaItem()
	{
		for(FacturaItem facturaItem : this.getFacturaItem())
		{
			facturaItem.setPrecio(facturaItem.getPrecio()/(121/100));
		}
	}*/

}
