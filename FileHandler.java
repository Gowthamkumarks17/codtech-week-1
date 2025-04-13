import java.io.*;
import java.util.*;

public class FileHandler {

    // File path (you can change this as per your system)
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n==== FILE HANDLING UTILITY ====");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    writeFile(scanner);
                    break;
                case 2:
                    readFile();
                    break;
                case 3:
                    modifyFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Method to write to the file
    private static void writeFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            System.out.println("Enter text to write to the file (type 'exit' to finish):");
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Text written successfully to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Method to read from the file
    private static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("\nContents of the file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    // Method to modify the file (replace specific text)
    private static void modifyFile(Scanner scanner) {
        System.out.print("Enter the text to be replaced: ");
        String oldText = scanner.nextLine();
        System.out.print("Enter the new text: ");
        String newText = scanner.nextLine();

        StringBuilder content = new StringBuilder();

        // Read the file and replace text
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.replace(oldText, newText)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file for modification.");
            e.printStackTrace();
            return;
        }

        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content.toString());
            System.out.println("File modified successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing modified content to the file.");
            e.printStackTrace();
        }
    }
}
