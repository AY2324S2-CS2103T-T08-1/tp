package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;

import seedu.address.logic.commands.ClusterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressDiagnosisStatusPredicate;

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_ADDRESS, PREFIX_DIAGNOSIS);

        int clusterSize;
        try {
            clusterSize = Integer.parseInt(argMultimap.getPreamble());
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE), nfe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ADDRESS, PREFIX_DIAGNOSIS);
        if (clusterSize < 1 || !argMultimap.getValue(PREFIX_ADDRESS).isPresent()
                || !argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));
        }

        String address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).toString();
        String disease = ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get()).toString();
        return new ClusterCommand(clusterSize,
                new AddressDiagnosisStatusPredicate(address, disease, "UNWELL"));
    }
}
