package ir.app.language.domain;


import java.util.ArrayList;
import java.util.List;

public class Woordenlijst {
    private List<Word> woorden =  new ArrayList<Word>();

    public Woordenlijst() {
        this.voegToe(new Word("hangman"));
        this.voegToe(new Word("wiel", "beginner"));
        this.voegToe(new Word("pneumonoultramicroscopicsilicovolcanoconiosis", "expert"));

    }

    public void voegToe(Word woord) {
        if (woord == null) {
            throw new DomainException("Het woord mag niet leeg zijn.");
        }
        if (this.woorden.contains(woord)) {
            this.vind(woord.getWoord()).setNiveau(woord.getNiveau());
        }
        else {
            this.woorden.add(woord);
        }
    }

    public Word vind(String woord) {
        if (woord == null || woord.trim().isEmpty()) {
            throw new DomainException("De naam mag niet leeg zijn.");
        }
        for (Word w : this.woorden) {
            if (w.getWoord().equals(woord)) {
                return w;
            }
        }
        return null;
    }

    public void verwijder(String woord) {
        this.woorden.remove(this.vind(woord));
    }

    public List<Word> getWoorden() {
        return this.woorden;
    }

    public int getAantalWoorden() {
        return this.woorden.size();
    }

    public String getLangsteWoord() {
        if (this.woorden.size() == 0) {
            return "De woordenlijst is nog leeg.";
        }
        int lengte = 0;
        Word result = null;
        for (Word w : woorden) {
            if (w.getLengte() > lengte) {
                lengte = w.getLengte();
                result = w;
            }
        }
        return result.getWoord();
    }

    public String getKortsteWoord() {
        if (this.woorden.size() == 0) {
            return "De woordenlijst is nog leeg.";
        }
        int lengte = this.woorden.get(0).getLengte();
        Word result = null;
        for (Word w : woorden) {
            if (w.getLengte() < lengte) {
                lengte = w.getLengte();
                result = w;
            }
        }
        return result.getWoord();
    }

    public float getGemiddeldAantalVerschillendeLetters() {
        int aantalWoorden = this.getAantalWoorden();
        int tekens = 0;
        for (Word w : this.woorden) {
            tekens += w.getAantalVerschillendeLetters();
        }
        float result = (float) tekens/ (float)aantalWoorden;
        return Math.round(result*100.0F)/100.0F;
    }

    public List<Word> getBeginner() {
        ArrayList<Word> result = new ArrayList<Word>();
        for (Word w : this.getWoorden()) {
            if (w.getNiveau() != null && w.getNiveau().equals("beginner")) {
                result.add(w);
            }
        }
        return result;
    }

    public List<Word> getExpert() {
        ArrayList<Word> result = new ArrayList<Word>();
        for (Word w : this.getWoorden()) {
            if (w.getNiveau() != null && w.getNiveau().equals("expert")) {
                result.add(w);
            }
        }
        return result;
    }
}
