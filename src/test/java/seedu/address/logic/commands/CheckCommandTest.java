package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CheckCommand.MESSAGE_READ_PERSON_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalVisits.VISIT_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.visit.VisitContainsNricPredicate;
import seedu.address.testutil.TypicalVisits;

public class CheckCommandTest {

    private final Model model = new ModelManager(TypicalVisits.getTypicalCombinedAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(
        TypicalVisits.getTypicalCombinedAddressBook(), new UserPrefs());
    /*@Test
    void execute_validVisitNric_success() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ALICE.getName()).append(" (NRIC: ").append(VISIT_ALICE.getNric()).append("):\n");
        stringBuilder.append(Messages.formatCheck(VISIT_ALICE)).append("\n");
        stringBuilder.append(Messages.formatCheck(TypicalVisits.VISIT_ALICE_2)).append("\n");
        stringBuilder.append(Messages.formatCheck(TypicalVisits.VISIT_ALICE_3)).append("\n");
        String expectedMessage = String.format(MESSAGE_READ_PERSON_SUCCESS, stringBuilder);
        CheckCommand command = new CheckCommand(VISIT_ALICE.getNric());
        expectedModel.updateFilteredPersonList(new NricContainsKeywordsPredicate(VISIT_ALICE.getNric().toString()));
        expectedModel.updateFilteredVisitList(new VisitContainsNricPredicate(VISIT_ALICE.getNric().toString()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }*/

    @Test
    void execute_invalidVisitNric_success() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ALICE.getName()).append(" (NRIC: ").append(ALICE.getNric()).append("):\n");
        String expectedMessage = String.format(MESSAGE_READ_PERSON_SUCCESS, stringBuilder);
        CheckCommand command = new CheckCommand(ALICE.getNric());
        expectedModel.updateFilteredPersonList(new NricContainsKeywordsPredicate(ALICE.getNric().toString()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_nonExistentNric_failure() {
        assertCommandFailure(new ReadCommand(new Nric(NON_EXISTENT_NRIC)), model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    void equals() {
        CheckCommand checkFirstCommand = new CheckCommand(ALICE.getNric());
        CheckCommand checkSecondCommand = new CheckCommand(new Nric(NON_EXISTENT_NRIC));

        // same object -> returns true
        assertTrue(checkFirstCommand.equals(checkFirstCommand));

        // same values -> returns true
        CheckCommand checkFirstCommandCopy = new CheckCommand(ALICE.getNric());
        assertTrue(checkFirstCommand.equals(checkFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(checkFirstCommand.equals(checkSecondCommand));
    }

    @Test
    void testToString() {
        CheckCommand checkCommand = new CheckCommand(ALICE.getNric());
        String expected = CheckCommand.class.getCanonicalName() + "{nric=" + ALICE.getNric() + "}";
        assertEquals(expected, checkCommand.toString());
    }

}
