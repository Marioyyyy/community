package com.lxy.community.util;

import com.lxy.community.config.RedisConfig;
import com.lxy.community.dto.ViewCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Component
public class RedisUtil {

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * zet增加操作
     *
     * @param key
     * @param value 属性值
     * @param map   具体分数
     * @return
     */
    public Boolean zsAdd(String key, String value, HashMap<String, Object> map) {
        try {
//            redisTemplate.opsForZSet().add("viewNum", "h1", Double.valueOf(h1.get("viewNum").toString()));

            redisTemplate.opsForZSet().add(key, value, Double.valueOf(map.get(key).toString()));

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    /**
     * zset给某个key某个属性增值操作
     *
     * @param key
     * @param value 属性值
     * @param delta 增加值
     * @return
     */
    public Boolean zsIncr(String key, String value, Integer delta) {
        try {
            redisTemplate.opsForZSet().incrementScore(key, value, delta.doubleValue());


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * zscore 返回属性值
     *
     * @param key   key值
     * @param value 属性值
     * @return
     */
    public Double zscore(String key, String value) {
        Double score = redisTemplate.opsForZSet().score(key, value);
        return score;
    }

    public Boolean set(String key, Integer value) {

        try {
            redisTemplate.opsForValue().set(key, value);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {

        try {
            Object o = redisTemplate.opsForValue().get(key);

            return o;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public void incrView(String key) {

        redisTemplate.opsForValue().increment(key);
    }


    public List<ViewCountDTO> getViewCountFromRedis() {

        ArrayList<ViewCountDTO> dtoList = new ArrayList<>();
        //key("*") 获取所有键
        Set<String> keys = redisTemplate.keys("*");

        for (String key : keys) {

            //String类型的键值获取
            if (redisTemplate.type(key).code() == "string") {
                Object value = redisTemplate.opsForValue().get(key);
                ViewCountDTO viewCountDTO = new ViewCountDTO();
                viewCountDTO.setId(key);
                viewCountDTO.setView(Long.valueOf(value.toString()));
                dtoList.add(viewCountDTO);
            }
        }
        return dtoList;
    }
}
