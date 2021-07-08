package com.raven.common.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yiliang
 * @Date 2021/1/11
 */
public class StreamUtils {

    private StreamUtils() {
    }

    public static <T> Stream<T> getStream(List<T> list, int needParallelNum) {
        if (needParallelNum <= 0 || CollectionUtils.isEmpty(list)) {
            return Stream.empty();
        }
        Stream<T> stream;
        if (list.size() > needParallelNum) {
            stream = list.parallelStream();
        } else {
            stream = list.stream();
        }
        return stream;
    }

    public static <T> Stream<T> getStream(Set<T> set, int needParallelNum) {
        if (needParallelNum <= 0 || CollectionUtils.isEmpty(set)) {
            return Stream.empty();
        }
        Stream<T> stream;
        if (set.size() > needParallelNum) {
            stream = set.parallelStream();
        } else {
            stream = set.stream();
        }
        return stream;
    }

    public static <T> Set<T> getResult(Set<T> collection, int needParallelNum, int parallelNum) throws Exception {
        Set<T> result;
        if (collection.size() > needParallelNum) {
            ForkJoinPool pool = new ForkJoinPool(parallelNum);
            result = pool.submit(() -> collection.parallelStream().collect(Collectors.toSet())).get();
        } else {
            result = collection.stream()
                    .collect(Collectors.toSet());
        }
        return result;
    }

    public static <T> List<T> getResult(List<T> collection, int needParallelNum, int parallelNum) throws Exception {
        List<T> result;
        if (collection.size() > needParallelNum) {
            ForkJoinPool pool = new ForkJoinPool(parallelNum);
            result = pool.submit(() -> collection.parallelStream().collect(Collectors.toList())).get();
        } else {
            result = collection.stream().collect(Collectors.toList());
        }
        return result;
    }


}
