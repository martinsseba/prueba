package modelo;

import java.sql.*;
import java.util.ArrayList;

public class DAOArticulo 
{
	public Connection con;
	
	public DAOArticulo()
	{
		con = Conector.getInstance().getConnection();
	}
	
	public ArrayList<Articulo> getArticulo()
	{
		ArrayList<Articulo> listaArticulo = new ArrayList<Articulo>();
		String sqlSelect = "SELECT * FROM articulo a ORDER BY a.idArticulo";
		Articulo articulo;
		
		try
		{
			Statement sentencia = con.createStatement();
			
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				articulo = new Articulo(rs.getInt("idArticulo"),rs.getString("codigoArticulo"),
						rs.getString("articulo"),rs.getFloat("precio"));
				
				listaArticulo.add(articulo);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return(listaArticulo);
	}
	
	public Articulo getArticulo(int idArticulo)
	{
		String sqlSelect = "SELECT * FROM articulo WHERE idArticulo = "+idArticulo;
		Articulo articulo = null;
		
		try
		{
			Statement sentencia = con.createStatement();
			
			ResultSet rs = sentencia.executeQuery(sqlSelect);
/****  cuando ande sacar el while.!!!! */
			while(rs.next())
			{
				articulo = new Articulo(rs.getInt("idArticulo"),rs.getString("codigoArticulo"),
						rs.getString("articulo"),rs.getFloat("precio"));
				
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return(articulo);
	}

}
