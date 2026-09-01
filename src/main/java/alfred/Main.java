package alfred;

import alfred.ui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Displays Alfred's initial JavaFX chat layout.
 */
public class Main extends Application {
    private static final double WINDOW_WIDTH = 400.0;
    private static final double WINDOW_HEIGHT = 600.0;
    private static final double SCROLL_PANE_WIDTH = 385.0;
    private static final double SCROLL_PANE_HEIGHT = 535.0;
    private static final double USER_INPUT_WIDTH = 325.0;
    private static final double SEND_BUTTON_WIDTH = 55.0;
    private static final double ANCHOR_OFFSET = 1.0;

    private final Image userImage = loadImage("/images/User.png");

    /**
     * Creates and displays the application's primary window.
     *
     * @param stage primary window supplied by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        scrollPane.setContent(dialogContainer);
        dialogContainer.getChildren().add(new DialogBox("Hello!", userImage));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        configureLayout(stage, mainLayout, scrollPane, dialogContainer, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Configures the initial size and positions of the chat controls.
     */
    private void configureLayout(Stage stage, AnchorPane mainLayout, ScrollPane scrollPane,
                                 VBox dialogContainer, TextField userInput, Button sendButton) {
        stage.setTitle("Alfred");
        stage.setResizable(false);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);

        mainLayout.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        scrollPane.setPrefSize(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(USER_INPUT_WIDTH);
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, ANCHOR_OFFSET);
        AnchorPane.setBottomAnchor(sendButton, ANCHOR_OFFSET);
        AnchorPane.setRightAnchor(sendButton, ANCHOR_OFFSET);
        AnchorPane.setBottomAnchor(userInput, ANCHOR_OFFSET);
        AnchorPane.setLeftAnchor(userInput, ANCHOR_OFFSET);
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
