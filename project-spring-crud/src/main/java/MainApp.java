import com.example.config.AppConfig;
import com.example.controller.UserController;
import com.example.db.DatabaseConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String ars[]){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserController userController = applicationContext.getBean(UserController.class);

        userController.createUser("Zain");
//        userController.createUser("Awais");
//        userController.createUser("Waqas");

        userController.listUsers();
    }
}
