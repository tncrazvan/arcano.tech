/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.tncrazvan.arcanotech.controllers;

import com.github.tncrazvan.arcano.Bean.WebMethod;
import com.github.tncrazvan.arcano.Bean.WebPath;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcano.Tool.Http.Fetch;
import com.github.tncrazvan.arcano.Tool.Http.FetchResult;
import com.github.tncrazvan.arcano.Tool.ServerFile;
import com.github.tncrazvan.arcano.Tool.Zip.ZipArchive;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
@WebPath(name = "/")
public class App extends HttpController{
    @WebPath(name = "/")
    public File main(){
        return new File(webRoot,"../index.html");
    }
    
    @WebPath(name = "/home")
    public File home(){
        return main();
    }
    
    @WebPath(name = "/index.html")
    public File index(){
        return main();
    }
    
    @WebPath(name = "/quick")
    public File quick(){
        return main();
    }
    
    @WebPath(name = "/create")
    @WebMethod(name = "GET")
    public File createGET(){
        return main();
    }
    
    @WebMethod(name = "POST")
    @WebPath(name = "/create")
    public byte[] createPOST() throws FileNotFoundException, IOException{
        ZipArchive archive = new ZipArchive(uuid()+"");
        Fetch fetch = new Fetch(){};
        FetchResult jar = fetch.get("https://github.com/tncrazvan/Arcano/raw/maven-repository/com/github/tncrazvan/Arcano/1.1.0/Arcano-1.1.0.jar");
        if(!jar.isNull()){
            JsonObject data = toJsonObject(new String(input));
            archive.addEntry(data.get("webRoot").getAsString()+"/"+data.get("entryPoint").getAsString(), "<!DOCTYPE html>\n<html>\n\t<head></head>\n\t<body></body>\n</html>");
            archive.addEntry("http.json", new String(input).replaceAll(",", ",\n\t").replaceAll("\\{", "{\n\t").replaceAll("\\}","\n}"));
            archive.addEntry("arcano.jar",jar.getBytes());
            archive.make();
            ServerFile f = archive.getFile();
            byte[] result = f.read();
            f.delete();
            return result;
        }
        return "".getBytes(charset);
    }
}
