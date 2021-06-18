package fr.jadys.sailly;

import java.util.List;

public interface ILang {

    List<String> getRegex();
    String getKingLetter();
    String getQueenLetter();
    String getRookLetter();
    String getBishopLetter();
    String getKnightLetter();
    String getPawnLetter();

}
