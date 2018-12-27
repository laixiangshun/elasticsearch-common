package utils;

import mapping.IElasticSearchMapping;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * elasticsearch 工具类
 **/
public class ElasticSearchUtils {

    private static final Logger log = Loggers.getLogger(ElasticSearchUtils.class, ElasticSearchUtils.class.getName());

    /**
     * 判断index是否存在
     **/
    public static IndicesExistsResponse indexExist(Client client, String indexName) {
        return client.admin().indices().prepareExists(indexName).execute().actionGet();
    }

    /**
     * 创建index
     **/
    public static CreateIndexResponse createIndex(Client client, String indexName) {
        CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(indexName);
        CreateIndexResponse createIndexResponse = createIndexRequestBuilder.execute().actionGet();
        if (log.isDebugEnabled()) {
            log.debug("CreatedIndexResponse: isAcknowledged {}", createIndexResponse.isAcknowledged());
        }
        return createIndexResponse;
    }

    /**
     * 绑定mapping
     **/
    public static AcknowledgedResponse putMapping(Client client, String indexName, IElasticSearchMapping mapping) {
        String string = Strings.toString(mapping.getMapping());
        PutMappingRequest request = new PutMappingRequest(indexName).type(mapping.getIndexType()).source(string, XContentType.JSON);
        AcknowledgedResponse acknowledgedResponse = client.admin().indices().putMapping(request).actionGet();
        if (log.isDebugEnabled()) {
            log.debug("PutMappingResponse: isAcknowledged {}", acknowledgedResponse.isAcknowledged());
        }
        return acknowledgedResponse;
    }

    /**
     * 删除指定的index
     **/
    public static AcknowledgedResponse deleteIndex(Client client, String indexName) {
        return client.admin().indices().prepareDelete(indexName).execute().actionGet();
    }
}
