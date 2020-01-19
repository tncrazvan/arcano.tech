package com.github.tncrazvan.arcanotech;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.Bean.Web.HttpParam;
import com.github.tncrazvan.arcano.Bean.Web.HttpPath;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcanotech.WasmApp.WasmApp;
import com.github.tncrazvan.arcanotech.controller.App;
import com.github.tncrazvan.arcanotech.controller.Documentation;
import de.inetsoftware.jwebassembly.JWebAssembly;
import java.io.File;
import java.net.URL;

public class Starter extends HttpController{
    
    @HttpPath(name = "/compile")
    public String compileGET(){
        compile(WasmApp.class);
        return "Done!";
    }
    
    public static void compile(Class cls){
        File wasmFile = new File("www/app.wasm");
        JWebAssembly wasm = new JWebAssembly();
        URL url = cls.getResource("/"+cls.getName().replace(".", "/")+".class");
        wasm.addFile(url);
        wasm.compileToBinary(wasmFile);
    }
    
    @HttpPath(name = "/paramTest")
    public Integer[] paramTest(@HttpParam String username, @HttpParam String password, @HttpParam Integer lvl){
        return new Integer[]{
            lvl==null?0:lvl
        };
    }
    
    public static void main(final String[] args) {
        compile(WasmApp.class);
        new Arcano(
            args,
            App.class,
            Documentation.class,
            Starter.class
        );
    }
}
