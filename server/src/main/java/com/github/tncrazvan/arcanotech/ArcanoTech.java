/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Server;
import com.github.tncrazvan.arcanotech.controllers.App;
import com.github.tncrazvan.arcanotech.controllers.Documentation;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Administrator
 */
public class ArcanoTech extends Server{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, URISyntaxException {
        ArcanoTech server = new ArcanoTech();
        server.expose(
            App.class,
            Documentation.class
        );
        server.listen(args);
    }
}
