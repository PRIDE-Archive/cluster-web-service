package uk.ac.ebi.pride.cluster.ws.swagger;

import com.google.common.collect.Ordering;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.ordering.ResourceListingPositionalOrdering;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiDescription;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Configuration
@EnableSwagger
@ComponentScan("uk.ac.ebi.pride.cluster.ws.modules.clustersummary.controller")
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                // try the default RelativeSwaggerPathProvider ToDo: test with public deployment
                //.pathProvider(new DocuPathProvider())
                .apiListingReferenceOrdering(new ResourceListingPositionalOrdering())
                .directModelSubstitute(URL.class, String.class) // don't document URL as complex object, but as simple string
                .apiDescriptionOrdering(new ApiDescriptionPositionOrdering())
                .includePatterns("/.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PRIDE Cluster web service API",
                "Programmatic access to the PRIDE Cluster data via RESTful Web Services.",
                "http://www.ebi.ac.uk/about/terms-of-use",
                "pride-support@ebi.ac.uk",
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
    }

    private class ApiDescriptionPositionOrdering extends Ordering<ApiDescription> {
        @Override
        public int compare(ApiDescription apiDescription, ApiDescription other) {
            int pos1 = apiDescription.operations().iterator().next().position();
            int pos2 = other.operations().iterator().next().position();
            return Integer.compare(pos1, pos2);
        }
    }

}