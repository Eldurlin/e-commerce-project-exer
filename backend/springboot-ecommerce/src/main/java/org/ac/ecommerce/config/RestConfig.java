package org.ac.ecommerce.config;

import org.ac.ecommerce.entity.Product;
import org.ac.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] unsupportMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        /*
        Disable HTTP methods for Product: PUT, POST, DELETE for 1.0 project version 
        */
        config.getExposureConfiguration()
            .forDomainType(Product.class)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods));
        
        /*
        Disable HTTP methods for ProductCategory: PUT, POST, DELETE for 1.0 project version 
        */
        config.getExposureConfiguration()
        .forDomainType(ProductCategory.class)
        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods))
        .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods));
    }
}