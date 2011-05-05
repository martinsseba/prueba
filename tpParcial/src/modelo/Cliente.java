package modelo;

public class Cliente 
{
	private int idCliente;
	private String apellido;
	private String nombre;
	private int documento;
	private TipoDocumento tipoDocumento;
	private Ciudad ciudad;
	private String cuit;
	private String direccion;
	
	//Contructor de todas las variables de la clase  (responsable inscripto)
	public Cliente(int idCliente,String apellido, String nombre, int documento, TipoDocumento tipoDocumento,Ciudad ciudad,String cuit,String direccion)
	{
		this.setIdCliente(idCliente);
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setDocumento(documento);
		this.setTipoDocumento(tipoDocumento);
		this.setCiudad(ciudad);
		this.setCuit(cuit);
		this.setDireccion(direccion);
		
	}
	
	//Constructor sin cuit ni direccion (Consumudir final)
	public Cliente(int idCliente,String apellido, String nombre, int documento, TipoDocumento tipoDocumento,Ciudad ciudad)
	{
		this.setIdCliente(idCliente);
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setDocumento(documento);
		this.setTipoDocumento(tipoDocumento);
		this.setCiudad(ciudad);
		
	}
	
	//contructor vacio.
	public Cliente()
	{
		
	}
	
	public void setIdCliente(int idCliente) 
	{
		this.idCliente = idCliente;
	}
	public int getIdCliente() 
	{
		return idCliente;
	}
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	public String getApellido() 
	{
		return apellido;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getNombre()
	{
		return nombre;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public int getDocumento() {
		return documento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCuit() {
		return cuit;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}
	
	

}
