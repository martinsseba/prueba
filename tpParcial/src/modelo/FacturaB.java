package modelo;

import java.util.ArrayList;

public class FacturaB extends Factura
{
	
	public FacturaB(int idFactura,String fecha,Cliente cliente,FormaDePago formaDePago,ArrayList<FacturaItem> facturaItem, TipoFactura tipoFactura)
	{
		super(idFactura,fecha,cliente,formaDePago,facturaItem,tipoFactura);
	}
	
	/*public FacturaB(Cliente cliente,List<FacturaItem> facturaItem,TipoFactura tipoFactura )
	{
		this.setIdFactura(new DAOFactura().proxId());
		this.setFecha();
		this.setFacturaItem(facturaItem);
		this.setTipoFactura(tipoFactura);
	}*/
	
	public FacturaB(Cliente cliente,ArrayList<FacturaItem> facturaItem,TipoFactura tipoFactura )
	{
		super(cliente,facturaItem,tipoFactura);
	}
	
	public FacturaB()
	{
		super();
	}
	
	public float calcularTotal()
	{
		return(this.sumarPrecioItems()+this.CalcularDescuentoORecargo());
	}
	
	
	public float calcularSubTotal()
	{
		return(0);
	}
	
	public float calcularIva()
	{
		return (0);	
	}
	
	


}
