package app.ladderproject.core.mapper.jackson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.util.Map;

/**
 * @author nima
 * @apiNote this class Used For convert General Object To Another
 */
public interface Mapper {
    /**
     * @param o is the Object that you need To convert To json
     * @return the Json String
     */
    String convertToJson(Object o);

    /**
     * @param json is the json that Type is String
     * @param <G>  is the type Of Response Class
     * @return the Object of the Json
     */
    <G> G jsonToObject(String json, Class<G> aClass);

    /**
     * @param <G> is the type Of Response Class
     * @return the Object of the Json
     */
    <G> G mapToObject(Map<String, Object> obj, Class<G> aClass);

    /**
     * @param <G> is the type Of Response Class
     * @return the Object of the Json
     */
    <G> G mapStrToObject(Map<String, String> obj, Class<G> aClass);


    /**
     * @param json is the json that Type is String
     * @param <G>  is the type Of Response Class
     * @return the Object of the Json
     */
    <G> G jsonToObject(String json, TypeReference<G> reference);

    <G, T> JavaType castToJavaType(Class<G> g, Class<T> t);

    /**
     * @param json is the json that Type is String
     * @param <G>  is the type Of Response Class
     * @return the Object of the Json
     */
    <G> G jsonToObject(String json, JavaType javaType);
}
