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
                PathSingleton.getInstance().setPath(path);
                fileActions.put("Open", new OpenFile(PathSingleton.getInstance().getPath()));
                fileMenu.processUserChoice("Open");
            } else if (userChoice.startsWith("Save as ")) {
                String[] parts = userChoice.split("Save as ");
                if (parts.length >= 2) {
                    String filePath = parts[1].trim();
                    fileActions.put("Save as", new SaveAsFile(filePath));
                    fileMenu.processUserChoice("Save as");
                } else {
                    System.out.println("Invalid file path.");
                }
            } else if (userChoice.startsWith("Select ")) {
                String[] parts = userChoice.split(" ");
                if (parts.length >= 3) {
                    String id = parts[1];
                    String key = parts[2];
                    fileActions.put("Select", new SelectAttribute(id, key));
                    fileMenu.processUserChoice("Select");
                } else {
                    System.out.println("Invalid command. Usage: select <id> <key>");
                }
            } else if (userChoice.startsWith("Set ")) {
                String[] parts = userChoice.split(" ");
                if (parts.length >= 4) {
                    String id = parts[1];
                    String key = parts[2];
                    String value = parts[3];
                    fileActions.put("Set", new SetAttribute(id, key, value));
                    fileMenu.processUserChoice("Set");
                } else {
                    System.out.println("Invalid command. Usage: set <id> <key> <value>");
                }
            } else if (userChoice.startsWith("Children ")) {
                String[] parts = userChoice.split(" ");
                if (parts.length >= 2) {
                    String id = parts[1];
                    fileActions.put("Children", new ListChildrenAttributes(id));
                    fileMenu.processUserChoice("Children");
                } else {
                    System.out.println("Invalid command. Usage: children <id>");
                }
            }
            else {
                fileMenu.processUserChoice(userChoice);
            }
        } while (true);
    }
}



