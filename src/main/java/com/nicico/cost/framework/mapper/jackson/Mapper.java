package com.nicico.cost.framework.mapper.jackson;


import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author nima
 * @apiNote this class Used For convert General Object To Another
 */
public interface Mapper {
    /**
     * @param o is the Object that you need To convert To json
     * @return the Json String
     * @throws Exception not Throw In this Method
     */
    String convertToJson(Object o);

    /**
     * @param json is the json that Type is String
     * @return the Object of the Json
     * @param <G> is the type Of Response Class
     * @throws Exception not Throw In this Method
     */
    <G> G jsonToObject(String json,Class<G> aClass);

    /**
     * @param json is the json that Type is String
     * @param <G> is the type Of Response Class
     * @return the Object of the Json
     * @throws Exception not Throw In this Method
     */
    <G> G jsonToObject(String json, TypeReference<G> reference);

}
