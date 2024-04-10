package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFVISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;
import seedu.address.model.visit.DateOfVisit;
import seedu.address.model.visit.Visit;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddVisitCommandParser implements Parser<AddVisitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    //TODO test cases
    public AddVisitCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_DATEOFVISIT,
                  PREFIX_SYMPTOM, PREFIX_DIAGNOSIS, PREFIX_STATUS);
        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_DATEOFVISIT, PREFIX_SYMPTOM,
                PREFIX_DIAGNOSIS, PREFIX_STATUS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVisitCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_DATEOFVISIT,
            PREFIX_SYMPTOM, PREFIX_DIAGNOSIS, PREFIX_STATUS);
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        DateOfVisit dov = ParserUtil.parseDateOfVisit(argMultimap.getValue(PREFIX_DATEOFVISIT).get());
        Symptom symptom = ParserUtil.parseSymptom(argMultimap.getValue(PREFIX_SYMPTOM).get());
        Diagnosis diagnosis = ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        //TODO (later): assersion to make sure optional values don't generate errors
        Visit visit = new Visit(nric, dov, symptom, diagnosis, status);

        return new AddVisitCommand(visit);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
