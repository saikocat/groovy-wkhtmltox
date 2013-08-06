import com.google.inject.*;

import com.saikocat.wkhtmltox.inject.*;

public class Shell implements Runnable {
    public void run() {
        def injector = Guice.createInjector(new DefaultConfigModule());
        injector.injectMembers(this);
    }
}
