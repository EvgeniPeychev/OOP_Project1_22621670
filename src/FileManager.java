import java.util.LinkedHashMap;
import java.util.Map;

public class FileManager {
    public static Map<String, FileAction> createFileActions() {
        Map<String, FileAction> fileActions = new LinkedHashMap<>();
        fileActions.put("Open", new OpenFile(null));
        fileActions.put("Close", new CloseFile());
        fileActions.put("Save", new SaveFile());
        fileActions.put("Save as", new SaveAsFile(null));
        fileActions.put("Help", new Help(fileActions));
        fileActions.put("Exit", new Exit());
        fileActions.put("Print", new Print());
        fileActions.put("Select", new SelectAttribute(null, null));
        fileActions.put("Set", new SetAttribute(null, null, null));
        return fileActions;
    }

    public static Menu createFileMenu(Map<String, FileAction> fileActions) {
        return new FileMenu(fileActions);
    }
}