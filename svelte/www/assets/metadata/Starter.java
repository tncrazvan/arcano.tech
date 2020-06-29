package $namespace;

import com.github.tncrazvan.arcano.Arcano;
import com.github.tncrazvan.arcano.SharedObject;
import com.github.tncrazvan.arcano.http.HttpResponse;
import com.github.tncrazvan.arcano.tool.system.ServerFile;

public class Starter{
    public static void main(final String[] args) {
        Arcano server = new Arcano();

        server.addHttpEventListener("*","@404",e->{
            ServerFile file = new ServerFile(e.reader.so.config.webRoot,String.join("/",e.reader.location));
            if(file.exists())
                return new HttpResponse(file);
            else return SharedObject.RESPONSE_NOT_FOUND;
        });

        server.addHttpEventListener("GET","/test",e->{
            return "this is a test!";
        });

        server.listen(args);
    }
}
