package $namespace;

import com.github.tncrazvan.arcano.Arcano;
import $namespace.Controller.HelloWorld;

public class Starter{
    public static void main(final String[] args) throws IOException, NoSuchAlgorithmException, 
    ClassNotFoundException, URISyntaxException, NoSuchMethodException, InstantiationException, 
    IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Arcano server = new Arcano(Starter.class.getPackage());
        server.listen(args,(so) -> {
            so.config.pack("imports.json");
            return 100L;
        });
    }
}
