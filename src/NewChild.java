import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class NewChild implements FileAction {
    private String id;
    private String name;

    public NewChild(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                String line;
                StringBuilder newContent = new StringBuilder();
                boolean childAdded = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("id=\"" + id + "\"")) {
                        newContent.append(line).append("\n");
                        newContent.append("        <" + name + "></" + name + ">\n");
                        childAdded = true;
                    } else {
                        newContent.append(line).append("\n");
                    }
                }
                if (childAdded) {
                    PathSingleton.getInstance().setContent(newContent.toString());
                    System.out.println("New child " + name + " added to element with id " + id);
                } else {
                    System.out.println("Element with id " + id + " not found.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
