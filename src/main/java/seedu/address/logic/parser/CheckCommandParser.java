package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new ReadCommand object
 */
public class CheckCommandParser implements Parser<CheckCommand> {
    /**
     * Parses the given {@code String} of argument in the context of the ReadCommand
     * and returns an ReadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckCommand parse(String args) throws ParseException {
        requireNonNull(args);
        //ToUpperCase to be correctly implemented in model
        String trimmedArg = args.trim();
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }

        try {
            return new CheckCommand(new Nric(trimmedArg.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new ParseException(
                    String.format("%s", e.getMessage()));
        }
    }
}


