package upse.ia.search.problems;

public class NoMasAccionesEnColumnaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "No hay mas acciones disponibles en esta columna";

	public NoMasAccionesEnColumnaException() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage()
	{
		return message;
	}
}
