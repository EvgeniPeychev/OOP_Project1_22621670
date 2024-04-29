import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class ListChildrenAttributes implements FileAction {
    private String id;

    public ListChildrenAttributes(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                boolean isIdFound = false;
                boolean insideTargetElement = false;
                int targetIndentationLevel = -1;

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("id=\"" + id + "\"")) {
                        isIdFound = true;
                        insideTargetElement = true;
                        targetIndentationLevel = countIndentation(line);
                    } else if (insideTargetElement && countIndentation(line) <= targetIndentationLevel) {
                        insideTargetElement = false;
                    }

                    if (insideTargetElement && !line.contains("id=\"" + id + "\"")) {
                        System.out.println(line.trim());
                    }
                }

                if (!isIdFound) {
                    System.out.println("Element with id " + id + " not found.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }

    private int countIndentation(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
