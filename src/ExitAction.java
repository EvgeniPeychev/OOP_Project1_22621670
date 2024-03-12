class ExitAction implements FileAction {
    @Override
    public void execute() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}