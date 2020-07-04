package com.github.tncrazvan.arcanotech;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.SharedObject;
import com.github.tncrazvan.arcano.http.HttpRequestReader;
import com.github.tncrazvan.arcano.http.HttpResponse;
import com.github.tncrazvan.arcano.tool.Strings;
import com.github.tncrazvan.arcano.tool.action.HttpEventAction;
import com.github.tncrazvan.arcano.tool.encoding.JsonTools;
import com.github.tncrazvan.arcano.tool.http.Status;
import com.github.tncrazvan.arcano.tool.system.ServerFile;
import com.github.tncrazvan.arcano.tool.zip.ZipArchive;
import com.google.gson.JsonObject;


public class Starter {
    public static void main(final String[] args) {
        Arcano server = new Arcano();
        server.addHttpEventListener("*","@404",eventNotFound);
        server.addHttpEventListener("POST","/create",eventCreateZipArchive);
        
        server.addHttpEventListener("GET","/",index);
        server.addHttpEventListener("GET","/create",index);
        server.addHttpEventListener("GET","/home",index);

        server.listen(args);
    }

    private static final HttpEventAction<Object> index = e -> {
        ServerFile file = new ServerFile(e.so.config.webRoot, e.so.config.entryPoint);
        if(file.exists()) return new HttpResponse(file);
        
        return SharedObject.HTTP_RESPONSE_NOT_FOUND;
    };

    private static final HttpEventAction<Object> eventNotFound = e -> {
        ServerFile file = new ServerFile(e.so.config.webRoot, String.join("/", e.request.reader.location));
        if(file.exists()) 
            return new HttpResponse(file);
        
        return SharedObject.HTTP_RESPONSE_NOT_FOUND;
    };

    private static final HttpEventAction<Object> eventCreateZipArchive = e -> {

        HttpRequestReader reader = e.request.reader;

        ZipArchive archive;
        try {
            archive = new ZipArchive(Strings.uuid() + "");
        } catch (FileNotFoundException e1) {
            e.response.headers.setStatus(Status.STATUS_INTERNAL_SERVER_ERROR);
            return e1.toString();
        }
        JsonObject data = JsonTools.jsonObject(new String(reader.content.body));
        String serverRoot = data.get("serverRoot").getAsString();
        String webRoot = data.get("webRoot").getAsString();
        String entryPoint = data.get("entryPoint").getAsString();
        String appname = data.get("appname").getAsString();
        String namespace = data.get("namespace").getAsString()+"."+appname.toLowerCase();
        String path = namespace.replaceAll("\\.", "/");
        String mainClass = namespace+".Starter";
        String pom;
        try {
            pom = new String(new ServerFile(e.so.config.webRoot, "assets/metadata/pom-template.xml").read(),e.so.config.charset)
                            .replaceAll("\\$namespace", namespace)
                            .replaceAll("\\$appname", appname)
                            .replaceAll("\\$mainClass", mainClass);


            String starter = new String(new ServerFile(e.so.config.webRoot,"assets/metadata/Starter.java").read(),e.so.config.charset)
                            .replaceAll("\\$namespace", namespace);
            
    
            String index = new String(new ServerFile(e.so.config.webRoot,"assets/metadata/index.html").read(),e.so.config.charset);
    

            String classpath = new String(new ServerFile(e.so.config.webRoot,"assets/metadata/classpath").read(),e.so.config.charset);
    

            String start = new String(new ServerFile(e.so.config.webRoot,"assets/metadata/start").read(),e.so.config.charset)
                            .replaceAll("\\$appname", appname);


            String update = new String(new ServerFile(e.so.config.webRoot,"assets/metadata/update").read(),e.so.config.charset)
                            .replaceAll("\\$appname", appname);


            String install = new String(new ServerFile(e.so.config.webRoot,"assets/metadata/install").read(),e.so.config.charset)
                            .replaceAll("\\$appname", appname);



            JsonObject tmp = JsonTools.jsonObject(new String(reader.content.body));
            tmp.remove("appname");
            tmp.remove("namespace");
            String json = new String(tmp.toString())
                            .replaceAll(",", ",\n\t")
                            .replaceAll("\\{", "{\n\t")
                            .replaceAll("\\}","\n}");
            archive.addEntry(webRoot+"/"+entryPoint, index, e.so.config.charset);
            archive.addEntry(serverRoot+"/pom.xml",pom, e.so.config.charset);
            archive.addEntry(serverRoot+"/.classpath",classpath, e.so.config.charset);
            archive.addEntry(serverRoot+"/src/main/java/"+path+"/Starter.java",starter, e.so.config.charset);
            archive.addEntry("http.json", json, e.so.config.charset);
            archive.addEntry("start", start, e.so.config.charset);
            archive.addEntry("update", update, e.so.config.charset);
            archive.addEntry("install", install, e.so.config.charset);
            archive.make();
            ServerFile f = archive.getFile();
            return new HttpResponse(f).then(() -> {
                f.delete();
            });
        } catch (IOException e1) {
            e.response.headers.setStatus(Status.STATUS_INTERNAL_SERVER_ERROR);
            return e1.toString();
        }
    };
}