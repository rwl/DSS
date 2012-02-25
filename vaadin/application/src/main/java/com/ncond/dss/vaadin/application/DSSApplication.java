package com.ncond.dss.vaadin.application;

import org.vaadin.console.Console;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DSSApplication extends com.vaadin.Application {

    Window main = new Window("Distribution System Simulator");

    // Create a console
    final Console console = new Console();

    @Override
    public void init() {
        setMainWindow(main);

        // Handler for the console
        main.addComponent(console);

        VerticalLayout vl = (VerticalLayout) main.getContent();
        vl.setComponentAlignment(console, Alignment.TOP_CENTER);

        // Size, greeting and other configuration
        console.setPs(">> ");
        console.setCols(120);
        console.setRows(36);
        console.setMaxBufferSize(50);
        console.setGreeting("Welcome to DSS.");
        console.reset();
        console.focus();

        setTheme("dss");
    }
}
