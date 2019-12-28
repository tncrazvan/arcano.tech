package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcanotech.controller.App;
import com.github.tncrazvan.arcanotech.controller.Documentation;

public class Starter{
    public static void main(final String[] args) {
        new Arcano(
            args,
            App.class,
            Documentation.class
        );
    }
}
