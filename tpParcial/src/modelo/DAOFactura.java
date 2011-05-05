package modelo;

import java.sql.*;

//AGREGAR PARA TRAER UNA FACTURA POR ID, Y TRAER VARIAS POR RANGO.


public class DAOFactura
{
	public Connection con;
	
	public DAOFactura()
	{
		con = Conector.getInstance().getConnection();
	}
	
	public boolean altaFactura(Factura factura)
	{
		PreparedStatement pstm;
		String sqlInsertFactura;
		boolean altaExitosa = true;
				
		try
		{
			sqlInsertFactura = "INSERT INTO Factura(idFactura,fecha,Cliente_idCliente,FormaDePago_idFormaDePago,TipoFactura_idTipoFactura,descuentoInteres)" +
			"VALUES(?,curdate(),?,?,?,?)";

			
			pstm = con.prepareStatement(sqlInsertFactura);
			pstm.setInt(1, factura.getIdFactura());
			//pstm.setDate(2, Fecha.stringToSqlDate(factura.getFecha()));
			pstm.setInt(2, factura.getCliente().getIdCliente());
			pstm.setInt(3, factura.getFormaDePago().getIdFormaDePago());
			pstm.setInt(4, factura.getTipoFactura().getIdTipoFactura());
			//Le agrego el insert del descuentoInteres
			pstm.setFloat(5, factura.getDescuentoInteres());
			
			
			pstm.executeUpdate();
			
			altaExitosa = new DAOFacturaItem().insertFacturaItem(factura.getIdFactura(),factura.getFacturaItem());
			/*************		insertFacturaItem	**********/
	/*		sqlInsertFacturaItem ="INSERT INTO FacturaItem(Articulo_idArticulo,Factura_idFactura,cantidad,precio)" +
					"VALUES(?,?,?,?)";
			pstm = con.prepareStatement(sqlInsertFacturaItem);
			
			pstm.setInt(2, factura.getIdFactura());
			for(FacturaItem factItem: factura.getFacturaItem())
			{
				pstm.setInt(1, factItem.getArticulo().getIdArticulo());
				//pstm.setInt(2, factura.getIdFactura());//LO SACO DEL FOR xq siempre es el
				//mismo IdFactura
				pstm.setInt(3, factItem.getCantidad());
				pstm.setFloat(4, factItem.getPrecio());
				pstm.executeUpdate();
				
			}			
			
			/******************		FIN		************************/
			
						
		}
		catch(SQLException ex)
		{
			altaExitosa = false;
			ex.printStackTrace();
		}
		
		//VER COMO SE ASEGURA, QUE SI SE GRABO YA LA FACTURA, Y DA DESPUES ERROR
		//EN LOS ITEM DE LA FACTURA.... QUE SE HACE???? PARA QUE NO QUEDE MAL.
		
	
		return altaExitosa;
	}
	
	/* POR AHORA ESTA DENTRO DEL OTRO METODO VER SI NO ES MEJOR SACARLO!.
	private boolean insertFacturaItem(Factura factura)
	{
		
		
	}
	*/
	
	public int proxId()
	{
		int proxId=1;
		String sqlProxId;
		
		try
		{
			sqlProxId = "SELECT IFNULL(MAX(idFactura)+1,1) AS proxId FROM Factura";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sqlProxId);
			rs.next();
			proxId = rs.getInt("proxId");
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return(proxId);
	}
	

}
