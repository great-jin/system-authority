package xyz.ibudai.authority.common.cache;

import org.springframework.util.CollectionUtils;
import xyz.ibudai.authority.common.enums.CacheOperate;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseCache<K, V> {

    /**
     * 根据 Key 读取
     *
     * @param key   the key
     * @param cache 缓存容器
     */
    public Set<V> read(K key, Map<K, Set<V>> cache) {
        return cache.getOrDefault(key, Collections.emptySet());
    }

    /**
     * 根据 Key 批量读取
     *
     * @param keys  the key
     * @param cache 缓存容器
     * @return the set
     */
    public Set<V> read(Set<K> keys, Map<K, Set<V>> cache) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }

        return cache.entrySet()
                .stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * 缓存容器计算
     *
     * @param cache   缓存容器
     * @param operate 操作类型
     * @param key     the key
     * @param values  the values
     */
    public void compute(Map<K, Set<V>> cache, CacheOperate operate, K key, Set<V> values) {
        if (CollectionUtils.isEmpty(values)) {
            return;
        }

        cache.compute(key, (k, v) -> {
            if (Objects.isNull(v)) {
                v = new HashSet<>();
            }
            switch (operate) {
                case ADD:
                    v.addAll(values);
                    break;
                case DELETE:
                    v.removeAll(values);
                    break;
                default:
                    throw new IllegalArgumentException("Not support type of " + operate);
            }
            return v;
        });
    }
}
