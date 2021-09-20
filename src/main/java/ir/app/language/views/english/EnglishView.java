package ir.app.language.views.english;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ir.app.language.views.MainLayout;

@PageTitle("Hello Words, Learn English")
@Route(value = EnglishView.ROUTE, layout = MainLayout.class)
@JsModule(value = "https://cdnjs.cloudflare.com/ajax/libs/nlp_compromise/6.5.3/nlp_compromise.min.js")
public class EnglishView extends VerticalLayout implements AfterNavigationObserver {
    public static final String ROUTE = "english";
    public static final String TITLE = "English";
    public static final String SLOGAN = "Hello Words, Learn English";

    public EnglishView() {

        TextField txtField = new TextField("Search a word", "Type a word");
        Button btnSearch = new Button("Search");
        btnSearch.addClickListener(buttonClickEvent -> {
            String word = txtField.getValue();
            StringBuilder scriptBuilder = new StringBuilder("var nlp = window.nlp_compromise; ");
            scriptBuilder.append("The past tense of make is   nlp.verb(" + word + ").conjugate().past");

            Page page = UI.getCurrent().getPage();
            page.executeJs(scriptBuilder.toString());

            String init =
                    "const natural = require('natural');" +
                            "const wordnet = new natural.WordNet();";
            page.executeJs(init);

            // start using wordnet
            String findDefinition =
                    "wordnet.lookup(word, function(details) {\n" +
                            "    details.forEach(function(detail) {\n" +
                            "        console.log(\"------------------------------\");\n" +
                            "        console.log(\"Definition: \" + detail.def);\n" +
                            "        console.log(\"Synonyms: \" + detail.synonyms);\n" +
                            "        console.log(\"POS: \" + detail.pos);\n" +
                            "\n" +
                            "        // Display examples, if available\n" +
                            "        detail.exp.forEach(function(example) {\n" +
                            "            console.log(\"EXAMPLE: \" + example);\n" +
                            "        });\n" +
                            "        console.log(\"------------------------------\");\n" +
                            "    });\n" +
                            "});";
            page.executeJs(findDefinition);

        });
        add(txtField, btnSearch);
        setFlexGrow(1, txtField);


    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        MainLayout.appName.setText(SLOGAN);
    }
}
