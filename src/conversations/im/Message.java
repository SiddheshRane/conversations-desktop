package conversations.im;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *
 * @author Siddhesh Rane
 */
public class Message {

    public static final Message EMPTY = new Message();
    
    Contact sender;
    Date sentTime;
    String content;

    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private static final String MESSAGES[] = {
        "Hi", "Hello", "Lol!", "zzz...", "Hey, Hows life", "Bye", "See you tomorrow",
        "This one is a particularly long message, not because it was purposefully contrived to be, in which situation it will astray the reader from the original message but I hope you haent lost track, yet, but it is long because I need to test somthing"
    };

    public enum Contact {
        SIDDHESH("Siddhesh Rane", "images/profile-thumb.jpg"),
        CHINU("Chinu", "images/chinu.png"),
        LIAM("Liam D'Souza", "images/chinu.png");
        private String name;
        private Image profile;

         Contact(String name, String url) {
            this.name = name;
            profile = new Image(Message.class.getResourceAsStream(url));
        }

        public String getName() {
            return name;
        }

        public Image getProfile() {
            return profile;
        }
         
    };
    
    public static ObservableList<Message> getRandom(int num) {
        if (num < 0) {
            num = 0;
        }
        if (num == 0) {
            return FXCollections.emptyObservableList();
        }
        Random random = new Random();
        ObservableList<Message> list = FXCollections.observableArrayList();
        for (int i = 0; i < num; i++) {
            Message message = new Message();
            message.setContent(MESSAGES[random.nextInt(MESSAGES.length)]);
            message.setSender(Contact.values()[random.nextInt(Contact.values().length)]);
            message.setSentTime(new GregorianCalendar(2017, random.nextInt(12), random.nextInt(30), random.nextInt(24), random.nextInt(60)).getTime());
            list.add(message);
        }
        return list;
    }

    @Override
    public String toString() {
        return "From:"+getSender().name()+" Date:"+getSentTime()+" "+getContent();
    }
    
    
}
