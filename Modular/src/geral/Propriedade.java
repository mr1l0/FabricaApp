package geral;

import java.io.FileInputStream;
import java.util.Properties;

public class Propriedade {

	private static String path;

	public static void setPath(String caminhoDoArquivo) {
		path=caminhoDoArquivo;
	}

	public static String getValor(String chave) {
		String retorno = null;
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(path);
			prop.load(fis);
			retorno = prop.getProperty(chave);
			if (fis != null) {
				fis.close();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return retorno;
	}

}
