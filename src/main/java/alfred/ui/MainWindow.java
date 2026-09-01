package alfred.ui;

import alfred.Alfred;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controls the main JavaFX chat window.
 */
public class MainWindow {
    private static final String WELCOME_MESSAGE = "How can I assist from the cave?";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private final Image userImage = loadImage("/images/User.png");
    private final Image alfredImage = loadImage("/images/Alfred.png");
    private Alfred alfred;

    /**
     * Connects the dialog area to the scroll position after FXML injection.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Alfred application used to generate responses.
     *
     * @param alfred application that generates chat responses.
     */
    public void setAlfred(Alfred alfred) {
        this.alfred = alfred;
        dialogContainer.getChildren().add(DialogBox.getAlfredDialog(WELCOME_MESSAGE, alfredImage));
    }

    /**
     * Appends the user's message and Alfred's response, then clears the input field.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String alfredText = alfred.getResponse(userText);
        String commandType = alfred.getLastCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getAlfredDialog(alfredText, alfredImage, commandType));
        userInput.clear();
    }

    /**
     * Loads an image packaged with the application.
     *
     * @param imagePath classpath location of the image.
     * @return loaded image.
     */
    private Image loadImage(String imagePath) {
        return new Image(getClass().getResourceAsStream(imagePath));
    }
}
