package client;

import mapping.IElasticSearchMapping;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import utils.JsonUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public class ElasticSearchClient<TEntity> implements IElasticSearchClient<TEntity> {
    private final Client client;
    private final String indexName;
    private final IElasticSearchMapping mapping;
    private final BulkProcessor bulkProcessor;

    public ElasticSearchClient(Client client, String indexName, IElasticSearchMapping mapping, BulkProcessor bulkProcessor) {
        this.client = client;
        this.indexName = indexName;
        this.mapping = mapping;
        this.bulkProcessor = bulkProcessor;
    }

    @Override
    public void index(TEntity tEntity) {
        index(Collections.singletonList(tEntity));
    }

    @Override
    public void index(List<TEntity> entities) {
        index(entities.stream());
    }

    @Override
    public void index(Stream<TEntity> tEntityStream) {
        tEntityStream.map(JsonUtils::convertJsonToBytes)
                .filter(Optional::isPresent)
                .map(x -> client.prepareIndex()
                        .setIndex(indexName)
                        .setType(mapping.getIndexType())
                        .setSource(x.get(), XContentType.JSON)
                        .request())
                .forEach(bulkProcessor::add);
    }

    @Override
    public void flush() {
        bulkProcessor.flush();
    }

    @Override
    public synchronized void awaitClose(long timeout, TimeUnit unit) throws InterruptedException {
        bulkProcessor.awaitClose(timeout, unit);
    }

    @Override
    public void close() throws Exception {
        bulkProcessor.close();
    }
}
