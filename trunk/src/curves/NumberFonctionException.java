package curves;

/**
 * 
 * Cette exception est a lever si on essaie de supprimer une fonction variations alors
 * qu'il y en a plus à supprimer
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class NumberFonctionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2446648291138836908L;
	
	public NumberFonctionException() {
		// TODO Auto-generated constructor stub
		super("Nombre de fonction incorect");
	}
}
