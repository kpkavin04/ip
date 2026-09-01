package alfred.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private static final double AVATAR_SIZE = 100.0;

    /**
     * Creates a dialog containing a message and an avatar.
     *
     * @param message message displayed in the dialog.
     * @param avatar image displayed beside the message.
     */
    public DialogBox(String message, Image avatar) {
        Label text = new Label(message);
        ImageView displayPicture = new ImageView(avatar);

        text.setWrapText(true);
        displayPicture.setFitWidth(AVATAR_SIZE);
        displayPicture.setFitHeight(AVATAR_SIZE);
        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(text, displayPicture);
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
     * Positions the avatar on the left and aligns the dialog to the left edge.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);
    }
}
