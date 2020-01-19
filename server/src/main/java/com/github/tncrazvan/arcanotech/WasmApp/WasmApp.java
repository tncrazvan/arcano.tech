/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.tncrazvan.arcanotech.WasmApp;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.web.dom.Document;
import de.inetsoftware.jwebassembly.web.dom.HTMLElement;
import de.inetsoftware.jwebassembly.web.dom.Node;
import de.inetsoftware.jwebassembly.web.dom.Text;
import de.inetsoftware.jwebassembly.web.dom.Window;

/**
 *
 * @author Administrator
 */
public class WasmApp {
    @Export
    public static int main() {
        //Document document = Window.document();
        /*HTMLElement div = document.createElement("div");
        Text text = document.createTextNode("Hello World, this text comes from WebAssembly."); 
        div.appendChild( text );
        document.body().appendChild( div );*/
        return 0;
    }
}
