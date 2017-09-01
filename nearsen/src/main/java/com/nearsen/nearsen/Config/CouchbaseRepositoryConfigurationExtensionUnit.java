//package com.nearsen.nearsen.Config;
//
//import java.io.Serializable;
//
//import org.springframework.data.couchbase.core.mapping.Document;
//import org.springframework.data.couchbase.repository.CouchbaseRepository;
//import org.springframework.data.couchbase.repository.config.CouchbaseRepositoryConfigurationExtension;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.core.support.DefaultRepositoryMetadata;
//
///**
// * Unit tests for {@link CouchbaseRepositoryConfigurationExtension}.
// *
// * @author Oliver Gierke
// */
//public class CouchbaseRepositoryConfigurationExtensionUnit {
//
//    public void atDocumentIsIdentifyingAnnotation() {
//
//        CustomCouchbaseRepositoryConfigurationExtension extension = new CustomCouchbaseRepositoryConfigurationExtension();
//        RepositoryMetadata metadata = DefaultRepositoryMetadata.getMetadata(ByAggregateRootAnnotationRepository.class);
//
//    }
//
//    public void couchbaseRepositoryIsIdentifyingAnnotation() {
//
//        CustomCouchbaseRepositoryConfigurationExtension extension = new CustomCouchbaseRepositoryConfigurationExtension();
//        RepositoryMetadata metadata = DefaultRepositoryMetadata.getMetadata(ByRepository.class);
//    }
//
//    @Document
//    static class AggregateRoot {
//    }
//
//    interface ByAggregateRootAnnotationRepository extends Repository<AggregateRoot, Serializable> {
//    }
//
//    interface ByRepository extends CouchbaseRepository<Object, Serializable> {
//    }
//
//    ;
//
//    static class CustomCouchbaseRepositoryConfigurationExtension extends CouchbaseRepositoryConfigurationExtension {
//
//        /*
//         * (non-Javadoc)
//         * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#isStrictRepositoryCandidate(org.springframework.data.repository.core.RepositoryMetadata)
//         */
//        @Override
//        protected boolean isStrictRepositoryCandidate(RepositoryMetadata metadata) {
//            return super.isStrictRepositoryCandidate(metadata);
//        }
//    }
//}