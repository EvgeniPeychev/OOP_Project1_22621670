import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
public class SelectAttribute implements FileAction {
    private String id;
    private String key;

    public SelectAttribute(String id, String key) {
        this.id = id;
        this.key = key;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                String line;
                boolean isIdFound = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<" + key + ">")) {
                        if (isIdFound) {
                            int startIndex = line.indexOf("<" + key + ">") + key.length() + 2;
                            int endIndex = line.indexOf("</" + key + ">");
                            if (startIndex != -1 && endIndex != -1) {
                                String value = line.substring(startIndex, endIndex);
                                System.out.println("Value of " + key + ": " + value);
                                return;
                            }
                        }
                    } else if (line.contains("id=\"" + id + "\"")) {
                        isIdFound = true;
                    }
                }
                System.out.println("Element with id " + id + " or key " + key + " not found.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}

