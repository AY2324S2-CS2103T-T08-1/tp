package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitContainsNricPredicate;

/**
 * Checks the visits in history of an existing person in the address book.
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\nChecks the visits in history of the person identified"
            + " by the NRIC specified. "
            + "\nExample: " + COMMAND_WORD
            + " S0123456A";

    public static final String MESSAGE_READ_PERSON_SUCCESS = "Checked Person ->\n%1$s";
    private final Nric nric;

    /**
     * @param nric of the person to check
     */
    public CheckCommand(Nric nric) {
        requireNonNull(nric);

        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(Person.createPersonWithNric(nric))) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        model.updateFilteredPersonList(new NricContainsKeywordsPredicate(nric.toString()));
        Person checkedPerson = model.getFilteredPersonList().get(0);

        model.updateFilteredVisitList(new VisitContainsNricPredicate(nric.toString()));
        List<Visit> filteredVisits = model.getFilteredVisitList();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(checkedPerson.getName()).append(" (NRIC: ").append(checkedPerson.getNric()).append("):\n");
        for (Visit visit : filteredVisits) {
            stringBuilder.append(Messages.formatCheck(visit)).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_READ_PERSON_SUCCESS, stringBuilder));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckCommand)) {
            return false;
        }

        CheckCommand otherCheckCommand = (CheckCommand) other;
        return this.nric.equals(otherCheckCommand.nric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", nric)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nric);
    }

}
