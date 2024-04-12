package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFVISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;

import seedu.address.logic.commands.AddVisitCommand;
import seedu.address.model.visit.Visit;

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
