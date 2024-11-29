import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {

    // Function to generate the password
    public static String generatePassword(int length, boolean includeNumbers, boolean includeLowercase, boolean includeUppercase, boolean includeSpecial) {
        StringBuilder password = new StringBuilder();
        String charSet = "";

        // Add characters to the charset based on user preferences
        if (includeLowercase) {
            charSet += "abcdefghijklmnopqrstuvwxyz";
        }
        if (includeUppercase) {
            charSet += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if (includeNumbers) {
            charSet += "0123456789";
        }
        if (includeSpecial) {
            charSet += "!@#$%^&*()_+[]{}|;:,.<>?/";
        }

        Random random = new Random();

        // Generate the password using the character set
        for (int i = 0; i < length; i++) {
            password.append(charSet.charAt(random.nextInt(charSet.length())));
        }

        return password.toString();
    }

    // Function to check the strength of the password
    public static String checkPasswordStrength(String password) {
        int score = 0;

        // Check for different character types in the password
        if (password.matches(".*[a-z].*")) score++;  // Contains lowercase
        if (password.matches(".*[A-Z].*")) score++;  // Contains uppercase
        if (password.matches(".*[0-9].*")) score++;  // Contains numbers
        if (password.matches(".*[!@#$%^&*()_+\\[\\]{}|;:,.<>?].*")) score++;  // Contains special characters

        // Check password length (at least 8 characters for a stronger password)
        if (password.length() >= 8) score++;

        // Determine the strength based on the score
        if (score < 2) {
            return "Weak";
        } else if (score == 3) {
            return "Moderate";
        } else {
            return "Strong";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input password length
        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();

        // Validate password length
        if (length < 6) {
            System.out.println("Password length must be at least 6 characters.");
            return;
        }

        // Input whether to include numbers, lowercase, uppercase, and special characters
        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("yes");

        // Generate password
        String password = generatePassword(length, includeNumbers, includeLowercase, includeUppercase, includeSpecial);
        System.out.println("Generated Password: " + password);

        // Check password strength
        String strength = checkPasswordStrength(password);
        System.out.println("Password Strength: " + strength);
    }
}
