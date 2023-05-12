package javafundamentals.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

public class MergeJsonNodes {
    public static JsonNode merge(JsonNode originNode, JsonNode addNode) {
        Iterator<String> fieldNames = addNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode jsonNode = originNode.get(fieldName);

            if (jsonNode != null && jsonNode.isObject()) {
                merge(jsonNode, addNode.get(fieldName));
            } else {
                if (originNode instanceof ObjectNode) {
                    JsonNode value = addNode.get(fieldName);
                    ((ObjectNode) originNode).set(fieldName, value);
                }
            }
        }
        return originNode;
    }

}
