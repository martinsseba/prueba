package modelo;

import java.util.Calendar;

public class Fecha 
{

	public static java.sql.Date stringToSqlDate(String fecha)
	{
		int dia,mes,anio;
		
		anio = Integer.parseInt(fecha.substring(0,4));
		mes = Integer.parseInt(fecha.substring(5,7))-1;
		dia = Integer.parseInt(fecha.substring(8,10));
		
		Calendar calendar = Calendar.getInstance();

	    // seteamos anio, mes y dia
	    calendar.set(Calendar.YEAR, anio);
	    calendar.set(Calendar.MONTH, mes);
	    calendar.set(Calendar.DATE, dia);

	    // los demas valores en 0
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    
	    //creamos el sqlDate
	    java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
	    return javaSqlDate;
	}
}
