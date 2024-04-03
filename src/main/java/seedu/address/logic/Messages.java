package seedu.address.logic;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "The person provided was not found";
    public static final String MESSAGE_VISIT_NOT_FOUND = "The visit provided was not found";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_VISITS_LISTED_OVERVIEW = "%1$d visits listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(person.getName()).append("\n")
                .append("NRIC: ").append(person.getNric()).append("\n")
                .append("Phone: ").append(person.getPhone()).append("\n")
                .append("Address: ").append(person.getAddress()).append("\n")
                .append("DOB: ").append(person.getDateOfBirth()).append("\n")
                .append("Sex: ").append(person.getSex()).append("\n")
                .append("Status: ").append(person.getStatus()).append("\n")
                .append("Email: ")
                .append(Optional.ofNullable(person.getEmail()).map(Object::toString).orElse("-")).append("\n")
                .append("Country: ")
                .append(Optional.ofNullable(person.getCountry()).map(Object::toString).orElse("-")).append("\n")
                .append("Allergies: ")
                .append(Optional.ofNullable(person.getAllergies()).map(Object::toString).orElse("-")).append("\n")
                .append("Blood Type: ")
                .append(Optional.ofNullable(person.getBloodType()).map(Object::toString).orElse("-")).append("\n")
                .append("Condition: ")
                .append(Optional.ofNullable(person.getCondition()).map(Object::toString).orElse("-")).append("\n")
                .append("DOA: ")
                .append(Optional.ofNullable(person.getDateOfAdmission()).map(Object::toString).orElse("-"))
                .append("\n")
                .append("Diagnosis: ")
                .append(Optional.ofNullable(person.getDiagnosis()).map(Object::toString).orElse("-")).append("\n")
                .append("Symptom: ")
                .append(Optional.ofNullable(person.getSymptom()).map(Object::toString).orElse("-")).append("\n")
                .append("Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code visit} for display to the user.
     */
    public static String format(Visit visit) {
        final StringBuilder builder = new StringBuilder();
        builder.append("NRIC: ").append(visit.getNric()).append("\n")
          .append("DOV: ").append(visit.getDateOfVisit()).append("\n")
          .append("Symptom: ").append(visit.getSymptom()).append("\n")
          .append("Diagnosis: ").append(visit.getDiagnosis()).append("\n")
          .append("Status: ").append(visit.getStatus()).append("\n");
        return builder.toString();
    }

}
