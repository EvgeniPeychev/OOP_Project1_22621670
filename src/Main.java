import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, FileAction> fileActions = FileManager.createFileActions();
        Menu fileMenu = FileManager.createFileMenu(fileActions);

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("> ");
            String userChoice = scanner.nextLine();
            if (userChoice.startsWith("Open ")) {
                String[] parts = userChoice.split(" ", 2);
                String path = parts[1];
                fileActions.put("Open", new OpenFile(path));
                fileMenu.processUserChoice("Open");
            } else {
                fileMenu.processUserChoice(userChoice);
            }
        } while (true);
    }
}