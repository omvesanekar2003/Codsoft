import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Word Counter Program");
    System.out.println("1. Enter text manually");
    System.out.println("2. Provide a file");
    System.out.print("Choose an option ");
    int option = scanner.nextInt();
    scanner.nextLine();
    String text = "";
    if (option == 1) {
      System.out.print("Enter text: ");
      text = scanner.nextLine();
    } else if (option == 2) {
      System.out.print("Enter the path to the file: ");
      String filePath = scanner.nextLine();
      try {
        text = readTextFromFile(filePath);
      } catch (FileNotFoundException e) {
        System.err.println("Error: File not found.");
        System.exit(1);
      }
    } else {
      System.out.println("Invalid option. Exiting program.");
      System.exit(1);
    }
    String[] words = text.split("[\\s.,;!?()\\[\\]{}\"]+");
    int wordCount = 0;
    Map<String, Integer> wordFrequencyMap = new HashMap<>();
    for (String word : words) {
      if (!word.isEmpty()) {
        wordCount++;
        wordFrequencyMap.put(
          word.toLowerCase(),
          wordFrequencyMap.getOrDefault(word.toLowerCase(), 0) + 1
        );
      }
    }
    System.out.println("Total number of words: " + wordCount);
    System.out.println("Word Frequency Statistics:");
    for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
    }
  }

  private static String readTextFromFile(String filePath)
    throws FileNotFoundException {
    StringBuilder content = new StringBuilder();
    File file = new File(filePath);
    Scanner fileScanner = new Scanner(file);

    while (fileScanner.hasNextLine()) {
      content.append(fileScanner.nextLine()).append("\n");
    }
    fileScanner.close();
    return content.toString();
  }
}
