package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFADMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.DeleteInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;





/**
 * Parses input arguments and creates a new DeleteInfoCommand object
 */
public class DeleteInfoCommandParser implements Parser<DeleteInfoCommand> {
    /**
     * List of prefixes for optional fields, in the order of the fields in DeleteInfoCommand.
     */
    public static final Prefix[] OPTIONAL_PREFIXES = {PREFIX_EMAIL, PREFIX_ALLERGIES, PREFIX_BLOODTYPE,
        PREFIX_DATEOFADMISSION, PREFIX_COUNTRY, PREFIX_CONDITION, PREFIX_SYMPTOM, PREFIX_DIAGNOSIS};
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInfoCommand
     * and returns a DeleteInfoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInfoCommand parse(String args) throws ParseException {
        //Check for empty input
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE));
        }
        //Parse prefixes
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS,
                        PREFIX_DATEOFBIRTH, PREFIX_SEX, PREFIX_STATUS, PREFIX_TAG, PREFIX_EMAIL, PREFIX_COUNTRY,
                        PREFIX_DATEOFADMISSION, PREFIX_ALLERGIES, PREFIX_BLOODTYPE, PREFIX_CONDITION, PREFIX_SYMPTOM,
                        PREFIX_DIAGNOSIS);
        //Check for NRIC
        Nric nric;
        try {
            nric = ParserUtil.parseNric(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE), pe);
        }

        //Check for empty input
        if (argMultimap.length() <= 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE));
        }
        //check for mandatory fields
        Prefix[] mandatoryPrefixes = {PREFIX_NRIC, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_DATEOFBIRTH,
            PREFIX_SEX, PREFIX_STATUS};
        if (Arrays.stream(mandatoryPrefixes).anyMatch(argMultimap::contains)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_ONLY_OPTIONAL_FIELDS));
        }

        //Check for optional fields & any extra invalid input
        boolean[] fieldsToDelete = new boolean[DeleteInfoCommand.NUM_FIELDS];
        Arrays.fill(fieldsToDelete, false);
        Prefix[] optionalPrefixes = {PREFIX_EMAIL, PREFIX_ALLERGIES,
            PREFIX_BLOODTYPE, PREFIX_DATEOFADMISSION, PREFIX_COUNTRY,
            PREFIX_CONDITION, PREFIX_SYMPTOM, PREFIX_DIAGNOSIS};
        for (int i = 0; i < optionalPrefixes.length; i++) {
            //prefix is not mentioned
            if (argMultimap.getValue(optionalPrefixes[i]).isEmpty()) {
                continue;
            }
            //prefix is mentioned but value is not empty
            if (!argMultimap.getValue(optionalPrefixes[i]).get().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE));
            }
            fieldsToDelete[i] = true;
        }
        return new DeleteInfoCommand(nric, fieldsToDelete);
    }
}
