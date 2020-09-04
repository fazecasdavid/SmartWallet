package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = mainLoader.load();


        // Create dependencies
        CardsRepository repository = new CardsRepository();
        Service service = new Service(repository);
        Controller controller = mainLoader.getController();
        controller.setService(service);


        Scene mainScene = new Scene(root);
        primaryStage.setTitle("Smart Wallet");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
