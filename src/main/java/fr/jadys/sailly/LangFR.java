package fr.jadys.sailly;

import java.util.List;

public class LangFR implements ILang {

    @Override
    public List<String> getRegex() {
        return List.of("^[RDTFCP]$", "^[RDTFCP][0-8]$", "^[RDTFCP][abcdefgh]$", "^[RDTFCP]([abcdefgh]|[0-8])?x$", "^[RDTFCP]([abcdefgh]|[0-8])?x?[abcdefgh]$", "^[RDTFCP]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]$", "^[RDTFCP]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]\\+$", "^[RDTFCP]([abcdefgh]|[0-8])?x?[abcdefgh][0-8]\\+?#$", "^(O - O)|(O - O - O)$");
    }

    @Override
    public String getKingLetter() {
        return "R";
    }

    @Override
    public String getQueenLetter() {
        return "D";
    }

    @Override
    public String getRookLetter() {
        return "T";
    }

    @Override
    public String getBishopLetter() {
        return "F";
    }

    @Override
    public String getKnightLetter() {
        return "C";
    }

    @Override
    public String getPawnLetter() {
        return "P";
    }

}
