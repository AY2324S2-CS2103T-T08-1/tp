package seedu.address.model.visit;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;


/**
 * Represents a single Patient Visit
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Visit {
    private final Nric nric;
    private DateOfVisit dateOfVisit;
    private Symptom symptom;
    private Diagnosis diagnosis;
    private Status status;
    /**
     * Every mandatory field must be present and not null.
     */
    public Visit(Nric nric, DateOfVisit dateOfVisit, Symptom symptom, Diagnosis diagnosis, Status status) {
        //Only the fields that are mandatory are included down here
        requireAllNonNull(nric, dateOfVisit, diagnosis, symptom, status);
        this.nric = nric;
        this.dateOfVisit = dateOfVisit;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.status = status;
    }

    public Nric getNric() {
        return nric;
    }

    public DateOfVisit getDateOfVisit() {
        return this.dateOfVisit;
    }
    public Symptom getSymptom() {
        return this.symptom;
    }
    public Diagnosis getDiagnosis() {
        return this.diagnosis;
    }
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns true if both persons have the same nric.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Visit otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns true if the person has all mandatory fields.
     */
    public static boolean isValidVisit(Visit visit) {
        return visit.nric != null
                && visit.dateOfVisit != null
                && visit.symptom != null
                && visit.diagnosis != null
                && visit.status != null;
    }

    /**
     * Returns true if both visits have the same nric and date
     * NRIC and Date of Visit serve as the unique key for each Visit
     * This is different from equals
     * @return Boolean if two Visits are the same
     */
    public boolean isSameVisit(Visit otherVisit) {
        if (otherVisit == this) {
            return true;
        }

        return otherVisit != null && otherVisit.getNric().equals(getNric())
            && otherVisit.getDateOfVisit().equals(getDateOfVisit());
    }


    /**
     * Returns true if both persons have the same identity and all data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Visit)) {
            return false;
        }
        Visit otherVisit = (Visit) other;
        if (!(isValidVisit(this) && isValidVisit(otherVisit))) {
            return false;
        }
        return nric.equals(otherVisit.nric)
                && dateOfVisit.equals(otherVisit.dateOfVisit)
                && symptom.equals(otherVisit.symptom)
                && diagnosis.equals(otherVisit.diagnosis)
                && status.equals(otherVisit.status);



    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, dateOfVisit, symptom, diagnosis, status);
    }

    /**
     * @return String representation of Visit class
     */
    @Override
    public String toString() {
        // list view
        return new ToStringBuilder(this)
                .add("nric", nric)
                .add("date of visit", dateOfVisit)
                .add("symptom", symptom)
                .add("diagnosis", diagnosis)
                .add("status", status).toString();
    }
}
