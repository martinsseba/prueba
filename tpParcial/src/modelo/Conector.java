package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector 
{
	/*Esta clase tiene el conector de MySQL, la usamos como string apra usar
reflexion e invocarla*/
	private final String DRIVER = "com.mysql.jdbc.Driver";
	
	//otras propiedades para generar la cadena de conexion.
	
	private final String URL = "jdbc:mysql://localhost/Poo2";
	private final String USER = "root";
	private final String PASS = "";
	
	// Propiedades utiles para la clase
	
	private Connection con;
	private static Conector conn = new Conector(); /*variable que va a tener a la unica instancia
	de esta clase (Conector)*/
	
	//Contructor privado para cumplir el patron singleton.
	private Conector(){	}
	
	/*Con este metodo se descifra si hay o no un objeto Conector en el heap,
	 * si no lo hay, lo crea. 
	 *Se usa asi en el codigo cliente: Conector.getInstance()
	 *"Conector es la clase, no una variable de tipo Conector. 
	 */
	public static Conector getInstance()
	{
		/*if(conn==null)
			conn = new Conector();*/
		return conn;
	}
	
	/* metodo que realiza la conexion y la devuelve para ser usada por el DAL
	 * (la capa modelo "la BD").
	 */
	
	public Connection getConnection()
	{
		if(con == null)
		{
			try
			{
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASS);
			}
			catch(ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return con;
	}
}
