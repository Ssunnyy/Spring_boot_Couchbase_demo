package com.nearsen_enterprise.Config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.cluster.DefaultClusterInfo;
import com.couchbase.client.java.cluster.User;
import com.couchbase.client.java.util.features.CouchbaseFeature;
import com.couchbase.client.java.util.features.Version;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

import static org.mockito.Mockito.when;

@Configuration
@EnableCouchbaseRepositories
public class ConcreteCouchbaseConfig extends AbstractCouchbaseConfiguration{

    @Value("${storage.meetingBucket}")
    private String meetingBucket;
    @Value("${storage.meetinghosts}")
    private List<String> meetinghosts;
    @Value("$storage.meetingPassword")
    private String meetingPassword;

    @Value("${storage.nearsenBucket}")
    private String nearsenBucket;
    @Value("${storage.nearsenhosts}")
    private List<String> nearsenhosts;
    @Value("$storage.nearsenPassword")
    private String nearsenPassword;

    @Value("${storage.loginBucket}")
    private String loginBucket;
    @Value("${storage.hosts}")
    private List<String> hosts;
    @Value("$storage.loginPassword")
    private String loginPassword;

    @Override
    protected List<String> getBootstrapHosts() {
        return hosts;
    }
    @Override
    protected String getBucketName() {
        return loginBucket;
    }
    @Override
    protected String getBucketPassword() {
        return "";// ********  切记密码为""  不能nil/
    }

    @Bean
    public Bucket userBucket() throws Exception {
        return couchbaseCluster().openBucket("travel-sample","");
    }

    //... then the template (inspired by couchbaseTemplate() method)...
    @Bean(name = "userTemplate")
    public CouchbaseTemplate userTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(
                couchbaseClusterInfo(), //reuse the default bean
                userBucket(), //the bucket is non-default
                mappingCouchbaseConverter(), translationService() //default beans here as well
        );
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    //... then finally make sure all repositories of Users will use it
    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping //this is already using couchbaseTemplate as default
                    .mapEntity(User.class, userTemplate()); //every repository dealing with User will be backed by userTemplate()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
