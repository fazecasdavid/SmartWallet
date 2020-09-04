package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller {

    private Service service;

    @FXML TextField idTextField;
    @FXML TextField nameTextField;
    @FXML TextField balanceTextField;

    @FXML TableView<Card> allCardsTableView;
    @FXML TableColumn<Card, String> idColumn;
    @FXML TableColumn<Card, String> nameColumn;
    @FXML TableColumn<Card, Integer> balanceColumn;

    public void setService(Service service) {
        this.service = service;
    }

    public void addCard() {
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String balance = balanceTextField.getText();

        if (!Utils.isInteger(balance)) {
            displayMessage("Exception occurred", "Balance must be a number");
            return;
        }

        try {
            service.addCard(id, name, Integer.parseInt(balance));
        } catch (IllegalArgumentException e) {
            displayMessage("Exception", e.getMessage());
        }

        updateTableView();
    }

    public void removeCard() {
        Card selectedCard = getSelectedCardTableView();
        if (selectedCard == null) {
            displayMessage("Exception occurred", "Please select a Card");
            return;
        }

        try {
            service.removeCardById(selectedCard.getId());
        } catch (IllegalArgumentException e) {
            displayMessage("Exception", e.getMessage());
        }

        updateTableView();
    }

    public void changeSceneToBuy(ActionEvent event) throws IOException {

        Card card = getSelectedCardTableView();
        if(card == null){
            displayMessage("Exception", "You must select a Card");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buy.fxml"));
        Parent root = loader.load();
        BuyController buyController = loader.getController();
        buyController.setCard(card);
        buyController.setService(service);
        buyController.init();

        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setScene(scene);
        window.setOnCloseRequest( e -> {updateTableView();} );

        window.show();
    }

    public static void displayMessage(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(80);

        Label label = new Label();
        label.setText(message);

        VBox layout = new VBox(40);
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    private Card getSelectedCardTableView() {
        return allCardsTableView.getSelectionModel().getSelectedItem();
    }

    public ObservableList<Card> getCardsObsList() {
        ObservableList<Card> cardsObsList = FXCollections.observableArrayList();
        var allCards = service.getAllCards();
        cardsObsList.addAll(allCards);

        return cardsObsList;
    }

    private void updateTableView() {
        allCardsTableView.getItems().clear();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        allCardsTableView.getItems().addAll(getCardsObsList());
    }

    public void populateTextFields() {
        Card selectedCard = getSelectedCardTableView();
        if (selectedCard == null) {
            idTextField.clear();
            nameTextField.clear();
            balanceTextField.clear();
        } else {
            idTextField.setText(selectedCard.getId());
            nameTextField.setText(selectedCard.getName());
            balanceTextField.setText(Integer.toString(selectedCard.getBalance()));
        }
    }
}
