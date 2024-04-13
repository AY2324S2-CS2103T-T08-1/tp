package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFVISIT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFVISIT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SYMPTOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SYMPTOM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ImmuniMate;
import seedu.address.model.visit.Visit;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalVisits {
    // Basic Visits
    public static final Visit VISIT_ALICE =
        new VisitBuilder().withNric("T0139571B").withDateOfVisit("2023-01-01").withSymptom("Runny Nose, Headache")
            .withDiagnosis("Common Flu").withStatus("UNWELL").build();
    public static final Visit VISIT_BENSON = new VisitBuilder().withNric("T0439571C").withDateOfVisit("2022-02-15")
        .withSymptom("Runny Nose, Loss of taste, Headache").withDiagnosis("COVID-19").withStatus("PENDING").build();
    public static final Visit VISIT_CARL = new VisitBuilder().withNric("T0284994B").withDateOfVisit("2024-10-24")
        .withSymptom("High fever, Headache, Nausea, Sore joints").withDiagnosis("Dengue").withStatus("UNWELL").build();

    // Additional Visits
    public static final Visit VISIT_ALICE_2 =
        new VisitBuilder().withNric("T0139571B").withDateOfVisit("2023-01-14").withSymptom("NIL")
            .withDiagnosis("Recovered").withStatus("HEALTHY").build();
    public static final Visit VISIT_BENSON_2 = new VisitBuilder().withNric("T0439571C").withDateOfVisit("2022-02-25")
        .withSymptom("Sore joints, trouble sleeping").withDiagnosis("Recovered").withStatus("HEALTHY").build();
    public static final Visit VISIT_CARL_2 = new VisitBuilder().withNric("T0284994B").withDateOfVisit("2024-11-22")
        .withSymptom("Fever, Headache, Nausea, Vomiting").withDiagnosis("Dengue").withStatus("UNWELL").build();
    public static final Visit VISIT_ALICE_3 = new VisitBuilder().withNric("T0139571B").withDateOfVisit("2024-03-23")
        .withSymptom("Dark Urine, Joint pain, Pale stool").withDiagnosis("Hepatitis B").withStatus("PENDING").build();
    public static final Visit VISIT_BENSON_3 =
        new VisitBuilder().withNric("T0439571C").withDateOfVisit("2023-01-02").withSymptom("Headache")
            .withDiagnosis("NIL").withStatus("HEALTHY").build();
    public static final Visit VISIT_CARL_3 =
        new VisitBuilder().withNric("T0284994B").withDateOfVisit("2024-10-24").withSymptom("Slight Headache")
            .withDiagnosis("Recovered").withStatus("HEALTHY").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Visit VISIT_AMY =
        new VisitBuilder().withNric(VALID_NRIC_AMY).withDateOfVisit(VALID_DATEOFVISIT_AMY)
            .withSymptom(VALID_SYMPTOM_AMY).withDiagnosis(VALID_DIAGNOSIS_AMY).withStatus(VALID_STATUS_AMY).build();
    public static final Visit VISIT_BOB =
        new VisitBuilder().withNric(VALID_NRIC_BOB).withDateOfVisit(VALID_DATEOFVISIT_BOB)
            .withSymptom(VALID_SYMPTOM_BOB).withDiagnosis(VALID_DIAGNOSIS_BOB).withStatus(VALID_STATUS_BOB).build();

    private TypicalVisits() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical visits.
     */
    public static ImmuniMate getTypicalAddressBook() {
        ImmuniMate ab = new ImmuniMate();
        for (Visit visit : getTypicalVisits()) {
            ab.addVisit(visit);
        }
        return ab;
    }

    public static List<Visit> getTypicalVisits() {
        return new ArrayList<>(
            Arrays.asList(VISIT_ALICE, VISIT_ALICE_2, VISIT_ALICE_3, VISIT_BENSON, VISIT_BENSON_2, VISIT_BENSON_3,
                VISIT_CARL, VISIT_CARL_2, VISIT_CARL_3, VISIT_AMY, VISIT_BOB));
    }
}
