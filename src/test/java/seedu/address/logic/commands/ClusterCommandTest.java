package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AddressDiagnosisStatusPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class ClusterCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        int clusterSize = 4;
        String address = "Woodlands";
        String disease = "coronavirus";
        String status = "UNWELL";
        AddressDiagnosisStatusPredicate firstPredicate =
                new AddressDiagnosisStatusPredicate(address, disease, status);
        ClusterCommand clusterFirstCommand = new ClusterCommand(clusterSize, firstPredicate);

        // same object -> returns true
        assertEquals(clusterFirstCommand, clusterFirstCommand);

        // same values -> returns true
        ClusterCommand clusterFirstCommandCopy = new ClusterCommand(clusterSize, firstPredicate);
        assertEquals(clusterFirstCommand, clusterFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, clusterFirstCommand);

        // null -> returns false
        assertNotEquals(null, clusterFirstCommand);

        // different cluster size -> returns false
        ClusterCommand clusterSecondCommand = new ClusterCommand(8, firstPredicate);
        assertNotEquals(clusterFirstCommand, clusterSecondCommand);

        // different address -> returns false
        AddressDiagnosisStatusPredicate secondPredicate =
                new AddressDiagnosisStatusPredicate("Pasir Ris", disease, status);
        clusterSecondCommand = new ClusterCommand(clusterSize, secondPredicate);
        assertNotEquals(clusterFirstCommand, clusterSecondCommand);

        // different illness -> returns false
        secondPredicate = new AddressDiagnosisStatusPredicate(address, "dengue", status);
        clusterSecondCommand = new ClusterCommand(clusterSize, secondPredicate);
        assertNotEquals(clusterFirstCommand, clusterSecondCommand);

        // different status -> return false
        secondPredicate = new AddressDiagnosisStatusPredicate(address, disease, "PENDING");
        clusterSecondCommand = new ClusterCommand(clusterSize, secondPredicate);
        assertNotEquals(clusterFirstCommand, clusterSecondCommand);
    }

    @Test
    public void execute_noInfectedPeople() {
        int clusterSize = 3;
        String address = "nowhere";
        String disease = "nothing";
        AddressDiagnosisStatusPredicate predicate =
                new AddressDiagnosisStatusPredicate(address, disease, "UNWELL");
        ClusterCommand command = new ClusterCommand(clusterSize, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(
                ClusterCommand.MESSAGE_NO_INFECTED_PEOPLE, address, disease);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_clusterNotFound() {
        int clusterSize = 3;
        String address = "clementi";
        String disease = "dengue";
        AddressDiagnosisStatusPredicate predicate =
                new AddressDiagnosisStatusPredicate(address, disease, "UNWELL");
        ClusterCommand command = new ClusterCommand(clusterSize, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(ClusterCommand.MESSAGE_CLUSTER_NOT_FOUND,
                expectedModel.getFilteredPersonList().size(), address, disease);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_clusterFound() {
        int clusterSize = 3;
        String address = "jurong";
        String disease = "coronavirus";
        AddressDiagnosisStatusPredicate predicate =
                new AddressDiagnosisStatusPredicate(address, disease, "UNWELL");
        ClusterCommand command = new ClusterCommand(clusterSize, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(
                ClusterCommand.MESSAGE_CLUSTER_FOUND_SUCCESS, clusterSize, address, disease);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        int clusterSize = 5;
        AddressDiagnosisStatusPredicate predicate = new AddressDiagnosisStatusPredicate(
                "Woodlands", "coronavirus", "UNWELL");
        ClusterCommand clusterCommand = new ClusterCommand(clusterSize, predicate);
        String expected = ClusterCommand.class.getCanonicalName()
                + "{cluster size=" + clusterSize + ", "
                + "predicate=" + predicate + "}";
        assertEquals(expected, clusterCommand.toString());
    }
}
