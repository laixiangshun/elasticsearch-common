package client.bulk.configuration;

import client.bulk.listener.LoggingBulkProcessorListener;
import client.bulk.options.BulkProcessingOptions;
import client.bulk.options.BulkProcessingOptionsBuilder;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.client.Client;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public class BulkProcessorConfiguration {
    private BulkProcessingOptions options = new BulkProcessingOptionsBuilder().build();
    private BulkProcessor.Listener listener = new LoggingBulkProcessorListener();

    public BulkProcessorConfiguration(BulkProcessingOptions options) {
        this(options, new LoggingBulkProcessorListener());
    }

    public BulkProcessorConfiguration(BulkProcessingOptions options, BulkProcessor.Listener listener) {
        this.options = options;
        this.listener = listener;
    }

    public BulkProcessingOptions getOptions() {
        return options;
    }

    public void setOptions(BulkProcessingOptions options) {
        this.options = options;
    }

    public BulkProcessor.Listener getListener() {
        return listener;
    }

    public void setListener(BulkProcessor.Listener listener) {
        this.listener = listener;
    }

    public BulkProcessor build(Client client) {
        return BulkProcessor.builder(client, listener)
                .setConcurrentRequests(options.getConcurrentRequests())
                .setBackoffPolicy(options.getBackoffPolicy())
                .setBulkActions(options.getBulkActions())
                .setBulkSize(options.getBulkSize())
                .setFlushInterval(options.getFlushInterval())
                .build();
    }
}
