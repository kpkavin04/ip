package alfred.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Displays one chat message and its corresponding avatar.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog containing a message and an avatar.
     *
     * @param message message displayed in the dialog.
     * @param avatar image displayed beside the message.
     */
    private DialogBox(String message, Image avatar) {
        loadFxml();
        dialog.setText(message);
        displayPicture.setImage(avatar);
    }

    /**
     * Creates a right-aligned dialog for a user message.
     *
     * @param message message displayed in the dialog.
     * @param avatar image displayed beside the message.
     * @return user dialog.
     */
    public static DialogBox getUserDialog(String message, Image avatar) {
        return new DialogBox(message, avatar);
    }

    /**
     * Creates a left-aligned dialog for an Alfred response.
     *
     * @param message message displayed in the dialog.
     * @param avatar image displayed beside the message.
     * @return Alfred dialog.
     */
    public static DialogBox getAlfredDialog(String message, Image avatar) {
        DialogBox dialogBox = new DialogBox(message, avatar);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Loads the dialog's FXML view into this custom control.
     */
    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load Alfred's dialog box.", e);
        }
    }

    /**
     * Positions the avatar on the left and aligns the dialog to the left edge.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);
    }
}
