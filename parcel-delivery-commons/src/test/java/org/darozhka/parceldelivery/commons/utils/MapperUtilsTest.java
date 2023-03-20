package org.darozhka.parceldelivery.commons.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @author S.Darozhka
 */
class MapperUtilsTest {

    @Test
    public void testMappedToJsonAndBack() throws Exception {
        Assertions.assertNull(MapperUtils.toJson(null));

        List<String> list = Arrays.asList("1","2","3","4","5");

        String listJson = MapperUtils.toJson(list);
        Assertions.assertNotNull(listJson);

        List<String> listNew = MapperUtils.fromJson(listJson, List.class);
        Assertions.assertEquals(list, listNew);

        Map<String, String> map = new HashMap<>();
        map.put("key1", "1");
        map.put("key2", "2");
        map.put("key3", "3");
        map.put("key4", "4");
        map.put("key5", "5");

        String mapJson = MapperUtils.toJson(map);
        Assertions.assertNotNull(mapJson);

        Map<String, String> mapNew = MapperUtils.fromJson(mapJson, Map.class);
        Assertions.assertEquals(map, mapNew);
    }

}