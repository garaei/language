package ir.app.language.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class Words {

    public ArrayList<String> woorden = new ArrayList<String>();
    public ArrayList<String> vertaling = new ArrayList<String>();
    public ArrayList<Integer> gebruikt = new ArrayList<Integer>();
    public String separator = ";";
    public int index;
    public boolean AtoB = true; // Translation a -> b or a <- b
    // Follow list order or show words in random order?
    public boolean randomWoordjes = false;
    public String msg = "";
    Random r = new Random();
    String os = System.getProperty("os.name");
    String settingsPath, lang1, lang2;
    String wordlistPath;
    String tmpFileName = "woordjes";
    File wordlist; // The list with the words
    File Settings;

    public Words() {
        /*
         * WARNING: This are System specific settings
         */
        // Check for OS
       /* if (os.equalsIgnoreCase("Mac OS X")) {
        settingsPath = "/Library/Preferences/woordjes.settings.wrd";
        wordlistPath = "/Library/Preferences/woordjes.list.wrd";
        }
        // Check for Windows
        if (os.equalsIgnoreCase("Windows XP")) {
        settingsPath = "/woordjes.settings.wrd";
        wordlistPath = "/woordjes.list.wrd";
        } else {*/
        // TODO: implement other oses
        settingsPath = "woordjes.settings.wrd";
        // Linux can't write "/" if user isn't root.
        wordlistPath = "woordjes.list.wrd";
        // = most of the time. So don't use "/"
        // }

        getSettings();
    }

    private void createTmpWordList() {
        wordlist = new File(wordlistPath);
        try {
            wordlist.createNewFile();
        } catch (IOException e) {
            msg = msg.concat(java.util.ResourceBundle.getBundle("translations/Bundle").getString("IO EXCEPTION: ") + e + "\n");

        }
        // Write to temp file
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(wordlist));
            out.write("English ; Dutch\nDog ; Hond\nCat ; Kat");
            out.close();
        } catch (IOException e) {
            msg = msg.concat(java.util.ResourceBundle.getBundle("translations/Bundle").getString("IO EXCEPTION: ") + e + "\n");
        }
    }

    /*
     * TODO: Sadly there is no way to detect which characterset the inputFile is
     * using so only unicode (UTF-8) is supported which is the standard java
     * charset. All lists created within this program (woordjes.wrd) should be
     * valid. Read more about it here:
     * http://userguide.icu-project.org/conversion/detection#TOC-CharsetDetector
     * and here:
     * http://illegalargumentexception.blogspot.com/2009/05/java-rough-
     * guide-to-character-encoding.html
     */
    public void laadWoorden(File wordlist) throws UnsupportedEncodingException {

        int regel = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(wordlist), "UTF-8"));

            String s;

            Boolean firstLine = false;

            while ((s = in.readLine()) != null) {
                s = s.trim();

                regel++;
                // Check if every line has a translation
                if (!s.contains(separator)) {
                    msg = msg.concat("No translation found or wrong character used on rule number: "
                            + regel + "\n"); //Geen vertaling gevonden of een verkeerd teken gebruikt op regel:
                }
                // zorg ervoor dat accenten ook worden afgedrukt
                // String u = new String(s.getBytes(), "UTF-8"); Only works if
                // user uses a UTF-8 file. No way to see if this is the case
                // als je ook op komma wilt splits vervang [;] door [;,]
                String[] splits = s.split("[;]");

                // Strip the name of the languages
                if (!firstLine) {
                    firstLine = true;
                    lang1 = splits[0].trim();
                    lang2 = splits[1].trim();
                }

                if (AtoB) {
                    woorden.add(splits[0].trim());
                    vertaling.add(splits[1].trim());
                } else {
                    vertaling.add(splits[0].trim());
                    woorden.add(splits[1].trim());
                }
            }
            in.close();
        } catch (IOException e) {

            msg = msg.concat("IOException: " + e + "\n");
        } catch (Exception e) {
            msg = msg.concat("Exception: " + e + "\n");
        }

        // Strip the name of the languages
        woorden.remove(0);
        vertaling.remove(0);

        if (!randomWoordjes) {
            for (int i = 0; i < woorden.size(); i++) {
                gebruikt.add(i);
            }
        }

        randomize();
    }

    public void setSettings(File aFile, String aContents)
            throws FileNotFoundException, IOException {

        if (!aFile.exists()) {
            // No settingsfile found. Create one.
            Writer output = new BufferedWriter(new FileWriter(aFile));
            try {
                output.write("");
            } catch (IOException e) {
            } finally {
                output.close();
            }
        }

        if (aFile == null) {
            throw new IllegalArgumentException("  File should not be null.");
        }
        if (!aFile.exists()) {

            throw new FileNotFoundException(" File does not exist: " + aFile);

        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("  Should not be a directory: "
                    + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("  File cannot be written: "
                    + aFile);
        }

        // use buffering
        Writer output = new BufferedWriter(new FileWriter(aFile));
        try {
            // FileWriter always assumes default encoding is OK!
            output.write(aContents);
        } finally {
            output.close();
        }
    }

    private void getSettings() {

        Settings = new File(settingsPath);

        if (!Settings.exists()) {
            // Settings file not found. Create one.
            try {
                // Add the path for the new wordlist into the settingsfile
                setSettings(Settings, wordlistPath);
            } catch (FileNotFoundException e) {
                msg = msg.concat("File Not Found Exception: " + e + "\n");
            } catch (IOException e) {
                msg = msg.concat("IO Exception: " + e + "\n");
            }
            // Create a wordlist
            createTmpWordList();
            // Try again
            getSettings();
        } else {
            // Settings file found. Try to read it.
            try {
                BufferedReader in = new BufferedReader(new FileReader(Settings));
                String str;
                while ((str = in.readLine()) != null) {
                    // zorg ervoor dat accenten ook worden afgedrukt
                    String UTF8str = new String(str.getBytes(), "UTF-8");
                    wordlist = new File(UTF8str);
                    if (wordlist.exists()) {
                        // The path inside the settings does exists
                        laadWoorden(wordlist);
                    } else {
                        // The path inside the settings file doesn't exists
                        // so we create a new settings file
                        setSettings(Settings, wordlistPath);
                        // and create a wordlist file to
                        createTmpWordList();
                        // And try again
                        getSettings();
                    }
                }
                in.close();
            } catch (IOException e) {
                msg = msg.concat("IO Exception: " + e + "\n");

            }

        }
    }

    public int randomize() {
        if (randomWoordjes) {
            // Gebruikt is empty (initial)
            if (gebruikt.isEmpty()) {
                index = r.nextInt(woorden.size());
                gebruikt.add(index);
            } else {
                int prop;
                Boolean used = false;
                for (int i = 0; !used; i++) {
                    // Do a proposal
                    prop = r.nextInt(woorden.size());
                    // Check if proposal is already used
                    if (!gebruikt.contains(prop)) {
                        index = prop;
                        gebruikt.add(index);
                        used = true;
                    }
                }
            }
        } else {
            if (!gebruikt.isEmpty()) {
                index = gebruikt.get(0);
                gebruikt.remove(0);
            }
        }
        return index;
    }

    public String getOs() {
        return os;
    }

    public int getIndex() {
        return index;
    }

    public String getWoord() {
        return woorden.get(index);
    }

    public String getVertaling() {
        return vertaling.get(index);
    }

    public Boolean getAtoB() {
        return AtoB;
    }

    @Override
    public String toString() {
        return "Woordjes: " + msg;
    }

    public String getMessage() {
        return msg;
    }
}
