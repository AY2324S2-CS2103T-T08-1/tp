package seedu.address.testutil;


import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;
import seedu.address.model.visit.DateOfVisit;
import seedu.address.model.visit.Visit;

/**
 * A utility class to help with building Visit objects.
 */
public class VisitBuilder {
    // Default NRIC should match an existing Person.
    public static final String DEFAULT_NRIC = "T1234567B";
    public static final String DEFAULT_DOV = "2024-01-01";
    public static final String DEFAULT_SYMPTOM = "Runny nose, headache";
    public static final String DEFAULT_DIAGNOSIS = "COVID-19";
    public static final String DEFAULT_STATUS = "UNWELL";


    // Mandatory fields
    private Nric nric;
    private DateOfVisit dateOfVisit;
    private Symptom symptom;
    private Diagnosis diagnosis;
    private Status status;


    /**
     * Creates a {@code VisitBuilder} with the default details.
     */
    public VisitBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        dateOfVisit = new DateOfVisit(DEFAULT_DOV);
        symptom = new Symptom(DEFAULT_SYMPTOM);
        diagnosis = new Diagnosis(DEFAULT_DIAGNOSIS);
        status = new Status(DEFAULT_STATUS);
    }

    /**
     * Initializes the VisitBuilder with the data of {@code visitToCopy}.
     */
    public VisitBuilder(Visit visitToCopy) {
        nric = visitToCopy.getNric();
        dateOfVisit = visitToCopy.getDateOfVisit();
        symptom = visitToCopy.getSymptom();
        diagnosis = visitToCopy.getDiagnosis();
        status = visitToCopy.getStatus();
    }
    /**
     * Sets the {@code Nric} of the {@code Visit} that we are building.
     */
    public VisitBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }
    /**
     * Sets the {@code DateOfVisit} of the {@code Visit} that we are building.
     */
    public VisitBuilder withDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = new DateOfVisit(dateOfVisit);
        return this;
    }
    /**
     * Sets the {@code Symptom} of the {@code Visit} that we are building.
     */
    public VisitBuilder withSymptom(String symptom) {
        this.symptom = new Symptom(symptom);
        return this;
    }
    /**
     * Sets the {@code Diagnosis} of the {@code Visit} that we are building.
     */
    public VisitBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = new Diagnosis(diagnosis);
        return this;
    }
    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public VisitBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Returns a Person object with fields initialised to that of PersonBuilder object.
     */
    public Visit build() {
        return new Visit(nric, dateOfVisit, symptom, diagnosis, status);
    }
}
