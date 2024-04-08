package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMI;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
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
    public void constructor_emptyInput_throwsException() {
        Nric targetNric = new Nric(VALID_NRIC_AMI);
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true}; //too many fields
        assertThrows(NullPointerException.class, () -> new DeleteInfoCommand(targetNric, null));
        assertThrows(NullPointerException.class, () -> new DeleteInfoCommand(null, fieldsToDelete));
    }
    @Test
    public void execute_deleteInfo_success() {
        Person personToDeleteInfo = new PersonBuilder(ALICE).withNric("T0278967B").build();
        model.addPerson(personToDeleteInfo);
        Nric targetNric = new Nric("T0278967B");
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true};
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand(targetNric, fieldsToDelete);
        ModelManager expectedModel = new ModelManager(model.getImmuniMate(), new UserPrefs());
        Person expectedPerson = new PersonBuilder(ALICE).withNric("T0278967B").build();

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

    @Test
    public void equals_equalInput_true() {
        Nric targetNric = new Nric(VALID_NRIC_AMI);
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true};
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand(targetNric, fieldsToDelete);
        DeleteInfoCommand deleteInfoCommandCopy = new DeleteInfoCommand(targetNric, fieldsToDelete);

        assertTrue(deleteInfoCommand.equals(deleteInfoCommandCopy)); //equivalent objects
        assertTrue(deleteInfoCommand.equals(deleteInfoCommand)); //same objects
    }

    @Test
    public void equals_unequalInput_false() {
        Nric targetNric = new Nric(VALID_NRIC_AMI);
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true};
        boolean[] otherFieldsToDelete = {true, true, true, true, true, true, true, false};
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand(targetNric, fieldsToDelete);
        DeleteInfoCommand otherDeleteInfoCommand = new DeleteInfoCommand(targetNric, otherFieldsToDelete);

        assertFalse(deleteInfoCommand.equals(otherDeleteInfoCommand)); //other values
        assertFalse(deleteInfoCommand.equals(null)); //null
        assertFalse(deleteInfoCommand.equals(new CreateCommand(ALICE))); //different class
        assertFalse(deleteInfoCommand.equals(new DeleteInfoCommand(new Nric("T0123456A"), fieldsToDelete)));
        //different NRIC
    }

    @Test
    public void toStringTest() {
        Nric targetNric = new Nric(VALID_NRIC_AMI);
        boolean[] fieldsToDelete = {true, true, true, true, true, true, true, true};
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand(targetNric, fieldsToDelete);
        String expected = DeleteInfoCommand.class.getCanonicalName() + "{targetNric=" + VALID_NRIC_AMI
                + ", fieldsToDelete=" + "EMAIL,ALLERGIES,BLOODTYPE,DATEOFADMISSION,"
                + "COUNTRY,CONDITION,SYMPTOM,DIAGNOSIS,}";
        assertEquals(expected, deleteInfoCommand.toString());
    }
}
