import java.util.Scanner;

public class Functions {
	public static boolean yesOrNo(String question, Scanner s) {
		String answer = " ";
		boolean wow = true;
		do {
			System.out.printf(question);
			answer = s.next();
			answer = answer.toLowerCase();
			if (answer.contentEquals("y") || answer.contentEquals("n")) {
				wow = false;
			} else {
				System.out.printf("Please answer y/n%n");
				wow = true;
			}
		} while (wow);
		if (answer.contentEquals("y"))
			return true;
		return false;
	}
	
	public static boolean isOnlyNumbers(String s) {
		for(char c: s.toCharArray()) {
			if(Character.getNumericValue(c) > 9 || Character.getNumericValue(c) < 0) {
				if(Character.getNumericValue(s.charAt(0)) == -1) {
					continue;
				}
				return false;
			}
		}
		return true; 
	}
	public static int digitCharToInt(String s) {
		int d = 0;
		char[] c = s.toCharArray();
		for(int i = c.length - 1;i >= 0;i--) {
			if(Character.getNumericValue(c[i]) == -1) {
				d *= -1;
			} else {
				d += (int) (Character.getNumericValue(c[i]) * Math.pow(10, (c.length-1) - i));
			}
		}
		return d;
	}
	
	public static int chooseNum(String s, int lim, Scanner in) {
		String placeholder = "";
		int num = 0;
		do {
			System.out.printf("%s (max %d): ",s,lim);
			placeholder = in.next();
			if(isOnlyNumbers(placeholder)) {
				num = digitCharToInt(placeholder);
			} else {
				System.out.printf("Must be a number!%n");
			}
			if (num > lim)
				System.out.printf("Too many players!%n");
			if (num < 0 && isOnlyNumbers(placeholder))
				System.out.printf("Not enough players!%n");
		} while (num > lim || num < 0  || !(isOnlyNumbers(placeholder)));
		return num;
	}
}
