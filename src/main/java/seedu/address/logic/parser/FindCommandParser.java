package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.ConditionContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.startsWith(PREFIX_NAME.getPrefix())) {
            String[] nameKeywords = trimmedArgs.substring(2).trim().split("\\s+");
            List<String> list = Arrays.asList(nameKeywords);
            return new FindCommand(new NameContainsKeywordsPredicate(list));
        } else if (trimmedArgs.startsWith(PREFIX_ADDRESS.getPrefix())) {
            String[] addressKeywords = trimmedArgs.substring(2).trim().split(",");
            int len  = addressKeywords.length;
            for (int i = 0; i < len; i++) {
                addressKeywords[i] = addressKeywords[i].trim();
            }
            List<String> list = Arrays.asList(addressKeywords);
            return new FindCommand(new AddressContainsKeywordsPredicate(list));
        } else if (trimmedArgs.startsWith(PREFIX_CONDITION.getPrefix())) {
            String[] conditionKeywords = trimmedArgs.substring(4).trim().split(",");
            int len  = conditionKeywords.length;
            for (int i = 0; i < len; i++) {
                conditionKeywords[i] = conditionKeywords[i].trim();
            }
            List<String> list = Arrays.asList(conditionKeywords);
            return new FindCommand(new ConditionContainsKeywordsPredicate(list));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
