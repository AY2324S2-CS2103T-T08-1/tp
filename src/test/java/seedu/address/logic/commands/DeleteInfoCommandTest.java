package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class DeleteInfoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteInfo_success() {
        Person personToDeleteInfo = model.getFilteredPersonList().get(0);
        Nric targetNric = personToDeleteInfo.getNric();
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true};
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand(targetNric, fieldsToDelete);
        ModelManager expectedModel = new ModelManager(model.getImmuniMate(), new UserPrefs());

        Person expectedPerson = new PersonBuilder(ALICE).build();
        expectedPerson.setEmail(null);
        expectedPerson.setAllergies(null);
        expectedPerson.setBloodType(null);
        expectedPerson.setDateOfAdmission(null);
        expectedPerson.setCountry(null);
        expectedPerson.setCondition(null);
        expectedPerson.setSymptom(null);
        expectedPerson.setDiagnosis(null);
        String expectedMessage = String.format(DeleteInfoCommand.MESSAGE_DELETE_PERSON_INFORMATION_SUCCESS,
                Messages.format(expectedPerson));
        assertCommandSuccess(deleteInfoCommand, model, expectedMessage, expectedModel);
        assertEquals(expectedPerson, personToDeleteInfo);
    }


    @Test
    public void execute_invalidNric_throwsCommandException() {
        Nric targetNric = new Nric(NON_EXISTENT_NRIC);
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true};
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand(targetNric, fieldsToDelete);

        assertCommandFailure(deleteInfoCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }
}
