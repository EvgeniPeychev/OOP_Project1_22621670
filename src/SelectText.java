import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
public class SelectText implements FileAction {
    private String id;

    public SelectText(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("id=\"" + id + "\"")) {
                        String result = line.replace("id=\"" + id + "\"", "").replace("<", "").replace(">", "").trim();
                        System.out.println("Text on the line with id " + id + ": " + result);
                        return;
                    }
                }
                System.out.println("Element with id " + id + " not found.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
