package client.bulk.options;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public class BulkProcessingOptionsBuilder {
    private String name;
    private int concurrentRequests = 1;
    private int bulkActions = 1000;
    private ByteSizeValue bulkSize = new ByteSizeValue(5, ByteSizeUnit.MB);
    private TimeValue flushInterval = null;
    private BackoffPolicy backoffPolicy = BackoffPolicy.exponentialBackoff();

    public BulkProcessingOptionsBuilder() {
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

    public BulkProcessingOptions build() {
        return new BulkProcessingOptions(name, concurrentRequests, bulkActions, bulkSize, flushInterval, backoffPolicy);
    }
}
