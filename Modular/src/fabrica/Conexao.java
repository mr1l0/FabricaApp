package fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	//http://pastebin.com/eZVB4tDT
	public static Connection conectar() {
		try {
			//Propriedade.setPath(System.getProperty("user.dir") + "//propriedade//conexao.propertie");
			//String url = Propriedade.getValor("url");
			//String usr = Propriedade.getValor("user");
			
			//String pwd = Propriedade.getValor("password");
			//10.13.0.31/estacionamento
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+GeralConst.schemaConexao, "root", "murilo123");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}

