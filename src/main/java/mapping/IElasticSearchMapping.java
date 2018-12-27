package mapping;

import org.elasticsearch.Version;
import org.elasticsearch.common.xcontent.XContentBuilder;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public interface IElasticSearchMapping {
    XContentBuilder getMapping();

    String getIndexType();

    Version getVersion();
}
