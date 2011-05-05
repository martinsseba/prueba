package vista;

import java.util.Scanner;
import java.util.ArrayList;

import control.ControlFactura;

import modelo.Factura;
import modelo.FacturaItem;
import modelo.Articulo;
import modelo.Cliente;
import modelo.FormaDePago;
import modelo.TipoDocumento;
import modelo.TipoFactura;
import modelo.ListaCompra;

public class Interfaz 
{
	
	public Interfaz()
	{
		/*VER SI NO PUEDE ESTAR ASI... EL CONTROLfACTURA CON LA INTERFAZ*/
		//this.setControlFactura(new ControlFactura());
	}

	
	//VISTA PARA GENERAR LA LISTA DE COMPRAS EN XML.

	
	//BUSCA AL CLIENTE DE LA COMPRA POR DNI Y TIPODNI
	public Cliente clienteCompra()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Ingrese su documento:");
		int documento = in.nextInt();
		
		ArrayList<TipoDocumento> listaDoc = new ControlFactura().getTipoDocumento();
		for(TipoDocumento tipoDoc: listaDoc)
		{
			System.out.println(tipoDoc.getIdTipoDocumento()+") "+tipoDoc.getTipoDocumento());
		}
		System.out.println("Ingrese el numero que le corresponda a su Documento");
		int tipoDocumento = in.nextInt();
		
		Cliente cliente = new ControlFactura().getCliente(documento, tipoDocumento);
		
