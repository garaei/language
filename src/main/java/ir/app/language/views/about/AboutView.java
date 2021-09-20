package ir.app.language.views.about;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import ir.app.language.views.MainLayout;

@PageTitle("About")
@Route(value = AboutView.ROUTE, layout = MainLayout.class)
public class AboutView extends VerticalLayout implements AfterNavigationObserver {
    public static final String ROUTE = "about";
    public static final String TITLE = "About Us";
    public static final String SLOGAN = "Over ons, About us,";

    public AboutView() {
        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        MainLayout.appName.setText(SLOGAN);
    }
}
