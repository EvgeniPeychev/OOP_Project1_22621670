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
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Please provide a file path.");
            return;
        }

        System.out.println("Saving File As...");

        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);



            writer.close();
            System.out.println("Successfully saved " + file.getName());
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}
