package form;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by Evgeny Shilov on 11.05.2016.
 */
public class PluginSettingsForm extends HBox {
    private ComboBox<String> pluginComboBox;

    public PluginSettingsForm() {
        pluginComboBox = new ComboBox<>();
        pluginComboBox.getItems().addAll("First", "Second");
        GridPane choosePane = new GridPane();
        choosePane.addRow(1, new Label("Choose plugin"));
        choosePane.addRow(2, pluginComboBox);
        choosePane.setPrefSize(100, 200);
        this.getChildren().addAll(choosePane);
    }
}
