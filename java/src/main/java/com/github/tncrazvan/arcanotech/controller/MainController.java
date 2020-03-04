package com.github.tncrazvan.arcanotech.controller;

import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcano.Http.HttpResponse;
import com.github.tncrazvan.arcano.Tool.Encoding.JsonTools;
import static com.github.tncrazvan.arcano.Tool.Encoding.JsonTools.jsonObject;

import static com.github.tncrazvan.arcano.Tool.Strings.uuid;
import com.github.tncrazvan.arcano.Tool.System.ServerFile;
import com.github.tncrazvan.arcano.Tool.Zip.ZipArchive;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.github.tncrazvan.arcano.Bean.Http.HttpService;

public class MainController extends HttpController implements JsonTools{
    

    @HttpService(path = "/")
    public File main(){
        return new File(reader.so.config.webRoot,reader.so.config.entryPoint);
    }
    
    @HttpService(path = "")
    public File empty(){
        return main();
    }

    @HttpService(path = "/home")
    public File home(){
        return main();
    }
    
    @HttpService(path = "/quick")
    public File quick(){
        return main();
    }
    
    @HttpService(path = "/create")
    public File createGET(){
        return main();
    }
    
    @HttpService(path = "/create", method = "POST")
    public HttpResponse createPOST() throws FileNotFoundException, IOException{
        ZipArchive archive = new ZipArchive(uuid()+"");
        JsonObject data = jsonObject(new String(reader.request.content));
        String serverRoot = data.get("serverRoot").getAsString();
        String webRoot = data.get("webRoot").getAsString();
        String entryPoint = data.get("entryPoint").getAsString();
        String appname = data.get("appname").getAsString();
        String namespace = data.get("namespace").getAsString()+"."+appname.toLowerCase();
        String path = namespace.replaceAll("\\.", "/");
        String mainClass = namespace+".Starter";
        String pom = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/pom-template.xml").read(),reader.so.config.charset)
                    .replaceAll("\\$namespace", namespace)
                    .replaceAll("\\$appname", appname)
                    .replaceAll("\\$mainClass", mainClass);

        String starter = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/Starter.java").read(),reader.so.config.charset)
                    .replaceAll("\\$namespace", namespace);
        String helloWorld = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/HelloWorld.java").read(),reader.so.config.charset)
                    .replaceAll("\\$namespace", namespace+".Controller");

        String index = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/index.html").read(),reader.so.config.charset);

        String classpath = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/classpath").read(),reader.so.config.charset);

        String start = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/start").read(),reader.so.config.charset)
                        .replaceAll("\\$appname", appname);
        String update = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/update").read(),reader.so.config.charset)
                        .replaceAll("\\$appname", appname);
        String install = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/install").read(),reader.so.config.charset)
                        .replaceAll("\\$appname", appname);
        String imports = new String(new ServerFile(reader.so.config.webRoot,"assets/metadata/imports.json").read(),reader.so.config.charset)
                        .replaceAll("\\$appname", appname);

        JsonObject tmp = JsonTools.jsonObject(new String(reader.request.content));
        tmp.remove("appname");
        tmp.remove("namespace");
        String json = new String(tmp.toString())
                        .replaceAll(",", ",\n\t")
                        .replaceAll("\\{", "{\n\t")
                        .replaceAll("\\}","\n}");
        String js = "console.log('This is app!');";
        String css = "";
        archive.addEntry(webRoot+"/"+entryPoint, index, reader.so.config.charset);
        archive.addEntry(serverRoot+"/pom.xml",pom, reader.so.config.charset);
        archive.addEntry(serverRoot+"/.classpath",classpath, reader.so.config.charset);
        archive.addEntry(serverRoot+"/src/main/java/"+path+"/Starter.java",starter, reader.so.config.charset);
        archive.addEntry(serverRoot+"/src/main/java/"+path+"/Controller/HelloWorld.java",helloWorld, reader.so.config.charset);
        archive.addEntry("http.json", json, reader.so.config.charset);
        archive.addEntry("start", start, reader.so.config.charset);
        archive.addEntry("update", update, reader.so.config.charset);
        archive.addEntry("install", install, reader.so.config.charset);
        archive.addEntry(webRoot+"/imports.json", imports, reader.so.config.charset);
        archive.addEntry(webRoot+"/js/app.json", js, reader.so.config.charset);
        archive.addEntry(webRoot+"/css/style.css", css, reader.so.config.charset);
        archive.addEntry(webRoot+"/pack/main.css", css, reader.so.config.charset);
        archive.addEntry(webRoot+"/pack/main.js", js, reader.so.config.charset);
        archive.make();
        ServerFile f = archive.getFile();
        return new HttpResponse(f).then(() -> {
            f.delete();
        });
    }
}
