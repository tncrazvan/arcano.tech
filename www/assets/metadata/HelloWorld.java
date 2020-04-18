/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package $namespace;

import com.github.tncrazvan.arcano.bean.http.HttpService;
import com.github.tncrazvan.arcano.http.HttpController;

/**
 *
 * @author Administrator
 */
public class HelloWorld extends HttpController{
    @HttpService(path = "/helloworld/test", method = "GET")
    public String test(){
        return "Hello World, this is a test!";
    }
}
