package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataHelper {

	// Recebe um Calendar e um formato
	// devolve uma String -> data formatada
	public static String CalendarToString(String formato, Calendar calendario) {
		SimpleDateFormat sdf=new SimpleDateFormat(formato);
		return sdf.format(calendario.getTime());
	}

	// Recebe um formato e uma data como String
	// retorna um Calendar
	public static Calendar StringToCalendar(String formato, String data) {
		Date dataCovertida=null;
		SimpleDateFormat sdf=new SimpleDateFormat(formato);
		try {
			dataCovertida=sdf.parse(data);
		} catch (ParseException e) {
			System.out.println("DataHelper.StringToCalendar.erro:");
			e.printStackTrace();
			return null;
		}
		Calendar calendario=Calendar.getInstance();
		calendario.setTime(dataCovertida);
		//System.out.println("DataHelper.CalendarToString: " + data + " -> " + DataHelper.CalendarToString("dd/MM/YYYY", calendario));
		return calendario;
	}
	
		
}
