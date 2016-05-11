package form;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plugins.tunable.Tunable;

import java.util.ArrayList;

/**
 * Created by Evgeny Shilov on 11.05.2016.
 */
public class PluginSettingsForm extends HBox {
    private ComboBox<Tunable> pluginComboBox;
    private VBox settingsPane;
    private VBox choosePane;
    private Button applyButton;

    public PluginSettingsForm(ArrayList<Tunable> tunablePlugins) {
        pluginComboBox = new ComboBox<>();
        pluginComboBox.getItems().addAll(tunablePlugins);
        pluginComboBox.setOnAction(event -> {
            settingsPane.getChildren().clear();
            pluginComboBox.getSelectionModel().getSelectedItem().setSettingsForm(this);
        });

        choosePane = new VBox();
        choosePane.setPadding(new Insets(20));
        choosePane.getChildren().add(new Label("Choose plugin"));
        choosePane.getChildren().add(pluginComboBox);
        choosePane.getChildren().addAll(new Label("-----------------------------"));
        applyButton = new Button("Apply");
        choosePane.getChildren().addAll(applyButton);
        choosePane.setPrefSize(200, 300);

        settingsPane = new VBox();
        settingsPane.setPadding(new Insets(20));
        settingsPane.setPrefSize(400, 300);

        this.getChildren().addAll(choosePane, settingsPane);
    }

    public VBox getSettingsPane() {
        return settingsPane;
    }

    public VBox getChoosePane() {
        return choosePane;
    }

    public ComboBox<Tunable> getPluginComboBox() {
        return pluginComboBox;
    }
}
