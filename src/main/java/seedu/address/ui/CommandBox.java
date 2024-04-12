package seedu.address.ui;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final LinkedList<String> commandHistory = new LinkedList<>();
    private int historyIndex = 0;
    private boolean isFirstPress = true;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        // Listen for key events on the commandTextField
        commandTextField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case UP:
                navigateCommandHistory(1); // Navigate backwards in history
                event.consume(); // Consume the event to prevent further processing
                break;
            case DOWN:
                navigateCommandHistory(-1); // Navigate forwards in history
                event.consume();
                break;
            default:
                break;
            }
        });
    }


    private void navigateCommandHistory(int direction) {

        // Guard Clause when no commands yet
        if (commandHistory.isEmpty()) {
            return;
        }

        // Guard Clause for initial key input. Shows first command without skipping it.
        if (historyIndex == 0 && isFirstPress && direction == 1) {
            commandTextField.setText(commandHistory.get(historyIndex));
            isFirstPress = false;
            return;
        }
        // Adjust the history index based on the direction
        historyIndex += direction;

        // Boundary checks
        if (historyIndex < 0) {
            historyIndex = 0;
            isFirstPress = true;
            commandTextField.setText(""); // Clear the text field if we're past the last command
            return;
        } else if (historyIndex >= commandHistory.size()) {
            historyIndex = commandHistory.size();
        }
        // Set the commandTextField's text to the command at the new history index
        commandTextField.setText(commandHistory.get(historyIndex));
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandHistory.addFirst(commandText);
            historyIndex = 0;
            commandTextField.setText("");
            isFirstPress = true;
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
