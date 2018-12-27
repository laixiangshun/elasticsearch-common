package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * @Description
 * @Author hasee
 * @Date 2018/12/27
 **/
public class JsonUtils {
    private static final Logger log = LogManager.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <TEntity> Optional<byte[]> convertJsonToBytes(TEntity tEntity) {
        try {
            return Optional.of(mapper.writeValueAsBytes(tEntity));
        } catch (JsonProcessingException e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Failed to convert entity %s to JSON", tEntity), e);
            }
        }
        return Optional.empty();
    }

}
