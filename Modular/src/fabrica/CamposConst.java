package fabrica;

public interface CamposConst {
	public String nome = "COLUMN_NAME";
	public String ordem = "ORDINAL_POSITION";
	public String valorDefault = "COLUMN_DEFAULT";
	public String permiteNull = "IS_NULLABLE";
	public String tipo = "DATA_TYPE";
	public String tamanho = "CHARACTER_MAXIMUM_LENGTH";
	public String precisionNumeros = "NUMERIC_PRICISION";
	public String scaleNumeros = "NUMERIC_SCALE";
	public String tipoChave = "COLUMN_KEY";
	public String comentario = "COLUMN_COMMENT";
	public String expressao = "GENERATION_EXPRESSION";
	
	public String tipoChaveFK = "MUL";
	public String tipoChavePK = "PRI";
	
	public String tipoString = "varchar";
	public String tipoInt = "int";
	public String tipoDateTime = "datetime";
	public String tipoNumeric = "decimal";
	
}
