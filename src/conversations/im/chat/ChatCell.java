package conversations.im.chat;

import conversations.im.Message;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Siddhesh Rane
 */
public class ChatCell extends ListCell<Message> implements Initializable {

    private static final FXMLLoader LOADER = new FXMLLoader(ChatCell.class.getResource("ChatCell.fxml"));

    @FXML
    private HBox hbox;
    @FXML
    private Label sender;
    @FXML
    private Label time;
    @FXML
    private TextFlow textFlow;
    @FXML
    private Text text;
    @FXML
    private ImageView profile;
    @FXML
    private Pane root;
    boolean loaded;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E h:m a");

    public ChatCell() {
        setContextMenu(new ContextMenu(new MenuItem("Send  a personal message")));
        LOADER.setController(this);
        LOADER.setRoot(root);
        try {
            root = LOADER.load();
        } catch (IOException ex) {
            Logger.getLogger(ChatCell.class.getName()).log(Level.SEVERE, null, ex);
            root = new StackPane();
            root.getChildren().add(new Text("FXML could not be loaded"));
        }
    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if (!loaded) {
            return;
        }
        if (empty || item == Message.EMPTY) {
            setGraphic(null);
            setId("filler-cell");
            return;
        }
        setId(null);
        setGraphic(root);
        text.setText(item.getContent());
        sender.setText(item.getSender().getName());
        profile.setImage(item.getSender().getProfile());
        time.setText(dateFormat.format(item.getSentTime()));
        if (item.getSender() == Message.Contact.SIDDHESH) {
            hbox.setAlignment(Pos.TOP_RIGHT);
            profile.toFront();
        } else {
            hbox.setAlignment(Pos.TOP_LEFT);
            profile.toBack();
        }
        ListView<Message> listView = getListView();
        if (listView != null) {
            textFlow.maxWidthProperty().bind(getListView().widthProperty().multiply(0.7));
//            root.maxWidthProperty().bind(listView.widthProperty().multiply(0.9));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loaded = true;
    }

}
