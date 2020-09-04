package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class BuyController {

    private Card card;
    private Service service;
    @FXML Label nameLabel;
    @FXML Label idLabel;
    @FXML Label balanceLabel;
    @FXML TextField amountTextField;

    public void init() {
        nameLabel.setText(card.getName());
        idLabel.setText(card.getId());
        balanceLabel.setText(Integer.toString(card.getBalance()));
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void withdrawHandler() {
        String amountStr = amountTextField.getText();
        if(!Utils.isInteger(amountStr)) {
            Controller.displayMessage("Exception", "Not integer");
            return;
        }
        int amount = Integer.parseInt(amountStr);
        if(amount < 0){
            Controller.displayMessage("Exception", "Sorry");
            return;
        }

        try{
            service.withdrawFromCard(card, amount);
        }
        catch (IllegalArgumentException e){
            Controller.displayMessage("Exception", "Sorry");
            return;
        }
        balanceLabel.setText(Integer.toString(card.getBalance()));

    }
    public void depositHandler() {
        String amountStr = amountTextField.getText();
        if(!Utils.isInteger(amountStr)) {
            Controller.displayMessage("Exception", "Not integer");
            return;
        }
        int amount = Integer.parseInt(amountStr);
        if(amount < 0){
            Controller.displayMessage("Exception", "Sorry bro");
            return;
        }

        try{
            service.depositToCard(card, amount);
        }
        catch (IllegalArgumentException e){
            Controller.displayMessage("Exception", "Dont use negative numbers");
            return;
        }
        balanceLabel.setText(Integer.toString(card.getBalance()));
    }
}
