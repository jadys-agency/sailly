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
    private JFXButton buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH;
    @FXML
    private JFXButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight;
    @FXML
    private JFXButton buttonPawn, buttonBishop, buttonKnight, buttonRook, buttonQueen, buttonKing;
    @FXML
    private JFXButton buttonSmallCastling, buttonLargeCastling;
    @FXML
    private JFXButton buttonX, buttonTrash;
    @FXML
    private JFXButton buttonCheckMate, buttonChess;
    @FXML
    private JFXTextArea textMoves;
    @FXML
    private Label labelMove;
    @FXML
    private JFXButton buttonClose, buttonLock;
    @FXML
    private Pane paneMoveWindow;

    @Setter
    private Stage stage;
    private double xOffset;
    private double yOffset;

    private boolean eraseLabelMove = false;

    @FXML
    public void initialize() {
        this.setActions();
        this.setRegex();
        this.handleWindowMove();

        this.disableAllButtons();
        this.enablePieceButtons(true);
        this.buttonLargeCastling.setDisable(false);
        this.buttonSmallCastling.setDisable(false);
    }

    private void handleWindowMove() {
        this.paneMoveWindow.setOnMousePressed(event -> {
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });

        this.paneMoveWindow.setOnMouseDragged(event -> {
            this.stage.setX(event.getScreenX() - this.xOffset);
            this.stage.setY(event.getScreenY() - this.yOffset);
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
                this.appendToTextMoves(this.labelMove.getText());
                this.disableAllButtons();
                this.enablePieceButtons(true);
                this.buttonChess.setDisable(false);
                this.buttonCheckMate.setDisable(false);
                this.eraseLabelMove = true;
                this.buttonLargeCastling.setDisable(false);
                this.buttonSmallCastling.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]\\+$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enablePieceButtons(true);
                this.buttonCheckMate.setDisable(false);
                this.eraseLabelMove = true;
                this.buttonLargeCastling.setDisable(false);
                this.buttonSmallCastling.setDisable(false);
                return;
            }

            p = Pattern.compile("^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]\\+?#$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.disableAllButtons();
                this.enablePieceButtons(true);
                this.eraseLabelMove = true;
                this.buttonLargeCastling.setDisable(false);
                this.buttonSmallCastling.setDisable(false);
                return;
            }

            p = Pattern.compile("^(O - O)|(O - O - O)$");
            m = p.matcher(newValue);
            if (m.matches()) {
                this.appendToTextMoves(this.labelMove.getText());
                this.disableAllButtons();
                this.enablePieceButtons(true);
                this.eraseLabelMove = true;
                this.buttonLargeCastling.setDisable(false);
                this.buttonSmallCastling.setDisable(false);
                return;
            }
        });
    }

    private void setActions() {
        this.buttonKnight.setOnAction(event -> placePiece("N"));
        this.buttonRook.setOnAction(event -> placePiece("R"));
        this.buttonKing.setOnAction(event -> placePiece("K"));
        this.buttonQueen.setOnAction(event -> placePiece("Q"));
        this.buttonPawn.setOnAction(event -> placePiece("P"));
        this.buttonBishop.setOnAction(event -> placePiece("B"));

        this.buttonOne.setOnAction(event -> appendToLabelMove("1"));
        this.buttonTwo.setOnAction(event -> appendToLabelMove("2"));
        this.buttonThree.setOnAction(event -> appendToLabelMove("3"));
        this.buttonFour.setOnAction(event -> appendToLabelMove("4"));
        this.buttonFive.setOnAction(event -> appendToLabelMove("5"));
        this.buttonSix.setOnAction(event -> appendToLabelMove("6"));
        this.buttonSeven.setOnAction(event -> appendToLabelMove("7"));
        this.buttonEight.setOnAction(event -> appendToLabelMove("8"));

        this.buttonA.setOnAction(event -> appendToLabelMove("a"));
        this.buttonB.setOnAction(event -> appendToLabelMove("b"));
        this.buttonC.setOnAction(event -> appendToLabelMove("c"));
        this.buttonD.setOnAction(event -> appendToLabelMove("d"));
        this.buttonE.setOnAction(event -> appendToLabelMove("e"));
        this.buttonF.setOnAction(event -> appendToLabelMove("f"));
        this.buttonG.setOnAction(event -> appendToLabelMove("g"));
        this.buttonH.setOnAction(event -> appendToLabelMove("h"));

        this.buttonChess.setOnAction(event -> {
            this.appendToLabelMove("+");
            this.textMoves.setText(this.textMoves.getText().substring(0, this.textMoves.getLength()) + "+");
        });

        this.buttonCheckMate.setOnAction(event -> {
            this.appendToLabelMove("#");
            this.textMoves.setText(this.textMoves.getText().substring(0, this.textMoves.getLength()) + "#");
        });

        this.buttonSmallCastling.setOnAction(event -> {
            this.labelMove.setText("");
            this.appendToLabelMove("O - O");
            this.disableAllButtons();
            this.enablePieceButtons(true);
            this.buttonLargeCastling.setDisable(false);
            this.buttonSmallCastling.setDisable(false);

        });

        this.buttonLargeCastling.setOnAction(event -> {
            this.labelMove.setText("");
            this.appendToLabelMove("O - O - O");
            this.disableAllButtons();
            this.enablePieceButtons(true);
            this.enableCastlingButtons(false);
        });

        this.buttonX.setOnAction(event -> appendToLabelMove("x"));
        this.buttonTrash.setOnAction(event -> {
            this.labelMove.setText("");
            this.disableAllButtons();
            this.enablePieceButtons(true);
        });

        this.buttonLock.setOnAction(event -> {
            if (this.stage.isAlwaysOnTop()) {
                this.stage.setAlwaysOnTop(false);
                this.buttonLock.getStyleClass().remove("lock");
                this.buttonLock.getStyleClass().add("unlock");
            } else {
                this.stage.setAlwaysOnTop(true);
                this.buttonLock.getStyleClass().remove("unlock");
                this.buttonLock.getStyleClass().add("lock");
            }
        });

        this.buttonClose.setOnAction(event -> Platform.exit());
    }

    private void placePiece(String piece) {
        if (this.eraseLabelMove) {
            this.labelMove.setText("");
            this.eraseLabelMove = false;
        }
        this.appendToLabelMove(piece);
    }

    private void appendToLabelMove(String value) {
        this.labelMove.setText(this.labelMove.getText() + value);
    }

    private void appendToTextMoves(String value) {
        if (this.textMoves.getText().isEmpty()) {
            this.textMoves.setText(value);
        } else {
            this.textMoves.setText(this.textMoves.getText() + "\n" + value);
        }
    }

    private void enableColumnButtons(boolean enabled) {
        this.buttonA.setDisable(!enabled);
        this.buttonB.setDisable(!enabled);
        this.buttonC.setDisable(!enabled);
        this.buttonD.setDisable(!enabled);
        this.buttonE.setDisable(!enabled);
        this.buttonF.setDisable(!enabled);
        this.buttonG.setDisable(!enabled);
        this.buttonH.setDisable(!enabled);
    }

    private void enableRowButtons(boolean enabled) {
        this.buttonOne.setDisable(!enabled);
        this.buttonTwo.setDisable(!enabled);
        this.buttonThree.setDisable(!enabled);
        this.buttonFour.setDisable(!enabled);
        this.buttonFive.setDisable(!enabled);
        this.buttonSix.setDisable(!enabled);
        this.buttonSeven.setDisable(!enabled);
        this.buttonEight.setDisable(!enabled);
    }

    private void enablePieceButtons(boolean enabled) {
        this.buttonPawn.setDisable(!enabled);
        this.buttonBishop.setDisable(!enabled);
        this.buttonQueen.setDisable(!enabled);
        this.buttonKing.setDisable(!enabled);
        this.buttonKnight.setDisable(!enabled);
        this.buttonRook.setDisable(!enabled);
    }

    private void enableCastlingButtons(boolean enabled) {
        this.buttonSmallCastling.setDisable(!enabled);
        this.buttonLargeCastling.setDisable(!enabled);
    }

    private void disableAllButtons() {
        this.enableCastlingButtons(false);
        this.enablePieceButtons(false);
        this.enableRowButtons(false);
        this.enableColumnButtons(false);
        this.buttonX.setDisable(false);
        this.buttonTrash.setDisable(false);
        this.buttonCheckMate.setDisable(false);
        this.buttonChess.setDisable(false);
    }

}
