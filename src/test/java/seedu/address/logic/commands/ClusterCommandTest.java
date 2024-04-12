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
        int firstClusterSize = 4;
        AddressDiagnosisStatusPredicate firstPredicate = new AddressDiagnosisStatusPredicate(
                "Woodlands", "coronavirus", "UNWELL");
        ClusterCommand clusterFirstCommand = new ClusterCommand(firstClusterSize, firstPredicate);

        int secondClusterSize = 8;
        AddressDiagnosisStatusPredicate secondPredicate = new AddressDiagnosisStatusPredicate(
                "Pasir Ris", "dengue", "PENDING");
        ClusterCommand clusterSecondCommand = new ClusterCommand(secondClusterSize, secondPredicate);

        // same object -> returns true
        assertEquals(clusterFirstCommand, clusterFirstCommand);

        // same values -> returns true
        ClusterCommand clusterFirstCommandCopy = new ClusterCommand(firstClusterSize, firstPredicate);
        assertEquals(clusterFirstCommand, clusterFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, clusterFirstCommand);

        // null -> returns false
        assertNotEquals(null, clusterFirstCommand);

        // different cluster size, address, illness and status -> returns false
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
    public void execute_noClusterFound() {
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
