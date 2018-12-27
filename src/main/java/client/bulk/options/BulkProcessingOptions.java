package client.bulk.options;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public class BulkProcessingOptions {

    private String name;

    /**
     * 并发请求数，0不并发，1并发
     **/
    private int concurrentRequests;

    /**
     * 多少次请求执行一次bulk
     **/
    private int bulkActions;

    /**
     * 多大的数据刷新一次bulk
     **/
    private ByteSizeValue bulkSize;

    /**
     * 多久刷新一次
     **/
    private TimeValue flushInterval;

    /**
     * 设置退避，多少秒后执行，最大请求多少次
     **/
    private BackoffPolicy backoffPolicy;

    public BulkProcessingOptions(String name, int concurrentRequests, int bulkActions, ByteSizeValue bulkSize, TimeValue flushInterval, BackoffPolicy backoffPolicy) {
        this.name = name;
        this.concurrentRequests = concurrentRequests;
        this.bulkActions = bulkActions;
        this.bulkSize = bulkSize;
        this.flushInterval = flushInterval;
        this.backoffPolicy = backoffPolicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConcurrentRequests() {
        return concurrentRequests;
    }

    public void setConcurrentRequests(int concurrentRequests) {
        this.concurrentRequests = concurrentRequests;
    }

    public int getBulkActions() {
        return bulkActions;
    }

    public void setBulkActions(int bulkActions) {
        this.bulkActions = bulkActions;
    }

    public ByteSizeValue getBulkSize() {
        return bulkSize;
    }

    public void setBulkSize(ByteSizeValue bulkSize) {
        this.bulkSize = bulkSize;
    }

    public TimeValue getFlushInterval() {
        return flushInterval;
    }

    public void setFlushInterval(TimeValue flushInterval) {
        this.flushInterval = flushInterval;
    }

    public BackoffPolicy getBackoffPolicy() {
        return backoffPolicy;
    }

    public void setBackoffPolicy(BackoffPolicy backoffPolicy) {
        this.backoffPolicy = backoffPolicy;
    }

    public BulkProcessingOptionsBuilder builder() {
        return new BulkProcessingOptionsBuilder();
    }
}
