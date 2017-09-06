package com.nearsen_enterprise.Config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.cluster.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.util.List;

@Configuration
@EnableCouchbaseRepositories
public class ConcreteCouchbaseConfig extends AbstractCouchbaseConfiguration{

    //    @Autowired
    //    private CouchbaseProperties couchbaseProperties;

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
    @Value("${storage.travelBucket}")
    private String travelBucket;
    @Value("${storage.hosts}")
    private List<String> hosts;
    @Value("$storage.loginPassword")
    private String loginPassword;

    // 链接默认的服务器
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

    // 自己定义选择不同的服务器
    protected Cluster meetingCouchbaseCluster() throws Exception {
        return CouchbaseCluster.create(this.couchbaseEnvironment(), meetinghosts);
    }
    // 定义连接不同服务器的bucket/或者相同服务器的不同bucket
    @Bean
    public Bucket meetingBucket() throws Exception {
        return meetingCouchbaseCluster().openBucket(meetingBucket,"");
    }

    //... then the template (inspired by couchbaseTemplate() method)...
    @Bean(name = "meetingTemplate")
    public CouchbaseTemplate meetingTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(
                couchbaseClusterInfo(), //reuse the default bean
                meetingBucket(), //the bucket is non-default
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
                    .mapEntity(User.class, meetingTemplate()); //every repository dealing with User will be backed by userTemplate()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
