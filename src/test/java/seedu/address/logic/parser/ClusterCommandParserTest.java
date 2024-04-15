package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClusterCommand;
import seedu.address.model.person.AddressDiagnosisStatusPredicate;

public class ClusterCommandParserTest {

    private ClusterCommandParser parser = new ClusterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsClusterCommand() {
        // no leading and trailing whitespaces
        ClusterCommand expectedClusterCommand = new ClusterCommand(5,
                new AddressDiagnosisStatusPredicate("Woodlands", "swine flu", "UNWELL"));
        assertParseSuccess(parser, "5 a/Woodlands d/swine flu", expectedClusterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " 5  a/   Woodlands  d/   swine flu   ", expectedClusterCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no cluster size
        assertParseFailure(parser, "a/Woodlands d/swine flu",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));

        // floating point cluster size
        assertParseFailure(parser, "5.1 a/Woodlands d/swine flu",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));

        // non-number cluster size
        assertParseFailure(parser, "hello a/Woodlands d/swine flu",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));

        // negative cluster size
        assertParseFailure(parser, "-2 a/Woodlands d/swine flu",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClusterCommand.MESSAGE_USAGE));
    }
}
