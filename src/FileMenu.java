import java.util.Map;

class FileMenu implements Menu {
    private Map<String, FileAction> fileActions;

    public FileMenu(Map<String, FileAction> fileActions) {
        this.fileActions = fileActions;
    }

    @Override
    public void processUserChoice(String userChoice) {
        FileAction fileAction = fileActions.get(userChoice);
        if (fileAction != null) {
            System.out.println("Selected option: " + userChoice);
            fileAction.execute();
        } else {
            System.out.println("Invalid option. Please choose a valid option:");
        }
    }
}