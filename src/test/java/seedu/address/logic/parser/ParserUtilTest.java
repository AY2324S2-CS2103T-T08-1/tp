package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Allergies;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Country;
import seedu.address.model.person.DateOfAdmission;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DOB = "01-01-1990";
    private static final String INVALID_SEX = "MF";
    private static final String INVALID_STATUS = "DECEASED";
    private static final String INVALID_NRIC = "A1234567B";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_COUNTRY = " ";
    private static final String INVALID_DOA = "01-01-1990";
    private static final String INVALID_BLOODTYPE = "AO+";
    private static final String INVALID_CONDITION = " ";
    private static final String INVALID_DIAGNOSIS = " ";
    private static final String INVALID_ALLERGIES = " ";
    private static final String INVALID_SYMPTOMS = " ";
    private static final String INVALID_DOV = "01-01-1990";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_DOB = "1998-07-03";
    private static final String VALID_SEX = "F";
    private static final String VALID_STATUS = "HEALTHY";
    private static final String VALID_LOWER_STATUS = "healthy";
    private static final String VALID_NRIC = "S1234567A";
    private static final String VALID_LOWER_NRIC = "s1234567a";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_COUNTRY = "Singapore";
    private static final String VALID_DOA = "2021-07-03";
    private static final String VALID_BLOODTYPE = "A+";
    private static final String VALID_SYMPTOMS = "Fever";
    private static final String VALID_CONDITION = "Covid-19";
    private static final String VALID_DIAGNOSIS = "Positive";
    private static final String VALID_ALLERGIES = "Penicillin";
    private static final String VALID_DOV = "1990-01-01";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseDateOfBirth_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateOfBirth((String) null));
    }

    @Test
    public void parseDateOfBirth_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfBirth(INVALID_DOB));
    }

    @Test
    public void parseDateOfBirth_validValueWithoutWhitespace_returnsDateOfBirth() throws Exception {
        DateOfBirth expectedDateOfBirth = new DateOfBirth(VALID_DOB);
        assertEquals(expectedDateOfBirth, ParserUtil.parseDateOfBirth(VALID_DOB));
    }

    @Test
    public void parseDateOfBirth_validValueWithWhiteSpace_returnsDateOfBirth() throws Exception {
        DateOfBirth expectedDateOfBirth = new DateOfBirth(VALID_DOB);
        assertEquals(expectedDateOfBirth, ParserUtil.parseDateOfBirth(WHITESPACE + VALID_DOB + WHITESPACE));
    }

    @Test
    public void parseSex_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSex((String) null));
    }
    @Test
    public void parseSex_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSex(INVALID_SEX));
    }
    @Test
    public void parseSex_validValueWithoutWhitespace_returnsSex() throws Exception {
        Sex expectedSex = new Sex(VALID_SEX);
        assertEquals(expectedSex, ParserUtil.parseSex(VALID_SEX));
    }
    @Test
    public void parseSex_validValueWithWhitespace_returnsTrimmedSex() throws Exception {
        Sex expectedSex = new Sex(VALID_SEX);
        assertEquals(expectedSex, ParserUtil.parseSex(WHITESPACE + VALID_SEX + WHITESPACE));
    }
    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus((String) null));
    }
    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }
    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_LOWER_STATUS));
    }
    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedStatus() throws Exception {
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(WHITESPACE + VALID_STATUS + WHITESPACE));
    }

    @Test
    public void parseNric_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNric((String) null));
    }
    @Test
    public void parseNric_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNric(INVALID_NRIC));
    }
    @Test
    public void parseNric_validValueWithoutWhitespace_returnsNric() throws Exception {
        assertEquals(new Nric(VALID_NRIC), ParserUtil.parseNric(VALID_NRIC));
        assertEquals(new Nric(VALID_NRIC), ParserUtil.parseNric(VALID_LOWER_NRIC));
    }
    @Test
    public void parseNric_validValueWithWhitespace_returnsTrimmedNric() throws Exception {
        Nric expectedNric = new Nric(VALID_NRIC);
        assertEquals(expectedNric, ParserUtil.parseNric(WHITESPACE + VALID_NRIC + WHITESPACE));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }
    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }
    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }
    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }
    @Test
    public void parseCountry_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCountry((String) null));
    }
    @Test
    public void parseCountry_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCountry(INVALID_COUNTRY));
    }
    @Test
    public void parseCountry_validValueWithoutWhitespace_returnsCountry() throws Exception {
        Country expectedCountry = new Country(VALID_COUNTRY);
        assertEquals(expectedCountry, ParserUtil.parseCountry(VALID_COUNTRY));
    }
    @Test
    public void parseCountry_validValueWithWhitespace_returnsTrimmedCountry() throws Exception {
        Country expectedCountry = new Country(VALID_COUNTRY);
        assertEquals(expectedCountry, ParserUtil.parseCountry(WHITESPACE + VALID_COUNTRY + WHITESPACE));
    }
    @Test
    public void parseDateOfAdmission_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateOfAdmission((String) null));
    }
    @Test
    public void parseDateOfAdmission_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfAdmission(INVALID_DOA));
    }
    @Test
    public void parseDateOfAdmission_validValueWithoutWhitespace_returnsDateOfAdmission() throws Exception {
        DateOfAdmission expectedDateOfAdmission = new DateOfAdmission(VALID_DOA);
        assertEquals(expectedDateOfAdmission, ParserUtil.parseDateOfAdmission(VALID_DOA));
    }
    @Test
    public void parseDateOfAdmission_validValueWithWhitespace_returnsTrimmedDateOfAdmission() throws Exception {
        DateOfAdmission expectedDateOfAdmission = new DateOfAdmission(VALID_DOA);
        assertEquals(expectedDateOfAdmission, ParserUtil.parseDateOfAdmission(WHITESPACE + VALID_DOA + WHITESPACE));
    }
    @Test
    public void parseBloodType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBloodType((String) null));
    }
    @Test
    public void parseBloodType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBloodType(INVALID_BLOODTYPE));
    }
    @Test
    public void parseBloodType_validValueWithoutWhitespace_returnsBloodType() throws Exception {
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(VALID_BLOODTYPE));
    }
    @Test
    public void parseBloodType_validValueWithWhitespace_returnsTrimmedBloodType() throws Exception {
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(WHITESPACE + VALID_BLOODTYPE + WHITESPACE));
    }
    @Test
    public void parseSymptom_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSymptom((String) null));
    }
    @Test
    public void parseSymptom_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSymptom(INVALID_SYMPTOMS));
    }
    @Test
    public void parseSymptom_validValueWithoutWhitespace_returnsSymptoms() throws Exception {
        Symptom expectedSymptoms = new Symptom(VALID_SYMPTOMS);
        assertEquals(expectedSymptoms, ParserUtil.parseSymptom(VALID_SYMPTOMS));
    }
    @Test
    public void parseSymptoms_validValueWithWhitespace_returnsTrimmedSymptoms() throws Exception {
        Symptom expectedSymptoms = new Symptom(VALID_SYMPTOMS);
        assertEquals(expectedSymptoms, ParserUtil.parseSymptom(WHITESPACE + VALID_SYMPTOMS + WHITESPACE));
    }
    @Test
    public void parseCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCondition((String) null));
    }
    @Test
    public void parseCondition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCondition(INVALID_CONDITION));
    }
    @Test
    public void parseCondition_validValueWithoutWhitespace_returnsCondition() throws Exception {
        Condition expectedCondition = new Condition(VALID_CONDITION);
        assertEquals(expectedCondition, ParserUtil.parseCondition(VALID_CONDITION));
    }
    @Test
    public void parseCondition_validValueWithWhitespace_returnsTrimmedCondition() throws Exception {
        Condition expectedCondition = new Condition(VALID_CONDITION);
        assertEquals(expectedCondition, ParserUtil.parseCondition(WHITESPACE + VALID_CONDITION + WHITESPACE));
    }
    @Test
    public void parseDiagnosis_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDiagnosis((String) null));
    }
    @Test
    public void parseDiagnosis_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDiagnosis(INVALID_DIAGNOSIS));
    }
    @Test
    public void parseDiagnosis_validValueWithoutWhitespace_returnsDiagnosis() throws Exception {
        Diagnosis expectedDiagnosis = new Diagnosis(VALID_DIAGNOSIS);
        assertEquals(expectedDiagnosis, ParserUtil.parseDiagnosis(VALID_DIAGNOSIS));
    }
    @Test
    public void parseDiagnosis_validValueWithWhitespace_returnsTrimmedDiagnosis() throws Exception {
        Diagnosis expectedDiagnosis = new Diagnosis(VALID_DIAGNOSIS);
        assertEquals(expectedDiagnosis, ParserUtil.parseDiagnosis(WHITESPACE + VALID_DIAGNOSIS + WHITESPACE));
    }

    @Test
    public void parseAllergies_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAllergies((String) null));
    }
    @Test
    public void parseAllergies_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAllergies(INVALID_ALLERGIES));
    }
    @Test
    public void parseAllergies_validValueWithoutWhitespace_returnsAllergies() throws Exception {
        Allergies expectedAllergies = new Allergies(VALID_ALLERGIES);
        assertEquals(expectedAllergies, ParserUtil.parseAllergies(VALID_ALLERGIES));
    }
    @Test
    public void parseAllergies_validValueWithWhitespace_returnsTrimmedAllergies() throws Exception {
        Allergies expectedAllergies = new Allergies(VALID_ALLERGIES);
        assertEquals(expectedAllergies, ParserUtil.parseAllergies(WHITESPACE + VALID_ALLERGIES + WHITESPACE));
    }
    @Test
    public void parseDateOfVisit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateOfVisit(null));
    }
    @Test
    public void parseDateOfVisit_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfVisit(INVALID_DOV));
    }
    @Test
    public void parseDateOfVisit_validValueWithoutWhitespace_returnsDateOfVisit() throws Exception {
        DateOfBirth expectedDateOfVisit = new DateOfBirth(VALID_DOV);
        assertEquals(expectedDateOfVisit, ParserUtil.parseDateOfBirth(VALID_DOV));
    }
    @Test
    public void parseDateOfVisit_validValueWithWhiteSpace_returnsDateOfVisit() throws Exception {
        DateOfBirth expectedDateOfVisit = new DateOfBirth(VALID_DOV);
        assertEquals(expectedDateOfVisit, ParserUtil.parseDateOfBirth(WHITESPACE + VALID_DOV + WHITESPACE));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
