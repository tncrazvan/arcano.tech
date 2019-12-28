/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package $namespace;

import com.github.tncrazvan.arcano.Bean.Web.WebPath;
import com.github.tncrazvan.arcano.Http.HttpController;

/**
 *
 * @author Administrator
 */
@WebPath(name = "/helloworld")
public class HelloWorld extends HttpController{
    @WebPath(name = "/test")
    public String test(){
        return "Hello World, this is a test!";
    }
}
