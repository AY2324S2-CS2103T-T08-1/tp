package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.visit.Visit;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Visit> PREDICATE_SHOW_ALL_VISITS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getImmunimateFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setImmunimateFilePath(Path immuniMateFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setImmuniMate(ReadOnlyImmuniMate immuniMate);

    /** Returns the AddressBook */
    ReadOnlyImmuniMate getImmuniMate();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a visit with same unique identifier as {@code visit} exists in the address book.
     */
    boolean hasVisit(Visit visit);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given visit.
     * The visit must exist in the address book.
     */
    void deleteVisit(Visit target);


    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given visit.
     * {@code visit} must not already exist in the address book.
     */
    void addVisit(Visit visit);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given visit {@code target} with {@code editedVisit}.
     * {@code target} must exist in the address book.
     * The unique identifier of {@code editedVisit} must not be the same as another existing visit in the address book.
     */
    void setVisit(Visit target, Visit editedVisit);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered visit list */
    ObservableList<Visit> getFilteredVisitList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered visit list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVisitList(Predicate<Visit> predicate);
}
