package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;
import seedu.address.model.visit.DateOfVisit;
import seedu.address.model.visit.Visit;


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedVisit {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Visit's %s field is missing!";
    private final String nric;
    private final String dov;
    private final String diagnosis;
    private final String symptom;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedVisit(@JsonProperty("nric") String nric, @JsonProperty("dateOfVisit") String dov,
                            @JsonProperty("diagnosis") String diagnosis, @JsonProperty("symptom") String symptom,
                            @JsonProperty("status") String status) {
        this.nric = nric;
        this.dov = dov;
        this.status = status;
        this.diagnosis = diagnosis;
        this.symptom = symptom;
    }

    /**
     * Converts a given {@code Person} into this class for Json use.
     */
    public JsonAdaptedVisit(Visit source) {
        this.nric = source.getNric().toString();
        this.dov = source.getDateOfVisit().toString();
        this.diagnosis = source.getDiagnosis().toString();
        this.symptom = source.getSymptom().toString();
        this.status = source.getStatus().toString();
    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Visit toModelType() throws IllegalValueException {

        Visit visit;

        // NRIC Check
        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);
        // DateOfVisit Check
        if (dov == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              DateOfVisit.class.getSimpleName()));
        }
        if (!DateOfVisit.isValidDateOfVisit(dov)) {
            throw new IllegalValueException(DateOfVisit.MESSAGE_CONSTRAINTS);
        }
        final DateOfVisit modelDov = new DateOfVisit(dov);
        // Symptom check
        if (symptom == null || symptom.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              Symptom.class.getSimpleName()));
        }
        final Symptom modelSymptom = new Symptom(symptom);
        // Diagnosis check
        if (diagnosis == null || diagnosis.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              Diagnosis.class.getSimpleName()));
        }
        if (!Diagnosis.isValidDiagnosis(diagnosis)) {
            throw new IllegalValueException(Diagnosis.MESSAGE_CONSTRAINTS);
        }
        final Diagnosis modelDiagnosis = new Diagnosis(diagnosis);
        // Status Check
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
              Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);
        visit = new Visit(modelNric, modelDov, modelSymptom, modelDiagnosis, modelStatus);
        return visit;
    }

}
