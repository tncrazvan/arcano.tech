package com.github.tncrazvan.arcanotech.controller;

import com.github.tncrazvan.arcano.Bean.WebMethod;
import com.github.tncrazvan.arcano.Bean.WebPath;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcano.Http.HttpResponse;
import com.github.tncrazvan.arcano.Tool.Action;
import com.github.tncrazvan.arcano.Tool.JsonTools;
import static com.github.tncrazvan.arcano.Tool.JsonTools.jsonObject;
import com.github.tncrazvan.arcano.Tool.ServerFile;
import static com.github.tncrazvan.arcano.Tool.Strings.uuid;
import com.github.tncrazvan.arcano.Tool.Zip.ZipArchive;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebPath(name = "/")
public class App extends HttpController implements JsonTools{
    @WebPath(name = "/")
    public File main(){
        return new File(so.webRoot,so.entryPoint);
    }
    
    @WebPath(name = "/home")
    public File home(){
        return main();
    }
    
    @WebPath(name = "/quick")
    public File quick(){
        return main();
    }
    
    @WebMethod(name = "GET")
    @WebPath(name = "/create")
    public File createGET(){
        return main();
    }
    
    @WebMethod(name = "POST")
    @WebPath(name = "/create")
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
        String pom = new String(new ServerFile(so.webRoot,"metadata/pom-template.xml").read(),so.charset)
                    .replaceAll("\\$namespace", namespace)
                    .replaceAll("\\$appname", appname)
                    .replaceAll("\\$mainClass", mainClass);

        String starter = new String(new ServerFile(so.webRoot,"metadata/Starter.java").read(),so.charset)
                    .replaceAll("\\$namespace", namespace);
        String helloWorld = new String(new ServerFile(so.webRoot,"metadata/HelloWorld.java").read(),so.charset)
                    .replaceAll("\\$namespace", namespace+".Controller");

        String index = new String(new ServerFile(so.webRoot,"metadata/index.html").read(),so.charset);

        String classpath = new String(new ServerFile(so.webRoot,"metadata/classpath").read(),so.charset);

        String start = new String(new ServerFile(so.webRoot,"metadata/start").read(),so.charset)
                        .replaceAll("\\$appname", appname);
        String update = new String(new ServerFile(so.webRoot,"metadata/update").read(),so.charset)
                        .replaceAll("\\$appname", appname);

        String config = new String(request.content)
                        .replaceAll(",", ",\n\t")
                        .replaceAll("\\{", "{\n\t")
                        .replaceAll("\\}","\n}");

        archive.addEntry(webRoot+"/"+entryPoint, index,so.charset);
        archive.addEntry(serverRoot+"/pom.xml",pom,so.charset);
        archive.addEntry(serverRoot+"/.classpath",classpath,so.charset);
        archive.addEntry(serverRoot+"/src/main/java/"+path+"/Starter.java",starter,so.charset);
        archive.addEntry(serverRoot+"/src/main/java/"+path+"/Controller/HelloWorld.java",helloWorld,so.charset);
        archive.addEntry("http.json", config, so.charset);
        archive.addEntry("start", start, so.charset);
        archive.addEntry("update", update, so.charset);
        archive.make();
        ServerFile f = archive.getFile();
        return new HttpResponse(f).then(new Action<Void>() {
            @Override
            public boolean callback(Void o) {
                return f.delete();
            }
        });
    }
}
