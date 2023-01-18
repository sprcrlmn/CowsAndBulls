package CowsAndBulls;

import java.util.*;


class Game {

    private static int turn = 0;

    static boolean check(String num, String guess) {
        if (num.length() != guess.length()) {
            return true;
        }
        String[] answer = guess.split("");
        String[] numToChar = num.split("");
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < num.length(); i++) {
            if (Objects.equals(numToChar[i], answer[i])) {
                bulls += 1;
            } else {
                for (int j = 0; j < num.length(); j++) {
                    if (Objects.equals(numToChar[i], answer[j])) {
                        cows += 1;
                    }
                }
            }
        }
        System.out.print("Grade: ");
        if (cows > 0 && bulls > 0) {
            System.out.printf("%d bull(s) and %d cow(s). ", bulls, cows);
        } else if (cows > 0) {
            System.out.printf("%d cow(s). ", cows);
        } else if (bulls > 0) {
            System.out.printf("%d bull(s). ", bulls);
        } else {
            System.out.print("None. ");
        }
        System.out.println();
        if (bulls == guess.length()) {
            return false;
        } else {
            return true;
        }

    }
    public static String getRandomGenerator(int length, int possible) {
        if (length > 36) {
            System.out.printf("Error: can't generate a secret number with " +
                    "a length of %d because there aren't enough unique digits.", length);
            return null;
        }
        List<String> allNum = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
                "e", "f", "g", "h", "i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        List<String> numList = allNum.subList(0, possible);
        System.out.printf("The secret is prepared: %s (0-9, a-%s).", "*".repeat(length), allNum.get(possible-1));
        System.out.println();

        while (Objects.equals(numList.get(0), "0")) {
            Collections.shuffle(numList);
        }
        StringBuilder result = new StringBuilder();
        for (var ch : numList.subList(0, length)) {
            result.append(ch);
        }

        System.out.println("Okay, let's start a game!");
        return result.toString();
    }
    public static String getAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Turn" + ++turn + ":");
        return scanner.nextLine();
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String lens = scanner.nextLine();
        int len = -1;
        try {
            len = Integer.parseInt(lens);
            if (len < 1) {
                System.out.printf("Error: \"%s\" isn't a valid number.", lens);
                System.exit(-1);
            }
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", lens);
            System.exit(-1);
        }


        System.out.println("Input the number of possible symbols in the code:");

        int possible = scanner.nextInt();
        if (possible < len) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with " +
                    "%d unique symbols.\n", len, possible);
            System.exit(-1);
        }

        if (possible > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(-1);
        }
        String guess = Game.getRandomGenerator(len, possible);

        boolean a = true;
        while (a) {
            String answer = Game.getAnswer();
            a = Game.check(answer, guess);
        }
        System.out.println("Congratulations! You guessed the secret code.");

    }
}