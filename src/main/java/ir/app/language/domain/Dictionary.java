package ir.app.language.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by ali on 14/09/2021
 * utitlity stuff
 */
public class Dictionary {

    private final HashMap<String, ArrayList<String>> wordLists = new HashMap<>(26);
    private final HashMap<String, ArrayList<String>> filteredWordLists = new HashMap<>(26);
    private String avalableChars = "";

    private ArrayList<String> getWordListFromFile(String file){
        if(wordLists.containsKey(file)){
            return wordLists.get(file);
        }

        ArrayList<String> words = new ArrayList<>();
        Scanner c = new Scanner(WoordJachtFixer.class.getResourceAsStream(file));
        while(c.hasNextLine()){
            words.add(c.nextLine());
        }
        c.close();

        wordLists.put(file, words);

        return words;
    }

    public ArrayList<String> getFilteredWordList(char c){
        if(!filteredWordLists.containsKey(""+c)) {
            ArrayList<String> words = getWordListFromFile(c + "Words.txt");
            words = filterWithStart(words, "" + c);
            filteredWordLists.put("" + c, words);

        }
        return filteredWordLists.get(""+c);
    }

    private ArrayList<String> filterWithStart(ArrayList<String> words, String start){
        String regex = "".concat("[").concat(avalableChars).concat("]");
        ArrayList<String> toRemove = new ArrayList<>();

        for (String woord : words) {
            if (!woord.startsWith(start) || !woord.matches(regex)) {
                toRemove.add(woord);
            }
        }
        words.removeAll(toRemove);
        return words;
    }

    public void setAvalableChars(String avalableChars) {
        if(this.avalableChars.equals(avalableChars)){
            return;
        }
        this.avalableChars = avalableChars;
        filteredWordLists.clear();
    }
}