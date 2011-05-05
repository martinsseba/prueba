package modelo;

import java.sql.*;

public class DAOCiudad
{
	Connection con;
	
	public DAOCiudad()
	{
		con = Conector.getInstance().getConnection();
	}
	
	public Ciudad getCiudad(int idCiudad)
	{
		String sqlSelect = "SELECT * FROM ciudad c where(c.idCiudad = "+idCiudad+")";
		Ciudad ciudad = null;
		
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			while(rs.next())
			{
				ciudad = new Ciudad(rs.getInt("idCiudad"),rs.getString("ciudad"));
			}
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return(ciudad);
	}
	

}
