package com.github.tncrazvan.arcanotech;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcano.Http.HttpEvent;
import com.github.tncrazvan.arcano.Http.HttpResponse;
import com.github.tncrazvan.arcano.Tool.Actions.CompleteAction;

public class Starter extends HttpController{
    
    private static CompleteAction<Object,HttpEvent> myTest = (e) -> {
        return new HttpResponse("content");
    };

    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, 
    ClassNotFoundException, URISyntaxException, NoSuchMethodException, InstantiationException, 
    IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Arcano server = new Arcano(Starter.class.getPackage());
        server.exposeDefaults();
        server.expose("GET", "/mytest", myTest);
        server.listen(args);
    }
}