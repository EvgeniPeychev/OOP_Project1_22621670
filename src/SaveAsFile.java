import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SaveAsFile implements FileAction {
    private String filePath;

    public SaveAsFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (fileContent == null || fileContent.isEmpty()) {
            System.out.println("No file content available to save.");
            return;
        }

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Please provide a valid file path.");
            return;
        }

        if (!filePath.toLowerCase().endsWith(".xml")) {
            System.out.println("File must have an extension of .xml");
            return;
        }

        System.out.println("Saving File As...");

        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(fileContent);
            writer.close();
            System.out.println("Successfully saved content to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}
