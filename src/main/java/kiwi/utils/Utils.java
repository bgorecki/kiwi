package kiwi.utils;

/**
 * Klasa z narzędziami 
 * @author bartek
 */
public class Utils {
	/**
	 * Metoda filtrująca łańcuch tekstowy z potencjalnie niebezpiecznych znaków html
	 * @param text tekst do odfiltrowania
	 * @return tekst bez znaków specjalnych html
	 */
	public static String filterText(String text) {
		text = text.replace("&", "&amp;");
		text = text.replace("\"", "&quote;");
		text = text.replace(">", "&gt;");
		text = text.replace("<", "&lt;");
		return text;
	}
}
