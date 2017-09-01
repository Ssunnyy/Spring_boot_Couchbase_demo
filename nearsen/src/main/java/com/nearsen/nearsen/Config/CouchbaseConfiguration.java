package com.nearsen.nearsen.Config;

import static org.mockito.Mockito.*;

import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.WriteResultChecking;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.support.IndexManager;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseBucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.cluster.DefaultClusterInfo;
import com.couchbase.client.java.util.features.CouchbaseFeature;
import com.couchbase.client.java.util.features.Version;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Autowired
    private CouchbaseProperties couchbaseProperties;

    @Bean
    public String couchbaseAdminUser() {
        return "Administrator";
    }

    @Bean
    public String couchbaseAdminPassword() {
        return "password";
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return couchbaseProperties.getBootstrapHosts();
    }

    @Override
    protected String getBucketName() {
        return couchbaseProperties.getBucket().getName();
    }

    @Override
    protected String getBucketPassword() {
        return couchbaseProperties.getBucket().getPassword();
    }

    @Override
    public Cluster couchbaseCluster() throws Exception {
        return Mockito.mock(CouchbaseCluster.class);
    }

    @Override
    public ClusterInfo couchbaseClusterInfo() {
        DefaultClusterInfo mock = Mockito.mock(DefaultClusterInfo.class);
        when(mock.checkAvailable(CouchbaseFeature.N1QL)).thenReturn(true);
        when(mock.getMinVersion()).thenReturn(new Version(4, 0, 0));
        return mock;
    }

    @Override
    public Bucket couchbaseClient() throws Exception {
        return Mockito.mock(CouchbaseBucket.class);
    }

    @Override
    public CouchbaseTemplate couchbaseTemplate() throws Exception {
        CouchbaseTemplate template = super.couchbaseTemplate();
        template.setWriteResultChecking(WriteResultChecking.LOG);
        return template;
    }

    //this is for dev so it is ok to auto-create indexes
    @Override
    public IndexManager indexManager() {
        return new IndexManager();
    }

    @Override
    protected Consistency getDefaultConsistency() {
        return Consistency.READ_YOUR_OWN_WRITES;
    }
}

