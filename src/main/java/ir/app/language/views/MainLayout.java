package ir.app.language.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import ir.app.language.views.about.AboutView;
import ir.app.language.views.dutch.DutchView;
import ir.app.language.views.english.EnglishView;
import ir.app.language.views.french.FrenchView;
import ir.app.language.views.persian.PersianView;
import ir.app.language.views.spanish.SpanishView;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view is a top-level placeholder for other views.
 */
@PageTitle("Main")
public class MainLayout extends AppLayout {
    public static H1 appName;

    public static class MenuItemInfo {

        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    public MainLayout() {
        addToNavbar(createHeaderContent());
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "flex-col", "w-full");

        Div layout = new Div();
        layout.addClassNames("flex", "h-xl", "items-center", "px-l");

        appName = new H1("Online Taaltraining Centrum");
        appName.addClassNames("my-0", "me-auto", "text-l");
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

        for (RouterLink link : createLinks()) {
            nav.add(link);
        }

        header.add(layout, nav);
        return header;
    }

    private List<RouterLink> createLinks() {
        MenuItemInfo dutch;
        MenuItemInfo english;
        MenuItemInfo persian;
        MenuItemInfo french;
        MenuItemInfo spanish;
        MenuItemInfo about;
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                dutch = new MenuItemInfo(DutchView.TITLE, "las la-dragon la-2x", DutchView.class), //
                english = new MenuItemInfo(EnglishView.TITLE, "las la-spider la-2x", EnglishView.class), //
                persian = new MenuItemInfo(PersianView.TITLE, "las la-dove la-2x", PersianView.class), //
                french = new MenuItemInfo(FrenchView.TITLE, "las la-otter la-2x", FrenchView.class), //
                spanish = new MenuItemInfo(SpanishView.TITLE, "las la-hippo la-2x", SpanishView.class), //

                about = new MenuItemInfo("About", "las la-hiking la-2x", AboutView.class), //

        };
        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "h-m", "items-center", "px-s", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());

        Span icon = new Span();
        icon.addClassNames("me-s", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s", "whitespace-nowrap");

        link.add(icon, text);
        return link;
    }

}
