package conversations.im.chat;

import conversations.im.Message;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Siddhesh Rane
 */
public class ChatViewController implements Initializable {

    @FXML
    private ListView<Message> list;
    @FXML
    private TextField sendField;
    @FXML
    private Button sendButton;
    @FXML
    private Button regenerate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setItems(Message.getRandom(10));
        regenerate.setOnAction(ae -> {
            list.setItems(Message.getRandom(new Random().nextInt(50)));
            list.getItems().add(Message.EMPTY);
        });
        list.setCellFactory((param) -> {
            return new ChatCell();
        });
    }

}
