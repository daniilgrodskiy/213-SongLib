package widgets;

import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ErrorBox {

    public static void display(String message) {
        Stage window = new Stage();

        // Prevent user interactions with other windows
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Error");
        window.setMinWidth(250);

        // Label
        Label label = new Label(message);
        label.setText(message);

        // Button
        Button okayButton = new Button("Okay");
        okayButton.setOnAction(e -> window.close());


        // Button layout
        HBox buttonLayout = new HBox(20);
        buttonLayout.getChildren().addAll(okayButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Full layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, buttonLayout);
        layout.setAlignment(Pos.CENTER);

        // Create and show scene
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
