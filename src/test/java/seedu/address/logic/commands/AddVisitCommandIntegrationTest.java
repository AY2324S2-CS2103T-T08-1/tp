package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddVisitCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newVisit_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getImmuniMate(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new CreateCommand(validPerson), model,
                String.format(CreateCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getImmuniMate().getPersonList().get(0);
        assertCommandFailure(new CreateCommand(personInList), model,
                CreateCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
