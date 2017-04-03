package conversations.im.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Siddhesh Rane
 */
public class SettingsController implements Initializable {

    @FXML
    private ComboBox<String> themeChooser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        themeChooser.getItems().setAll("conversations/im/main.css", "conversations/im/main-dark.css");
        themeChooser.getSelectionModel().selectFirst();
        themeChooser.valueProperty().addListener(ob -> {
            themeChooser.getScene().getStylesheets().setAll(themeChooser.getValue());
        });

    }

}
