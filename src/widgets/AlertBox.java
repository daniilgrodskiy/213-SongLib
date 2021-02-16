package widgets;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class AlertBox {

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        // Prevent user interactions with other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(250);

        // Label
        Label label = new Label();
        label.setText(message);
        label.setPadding(new Insets(10, 10, 10, 10));

        // Button
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        // Button layout
        HBox buttonLayout = new HBox(20);
        buttonLayout.getChildren().addAll(yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));


        // Full layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, buttonLayout);
        layout.setAlignment(Pos.CENTER);


        // Create and show scene
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
