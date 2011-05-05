package control;

import java.util.ArrayList;
import modelo.*;
/*
import modelo.Factura;
import modelo.FormaDePago;
import modelo.DAOFactura;
import modelo.DAOFormaDePago;
import modelo.DAOArticulo;
import modelo.DAOCliente;
import modelo.Articulo;
import modelo.Cliente;
import modelo.DAOTipoDocumento;
import modelo.TipoDocumento;
*/

public class ControlFactura 
{
	
	
	public ControlFactura()
	{
		
	}
	/***NUEVO CONTROL, CLIENTE TIENE QUE TENER CUIT Y DIRECCION PARA LA FACTURA A**/
	
	/*****   SE PUEDE SACAR TIPOFACTURA, YA QUE EL CONTROL ES SOLO PARA LAS FACTURA A, NO HACE FALTA PASARLO
	 * POR PARAMETRO!!!
	 */
	public boolean controlTipoFactura(Cliente cliente, TipoFactura tipoFactura)
	{
		boolean esRI;
		if((tipoFactura.getIdTipoFactura() == 1)&&(cliente.getCuit()== null) || (cliente.getDireccion() == null))
			esRI = false;
		else
			esRI = true;
		
		return esRI;
	}
	
	public String validaFormaDePago(Factura factura,FormaDePago formaDePago)
	{
		/*tiene que validar que pueda pagar en las cuotas segun el precio
		 * DEVUELVE TRUE, SI ESTA BIEN. Y EL ERROR SI NO ES CORRECTO. */
		String valido = "El monto de la factura es menor a: $"+formaDePago.getCondicion();
		DAOFormaDePago daoFormaDePago = new DAOFormaDePago();
		ArrayList<FormaDePago> formaPagoPosible = new ArrayList<FormaDePago>();
		
		formaPagoPosible = daoFormaDePago.getFormaPagoPosible(factura.sumarPrecioItems());
		
		for(FormaDePago fp: formaPagoPosible)
		{
			if(fp.getIdFormaDePago() == formaDePago.getIdFormaDePago())
			{
				valido = "true";
				break;
			}
			
		}
			
		return(valido);
	}
	
	public boolean creaListaCompra(ArrayList<FacturaItem> listaFacturaItem)
	{
		return (new ListaCompra().creaListaCompra(listaFacturaItem));
	}
	
	public int getnumFacturaUltima()
	{
		return (new ListaCompra().numFacturaUltima());
	}
	
	public ArrayList<FacturaItem> getListaFacturaItem(int idFactura)
	{
		return(new DAOFacturaItem().getListaFacturaItem(idFactura));
	}
	
	//SOBRECARGAMOS EL METODO PARA TRAER ENTRE UN RANGO DE FACTURAS.
/*	public ArrayList<FacturaItem> getListaFacturaItem(int idFacturaIni, int idFacturaFin)
	{
		return(new DAOFacturaItem().getListaFacturaItem(idFacturaIni,idFacturaFin));
	}
*/
	public ArrayList<TipoFactura> getTipoFactura()
	{
		return(new DAOTipoFactura().getTipoFactura());
	}
	
	public ArrayList<Articulo> getArticulo()
	{
		return(new DAOArticulo().getArticulo());
	}
	
	public Cliente getCliente(int documento,int tipoDocumento)
	{
		return(new DAOCliente().getCliente(documento, tipoDocumento));
	}
	//GET CLIENTE SOBRECAGADO PARA BUSCAR POR ID.
	public Cliente getCliente(int idCliente)
	{
		return(new DAOCliente().getCliente(idCliente));
	}

	
	public boolean altaFactura(Factura factura)
	{
		return(new DAOFactura().altaFactura(factura));
	}
	
	public ArrayList<TipoDocumento> getTipoDocumento()
	{
		return(new DAOTipoDocumento().getTipoDocumento());
	}
	
	public ArrayList<FormaDePago> getFormaPagoPosible ()
	{
		return(new DAOFormaDePago().getFormaPagoPosible());
	}

}
