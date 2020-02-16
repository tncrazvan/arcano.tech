package $namespace;

import com.github.tncrazvan.arcano.Arcano;
import $namespace.Controller.HelloWorld;

public class Starter{
    public static void main(final String[] args) {
        new Arcano(
            args,
            HelloWorld.class
        );
    }
}
