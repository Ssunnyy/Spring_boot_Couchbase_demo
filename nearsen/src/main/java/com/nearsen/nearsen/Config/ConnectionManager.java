package com.nearsen.nearsen.Config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.view.AsyncViewResult;
import com.couchbase.client.java.view.AsyncViewRow;
import com.couchbase.client.java.view.Stale;
import com.couchbase.client.java.view.ViewQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;

@Configuration
/**
 * The ConnectionManager handles connecting, disconnecting and managing of the * Couchbase connection.
 */

public class ConnectionManager {

    @Value("${storage.loginBucket}")
    private String loginBucket;
    @Value("${storage.loginPassword}")
    private String loginPassword;
    @Autowired
    private Cluster couchbaseCluster;

    public ConnectionManager() {
    }

    @Bean
    public Bucket loginBucket() {
        return this.couchbaseCluster.openBucket(this.loginBucket, this.loginPassword);
    }

    /*//生成一个静态instance
    private static final ConnectionManager connectionManager = new ConnectionManager();
    public static ConnectionManager getInstance() {
        return connectionManager;
    }
    //生成一个静态集群
    static Cluster cluster = CouchbaseCluster.create();
    //打开一个数据桶, 这里为beer-sample, 安装时自动生成的
    static Bucket bucket = cluster.openBucket("beer-sample");
    //关闭集群
    /public static void disconnect() { cluster.disconnect(); }
    //得到视图的行集合列表, 这里使用异步获取方式
    /public static ArrayList<AsyncViewRow> getView(String designDoc, String view) {
        final ArrayList<AsyncViewRow> result = new ArrayList<AsyncViewRow>();
        final CountDownLatch latch = new CountDownLatch(1);
        bucket.async().query( ViewQuery.from(designDoc, view).limit(20).stale(Stale.FALSE)) .doOnNext(new Action1<AsyncViewResult>() {
            @Override public void call(AsyncViewResult viewResult) {
                if (!viewResult.success()) {
                    System.out.println(viewResult.error());
                }
            }
        }).flatMap(new Func1<AsyncViewResult, Observable<AsyncViewRow>>() {
            @Override public Observable<AsyncViewRow> call(AsyncViewResult viewResult) {
                return viewResult.rows();
            }
        }).subscribe(new Subscriber<AsyncViewRow>() {
            @Override public void onCompleted() {
                latch.countDown();
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void (Throwable throwable) {
                System.err.println("Whoops: " + throwable.getMessage());
            }
            @Override public void onNext(AsyncViewRow viewRow) {
                result.add(viewRow);
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } return result;
    }
    //获取一个Json文档
    public static JsonDocument getItem(String id) {
        JsonDocument response = null; try { response = bucket.get(id);
        } catch (NoSuchElementException e) {
            System.out.println("ERROR: No element with message: " + e.getMessage()); e.printStackTrace();
        }
        return response;
    }
    //删除一个Json文档
    public static void deleteItem(String id) {
        try {
            bucket.remove(id, PersistTo.MASTER);
        } catch (NoSuchElementException e){
            System.out.println("ERROR: No element with message: " + e.getMessage());
        }
    }
    //更新一个Json文档
    public static void updateItem(JsonDocument doc) {
        bucket.upsert(doc);
    }
    //关闭数据桶的连接
    public static void closeBucket() {
        bucket.close();
    }*/
    //为演示目的, 这里举一个把啤酒beer信息存储到Couchbase里的应用例子, 先要建立一个BeerModel类:package com.couchbase.beersample;public class BeerModel{ private String name, style, deion, category, abv, srm, ibu, upc, brewery, Id = ""; public String getName(){ return name; } public void setName(String name){ this.name = name; } public String getStyle(){ return style; } public void setStyle(String style){ this.style = style; } public String getDeion() { return deion; } public void setDeion(String deion) { this.deion = deion; } public String getCategory() { return category; } public void setCategory(String category) { this.category = category; } public String getAbv() { return abv; } public void setAbv(String abv) { this.abv = abv; } public String getSrm() { return srm; } public void setSrm(String srm) { this.srm = srm; } public String getIbu() { return ibu; } public void setIbu(String ibu) { this.ibu = ibu; } public String getUpc() { return upc; } public void setUpc(String upc) { this.upc = upc; } public String getBrewery() { return brewery; } public void setBrewery(String brewery) { this.brewery = brewery; } public String getId(){ return Id; } public void setId(String id) { this.Id = id; }}再建造一个Main类, 在main函数里进行测试,package com.couchbase.beersample;import com.couchbase.client.java.document.JsonDocument;import com.couchbase.client.java.document.json.JsonObject;import com.couchbase.client.java.view.AsyncViewRow;import java.util.ArrayList;import rx.functions.Action1;import rx.functions.Func1;/** * * @author Xijian */public class Main { public static void main(String[] args) { BeerModel beerModel = new BeerModel(); beerModel.setId("2"); beerModel.setName("beer2"); beerModel.setStyle("beer2 style"); beerModel.setDeion("beer2 Deion"); beerModel.setAbv("beer2 abv"); beerModel.setIbu("beer2 ibu"); beerModel.setSrm("beer2 srm"); beerModel.setUpc("beer2 upc"); beerModel.setBrewery("beer2 Brewery id"); JsonObject beer = JsonObject.empty() .put("name", beerModel.getName()) .put("style", beerModel.getStyle()) .put("deion", beerModel.getDeion()) .put("abv", beerModel.getAbv()) .put("ibu", beerModel.getIbu()) .put("srm", beerModel.getSrm()) .put("upc", beerModel.getUpc()) .put("brewery", beerModel.getBrewery()) .put("type", "beer"); JsonDocument doc = JsonDocument.create(beerModel.getId(), beer); ConnectionManager.updateItem(doc); beerModel = new BeerModel(); beerModel.setId("3"); beerModel.setName("beer3"); beerModel.setStyle("beer3 style"); beerModel.setDeion("beer3 Deion"); beerModel.setAbv("beer3 abv"); beerModel.setIbu("beer3 ibu"); beerModel.setSrm("beer3 srm"); beerModel.setUpc("beer3 upc"); beerModel.setBrewery("beer3 Brewery id"); beer = JsonObject.empty() .put("name", beerModel.getName()) .put("style", beerModel.getStyle()) .put("deion", beerModel.getDeion()) .put("abv", beerModel.getAbv()) .put("ibu", beerModel.getIbu()) .put("srm", beerModel.getSrm()) .put("upc", beerModel.getUpc()) .put("brewery", beerModel.getBrewery()) .put("type", "beer"); doc = JsonDocument.create(beerModel.getId(), beer); ConnectionManager.updateItem(doc); ArrayList<AsyncViewRow> result = ConnectionManager.getView("beer", "by_name"); System.out.println("size: " + result.size()); doc = ConnectionManager.getItem("2"); System.out.println("name: " + doc.content().getString("name")); }}
}


