package com.github.tncrazvan.arcanotech;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcano.Http.HttpEvent;
import com.github.tncrazvan.arcano.Http.HttpHeaders;
import com.github.tncrazvan.arcano.Http.HttpResponse;
import com.github.tncrazvan.arcano.Http.ShellScript;
import com.github.tncrazvan.arcano.Tool.Actions.CompleteAction;
import com.github.tncrazvan.arcano.Tool.Http.Status;
import com.github.tncrazvan.arcano.Tool.System.ServerFile;
import java.io.File;

public class Starter extends HttpController{
    
    private static CompleteAction<Object,HttpEvent> php = (e) -> {
        return new ShellScript(new File(e.reader.so.config.dir, "php"), "php","index.php");
    };

    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, 
    ClassNotFoundException, URISyntaxException, NoSuchMethodException, InstantiationException, 
    IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Arcano server = new Arcano();
        server.expose(Starter.class.getPackage());
        server.expose("GET", "/mytest", php);
        server.listen(args);
    }
}