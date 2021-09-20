package ir.app.language.domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WoordenTeller {
    private HashMap<String, Integer> lijst;
    private Scanner scanner;
    private String zin;

    // Maak een constructor die een zin inleest
    public WoordenTeller() {
        lijst = new HashMap<>();
        scanner = new Scanner(System.in);
        System.out.print("> ");
        zin = scanner.nextLine();
    }

    // Maak een methode die de woorden uit de zin haalt en in de HashMap zet.
    // Nakijken of het woord al voorkomt: indien wel, dan wordt de teller verhoogd.
    public void onderzoek() {
        String[] woorden = zin.split(" ");
        for (int i = 0; i < woorden.length; i++) {
            if (lijst.containsKey(woorden[i])) {    // woorden[i] bevat 1 element uit de array
                // het woord zit al in de lijst
                int waarde = lijst.get(woorden[i]); // welke waarde hoort nu bij dit woord(=key)
                waarde++;                           // waarde verhogen
                lijst.put(woorden[i], waarde);      // nieuwe waarde bij key zetten
            } else                  //het woord zit nog niet in de lijst
            {
                lijst.put(woorden[i], 1);
            }
        }
    }


    // Maak een methode die de inhoud van de HashMap toont
    public void printMap() {
        for (String woord : lijst.keySet()) {
            System.out.println(woord + " komt " + lijst.get(woord) + " voor.");
        }

    }

//    public String toString() {
//        return "lijst = " + lijst;
//    }

    // Maak een methode die de woord(en) toont die het meest voorkomen.
    // Plaats de woorden die het meest voorkomen in een ArrayList.
    // Verwijder deze woorden(entries) uit de Hashmap.
    public void meestVoorkomend() {
        ArrayList<String> woorden = new ArrayList<String>();
        int voorlopigMeeste = 0;      // eerst : wat is uw hoogste waarde?

        for (String woord : lijst.keySet()) {
            if (lijst.get(woord) > voorlopigMeeste) {
                voorlopigMeeste = lijst.get(woord);
                woorden.clear();
                woorden.add(woord);
            } else if (lijst.get(woord) == voorlopigMeeste) {
                woorden.add(woord);
            }
        }

        System.out.print("Meest voorkomende woorden " + "(" + voorlopigMeeste + " maal): ");
        for (String woord : woorden) {
            System.out.print(woord + " ");
            lijst.remove(woord);
        }
        System.out.println();
    }

    public static int countLetters(String woord, char letter){
        int teller = 0;

        for(int i = 0; i < woord.length(); i++){
            if(woord.charAt(i) == letter){
                teller++;
            }
        }
        return teller;
    }

    public static void main(String[] args) {
        WoordenTeller w = new WoordenTeller();
        w.onderzoek();
        w.printMap();
        w.meestVoorkomend();
        w.printMap();
    }
}


