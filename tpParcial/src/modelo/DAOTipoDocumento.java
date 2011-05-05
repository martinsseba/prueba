package modelo;

import java.sql.*;
import java.util.ArrayList;

public class DAOTipoDocumento 
{
	Connection con;
	
	public DAOTipoDocumento()
	{
		con = Conector.getInstance().getConnection();
	}
	
	public TipoDocumento getTipoDocumento(int idTipoDoc)
	{
		String sqlSelect = "SELECT * FROM TipoDocumento t where(t.idTipoDocumento = "+idTipoDoc+")";
		TipoDocumento tipoDocumento = null;
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				tipoDocumento = new TipoDocumento(rs.getInt("idTipoDocumento"),rs.getString("tipoDocumento"));
			}
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return tipoDocumento;
		
	}
	
	public ArrayList<TipoDocumento> getTipoDocumento()
	{
		String sqlSelect = "SELECT * FROM TipoDocumento t ORDER BY t.idTipoDocumento";
		ArrayList<TipoDocumento> listaTipoDoc = new ArrayList<TipoDocumento>();
		TipoDocumento tipoDocumento;
		
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			while(rs.next())
			{
				tipoDocumento = new TipoDocumento(rs.getInt("idTipoDocumento"),rs.getString("tipoDocumento"));
				listaTipoDoc.add(tipoDocumento);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return listaTipoDoc;
		
	}
	

}
