package ir.app.language.domain;

import java.util.ArrayList;

public class Word {
    private String woord;
    private String niveau = "";

    public Word(String woord) {
        this.setWoord(woord);
    }

    public Word(String woord, String niveau) {
        this.setWoord(woord);
        this.setNiveau(niveau);
    }

    public String getWoord() {
        return this.woord;
    }

    public String getNiveau() {
        return this.niveau;
    }

    public int getLengte() {
        return this.woord.length();
    }

    public void setWoord(String woord) {
        if (woord == null || woord.trim().isEmpty()) {
            throw new DomainException("Het woord mag niet leeg zijn.");
        }
        this.woord = woord;
    }

    public void setNiveau(String niveau) {
        if (niveau.equals("beginner")  || niveau.equals("expert") || niveau.isEmpty()) {
            this.niveau = niveau;
        }
        else {
            throw new DomainException("Het niveau mag enkel leeg, 'beginner' of 'expert' zijn.");
        }
    }

    public int getAantalVerschillendeLetters() {
        String s = this.getWoord().trim();
        ArrayList<String> tekens = new ArrayList<String>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            String sb = Character.toString(s.charAt(i));
            if (!tekens.contains(sb)) {
                tekens.add(sb);
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word woord = (Word) obj;
            // Een woord mag niet 2 keer in de lijst voorkomen want dan kan je
            // "hangman expert" en "hangman beginner" hebben, dus enkel op woord checken niet op woord en niveau
            if (this.getWoord().equals(woord.getWoord())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Woord: " + this.getWoord() + (this.niveau == null ? ", geen niveau." : ", niveau: " + this.getNiveau() + ".");
    }
}