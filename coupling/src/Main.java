import loose.EmailNotification;
import loose.NotificationService;
import loose.SMSNotificationService;
import loose.UserService;

public class Main {
    public static void main(String[] args){
        // tight
//        NotificationService userService = new UserService();
//        userService.send("Order Place");

        //loose
        NotificationService smsNotificationService = new SMSNotificationService();
        UserService userServiceLoose = new UserService(smsNotificationService);
        userServiceLoose.send("What?");
    }
}
