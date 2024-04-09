package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AddressDiagnosisStatusPredicate;


/**
 * Updates the details of an existing person in the address book.
 */
public class ClusterCommand extends Command {

    public static final String COMMAND_WORD = "cluster";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\nDetects if there is a cluster of the size given,"
            + " at the location given, of the disease given, and shows the details of all there with the disease "
            + "by the respective NRIC in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: [CLUSTER SIZE] "
            + "[" + PREFIX_ADDRESS + "LOCATION] "
            + "[" + PREFIX_DIAGNOSIS + "DISEASE]\n"
            + "Example: " + COMMAND_WORD + " 50 "
            + PREFIX_ADDRESS + "choa chu kang "
            + PREFIX_DIAGNOSIS + "dengue";

    public static final String MESSAGE_NO_INFECTED_PEOPLE = "Good news! This area \"%1$s\" has no patients with %2$s.";
    public static final String MESSAGE_CLUSTER_FOUND_SUCCESS =
            "Cluster of %1$s found!\nHere's everyone in the area of \"%2$s\" with \"%3$s\":";
    public static final String MESSAGE_CLUSTER_NOT_FOUND = "Cluster not found.\n"
            + "There are only %1$s people in the area of \"%2$s\" with \"%3$s\".\n"
            + "But here are infected people in the area to look out for:";
    private final int clusterSize;
    private final AddressDiagnosisStatusPredicate predicate;

    /**
     * @param clusterSize of the person in the filtered person list to update
     * @param predicate details to update the person with
     */
    public ClusterCommand(int clusterSize, AddressDiagnosisStatusPredicate predicate) {
        requireNonNull(predicate);

        this.clusterSize = clusterSize;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        int len = model.getFilteredPersonList().size();
        if (len == 0) {
            return new CommandResult(String.format(MESSAGE_NO_INFECTED_PEOPLE,
                    predicate.getAddress(), predicate.getDisease()));
        } else if (len < clusterSize) {
            return new CommandResult(String.format(MESSAGE_CLUSTER_NOT_FOUND, len,
                    predicate.getAddress(), predicate.getDisease()));
        } else {
            return new CommandResult(String.format(MESSAGE_CLUSTER_FOUND_SUCCESS, len,
                    predicate.getAddress(), predicate.getDisease()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClusterCommand)) {
            return false;
        }

        ClusterCommand otherClusterCommand = (ClusterCommand) other;
        return otherClusterCommand.clusterSize == clusterSize
                && predicate.equals(otherClusterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("cluster size", clusterSize)
                .add("predicate", predicate)
                .toString();
    }
}
