package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final Color customGreen = Color.web("#81C784");
    private static final Color customYellow = Color.web("#FFF176");
    private static final Color customRed = Color.web("#EF5350");


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label nric;
    @FXML
    private Circle statusCircle;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().toString() + " (" + person.getSex().toString() + ")");
        nric.setText(person.getNric().toString());
        updateStatusCircle(person.getStatus());
    }

    /**
     * Set colour of status circle to represent status
     *
     * @param status Status of the person
     */
    public void updateStatusCircle(Status status) {
        switch (status.getStatusType()) {
        case HEALTHY:
            statusCircle.setFill(customGreen);
            statusCircle.setStroke(customGreen);
            break;
        case UNWELL:
            statusCircle.setFill(customRed);
            statusCircle.setStroke(customRed);
            break;
        case PENDING:
            statusCircle.setFill(customYellow);
            statusCircle.setStroke(customYellow);
            break;
        default:
            statusCircle.setFill(Color.GREY);
            statusCircle.setStroke(Color.GREY);
            break;
        }
    }
}
