/* Yumeng Wang
 * CSE 373, Summer 2016
 * Homework 3: TextAssociator MyClient
 * 
 * Pokemon name translator that translate your favorite pokemon name from English to Japaness.
 */
import java.util.Scanner;
import java.util.Set;

public class MyClient {

	public static void main(String[] args) {
		TextAssociator sc = new TextAssociator();
		String[] pokemonEN = {"Pikachu","Bulbasaur", "Venusaur", "Charmander","Charmeleon","Charizard", 
				"Squirtle", "Wartortle", "Blastoise", "Caterpie", "Metapod", "Butterfree", 
				"Weedle","Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot", "Rattata",
				"Raticate", "Spearow"};
		String[] pokemonJP ={"Pikachu","Fushigidane", "Fushigibana", "Hitokage", "Lizardo", "Lizardon",
				"Zenigame", "Kameil", "Kamex", "Caterpie", "Transel", "Butterfree", "Beedle",
				"Cocoon", "Spear", "Poppo", "Pigeon", "Pigeot", "Koratta", "Ratta", "Onisuzume"};
		
		for(int i = 0; i < pokemonEN.length; i++){
			sc.addNewWord(pokemonEN[i].toLowerCase());
			sc.addAssociation(pokemonEN[i].toLowerCase(), pokemonJP[i].toLowerCase());
		}
		Scanner scan= new Scanner(System.in);
		String inputString = "";
		System.out.println("Welcome, Pokemon trainer!");
		System.out.println("Want to know what is your favorite pokemon called in Japaness?");
		System.out.println("This magic translation tool might help you out!");
		while (true) {
			System.out.print("Enter you favorite pokemon name to check it out now! (enter \"exit\" to exit):");
			inputString = scan.nextLine();
			if (inputString.equals("exit")) {
				break;
			}
			String[] tokens  = inputString.split(" ");
			String result = "";
			for (String token : tokens) {
				Set<String> words = sc.getAssociations(token.toLowerCase());
				if (words == null) {
					result += " " + token;
				} else {
					result += " " + words;
				}
			}
			System.out.println(result.trim());
			System.out.println();	
		}
		
	}

}
