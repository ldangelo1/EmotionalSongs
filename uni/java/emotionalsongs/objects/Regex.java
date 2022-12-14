package emotionalsongs.objects;

public class Regex {
	public static Boolean regexStandard(String text) {
		return text.matches("^[\\w!?$-_\\*\\.' àèéìòù]+$");
	} // Regular phrase
	
	public static Boolean regexNumber(String text, String quantifier) {
		return text.matches("^\\d{" + quantifier + "}$");
	} // numbers (quantifier set the length)
	
	public static Boolean regexEmail(String text) {
		return text.matches("^([a-z0-9]+\\.?)+@([a-z]+\\.){1,2}[a-z]{2,3}$");
	} // (lowercase letters or number + .?) more times + @ + (lower letters + .?){1,2} + lower letters {2,3}
	
	public static Boolean regexPhone(String text) {
		return text.matches("^\\+?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
	} // Regular italian phone number
	
	public static Boolean regexProvince(String text) {
		return text.toUpperCase().matches("^[A-Z]{2}$");
	} // uppercase letters {2}
	
	public static Boolean regexUsername(String text) {
		return text.matches("^[a-z0-9_-]{3,15}$");
	} // lowercase letters or number or _ or - {3,15}
	
	public static Boolean regexPassword(String text) {
		return text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[\\.-_^*#!$&]).{8,15}$");
	} // Must insert, at least: upper/lower -case letter, number, special char {8,15}
	
	public static Boolean regexID(String text) {
		return text.matches("^[A-Z]{6}\\d{2}[A-T]\\d{2}\\w\\d{3}\\w$");
	} // italian fiscal code
	
	public static Boolean regexURL(String text) {
		return text.matches("^(www\\.)?[a-z0-9]+(\\.?[a-z0-9]+){1,2}/?$");
	} // www.? + lowercase letters or number + (.? + lowercase letters or number) {1,2} + /?
}