package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMI;
import static seedu.address.testutil.TypicalPersons.BOB;

// TODO Adjust Test Cases
public class AddVisitCommandParserTest {
    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ALL_FIELDS_DESC_BOB, new CreateCommand(expectedPerson));
        //TODO: after implementing optional fields for person builder
    }


    @Test
    public void parse_repeatedNonTagValue_failure() {
        //TODO: add in optional fields (after person builder)
        String validExpectedPersonString = NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB;

        //multiple nrics
        assertParseFailure(parser, NRIC_DESC_BOB + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));
        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        //TODO: after implementing optional fields for person builder
        /*
        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
        */

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_NRIC, PREFIX_DATEOFBIRTH, PREFIX_SEX,
                        PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_STATUS));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
        /*
        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

         */

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        /*
        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
         */
        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMI).build();
        assertParseSuccess(parser, NRIC_DESC_AMI + NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + DATEOFBIRTH_DESC_AMY + SEX_DESC_AMY + STATUS_DESC_AMY,
                new CreateCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

        // missing nric prefix
        assertParseFailure(parser, VALID_NRIC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB,
                expectedMessage);
        // missing name prefix
        assertParseFailure(parser, NRIC_DESC_BOB + VALID_NAME_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + VALID_PHONE_BOB + ADDRESS_DESC_BOB
                        + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ADDRESS_BOB
                        + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);
        //TODO: optional fields
        /*
        // invalid email
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB, Email.MESSAGE_CONSTRAINTS);
         */
        // invalid address
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB, Address.MESSAGE_CONSTRAINTS);
        /*
        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
        */
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                        + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NRIC_DESC_BOB + NAME_DESC_BOB
                        + PHONE_DESC_BOB + ADDRESS_DESC_BOB + DATEOFBIRTH_DESC_BOB + SEX_DESC_BOB + STATUS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
    }
}
