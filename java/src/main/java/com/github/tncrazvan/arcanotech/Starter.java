package com.github.tncrazvan.arcanotech;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.SharedObject;
import com.github.tncrazvan.arcano.Tool.Actions.CompleteAction;
import com.github.tncrazvan.arcanotech.controller.MainController;

public class Starter{
    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, 
    ClassNotFoundException, URISyntaxException, NoSuchMethodException, InstantiationException, 
    IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Arcano server = new Arcano();
        server.expose(MainController.class);
        server.listen(args,new CompleteAction<Long,SharedObject>(){
        
            @Override
            public Long callback(SharedObject so) {
                so.config.pack(so.config.webRoot, "imports.json");
                return 1000L;
            }
        });
    }
}