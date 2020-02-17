package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.Http.HttpController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class Starter extends HttpController{
    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, URISyntaxException, NoSuchMethodException, InstantiationException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Arcano server = new Arcano(Starter.class.getPackage());
        server.listen(args,(so) -> {
            so.config.pack("imports.json");
            return 100L;
        });
    }
}