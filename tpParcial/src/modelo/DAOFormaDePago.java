package modelo;
import java.sql.*;
import java.util.ArrayList;

public class DAOFormaDePago 
{
	public Connection con;
	
	public DAOFormaDePago()
	{
		con = Conector.getInstance().getConnection();
	}
	
	
	public ArrayList<FormaDePago> getFormaPagoPosible()
	{
		ArrayList<FormaDePago> listaFormaPago = new ArrayList<FormaDePago>();
		String sqlSelect = "SELECT * FROM formaDePago";
		FormaDePago formaDePago;
		
		try
		{
			//Statement es necesario para poder mandar una consulta a la bd.
			Statement sentencia = con.createStatement();
			//Se obtienen los datos de la consulta.
/***executeQuery es solo para los select, para los otros es: executeUpdate*/
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				formaDePago = new FormaDePago(rs.getInt("idFormaDePago"),
						rs.getString("formaDePago"),rs.getFloat("condicion"),
						rs.getFloat("descuentoInteres"),rs.getInt("cantidadCuotas"));
				listaFormaPago.add(formaDePago);
				
			}
			
		}
		catch(SQLException ex)
		{
			/********VER BIEN QUE HACER CON LAS EXCEPCIONES******/
			ex.printStackTrace();
		}
		
		return listaFormaPago;
	}
	
	
	public ArrayList<FormaDePago> getFormaPagoPosible(float valor)
	{
		ArrayList<FormaDePago> listaFormaPago = new ArrayList<FormaDePago>();
		String sqlSelect = "SELECT * FROM formaDePago f WHERE f.condicion<="+valor;
		FormaDePago formaDePago;
		
		try
		{
			//Statement es necesario para poder mandar una consulta a la bd.
			Statement sentencia = con.createStatement();
			//Se obtienen los datos de la consulta.
/***executeQuery es solo para los select, para los otros es: executeUpdate*/
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				formaDePago = new FormaDePago(rs.getInt("idFormaDePago"),
						rs.getString("formaDePago"),rs.getFloat("condicion"),
						rs.getFloat("descuentoInteres"),rs.getInt("cantidadCuotas"));
				listaFormaPago.add(formaDePago);
				
			}
			
		}
		catch(SQLException ex)
		{
			/********VER BIEN QUE HACER CON LAS EXCEPCIONES******/
			ex.printStackTrace();
		}
		
		return listaFormaPago;
	}

}
