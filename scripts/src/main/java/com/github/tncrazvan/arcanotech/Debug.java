/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Server;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Administrator
 */
public class Debug extends Server{
    public static void main(String[] args) throws NoSuchAlgorithmException, ClassNotFoundException, IOException, URISyntaxException {
        Debug server = new Debug();
        server.listen(args);
    }
}
