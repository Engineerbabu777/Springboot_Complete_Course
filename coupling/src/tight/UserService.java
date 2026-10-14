package tight;

public class UserService {

   NotificationService notifcationService = new NotificationService();

   public void notiyUser(String message){
       notifcationService.send("Notification hello");
   }
}
