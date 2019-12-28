package $namespace;

import com.github.tncrazvan.arcano.ArcanoServer;
import $namespace.Controller.HelloWorld;

public class Starter{
    public static void main(final String[] args) {
        new ArcanoServer(
            args,
            HelloWorld.class
        );
    }
}
