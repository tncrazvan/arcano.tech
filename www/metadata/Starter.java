package $namespace;

import com.github.tncrazvan.arcano.Server;
import $namespace.Controller.HelloWorld;

public class Starter{
    public static void main(final String[] args) {
        Server server = new Server(
            args,
            HelloWorld.class
        );
    }
}
