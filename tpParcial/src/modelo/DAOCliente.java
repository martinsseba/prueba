package modelo;

import java.sql.*;

public class DAOCliente 
{
	Connection con;
	
	public DAOCliente()
	{
		con = Conector.getInstance().getConnection();
	}

	public Cliente getCliente(int idCliente)
	{
		String sqlSelect = "SELECT * FROM Cliente WHERE idCliente = "+idCliente;
		
		Cliente cliente = new Cliente();
		DAOTipoDocumento daoTipoDocumento = new DAOTipoDocumento(); 
		DAOCiudad daoCiudad = new DAOCiudad();
		
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);
			
			while(rs.next())
			{
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setDocumento(rs.getInt("documento"));
				cliente.setTipoDocumento(daoTipoDocumento.getTipoDocumento(rs.getInt("TipoDocumento_idTipoDocumento")));
				cliente.setCiudad(daoCiudad.getCiudad(rs.getInt("Ciudad_idCiudad")));
				cliente.setCuit(rs.getString("cuit"));
				cliente.setDireccion(rs.getString("direccion"));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return cliente;
	}

	public Cliente getCliente(int documento,int idTipoDocumento)
	{
		String sqlSelect = "SELECT * FROM Cliente where documento = "+documento+" and TipoDocumento_idTipoDocumento = "+idTipoDocumento;
		
		Cliente cliente = new Cliente();
		DAOTipoDocumento daoTipoDocumento = new DAOTipoDocumento(); 
		DAOCiudad daoCiudad = new DAOCiudad();
		try
		{
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery(sqlSelect);

/******** SACAR EL WHILE Y ARREGLAR EL SETEO DEL CLIENTE DESPUES!!!!!!!!!*/
			while(rs.next())
			{
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setDocumento(rs.getInt("documento"));
				cliente.setTipoDocumento(daoTipoDocumento.getTipoDocumento(rs.getInt("TipoDocumento_idTipoDocumento")));
				cliente.setCiudad(daoCiudad.getCiudad(rs.getInt("Ciudad_idCiudad")));
				cliente.setCuit(rs.getString("cuit"));
				cliente.setDireccion(rs.getString("direccion"));
					/*
				cliente = new Cliente(rs.getInt("idCliente"),rs.getString("apellido"),
					rs.getString("nombre"),rs.getInt("documento"),
					daoTipoDocumento.getTipoDocumento(rs.getInt("TipoDocumento_idTipoDocumento")),
					daoCiudad.getCiudad(rs.getInt("Ciudad_idCiudad")),rs.getString("cuit"),rs.getString("direccion"));
					*/
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		
		return cliente;
	}
	
	//SOBRECARGAMOS GETCLIENTE PARA OBTENER CLIENTES POR ID.
	
	

}
