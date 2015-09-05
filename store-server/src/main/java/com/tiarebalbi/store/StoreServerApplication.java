package com.tiarebalbi.store;

import com.tiarebalbi.store.core.Product;
import com.tiarebalbi.store.db.ProductDAO;
import com.tiarebalbi.store.resources.ProductResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.5.0
 */
public class StoreServerApplication extends Application<StoreServerConfiguration> {

    private final HibernateBundle<StoreServerConfiguration> hibernateBundle =
            new HibernateBundle<StoreServerConfiguration>(Product.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(StoreServerConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(String... args) throws Exception {
        // Init
        new StoreServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "store-server";
    }

    @Override
    public void run(StoreServerConfiguration storeServerConfiguration, Environment environment) throws Exception {

        final ProductDAO productDAO = new ProductDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new ProductResource(productDAO));
    }

    @Override
    public void initialize(Bootstrap<StoreServerConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new MigrationsBundle<StoreServerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(StoreServerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernateBundle);

    }
}