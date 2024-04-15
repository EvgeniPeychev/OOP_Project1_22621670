class CloseFile implements FileAction {

    @Override
    public void execute() {
        String filePath = PathSingleton.getInstance().getPath();

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("No file is currently open.");
            return;
        }

        System.out.println("Closing File...");

        PathSingleton.getInstance().setPath(null);

        System.out.println("Successfully closed " + filePath);
    }
}
