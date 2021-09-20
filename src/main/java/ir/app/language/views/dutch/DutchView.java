package ir.app.language.views.dutch;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import ir.app.language.domain.Word;
import ir.app.language.views.MainLayout;
import ir.app.language.views.global.LanguageActionInterface;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@PageTitle("Hallo Woorden, leer Nederlands")
@Route(value = DutchView.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@CssImport(value = "./styles/willekeurigwoord.css")
public class DutchView extends VerticalLayout implements AfterNavigationObserver, LanguageActionInterface {
    public static final String ROUTE = "nederlands";
    public static final String TITLE = "Nederlands";
    public static final String SLOGAN = "Hallo Woorden, leer Nederlands";

    //wiktionary
    private static String WIKTIONARY_URL = "https://nl.wiktionary.org/w/index.php?title=";
    private static String WILLEKEURIG_1_URL = "https://nl.wiktionary.org/wiki/Speciaal:WillekeurigeUitCategorie/Woorden_in_het_Nederlands";
    private static String WOORDEN_BEGIN_URL = "https://nl.wiktionary.org/wiki/Speciaal:Voorvoegselindex";
    // perfix = begin letter en namespace is het type woord
    private static String WOORDEN_BEGIN_MET_URL = "https://nl.wiktionary.org/wiki/Speciaal:Voorvoegselindex?prefix=w&namespace=2";

    private static String WILLEKEURIG_10_URL = "https://handigetools.nl/willekeurige-woorden?";
    private static String WOORDEN_URL = "https://www.woorden.org/woord/woord";

    private TextField txtWord;
    private Button btnSearch;
    private Button btnRandom;
    private Set<Word> words;
    private Word currentWord;
    private Set<Word> currentRandomWords;
    private Set<Word> allRandomWords;
    private Div divRandomWords;

    public DutchView() {
        addClassName("dutch-view");
        currentRandomWords = new HashSet<>();
        allRandomWords = new HashSet<>();
        words = new HashSet<>();

        divRandomWords = new Div();
        txtWord = new TextField("","Typ een woord");
        btnSearch = new Button("Zoek", VaadinIcon.SEARCH.create());
        btnRandom = new Button("Willekeurig", VaadinIcon.RANDOM.create());

        HorizontalLayout searchPanel = new HorizontalLayout();
        searchPanel.add(txtWord,btnSearch,btnRandom);
        searchPanel.setFlexGrow(1, txtWord);
        searchPanel.setVerticalComponentAlignment(Alignment.CENTER);

        add(searchPanel, new Hr(),divRandomWords);
        setHorizontalComponentAlignment(Alignment.CENTER);

        btnRandom.addClickListener(e -> addRandomWordsPanel());
        btnSearch.addClickListener(event -> searchOnlyOneWord(txtWord.getValue()));
    }

    private void createDefinitionDialog(ClickEvent<Span> spanClickEvent, Word woord) {
        String word = woord.getWoord();
        spanClickEvent.getSource().addClassName("active");
        try {
            Document doc = Jsoup.connect(WIKTIONARY_URL.concat(word)).get();

            String docHtml = doc.html();

            Html html = new Html("<h2> woorden</h2>");
            IFrame iFrame = new IFrame(WIKTIONARY_URL.concat(word));
            iFrame.setSizeFull();

            Dialog dialog = new Dialog(iFrame);
            dialog.setSizeFull();
            dialog.setCloseOnOutsideClick(true);
            dialog.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        MainLayout.appName.setText(SLOGAN);
        addRandomWordsPanel();
    }

    @Override
    public void addRandomWordsPanel() {
        divRandomWords.removeAll();

        Div randomPanel = createRandomWordsPanel(searchRandomWords());
        divRandomWords.add(randomPanel);
    }

    public Div createRandomWordsPanel(Set<Word> words){
        H2 h2 = new H2("Woorden");
        h2.addClassName("hoofd");
        Div divWord = new Div();
        divWord.setClassName("woorden");

        Div divRechts = new Div(h2, divWord);
        divRechts.setClassName("rechts");

        words.forEach(word -> {
            Span span = new Span(word.getWoord());
            span.getStyle().set("cursor", "pointer");
            span.getStyle().set("title", "Kick om meer te zien");
            span.addClickListener(spanClickEvent -> createDefinitionDialog(spanClickEvent, word));
            divWord.add(span);
        });

        return divWord;
    }

    @Override
    public Set<Word> searchRandomWords() {
        currentRandomWords = new HashSet<>();
        int counter = 0;
        String url2 = WILLEKEURIG_10_URL + counter++;
        try {
            Document doc = Jsoup.connect(url2).get();
            Elements sections = doc.select("section");
            Element firstDiv = sections.first().children().select("div").get(4);

            Elements spans = firstDiv.getAllElements().select("span");
            spans.forEach(element -> {
                Word word = new Word(element.text());
                currentRandomWords.add(word);
            });

        } catch (HttpStatusException exception) {
            System.out.println(exception);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        allRandomWords.addAll(currentRandomWords);

        return currentRandomWords;
    }

    @Override
    public Word searchOnlyOneWord(String beSearchWord) {
        // todo: find the word

        Word word = new Word("beSearchWord");
        return word;
    }
}
