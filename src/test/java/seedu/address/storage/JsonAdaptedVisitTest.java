package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedVisit.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVisits.VISIT_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Nric;
import seedu.address.model.visit.DateOfVisit;

public class JsonAdaptedVisitTest {
    private static final String INVALID_NRIC = "T-1";
    private static final String INVALID_DOV = "MARCH 10th 2021";
    private static final String INVALID_DIAGNOSIS = " Hel";
    private static final String INVALID_SYMPTOM = " ";
    private static final String INVALID_STATUS = "DYING";

    private static final String VALID_NRIC = VISIT_ALICE.getNric().toString();
    private static final String VALID_DOV = VISIT_ALICE.getDateOfVisit().toString();
    private static final String VALID_DIAGNOSIS = VISIT_ALICE.getDiagnosis().toString();
    private static final String VALID_SYMPTOM = VISIT_ALICE.getSymptom().toString();
    private static final String VALID_STATUS = VISIT_ALICE.getStatus().toString();

    @Test
    public void toModelType_validVisitDetails_returnsVisit() throws Exception {
        JsonAdaptedVisit visit = new JsonAdaptedVisit(VISIT_ALICE);
        assertEquals(VISIT_ALICE, visit.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedVisit visit =
            new JsonAdaptedVisit(INVALID_NRIC, VALID_DOV, VALID_DIAGNOSIS, VALID_SYMPTOM, VALID_STATUS);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, visit::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedVisit visit = new JsonAdaptedVisit(null, VALID_DOV, VALID_DIAGNOSIS, VALID_SYMPTOM, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, visit::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfVisit_throwsIllegalValueException() {
        JsonAdaptedVisit visit =
            new JsonAdaptedVisit(VALID_NRIC, INVALID_DOV, VALID_DIAGNOSIS, VALID_SYMPTOM, VALID_STATUS);
        String expectedMessage = DateOfVisit.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, visit::toModelType);
    }

    @Test
    public void toModelType_nullDateOfVisit_throwsIllegalValueException() {
        JsonAdaptedVisit visit = new JsonAdaptedVisit(VALID_NRIC, null, VALID_DIAGNOSIS, VALID_SYMPTOM, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfVisit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, visit::toModelType);
    }

    // Continue to add more tests for the remaining fields like diagnosis, symptom, and status

    // For example:
    @Test
    public void toModelType_invalidDiagnosis_throwsIllegalValueException() {
        JsonAdaptedVisit visit =
            new JsonAdaptedVisit(VALID_NRIC, VALID_DOV, INVALID_DIAGNOSIS, VALID_SYMPTOM, VALID_STATUS);
        String expectedMessage = Diagnosis.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, visit::toModelType);
    }

    @Test
    public void toModelType_nullDiagnosis_throwsIllegalValueException() {
        JsonAdaptedVisit visit = new JsonAdaptedVisit(VALID_NRIC, VALID_DOV, null, VALID_SYMPTOM, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Diagnosis.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, visit::toModelType);
    }
    // Add tests for Symptom and Status as done above
}
