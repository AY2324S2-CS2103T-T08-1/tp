package seedu.address.testutil;

import seedu.address.logic.commands.AddVisitCommand;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdatePersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Visit.
 */
public class VisitUtil {

    /**
     * Returns an add command string for adding the {@code visit}.
     */
    public static String getAddVisitCommand(Visit visit) {
        return AddVisitCommand.COMMAND_WORD + " " + getVisitDetails(visit);
    }

    /**
     * Returns the part of command string for the given {@code visit}'s details.
     */
    public static String getVisitDetails(Visit visit) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NRIC + visit.getNric().toString() + " ");
        sb.append(PREFIX_DATEOFVISIT + visit.getDateOfVisit().toString() + " ");
        sb.append(PREFIX_SYMPTOM + visit.getSymptom().toString() + " ");
        sb.append(PREFIX_DIAGNOSIS + visit.getDiagnosis().toString() + " ");
        sb.append(PREFIX_STATUS + visit.getStatus().toString() + " ");
        return sb.toString();
    }

}
