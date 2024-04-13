package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFVISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;

/**
 * Adds a person to the address book.
 */
public class AddVisitCommand extends Command {
    public static final String COMMAND_WORD = "addvisit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\nAdds a visit to a patient."
            + "\nParameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATEOFVISIT + "DATEOFVISIT "
            + PREFIX_SYMPTOM + "SYMPTOM "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS "
            + PREFIX_STATUS + "STATUS "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S0123456A "
            + PREFIX_DATEOFVISIT + "2024-01-04 "
            + PREFIX_SYMPTOM + "Fever, Rhinorrhea "
            + PREFIX_DIAGNOSIS + "Common Flu "
            + PREFIX_STATUS + "PENDING";

    public static final String MESSAGE_SUCCESS = "New Visit added: %1$s";
    public static final String MESSAGE_DUPLICATE_VISIT = "This visit already exists in the system";
    public static final String MESSAGE_INVALID_VISIT = "The NRIC supplied does not link to any existing Patient";

    private final Visit toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddVisitCommand(Visit visit) {
        requireNonNull(visit);
        toAdd = visit;
    }

    //TODO test cases
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Nric patientNric = toAdd.getNric();
        Person patient = Person.createPersonWithNric(patientNric);
        // Guard clauses to ensure NRIC is valid and Visit is not duplicate
        if (!model.hasPerson(patient)) {
            throw new CommandException(MESSAGE_INVALID_VISIT);
        }
        if (model.hasVisit(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_VISIT);
        }
        //TODO: Update patient symptom and diagnosis to reflect latest visit!
        model.addVisit(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddVisitCommand)) {
            return false;
        }

        AddVisitCommand otherAddVisitCommand = (AddVisitCommand) other;
        return toAdd.equals(otherAddVisitCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
