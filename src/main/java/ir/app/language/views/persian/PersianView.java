package ir.app.language.views.persian;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ir.app.language.views.MainLayout;


@PageTitle("سلام کلمات ، فارسی بیاموزید")
@Route(value = PersianView.ROUTE, layout = MainLayout.class)
public class PersianView extends VerticalLayout implements AfterNavigationObserver {
    public static final String TITLE = "فارسی";
    public static final String ROUTE = "persian";
    public static final String SLOGAN = "سلام کلمات ، فارسی بیاموزید";

    public PersianView() {
        add(new Paragraph(SLOGAN));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        MainLayout.appName.setText(SLOGAN);
    }
}
