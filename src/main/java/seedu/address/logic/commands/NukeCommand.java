package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_INDEX;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class NukeCommand extends Command {

    public static final String COMMAND_WORD = "nuke";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all entries within a range of index. EndIndex must be more than StartIndex\n" + "Parameters: " + PREFIX_START_INDEX + "START_INDEX " + PREFIX_END_INDEX + "END_INDEX \n" + "Example: " + COMMAND_WORD + "si/1 ei/3";

    public static final String MESSAGE_DELETE_ALL_SUCCESS = "Successfully deleted all entries";

    private final Index startIndex;
    private final Index endIndex;

    public NukeCommand(Index startIndex, Index endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int length = endIndex.getZeroBased() - startIndex.getZeroBased() + 1;
        if (endIndex.getZeroBased() >= lastShownList.size() || endIndex.getZeroBased() < 0 || startIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        for (int i = 0; i < length; i++) {
            Person personToDelete = lastShownList.get(startIndex.getZeroBased());
            model.deletePerson(personToDelete);
        }

        return new CommandResult(MESSAGE_DELETE_ALL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        NukeCommand otherNukeCommand = (NukeCommand) other;
        return startIndex.equals(otherNukeCommand.startIndex) && endIndex.equals(otherNukeCommand.endIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("startIndex", startIndex).add("endIndex", endIndex).toString();
    }
}
