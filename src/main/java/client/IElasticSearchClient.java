package client;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Elasticsearch 操作接口
 **/
public interface IElasticSearchClient<TEntity> extends AutoCloseable {

    void index(TEntity tEntity);

    void index(List<TEntity> entities);

    void index(Stream<TEntity> entityStream);

    void flush();

    void  awaitClose(long timeout, TimeUnit unit) throws InterruptedException;
}
