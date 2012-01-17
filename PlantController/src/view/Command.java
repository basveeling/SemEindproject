/**
 * 
 */
package view;

/**
 * @author bas
 *
 */

public abstract class Command {

	final static int STATE_MAIN = 0;
	final static int STATE_PLACE_ORDER = 0;
	
	private char sign;
	private int numberOfParameters;
	private String description;
	
	/**
	 * Construeert een commando dat gegeven is door een bepaald teken en een bepaald aantal parameters hoort te krijgen,
	 *  en met een gegeven beschrijving.
	 * @param teken het teken dat voor dit commando staat
	 * @param numPars het aantal parameters van dit commando
	 * @param beschrijving korte beschrijving van parameters en commando
	 * @require 0 <= aantalPars <= 2 && beschrijving != null
	 * @ensure this.getTeken() == teken && this.getBeschrijving() == beschrijving && this.getAantalPars() == aantalPars
	 */
	protected Command(char teken, int numPars, String beschrijving) {
		this.sign = teken;
		this.numberOfParameters = numPars;
		this.description = beschrijving;
	}
	
	/**
	 * Levert het aantal parameters dat dit commando verwacht. Hier is gekozen voor een parametertal tussen de 0 en 2.
	 * @return 0 <= result <= 2
	 */
	public int getAantalPars() {
		return numberOfParameters;
	}
	
	/**
	 * Korte beschrijving van parameters en commando. De beschrijving dient te bestaan uit een enkele regel,
	 *  waarop aantal en aard van de benodigde parameters worden uitgelegd, en de bedoeling van het commando wordt beschreven.
	 * @return beschrijving van het commando
	 * @ensure result != null
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Levert het karakteristieke teken van dit commando. Dit dient kenmerkend te zijn voor het commando; d.w.z.,
	 *  twee verschillende commando's moeten niet hetzelfde teken hebben.
	 */
	public char getSign() {
		return sign;
	}
	
	/**
	 * Geeft aan of een gegeven combinatie van teken en parameters een geldige aanroep van dit commando vormt. 
	 * Als het teken wel klopt maar het aantal parameters niet dan wordt een foutboodschap geprint.
	 * @param sign het teken van het uit te voeren commando
	 * @param par1 de eerste parameter van de aanroep, of null
	 * @param par2 de tweede parameter van de aanroep, of null 
	 */
	public boolean canExecute(char sign, int state, String par1, String par2) {
		
		return (checkState(state)) && (this.sign == sign) && (									//Kijk of teken gelijk is aan this.teken
				(getAantalPars() == 0 && par1 == null && par2 == null) ||	//Als dit commando 0 pars heeft, dan moeten par1 && par2 gelijk zijn aan null;
				(getAantalPars() == 1 && par1 != null && par2 == null) ||   //Voor 1, moet par1 niet null en par2 null
				(getAantalPars() == 2 && par1 != null && par2 != null));    //etc.
	}
	
	public boolean checkState(int state) {
		return (state == 0);
	}
	
	/**
	 * Voert dit commando uit, met gegeven parameters.
	 * @param par1 de eerste parameter van de methode, of null
	 * @param par2 de tweede parameter van de methode, of null
	 * @require this.kanUitvoeren(this.teken(), par1, par2)
	 */
	public abstract void execute(int state, String par1, String par2);
}
