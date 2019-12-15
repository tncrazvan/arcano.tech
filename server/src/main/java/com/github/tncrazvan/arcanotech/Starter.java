package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Server;
import com.github.tncrazvan.arcanotech.controller.App;
import com.github.tncrazvan.arcanotech.controller.Documentation;

public class Starter{
    public static void main(final String[] args) {
        Server server = new Server(
            args,
            App.class,
            Documentation.class
        );
    }
}
