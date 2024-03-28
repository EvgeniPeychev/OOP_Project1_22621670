import java.util.LinkedHashMap;
import java.util.Map;

public class FileManager {
    public static Map<String, FileAction> createFileActions() {
        Map<String, FileAction> fileActions = new LinkedHashMap<>();
        fileActions.put("Open", new OpenFile(null));
        fileActions.put("Close", new CloseFile());
        fileActions.put("Save", new SaveFile());
        fileActions.put("Save as", new SaveAsFile());
        fileActions.put("Help", new HelpAction(fileActions));
        fileActions.put("Exit", new ExitAction());
        return fileActions;
    }

    public static Menu createFileMenu(Map<String, FileAction> fileActions) {
        return new FileMenu(fileActions);
    }
}