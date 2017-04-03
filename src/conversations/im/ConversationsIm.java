package conversations.im;

import com.jfoenix.controls.JFXDrawer;
import conversations.im.banner.BannerController;
import conversations.im.chat.ChatViewController;
import conversations.im.settings.SettingsController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 *
 * @author Siddhesh Rane
 */
public class ConversationsIm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = jfxMain();
        Scene scene = new Scene(root);
        scene.getStylesheets().setAll("/conversations/im/main.css");
        stage.setScene(scene);
        stage.show();
        ScenicView.show(scene);
    }

    private Pane javaFxDemo() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        return root;
    }

    private Pane jfxMain() {
        FXMLLoader bannerLoader = new FXMLLoader(BannerController.class.getResource("banner.fxml"));
        FXMLLoader chatViewLoader = new FXMLLoader(ChatViewController.class.getResource("ChatView.fxml"));
        FXMLLoader settingsLoader = new FXMLLoader(SettingsController.class.getResource("settings.fxml"));
        
        Node banner = new Text("Banner not loaded"), chatView=new Text("chatView not loaded");
        Node settings = new Text("Settings not loaded");
        try {
            banner = bannerLoader.load();
            chatView = chatViewLoader.load();
            settings = settingsLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ConversationsIm.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFXDrawer drawer = new JFXDrawer();
        drawer.setSidePane(settings);
        drawer.setContent(chatView);
        drawer.setDirection(JFXDrawer.DrawerDirection.TOP);
        drawer.setResizableOnDrag(true);
        drawer.open();
        BorderPane borderPane = new BorderPane(drawer);
        borderPane.setTop(banner);
        banner.setOnSwipeDown(se->{drawer.open();});
        
        EventHandler<MouseEvent> openDrawer = new EventHandler<MouseEvent>() {
            double  x,y;
            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    x= event.getX();
                    y=event.getY();
                }else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    if (event.getY() - y > 30 && drawer.isHidden()) {
                        drawer.open();
                    }
                }
            }
        };
        banner.addEventHandler(MouseEvent.MOUSE_DRAGGED, openDrawer);
        banner.addEventHandler(MouseEvent.MOUSE_PRESSED, openDrawer);
        return borderPane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
