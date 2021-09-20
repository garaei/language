package ir.app.language.views.global;

import ir.app.language.domain.Word;

import java.util.Set;

public interface LanguageActionInterface extends GlobalActionInterface{
    Word searchOnlyOneWord(String word);

    Set<Word> searchRandomWords();

    void addRandomWordsPanel();
}
