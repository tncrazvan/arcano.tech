package $namespace;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import com.github.tncrazvan.arcano.Arcano;

public class Starter{
    public static void main(final String[] args) {
        Arcano server = new Arcano();

        server.addHttpEventListener("*","@404",e->{
            ServerFile file = new ServerFile(e.reader.so.config.webRoot,String.join("/",e.reader.location));
            return HttpResponse(file);
        });

        server.addHttpEventListener("GET,POST","/test",e->{
            return "this is a test!";
        });

        server.listen(args);
    }
}
