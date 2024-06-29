package ly.alghjamia.springbootlibrary.config;

import ly.alghjamia.springbootlibrary.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    public DataRestConfig() {
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {

       HttpMethod[] unSupportedAction= {
               HttpMethod.POST,
               HttpMethod.PATCH,
               HttpMethod.DELETE,
               HttpMethod.PUT
       };
        config.exposeIdsFor(Book.class);

        disableHttpMethode(config,unSupportedAction);

        String allowedOrigins = "http://localhost:3000";
        corsRegistry.addMapping(config.getBasePath()+ "/**")
                .allowedOrigins(allowedOrigins);
    }

    private void disableHttpMethode(RepositoryRestConfiguration config, HttpMethod[] unSupportedAction) {
        config.getExposureConfiguration()
                .forDomainType(Book.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unSupportedAction))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unSupportedAction));
    }

}
