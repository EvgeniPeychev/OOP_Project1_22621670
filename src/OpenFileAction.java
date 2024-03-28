import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class OpenFile implements FileAction {
    private String filePath;

    public OpenFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Path is required. Please enter a valid file path.");
            return;
        }

        System.out.println("Opening File...");
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File does not exist. Creating a new file.");
                createEmptyFile(file);
                return;
            }

            System.out.println("Opened file: " + filePath);
            readFileContent(file);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please enter a valid file path.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private void createEmptyFile(File file) throws IOException {
        if (file.createNewFile()) {
            System.out.println("New file created: " + file.getAbsolutePath());
        } else {
            System.out.println("File already exists.");
        }
    }

    private void readFileContent(File file) throws IOException {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        }
    }
}