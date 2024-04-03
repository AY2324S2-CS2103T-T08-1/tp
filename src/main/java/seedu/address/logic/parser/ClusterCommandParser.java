package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ClusterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressAndStatusPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ClusterCommandParser implements Parser<ClusterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClusterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+", 2);
        int clusterSize;
        try {
            clusterSize = Integer.parseInt(keywords[0]);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE), nfe);
        }

        if (keywords.length < 2 || clusterSize < 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));
        }

        String addressKeyword = keywords[1];
        return new ClusterCommand(clusterSize, new AddressAndStatusPredicate(addressKeyword, "UNWELL"));
    }
}
