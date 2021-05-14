package fr.jadys.sailly;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    JFXButton buttonX, buttonTrash;
    @FXML
    JFXTextArea textMoves;
    @FXML
    Label labelMove;

    private boolean eraseLabelMove = false;

    @FXML
    public void initialize() {
        buttonKnight.setOnAction(event -> placePiece("N"));
        buttonRook.setOnAction(event -> placePiece("R"));
        buttonKing.setOnAction(event -> placePiece("K"));
        buttonQueen.setOnAction(event -> placePiece("Q"));
        buttonPawn.setOnAction(event -> placePiece("P"));
        buttonBishop.setOnAction(event -> placePiece("B"));

        buttonOne.setOnAction(event -> appendToLabelMove("1"));
        buttonTwo.setOnAction(event -> appendToLabelMove("2"));
        buttonThree.setOnAction(event -> appendToLabelMove("3"));
        buttonFour.setOnAction(event -> appendToLabelMove("4"));
        buttonFive.setOnAction(event -> appendToLabelMove("5"));
        buttonSix.setOnAction(event -> appendToLabelMove("6"));
        buttonSeven.setOnAction(event -> appendToLabelMove("7"));
        buttonEight.setOnAction(event -> appendToLabelMove("8"));

        buttonA.setOnAction(event -> appendToLabelMove("a"));
        buttonB.setOnAction(event -> appendToLabelMove("b"));
        buttonC.setOnAction(event -> appendToLabelMove("c"));
        buttonD.setOnAction(event -> appendToLabelMove("d"));
        buttonE.setOnAction(event -> appendToLabelMove("e"));
        buttonF.setOnAction(event -> appendToLabelMove("f"));
        buttonG.setOnAction(event -> appendToLabelMove("g"));
        buttonH.setOnAction(event -> appendToLabelMove("h"));

        buttonX.setOnAction(event -> appendToLabelMove("x"));
        buttonTrash.setOnAction(event -> {
            labelMove.setText("");
            this.disableAllButtons();
            this.enablePieceButtons(true);
        });

        this.disableAllButtons();
        this.enablePieceButtons(true);

        labelMove.textProperty().addListener((observable, oldValue, newValue) -> {
            Pattern p = Pattern.compile("^[NRKQPB]$");
            Matcher m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enableColumnButtons(true);
                this.enableRowButtons(true);
                this.buttonX.setDisable(false);
                this.buttonTrash.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB][0-8]$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enableColumnButtons(true);
                this.buttonX.setDisable(false);
                this.buttonTrash.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB][abcdefgh]$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enableColumnButtons(true);
                this.enableRowButtons(true);
                this.buttonX.setDisable(false);
                this.buttonTrash.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB]([abcdefgh]|[0-8])?x$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enableColumnButtons(true);
                this.buttonTrash.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh]$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enableRowButtons(true);
                this.buttonTrash.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]$");
            m = p.matcher(newValue);
            if (m.matches()) {
                appendToTextMoves(this.labelMove.getText());
                this.disableAllButtons();
                this.enablePieceButtons(true);
                eraseLabelMove = true;
            }
        });
    }

    private void placePiece(String piece) {
        if (eraseLabelMove) {
            this.labelMove.setText("");
            eraseLabelMove = false;
        }
        appendToLabelMove(piece);
    }

    private void appendToLabelMove(String value) {
        labelMove.setText(labelMove.getText() + value);
    }

    private void appendToTextMoves(String value) {
        if ( textMoves.getText().isEmpty() ) {
            textMoves.setText(value);
        } else {
            textMoves.setText(textMoves.getText() + "\n" + value);
        }
    }

    private void enableColumnButtons(boolean enabled) {
        buttonA.setDisable(!enabled);
        buttonB.setDisable(!enabled);
        buttonC.setDisable(!enabled);
        buttonD.setDisable(!enabled);
        buttonE.setDisable(!enabled);
        buttonF.setDisable(!enabled);
        buttonG.setDisable(!enabled);
        buttonH.setDisable(!enabled);
    }

    private void enableRowButtons(boolean enabled) {
        buttonOne.setDisable(!enabled);
        buttonTwo.setDisable(!enabled);
        buttonThree.setDisable(!enabled);
        buttonFour.setDisable(!enabled);
        buttonFive.setDisable(!enabled);
        buttonSix.setDisable(!enabled);
        buttonSeven.setDisable(!enabled);
        buttonEight.setDisable(!enabled);
    }

    private void enablePieceButtons(boolean enabled) {
        buttonPawn.setDisable(!enabled);
        buttonBishop.setDisable(!enabled);
        buttonQueen.setDisable(!enabled);
        buttonKing.setDisable(!enabled);
        buttonKnight.setDisable(!enabled);
        buttonRook.setDisable(!enabled);
    }

    private void enableCastlingButtons(boolean enabled) {
        buttonSmallCastling.setDisable(!enabled);
        buttonLargeCastling.setDisable(!enabled);
    }

    private void disableAllButtons() {
        enableCastlingButtons(false);
        enablePieceButtons(false);
        enableRowButtons(false);
        enableColumnButtons(false);
        buttonX.setDisable(true);
        buttonTrash.setDisable(true);
    }

    private void enableAllButtons() {
        enableCastlingButtons(false);
        enablePieceButtons(false);
        enableRowButtons(false);
        enableColumnButtons(false);
        buttonX.setDisable(true);
        buttonTrash.setDisable(true);
    }

}
