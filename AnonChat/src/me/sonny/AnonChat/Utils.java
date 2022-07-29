package me.sonny.AnonChat;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Random;

public final class Utils {
	
	// Create an random character set based on the given player name, a random text, and the current local date. 
	public static String createRandomChars(String playerName, String chars, int len, String randomText) {
		String result = "";
		
		// Random string changes in daily basis
		LocalDate date = LocalDate.now();
		
		int seed = playerName.hashCode() * date.toString().hashCode() * randomText.hashCode();
		
		Random rand = new Random();
		rand.setSeed(seed);
		
		for (int i=0; i<len; i++) {
			result += chars.charAt(rand.nextInt(chars.length()));
		}
		
		return result;
	}
	
	// Remove duplicating characters
	public static String removeDuplicates(String str) {
		String result = "";
		
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        for(int i=0; i<str.length(); i++) {
            lhs.add(str.charAt(i));
        }
         
        // print string after deleting duplicate elements
        for(Character ch : lhs) {
        	result += ch.toString();
        }
        
        return result;
    }
}
