package mapping;

import com.google.common.collect.ImmutableMap;
import exceptions.GetMappingFailedException;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.mapper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public abstract class BaseElasticSearchMapping implements IElasticSearchMapping {

    private final String indexType;

    private final Version version;

    public BaseElasticSearchMapping(String indexType, Version version) {
        this.indexType = indexType;
        this.version = version;
    }

    public XContentBuilder getMapping() {
        try {
            return internalGetMapping();
        } catch (IOException e) {
            throw new GetMappingFailedException(indexType, e);
        }
    }

    public XContentBuilder internalGetMapping() throws IOException {
        RootObjectMapper.Builder rootObjectBuilder = getRootObjectBuilder();
        Settings.Builder settingsBuilder = getSettingsBuilder();
        Mapping mapping = new Mapping(version,
                rootObjectBuilder.build(new Mapper.BuilderContext(settingsBuilder.build(), new ContentPath(1))),
                getMetaDataFieldMappers(),
                getMeta());
        return mapping.toXContent(XContentFactory.jsonBuilder().startObject(), new ToXContent.MapParams(Collections.emptyMap())).endObject();
    }

    public Settings.Builder getSettingsBuilder() {
        Settings.Builder builder = Settings.builder().put(IndexMetaData.SETTING_VERSION_CREATED, version)
                .put(IndexMetaData.SETTING_CREATION_DATE, System.currentTimeMillis());
        configureSettingsBuilder(builder);
        return builder;
    }

    public RootObjectMapper.Builder getRootObjectBuilder() {
        RootObjectMapper.Builder builder = new RootObjectMapper.Builder(indexType);
        configureRootObjectBuilder(builder);
        return builder;
    }

    public MetadataFieldMapper[] getMetaDataFieldMappers() {
        List<MetadataFieldMapper> fieldMappers = new ArrayList<>();
        configureMetaDataFieldMappers(fieldMappers);
        return fieldMappers.toArray(new MetadataFieldMapper[0]);
    }

    public ImmutableMap<String, Object> getMeta() {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        configureMetaFields(builder);
        return builder.build();
    }

    protected abstract void configureRootObjectBuilder(RootObjectMapper.Builder builder);

    protected void configureMetaDataFieldMappers(List<MetadataFieldMapper> fieldMappers) {
    }


    protected void configureMetaFields(ImmutableMap.Builder<String, Object> builder) {
    }

    protected void configureSettingsBuilder(Settings.Builder builder) {
    }

    public String getIndexType() {
        return indexType;
    }

    public Version getVersion() {
        return version;
    }
}
