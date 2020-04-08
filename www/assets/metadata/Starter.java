package $namespace;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import com.github.tncrazvan.arcano.Arcano;

public class Starter{
    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, 
    ClassNotFoundException, URISyntaxException, NoSuchMethodException, InstantiationException, 
    IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Arcano server = new Arcano(Starter.class.getPackage());
        server.exposeDefaults();
        server.listen(args,(so) -> {
            so.config.pack(so.config.webRoot,"imports.json");
            return 100L;
        });
    }
}
