package fr.jadys.sailly;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Setter;

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
    @FXML
    JFXButton buttonClose;
    @FXML
    JFXButton buttonLock;
    @FXML
    Pane paneMoveWindow;
    @FXML
    JFXButton buttonCheckMate, buttonChess;
    @Setter
    private Stage stage;
    private double xOffset;
    private double yOffset;

    private boolean eraseLabelMove = false;

    @FXML
    public void initialize() {
        setActions();
        setRegex();
        handleWindowMove();

        this.buttonClose.setOnAction(event -> Platform.exit());

        this.disableAllButtons();
        this.enablePieceButtons(true);
        this.buttonLargeCastling.setDisable(false);
        this.buttonSmallCastling.setDisable(false);
    }

    private void handleWindowMove() {
        paneMoveWindow.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        paneMoveWindow.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    private void setRegex() {
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
                this.buttonLargeCastling.setDisable(false);
                this.buttonSmallCastling.setDisable(false);
                return;
            }

            p = Pattern.compile("^(O - O)|(O - O - O)$");
            m = p.matcher(newValue);
            if (m.matches()) {
                appendToTextMoves(this.labelMove.getText());
                this.disableAllButtons();
                this.enablePieceButtons(true);
                eraseLabelMove = true;
                this.buttonLargeCastling.setDisable(false);
                this.buttonSmallCastling.setDisable(false);
                return;
            }
        });
    }

    private void setActions() {
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

        buttonChess.setOnAction(event -> {
            appendToLabelMove("+");
            this.textMoves.setText(this.textMoves.getText().substring(0, this.textMoves.getLength()) + "+");
        });

        buttonCheckMate.setOnAction(event -> {
            appendToLabelMove("#");
            this.textMoves.setText(this.textMoves.getText().substring(0, this.textMoves.getLength()) + "#");
        });

        buttonSmallCastling.setOnAction(event -> {
            labelMove.setText("");
            appendToLabelMove("O - O");
            this.disableAllButtons();
            this.enablePieceButtons(true);
            this.buttonLargeCastling.setDisable(false);
            this.buttonSmallCastling.setDisable(false);

        });

        buttonLargeCastling.setOnAction(event -> {
            labelMove.setText("");
            appendToLabelMove("O - O - O");
            this.disableAllButtons();
            this.enablePieceButtons(true);
            this.buttonLargeCastling.setDisable(false);
            this.buttonSmallCastling.setDisable(false);
        });

        buttonX.setOnAction(event -> appendToLabelMove("x"));
        buttonTrash.setOnAction(event -> {
            labelMove.setText("");
            this.disableAllButtons();
            this.enablePieceButtons(true);
        });

        buttonLock.setOnAction(event -> {
            if (stage.isAlwaysOnTop()) {
                stage.setAlwaysOnTop(false);
                buttonLock.getStyleClass().remove("lock");
                buttonLock.getStyleClass().add("unlock");
            } else {
                stage.setAlwaysOnTop(true);
                buttonLock.getStyleClass().remove("unlock");
                buttonLock.getStyleClass().add("lock");
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
        if (textMoves.getText().isEmpty()) {
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
