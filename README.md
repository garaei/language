# Hallo word

This project can be used as a starting point to create your own Vaadin application with Spring Boot.
It contains all the necessary configuration and some placeholder files to get you started.

## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to set up a development environment for
Vaadin projects](https://vaadin.com/docs/latest/guide/install) (Windows, Linux, macOS).

## Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/hallowoord-1.0-SNAPSHOT.jar`

## Project structure

- `MainView.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training](https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/platform).

English references:

    // ref to read more about NLP: https://progur.com/2016/10/getting-started-with-nlp-compromise-js.html
    // ref to api: http://wordnetweb.princeton.edu/perl/webwn?s=Doodly&sub=Search+WordNet&o2=&o0=1&o8=1&o1=1&o7=&o5=&o9=&o6=&o3=&o4=&h=
    //ref: https://www.babelnet.org/

Nederlandse verwijzingen:

    // references
    //https://apps-gallery.toolforge.org/
    //https://maken.wikiwijs.nl/83178/Leerlijn_Woordenschat_hv12#!page-2308099

    // onderwijs
    //https://www.kennisnet.nl/edurep/edurep-widgets/edurep-wizard/
    //https://schooltv.nl/
    //http://taalunieversum.org/inhoud/leermiddelengids
    //https://taaluniebericht.org/haal-de-fout-uit-de-zin
    //https://synoniemen.net/uitleg.php#colofon
    //https://languagetoolplus.com/http-api/#/default


Icon reference:

    // https://www.angularjswiki.com/fontawesome/fa-glass-martini/
    // https://icons8.com/line-awesome
Ali's note:
https://dumps.wikimedia.org/enwiktionary/latest/
-----------------------------------------------------------
To keep it really simple, extract the words from the dump like this:
bzcat pages-articles.xml.bz2 | grep '<title>[^[:space:][:punct:]]*</title>' | sed 's:.*<title>\(.*\)</title>.*:\1:' > words
------------------------------------------------------------------------
