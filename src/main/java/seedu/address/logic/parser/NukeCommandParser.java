package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.NukeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_INDEX;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class NukeCommandParser implements Parser<NukeCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NukeCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_INDEX, PREFIX_END_INDEX);
            if (!arePrefixesPresent(argMultimap, PREFIX_START_INDEX, PREFIX_END_INDEX) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NukeCommand.MESSAGE_USAGE));
            }

            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_INDEX, PREFIX_END_INDEX);
            Index startIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_START_INDEX).get());
            Index endIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_END_INDEX).get());
            if (endIndex.getZeroBased() <= startIndex.getZeroBased()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NukeCommand.MESSAGE_USAGE));
            }
            return new NukeCommand(startIndex, endIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NukeCommand.MESSAGE_USAGE), pe);
        }
    }
}
