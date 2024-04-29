import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
public class SetAttribute implements FileAction {
    private String id;
    private String key;
    private String value;

    public SetAttribute(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                StringBuilder newContent = new StringBuilder();
                String line;
                boolean isIdFound = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<" + key + ">")) {
                        if (isIdFound) {
                            int startIndex = line.indexOf("<" + key + ">") + key.length() + 2;
                            int endIndex = line.indexOf("</" + key + ">");
                            if (startIndex != -1 && endIndex != -1) {
                                line = line.substring(0, startIndex) + value + line.substring(endIndex);
                            }
                        }
                    } else if (line.contains("id=\"" + id + "\"")) {
                        isIdFound = true;
                    }
                    newContent.append(line).append("\n");
                }
                if (!isIdFound) {
                    System.out.println("Element with id " + id + " not found.");
                } else {
                    PathSingleton.getInstance().setContent(newContent.toString());
                    System.out.println("Value of " + key + " set to " + value);
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
