package ir.app.language.views.spanish;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ir.app.language.views.MainLayout;

@PageTitle("Hola palabras, aprende español")
@Route(value = SpanishView.ROUTE, layout = MainLayout.class)
public class SpanishView extends VerticalLayout implements AfterNavigationObserver {
    public static final String ROUTE = "spanish";
    public static final String TITLE = "Spanish";
    public static final String SLOGAN = "Hola palabras, aprende español";

    public SpanishView() {

        TextField txtField = new TextField("Search a word", "Type a word");
        Button btnSearch = new Button("Search");
        btnSearch.addClickListener(buttonClickEvent -> {
            String word = txtField.getValue();
            StringBuilder scriptBuilder = new StringBuilder("");

            Page page = UI.getCurrent().getPage();
        });
        add(txtField, btnSearch);
        setFlexGrow(1, txtField);


    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        MainLayout.appName.setText(SLOGAN);
    }
}
