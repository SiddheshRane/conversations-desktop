package conversations.im;

import conversations.im.banner.BannerController;
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
public class MainController implements Initializable {

    @FXML
    BannerController bannerController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (bannerController == null) {
            System.out.println("It is null");
        }
        else System.out.println("it is not null");
        
    }

}
