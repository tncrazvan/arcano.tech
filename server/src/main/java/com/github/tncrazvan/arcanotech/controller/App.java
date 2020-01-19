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
import com.github.tncrazvan.arcano.Bean.Web.HttpMethod;
import com.github.tncrazvan.arcano.Bean.Web.HttpPath;

public class App extends HttpController implements JsonTools{
    @HttpPath(name = "/")
    public File main(){
        return new File(config.webRoot,config.entryPoint);
    }
    
    @HttpPath(name = "/home")
    public File home(){
        return main();
    }
    
    @HttpPath(name = "/quick")
    public File quick(){
        return main();
    }
    
    @HttpMethod(name = "GET")
    @HttpPath(name = "/create")
    public File createGET(){
        return main();
    }
    
    @HttpMethod(name = "POST")
    @HttpPath(name = "/create")
    public HttpResponse createPOST() throws FileNotFoundException, IOException{
        ZipArchive archive = new ZipArchive(uuid()+"");
        JsonObject data = jsonObject(new String(request.content));
        String serverRoot = data.get("serverRoot").getAsString();
        String webRoot = data.get("webRoot").getAsString();
        String entryPoint = data.get("entryPoint").getAsString();
        String appname = data.get("appname").getAsString();
        String namespace = data.get("namespace").getAsString()+"."+appname.toLowerCase();
        String path = namespace.replaceAll("\\.", "/");
        String mainClass = namespace+".Starter";
        String pom = new String(new ServerFile(config.webRoot,"metadata/pom-template.xml").read(),config.charset)
                    .replaceAll("\\$namespace", namespace)
                    .replaceAll("\\$appname", appname)
                    .replaceAll("\\$mainClass", mainClass);

        String starter = new String(new ServerFile(config.webRoot,"metadata/Starter.java").read(),config.charset)
                    .replaceAll("\\$namespace", namespace);
        String helloWorld = new String(new ServerFile(config.webRoot,"metadata/HelloWorld.java").read(),config.charset)
                    .replaceAll("\\$namespace", namespace+".Controller");

        String index = new String(new ServerFile(config.webRoot,"metadata/index.html").read(),config.charset);

        String classpath = new String(new ServerFile(config.webRoot,"metadata/classpath").read(),config.charset);

        String start = new String(new ServerFile(config.webRoot,"metadata/start").read(),config.charset)
                        .replaceAll("\\$appname", appname);
        String update = new String(new ServerFile(config.webRoot,"metadata/update").read(),config.charset)
                        .replaceAll("\\$appname", appname);

        JsonObject tmp = JsonTools.jsonObject(new String(request.content));
        tmp.remove("appname");
        tmp.remove("namespace");
        String json = new String(tmp.toString())
                        .replaceAll(",", ",\n\t")
                        .replaceAll("\\{", "{\n\t")
                        .replaceAll("\\}","\n}");

        archive.addEntry(webRoot+"/"+entryPoint, index, config.charset);
        archive.addEntry(serverRoot+"/pom.xml",pom, config.charset);
        archive.addEntry(serverRoot+"/.classpath",classpath, config.charset);
        archive.addEntry(serverRoot+"/src/main/java/"+path+"/Starter.java",starter, config.charset);
        archive.addEntry(serverRoot+"/src/main/java/"+path+"/Controller/HelloWorld.java",helloWorld, config.charset);
        archive.addEntry("http.json", json, config.charset);
        archive.addEntry("start", start, config.charset);
        archive.addEntry("update", update, config.charset);
        archive.make();
        ServerFile f = archive.getFile();
        return new HttpResponse(f).then(() -> {
            f.delete();
        });
    }
}
