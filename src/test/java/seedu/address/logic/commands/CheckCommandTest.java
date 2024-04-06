package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;

public class CheckCommandTest {
    private static ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void constuctor_nullNric_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckCommand(null));
    }
    @Test
    public void execute_nullModel_throwsNullPointerException() {
        CheckCommand checkCommand = new CheckCommand(new Nric("S0123456A"));
        assertThrows(NullPointerException.class, () -> checkCommand.execute(null));
    }
    @Test
    public void execute_personNotFound_throwsCommandException() {
        CheckCommand checkCommand = new CheckCommand(new Nric(NON_EXISTENT_NRIC));
        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND, () -> checkCommand.execute(model));
    }
}
