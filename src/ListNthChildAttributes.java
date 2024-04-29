import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
public class ListNthChildAttributes implements FileAction {
    private String id;
    private int n;

    public ListNthChildAttributes(String id, int n) {
        this.id = id;
        this.n = n;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                boolean isIdFound = false;
                boolean insideTargetElement = false;
                int targetIndentationLevel = -1;
                int childCount = 0;

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
                        childCount++;
                        if (childCount == n) {
                            System.out.println(line.trim());
                            break;
                        }
                    }
                }

                if (!isIdFound) {
                    System.out.println("Element with id " + id + " not found.");
                } else if (childCount < n) {
                    System.out.println("Element with id " + id + " does not have " + n + " children.");
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
