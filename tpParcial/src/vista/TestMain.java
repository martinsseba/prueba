package vista;
import java.util.ArrayList;
import java.util.Scanner;

import control.ControlFactura;

import modelo.*;

/*
import modelo.Factura;
import modelo.Cliente;
import modelo.FacturaItem;
import modelo.TipoFactura;
import modelo.FacturaA;
import modelo.FacturaB;*/

public class TestMain 
{
	
	public static void main(String[] args)
	{

		Interfaz vista = new Interfaz();
		ControlFactura controlFactura = new ControlFactura();
		TipoFactura tipoFactura = null;
		Factura factura = null;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Ingrese la opcion que desea realizar:");
		System.out.println("1) Generar una factura.");
		System.out.println("2) Generar Lista de productos a comprar.");
		int opcion = in.nextInt();
		
		if(opcion == 1)
		{
			//si el usuario no es RI, le mando la factura B de una.
			
			//SACAMOS BUSCAR POR DNI Y TIPO DNI
			//Cliente cliente = vista.clienteCompra();
			
			// PONEMOS BUSCAR POR ID:
			Cliente cliente = vista.clienteCompraXId();
			
			ArrayList<FacturaItem> carritoCompra = vista.carritoCompra();
			
			
			tipoFactura = vista.tipoFacturaCompra(cliente);
				
			if(tipoFactura.getIdTipoFactura() == 1)
			{
				factura = new FacturaA();
			}
			else
			{	
				factura = new FacturaB();
			}
				
			
			factura.setCliente(cliente);
			factura.setFacturaItem(carritoCompra);
			factura.setTipoFactura(tipoFactura);
			factura.setFormaDePago(vista.pagoCompra(factura));
			//Estamos agregando el descuentoInteres a la factura
			factura.setDescuentoInteres(factura.getFormaDePago().getDescuentoInteres());

			boolean cFactura = controlFactura.altaFactura(factura);
			if(cFactura)
			{
				System.out.println("Operacion Realizada satisfactoriamente!!.");
			}
			else
			{
				System.out.println("Error al registrar la operacion.");
				System.out.println("Intente nuevamente.");
			}
			
			vista.imprimeFactura(factura);
		}
		else
		{
			System.out.println("Generando lista de compra.....");
			
			int idFactura = new ControlFactura().getnumFacturaUltima();
			if(idFactura == -1)
			{
				System.out.println("Error en la generacion de la lista Compra.");
			}
			else
			{
				ArrayList<FacturaItem> listaFacturaItem = new ControlFactura().getListaFacturaItem(idFactura);
				boolean listaCreada = new ControlFactura().creaListaCompra(listaFacturaItem);
				if(!listaCreada)
					System.out.println("No se pudo escribir la lista de compra.");
				else
					System.out.println("Lista de compra generada exitosamente!.");
			}
			
		}
		

	}

}
