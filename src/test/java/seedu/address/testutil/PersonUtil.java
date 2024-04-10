package seedu.address.testutil;

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

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdatePersonDescriptor;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getCreateCommand(Person person) {
        return CreateCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    //TODO: add in optionals
    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NRIC + person.getNric().toString() + " ");
        sb.append(PREFIX_NAME + person.getName().toString() + " ");
        sb.append(PREFIX_PHONE + person.getPhone().toString() + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().toString() + " ");
        sb.append(PREFIX_DATEOFBIRTH + person.getDateOfBirth().toString() + " ");
        sb.append(PREFIX_SEX + person.getSex().toString() + " ");
        sb.append(PREFIX_STATUS + person.getStatus().toString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(UpdatePersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        assert descriptor.getName().isPresent() && descriptor.getPhone().isPresent()
                && descriptor.getAddress().isPresent() && descriptor.getDateOfBirth().isPresent()
                && descriptor.getSex().isPresent() && descriptor.getStatus().isPresent();
        sb.append(PREFIX_NAME).append(descriptor.getName().get().toString()).append(" ");
        sb.append(PREFIX_PHONE).append(descriptor.getPhone().get().toString()).append(" ");
        sb.append(PREFIX_ADDRESS).append(descriptor.getAddress().get().toString()).append(" ");
        sb.append(PREFIX_DATEOFBIRTH).append(descriptor.getDateOfBirth().get().toString()).append(" ");
        sb.append(PREFIX_SEX).append(descriptor.getSex().get().toString()).append(" ");
        sb.append(PREFIX_STATUS).append(descriptor.getStatus().get().toString()).append(" ");
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.toString()).append(" "));
        descriptor.getCountry().ifPresent(country -> sb.append(PREFIX_COUNTRY).append(country.toString()).append(" "));
        descriptor.getAllergies().ifPresent(allergies -> sb.append(PREFIX_ALLERGIES)
                .append(allergies.toString()).append(" "));
        descriptor.getBloodType().ifPresent(bloodType -> sb
                .append(PREFIX_BLOODTYPE).append(bloodType.toString()).append(" "));
        descriptor.getCondition().ifPresent(condition -> sb
                .append(PREFIX_CONDITION).append(condition.toString()).append(" "));
        descriptor.getDateOfAdmission().ifPresent(dateOfAdmission -> sb
                .append(PREFIX_DATEOFADMISSION).append(dateOfAdmission.toString()).append(" "));
        descriptor.getDiagnosis().ifPresent(diagnosis -> sb
                .append(PREFIX_DIAGNOSIS).append(diagnosis.toString()).append(" "));
        descriptor.getSymptom().ifPresent(symptom -> sb.append(PREFIX_SYMPTOM).append(symptom.toString()).append(" "));
        /*
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        */
        return sb.toString();
    }
}
