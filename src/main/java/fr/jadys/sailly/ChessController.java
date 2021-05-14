package fr.jadys.sailly;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChessController {

    @FXML
    JFXButton buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH;
    @FXML
    JFXButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight;
    @FXML
    JFXButton buttonPawn, buttonBishop, buttonKnight, buttonRook, buttonQueen, buttonKing;
    @FXML
    JFXButton buttonSmallCastling, buttonLargeCastling;
    @FXML
    JFXTextArea textMoves;
    @FXML
    Label labelMove;

    @FXML
    public void initialize() {

    }

}
