import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SaveFile implements FileAction {

    @Override
    public void execute() {
        String filePath = PathSingleton.getInstance().getPath();

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("No file is currently open.");
            return;
        }

        System.out.println("Saving File...");

        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);

            writer.close();
            System.out.println("File saved successfully: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}
