import java.util.Map;

class Help implements FileAction {
    private Map<String, FileAction> fileActions;

    public Help(Map<String, FileAction> fileActions) {
        this.fileActions = fileActions;
    }

    @Override
    public void execute() {
        System.out.println("The following commands are supported:");

        int maxCommandLength = getMaxCommandLength();

        for (Map.Entry<String, FileAction> entry : fileActions.entrySet()) {
            String command = entry.getKey();

            String description = switch (command) {
                case "Open" -> "opens <file>";
                case "Close" -> "closes currently opened file";
                case "Save" -> "saves the currently open file";
                case "Save as" -> "saves the currently open file in <file>";
                case "Help" -> "prints this information";
                case "Exit" -> "exits the program";
                case "Print" -> "prints the content of the file";
                case "Set" -> "Assigning a value to an attribute";
                case "Select" -> "Returns an attribute value";
                case "Children" -> "List of nested element attributes";
                case "Child" -> "Access the nth descendant of an element";
                case "Text" -> "Access the text of an element";
                case "Delete" -> "Delete element attribute by key";
                case "Newchild" -> "Adding a new element successor.";
                case "Xpath" -> "Simple Xpath request";
                default -> "";
            };

            if (command.equals("Open") || command.equals("Save as")) {
                System.out.printf("%-" + (maxCommandLength + 10) + "s%s%n", command+" <file> ",description);
            } else {
                System.out.printf("%-" + (maxCommandLength + 10) + "s%s%n", command, description);
            }
        }
    }

    private int getMaxCommandLength() {
        int maxCommandLength = 0;

        for (String command : fileActions.keySet()) {
            maxCommandLength = Math.max(maxCommandLength, command.length());
        }

        return maxCommandLength;
    }
}
