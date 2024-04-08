package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Deletes the information of a person identified using it's NRIC from the address book.
 */
public class DeleteInfoCommand extends Command {
    /**
     * The command word.
     */
    public static final String COMMAND_WORD = "deleteinfo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":\nDeletes the information of the person identified by NRIC.\n"
            + "Parameters: NRIC, fields to be deleted\n"
            + "Example: " + COMMAND_WORD + " S1234567B" + PREFIX_EMAIL;
    public static final String MESSAGE_ONLY_OPTIONAL_FIELDS = "Only optional fields can be deleted.";
    /**
     * The correspondance of optional field with their position in fieldstoDelete array.
     */
    public enum Fields {
        EMAIL, ALLERGIES, BLOODTYPE, DATEOFADMISSION, COUNTRY, CONDITION, SYMPTOM, DIAGNOSIS
    }
    public static final String MESSAGE_DELETE_PERSON_INFORMATION_SUCCESS = "Deleted Patient info: %1$s";
    public static final int NUM_FIELDS = 8;

    private final Nric targetNric;
    //{email, allergies, bloodtype, date of admission, country, condition, symptom, diagnosis}
    private boolean[] fieldsToDelete = new boolean[NUM_FIELDS];
    /**
     * Creates a DeleteInfoCommand to delete the specified {@code Person}'s information
     */
    public DeleteInfoCommand(Nric targetNric, boolean[] fieldsToDelete) {
        requireNonNull(targetNric);
        requireNonNull(fieldsToDelete);
        this.targetNric = targetNric;
        this.fieldsToDelete = fieldsToDelete;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> persons = model.getFilteredPersonList();
        if (!model.hasPerson(Person.createPersonWithNric(targetNric))) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
        //Difference between filteredPersons.contains and model.hasPerson: first checks if the instance is in the list,
        //second checks if the NRIC is in the list
        Person personToChange = persons.filtered(person -> person.getNric().equals(targetNric)).get(0);
        if (fieldsToDelete[Fields.EMAIL.ordinal()]) {
            personToChange.setEmail(null);
        }
        if (fieldsToDelete[Fields.ALLERGIES.ordinal()]) {
            personToChange.setAllergies(null);
        }
        if (fieldsToDelete[Fields.BLOODTYPE.ordinal()]) {
            personToChange.setBloodType(null);
        }
        if (fieldsToDelete[Fields.DATEOFADMISSION.ordinal()]) {
            personToChange.setDateOfAdmission(null);
        }
        if (fieldsToDelete[Fields.COUNTRY.ordinal()]) {
            personToChange.setCountry(null);
        }
        if (fieldsToDelete[Fields.CONDITION.ordinal()]) {
            personToChange.setCondition(null);
        }
        if (fieldsToDelete[Fields.SYMPTOM.ordinal()]) {
            personToChange.setSymptom(null);
        }
        if (fieldsToDelete[Fields.DIAGNOSIS.ordinal()]) {
            personToChange.setDiagnosis(null);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_INFORMATION_SUCCESS,
                Messages.format(personToChange)));
    }

    private boolean fieldsToDeleteEquals(boolean[] otherFieldsToDelete) {
        if (otherFieldsToDelete.length != NUM_FIELDS) {
            return false;
        }
        for (int i = 0; i < NUM_FIELDS; i++) {
            if (this.fieldsToDelete[i] != otherFieldsToDelete[i]) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInfoCommand)) {
            return false;
        }

        DeleteInfoCommand otherDeleteCommand = (DeleteInfoCommand) other;
        return targetNric.equals(otherDeleteCommand.targetNric)
                && fieldsToDeleteEquals(otherDeleteCommand.fieldsToDelete);
    }
    @Override
    public String toString() {
        StringBuilder fields = new StringBuilder();
        for (int i = 0; i < NUM_FIELDS; i++) {
            if (fieldsToDelete[i]) {
                fields.append(Fields.values()[i].toString()).append(",");
            }
        }
        return new ToStringBuilder(this)
                .add("targetNric", targetNric)
                .add("fieldsToDelete", fields)
                .toString();
    }
}