		return cliente;
	}
	//Busca al CLIENTE de la COMPRA POR el IDCLIENTE
	public Cliente clienteCompraXId()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Ingrese el id");
		int idCliente = in.nextInt();
		
		Cliente cliente = new ControlFactura().getCliente(idCliente);
		
		return cliente;
	}


	
	public TipoFactura tipoFacturaCompra(Cliente cliente)
	{
		Scanner in = new Scanner(System.in);
		//controlFactura().getTipoFactura(), se comunica con el DAO para obtener todos los tipos de factura.
		ArrayList<TipoFactura> listaTipoFactura = new ControlFactura().getTipoFactura();
		System.out.println("Elija el tipo de factura que desea!");
		for(TipoFactura tFactura: listaTipoFactura)
		{
			System.out.println(tFactura.getIdTipoFactura()+")  "+tFactura.getTipoFactura());
		}
		int idTipoFactura = in.nextInt();
/***AGREGAR VERIFICAR QUE ESTE ENTRE EL RANGO CORRECTO.**/		
		
		//agregamos el control aca para que ya devuelva la factura correcta.
		if(idTipoFactura == 2)
		{
			idTipoFactura = 1;
		}
		else
		{
			if(new ControlFactura().controlTipoFactura(cliente,listaTipoFactura.get(idTipoFactura-1)))
				idTipoFactura = idTipoFactura-1;
			else
			{
				idTipoFactura = 1;
				System.out.println("Usted no es responsable Inscripto");
				System.out.println("En el caso de serlo debe registrar su CUIT y su domicilio para poder usar Facturas A.!");
				//en un futuro se le puede dar la opcion de agregar los datos. por ahora le hacemos la B
				
			}
		}

		
		return listaTipoFactura.get(idTipoFactura);
	}
	
	public ArrayList<FacturaItem> carritoCompra()
	{
		Scanner in = new Scanner(System.in);
		ArrayList<FacturaItem> carrito = new ArrayList<FacturaItem>();
		FacturaItem item;
		ArrayList<Articulo> stock = new ControlFactura().getArticulo();
		String segCompra ="si";
		int idArtCompra,cant;
		
		
		while(segCompra.equalsIgnoreCase("si"))
		{
			for(Articulo a: stock)
			{
				System.out.println(a.getIdArticulo()+" "+a.getArticulo());
			}
			System.out.println("Elija el articulo a comprar:");
			idArtCompra = in.nextInt();
			System.out.println("Indique la cantidad a comprar:");
			cant = in.nextInt();

			item = new FacturaItem(stock.get(idArtCompra-1),cant);
			carrito.add(item);
			System.out.println("Desea seguir comprando?( Escriba: \"si\" para continuar comprando)");
			segCompra = in.next();
		}
		
		return(carrito);
		
	}
	
	
	public FormaDePago pagoCompra(Factura factura)
	{
		Scanner in = new Scanner(System.in);
		int formPago = 0;
//VER ACA CAMBIAR ESTE METODO DEL DAO A UNO DEL CONTROLER.
//HACER QUE EL CONTROL ESTE SOLO EN EL CONTROLER.
		//TRAE TODAS LAS FORMAS DE PAGO, LAS CORRECTAS Y LAS QUE NO CUMPLEN LA CONDICION DE MONTO DE FACTURA.
		ArrayList<FormaDePago> listaFormaPago = new ControlFactura().getFormaPagoPosible();
		String formaPagoValida = "false";
		
		while(formaPagoValida != "true")
		{
			do
			{
				System.out.println("Indique la forma de pago de la compra:");
				for(FormaDePago formaPago: listaFormaPago)
				{
					System.out.println(formaPago.getIdFormaDePago()+") "+formaPago.getFormaDePago());
				}
				formPago = in.nextInt();
			}while((formPago > listaFormaPago.size()) || (formPago <= 0));
			//Sale cuando es falso.
			formaPagoValida = new ControlFactura().validaFormaDePago(factura,listaFormaPago.get(formPago-1));
			if(formaPagoValida != "true")
			{
				System.out.println(formaPagoValida);
			}
		}
		
		return(listaFormaPago.get(formPago-1));
		
		
	}
	


	
	public void imprimeFactura(Factura factura)
	{
		/******* Cabeza de la factura ***************/
		System.out.println("****************************************************************************");
		System.out.println("Factura Nro:"+factura.formatearNroFactura());
		System.out.println("\t\t\t "+factura.getTipoFactura().getTipoFactura());
		System.out.println("Fecha: "+factura.getFecha());
		System.out.println();
		System.out.println("Señor: "+factura.getCliente().getApellido()+" "+factura.getCliente().getNombre());
		
		if(factura.getTipoFactura().getIdTipoFactura() == 1)
		{
			System.out.println("Domicilio: "+factura.getCliente().getDireccion());
			System.out.println("Cuit: "+factura.getCliente().getCuit());
			System.out.println("****************************************************************************");
	/******************* Cuerpo de la factura ******************/		
			System.out.println("Cantidad | \t\t Detalle | \t P.Unitario | \t Importe");
			for(FacturaItem facturaItem: factura.getFacturaItem())
			{
				System.out.println(facturaItem.getCantidad()+"\t |\t "+facturaItem.getArticulo().getArticulo()+
						" \t|\t "+facturaItem.getPrecio()/(121/100)+" \t|\t "+facturaItem.totalItem());
			}
			System.out.println("****************************************************************************");
	/******************* pie de la factura ******************/		
			System.out.println("\t\t\t          SubTotal: $"+factura.sumarPrecioItems());
			System.out.println("\t\t\t Descuento/Interes: $"+factura.CalcularDescuentoORecargo());
			System.out.println("\t\t\t          SubTotal: $"+factura.calcularSubTotal());
			System.out.println("\t\t\t			I.V.A: $"+factura.calcularIva());
			System.out.println("\t\t\t            Total: $"+factura.calcularTotal());
			System.out.println("Monto a pagar con: "+factura.getFormaDePago().getFormaDePago()+
					" de un monto individual de: $"+factura.calcularCuota());
			System.out.println("****************************************************************************");
			
		}
		else
		{
			System.out.println("****************************************************************************");
	/******************* Cuerpo de la factura ******************/		
			System.out.println("Cantidad | \t\t Detalle | \t P.Unitario | \t Importe");
			for(FacturaItem facturaItem: factura.getFacturaItem())
			{
				System.out.println(facturaItem.getCantidad()+"\t |\t "+facturaItem.getArticulo().getArticulo()+
						" \t|\t "+facturaItem.getPrecio()+" \t|\t "+facturaItem.totalItem());
			}
			System.out.println("****************************************************************************");
	/******************* pie de la factura ******************/		
			System.out.println("\t\t\t          SubTotal: $"+factura.sumarPrecioItems());
			System.out.println("\t\t\t Descuento/Interes: $"+factura.CalcularDescuentoORecargo());
			System.out.println("\t\t\t            Total: $"+factura.calcularTotal());
			System.out.println("Monto a pagar con: "+factura.getFormaDePago().getFormaDePago()+
					" de un monto individual de: $"+factura.calcularCuota());
			System.out.println("****************************************************************************");
			
		}
		

		
		
	}

}
