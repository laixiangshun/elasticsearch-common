package client.bulk.listener;

import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.logging.Loggers;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public class LoggingBulkProcessorListener implements BulkProcessor.Listener {
    private static final Logger log = Loggers.getLogger(LoggingBulkProcessorListener.class, LoggingBulkProcessorListener.class.getName());

    public LoggingBulkProcessorListener() {
    }

    @Override
    public void beforeBulk(long executionId, BulkRequest bulkRequest) {
        if (log.isDebugEnabled()) {
            log.debug("ExecutionId = {}, Actions = {}, Estimated Size = {}", executionId, bulkRequest.numberOfActions(), bulkRequest.estimatedSizeInBytes());
        }
    }

    @Override
    public void afterBulk(long executionId, BulkRequest bulkRequest, BulkResponse bulkResponse) {
        if (log.isDebugEnabled()) {
            log.debug("ExecutionId = {}, Actions = {}, Estimated Size = {}", executionId, bulkRequest.numberOfActions(), bulkRequest.estimatedSizeInBytes());
        }
    }

    @Override
    public void afterBulk(long executionId, BulkRequest bulkRequest, Throwable throwable) {
        if (log.isErrorEnabled()) {
            log.error("ExecutionId = {}, Error = {}", executionId, throwable);
        }
    }
}
