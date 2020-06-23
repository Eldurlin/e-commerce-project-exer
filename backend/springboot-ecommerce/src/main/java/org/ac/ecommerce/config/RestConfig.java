package org.ac.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.ac.ecommerce.entity.Product;
import org.ac.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;

    @Autowired
    public RestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] unsupportMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        /**
         * Disable HTTP methods for Product: PUT, POST, DELETE for 1.0 project version.
         */
        config.getExposureConfiguration()
            .forDomainType(Product.class)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods));
        
        /**
         * Disable HTTP methods for ProductCategory: PUT, POST, DELETE for 1.0 project version.
         */
        config.getExposureConfiguration()
        .forDomainType(ProductCategory.class)
        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods))
        .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportMethods));

        /**
         * Call an internal helper method.
         */
        exposeId(config);
    }

    private void exposeId(RepositoryRestConfiguration config) {
        /**
         * Expose entity ids.
         * Get a list of all entity classes from the entity manager.
         * Create array of the entity types.
         * Get the entity types for the entities.
         */
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entityTypes) {
            entityClasses.add(tempEntityType.getJavaType());
        }
        /**
         * Expose entity ids for the array of entity/domain type.
         */
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}