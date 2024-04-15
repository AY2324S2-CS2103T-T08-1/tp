package seedu.address.model.visit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFVISIT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SYMPTOM_BOB;
import static seedu.address.testutil.TypicalVisits.VISIT_ALICE;
import static seedu.address.testutil.TypicalVisits.VISIT_ALICE_2;
import static seedu.address.testutil.TypicalVisits.VISIT_ALICE_3;
import static seedu.address.testutil.TypicalVisits.VISIT_BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.VisitBuilder;


public class VisitTest {


    @Test
    public void isSameVisit() {
        // same object -> returns true
        assertTrue(VISIT_ALICE.isSameVisit(VISIT_ALICE));

        // null -> returns false
        assertFalse(VISIT_ALICE.isSameVisit(null));

        // same nric, all other attributes different -> returns true
        Visit editedAliceVisit =
            new VisitBuilder(VISIT_ALICE).withDiagnosis(VALID_DIAGNOSIS_BOB)
                .withStatus(VALID_STATUS_BOB).withSymptom(VALID_SYMPTOM_BOB).build();
        assertTrue(VISIT_ALICE.isSameVisit(editedAliceVisit));

        // different NRIC, all other attributes same -> returns false
        editedAliceVisit = new VisitBuilder(VISIT_ALICE).withNric(VALID_NRIC_BOB).build();
        assertFalse(VISIT_ALICE.isSameVisit(editedAliceVisit));
        // different DOV, all other attributes same -> returns false
        editedAliceVisit = new VisitBuilder(VISIT_ALICE).withDateOfVisit(VALID_DATEOFVISIT_BOB).build();
        assertFalse(VISIT_ALICE.isSameVisit(editedAliceVisit));
        // different DOV, same NRIC -> returns false
        assertFalse(VISIT_ALICE.isSameVisit(VISIT_ALICE_2));
        assertFalse(VISIT_ALICE.isSameVisit(VISIT_ALICE_3));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Visit aliceCopy = new VisitBuilder(VISIT_ALICE).build();
        assertEquals(VISIT_ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(VISIT_ALICE, VISIT_ALICE);

        // null -> returns false
        assertNotEquals(null, VISIT_ALICE);

        // different type -> returns false
        assertNotEquals(5, VISIT_ALICE);

        // different person -> returns false
        assertNotEquals(VISIT_ALICE, VISIT_BENSON);

        // different nric -> returns false
        Visit editedAlice = new VisitBuilder(VISIT_ALICE).withNric(VALID_NRIC_BOB).build();
        assertNotEquals(VISIT_ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new VisitBuilder(VISIT_ALICE).withSymptom(VALID_SYMPTOM_BOB).build();
        assertNotEquals(VISIT_ALICE, editedAlice);

        // different address -> returns false
        editedAlice = new VisitBuilder(VISIT_ALICE).withDiagnosis(VALID_DIAGNOSIS_BOB).build();
        assertNotEquals(VISIT_ALICE, editedAlice);
    }

}
