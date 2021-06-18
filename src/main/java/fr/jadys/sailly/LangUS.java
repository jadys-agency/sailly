package fr.jadys.sailly;

import java.util.List;

public class LangUS implements ILang {

    @Override
    public List<String> getRegex() {
        return List.of("^[NRKQPB]$", "^[NRKQPB][0-8]$", "^[NRKQPB][abcdefgh]$", "^[NRKQPB]([abcdefgh]|[0-8])?x$", "^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh]$", "^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]$", "^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]\\+$", "^[NRKQPB]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]\\+?#$", "^(O - O)|(O - O - O)$");
    }

    @Override
    public String getKingLetter() {
        return "K";
    }

    @Override
    public String getQueenLetter() {
        return "Q";
    }

    @Override
    public String getRookLetter() {
        return "R";
    }

    @Override
    public String getBishopLetter() {
        return "B";
    }

    @Override
    public String getKnightLetter() {
        return "N";
    }

    @Override
    public String getPawnLetter() {
        return "P";
    }

}
