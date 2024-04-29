import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class DeleteAttribute implements FileAction {
    private String id;
    private String key;

    public DeleteAttribute(String id, String key) {
        this.id = id;
        this.key = key;
    }

    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                String line;
                StringBuilder newContent = new StringBuilder();
                boolean isIdFound = false;
                String deletedLine = null;
                boolean isElementDeleted = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<" + key + ">")) {
                        if (isIdFound) {
                            int startIndex = line.indexOf("<" + key + ">");
                            int endIndex = line.indexOf("</" + key + ">") + key.length() + 3;
                            if (startIndex != -1 && endIndex != -1) {
                                deletedLine = line.substring(startIndex, endIndex);
                                line = line.substring(0, startIndex) + line.substring(endIndex);
                                isElementDeleted = true;
                            }
                        }
                    } else if (line.contains("id=\"" + id + "\"")) {
                        isIdFound = true;
                    }
                    if (!line.trim().isEmpty() || !isElementDeleted) {
                        newContent.append(line).append("\n");
                    }
                    if (isElementDeleted && line.trim().isEmpty()) {
                        isElementDeleted = false;
                    }
                }
                PathSingleton.getInstance().setContent(newContent.toString());
                if (deletedLine != null) {
                    System.out.println("Successfully deleted: " + deletedLine);
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}