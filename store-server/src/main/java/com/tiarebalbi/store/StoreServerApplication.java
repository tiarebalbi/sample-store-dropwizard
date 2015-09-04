package com.tiarebalbi.store;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.5.0
 */
public class StoreServerApplication extends Application<StoreServerConfiguration> {


    public static void main(String... args) throws Exception {
        // Init
        new StoreServerApplication().run(args);
    }

    @Override
    public void run(StoreServerConfiguration storeServerConfiguration, Environment environment) throws Exception {

    }
}
