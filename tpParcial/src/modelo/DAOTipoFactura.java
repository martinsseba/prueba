package modelo;

import java.sql.*;
import java.util.ArrayList;

public class DAOTipoFactura 
{
	Connection con;
	
	public DAOTipoFactura()
	{
		con = Conector.getInstance().getConnection();
	}
	
	public ArrayList<TipoFactura> getTipoFactura()
	{
		String sqlSelect = "SELECT * FROM TipoFactura";
		ArrayList<TipoFactura> listaTipoFactura = new ArrayList<TipoFactura>();
		TipoFactura tipoFactura = null;
		
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				tipoFactura = new TipoFactura(rs.getInt("idTipoFactura"),
						rs.getString("tipoFactura"));
				listaTipoFactura.add(tipoFactura);
				
				
			}

		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return listaTipoFactura;
	}

}
