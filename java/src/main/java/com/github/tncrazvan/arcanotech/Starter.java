package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcanotech.controller.App;
import com.github.tncrazvan.arcanotech.controller.Documentation;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class Starter extends HttpController{
    
    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, URISyntaxException {
        new Arcano(
            args,
            App.class,
            Documentation.class,
            Starter.class
        );
    }
}