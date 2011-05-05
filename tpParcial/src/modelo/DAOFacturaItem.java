package modelo;
import java.sql.*;
import java.util.ArrayList;


public class DAOFacturaItem 
{
	Connection con;
	
	public DAOFacturaItem()
	{
		con = Conector.getInstance().getConnection();
	}
/*********
select Articulo_idArticulo, sum(cantidad) as cantidad from facturaItem where Factura_idFactura between 1 and 6 group by Articulo_idArticulo;

esta consulta devuelve los id articulo y la suma ya echa para la lista de articulos vendidos
para realizar el pedido.



 */
	//devuelve los productos vendidos entre ese rango de facturas para la listaCompra.
	public ArrayList<FacturaItem> getListaFacturaItem(int idFacturaIni, int idFacturaFin)
	{
		String sqlSelect = "select Articulo_idArticulo,sum(cantidad) as cantidad , precio FROM "+
		"facturaItem where Factura_idFactura between " + idFacturaIni +" and "+ idFacturaFin+
		" group by Articulo_idArticulo";
		ArrayList<FacturaItem> listaFacturaItem = new ArrayList<FacturaItem>();
		FacturaItem facturaItem;
		Articulo articulo;
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				// SACAR EL ARTICULO PARA CREAR LA LISTA.!!!!
				articulo = new DAOArticulo().getArticulo(rs.getInt("Articulo_idArticulo"));
				facturaItem = new FacturaItem(articulo,rs.getInt("cantidad"),rs.getFloat("precio"));
				listaFacturaItem.add(facturaItem);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return listaFacturaItem;
	}
	
	//devuelve los productos vendidos entre el id de la factura y la ultima para la listaCompra.
	public ArrayList<FacturaItem> getListaFacturaItem(int idFactura)
	{
		String sqlSelect = "SELECT Articulo_idArticulo,sum(cantidad) as cantidad , precio" +
			" FROM FacturaItem WHERE Factura_idFactura > "+ idFactura+
			" group by Articulo_idArticulo";
		ArrayList<FacturaItem> listaFacturaItem = new ArrayList<FacturaItem>();
		FacturaItem facturaItem;
		Articulo articulo;
		
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				// SACAR EL ARTICULO PARA CREAR LA LISTA.!!!!
				articulo = new DAOArticulo().getArticulo(rs.getInt("Articulo_idArticulo"));
				facturaItem = new FacturaItem(articulo,rs.getInt("cantidad"),rs.getFloat("precio"));
				listaFacturaItem.add(facturaItem);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return listaFacturaItem;
	}
	
	public boolean insertFacturaItem(int idFactura, ArrayList<FacturaItem> listaFacturaItem)
	{
		boolean altaExitosa = true;
		PreparedStatement pstm;
		String sqlInsertFacturaItem ="INSERT INTO FacturaItem(Articulo_idArticulo,Factura_idFactura,cantidad,precio)" +
		"VALUES(?,?,?,?)";
		try
		{
			pstm = con.prepareStatement(sqlInsertFacturaItem);

			pstm.setInt(2, idFactura);
			for(FacturaItem factItem: listaFacturaItem)
			{
				pstm.setInt(1, factItem.getArticulo().getIdArticulo());
				//pstm.setInt(2, factura.getIdFactura());//LO SACO DEL FOR xq siempre es el
				//mismo IdFactura
				pstm.setInt(3, factItem.getCantidad());
				pstm.setFloat(4, factItem.getPrecio());
				pstm.executeUpdate();
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			altaExitosa = false;
		}
		
		return altaExitosa;
		
	}

}
