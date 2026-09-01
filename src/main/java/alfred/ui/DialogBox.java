package alfred.ui;

import javafx.geometry.Pos;
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
}
