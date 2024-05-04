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
        fileActions.put("Children", new ListChildrenAttributes(null));
        fileActions.put("Child", new ListNthChildAttributes(null, 0));
        fileActions.put("Text", new SelectText(null));
        fileActions.put("Delete", new DeleteAttribute(null, null));
        fileActions.put("Newchild", new NewChild(null, null));
        fileActions.put("Xpath", new XPath(null));
        return fileActions;
    }

    public static Menu createFileMenu(Map<String, FileAction> fileActions) {
        return new FileMenu(fileActions);
    }
}