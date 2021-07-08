package com.raven.common.utils;

import java.util.HashMap;
import java.util.Map;

public class MapperUtils {
    Map<String, Object> mapper = new HashMap<>();

    public MapperUtils put(String name, Object val) {
        mapper.put(name, val);
        return this;
    }

    public Map<String, Object> getMaped() {
        return mapper;
    }
}
