package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteInfoCommand;



public class DeleteInfoCommandParserTest {
    private DeleteInfoCommandParser parser = new DeleteInfoCommandParser();
    @Test
    public void parse_validArgs_returnsDeleteInfoCommand() {
        boolean[] fieldsToBeDeleted = {true, false, true, false, true, true, true, true};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldsToBeDeleted.length; i++) {
            if (fieldsToBeDeleted[i]) {
                stringBuilder.append(" " + DeleteInfoCommandParser.OPTIONAL_PREFIXES[i].getPrefix());
            }
        }
        assertParseSuccess(parser, BOB.getNric() + stringBuilder.toString(),
                new DeleteInfoCommand(BOB.getNric(), fieldsToBeDeleted));
    }
    @Test
    public void parse_invalidNric_throwsParseException() {
        boolean[] fieldsToBeDeleted = {true, false, true, false, true, true, true, true};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldsToBeDeleted.length; i++) {
            if (fieldsToBeDeleted[i]) {
                stringBuilder.append(" " + DeleteInfoCommandParser.OPTIONAL_PREFIXES[i].getPrefix());
            }
        }
        assertParseFailure(parser, INVALID_NRIC + stringBuilder.toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_mandatoryFields_throwsParseException() {
        boolean[] fieldsToBeDeleted = {true, false, true, false, true, true, true, true};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldsToBeDeleted.length; i++) {
            if (fieldsToBeDeleted[i]) {
                stringBuilder.append(" " + DeleteInfoCommandParser.OPTIONAL_PREFIXES[i].getPrefix());
            }
        }
        assertParseFailure(parser, BOB.getNric() + stringBuilder.toString() + " " + PREFIX_NRIC.getPrefix(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_INVALID_FIELDS));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, BOB.getNric().toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        boolean[] fieldsToBeDeleted = {true, false, true, false, true, true, true, true};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldsToBeDeleted.length; i++) {
            if (fieldsToBeDeleted[i]) {
                stringBuilder.append(" " + DeleteInfoCommandParser.OPTIONAL_PREFIXES[i].getPrefix());
            }
        }
        //append some non_prefix string to the input
        assertParseFailure(parser, BOB.getNric().toString() + stringBuilder.toString() + VALID_SEX_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInfoCommand.MESSAGE_USAGE));
    }

}
