package com.epsilon.cp4j.segmenttree;

import java.util.Arrays;
import java.util.function.BiFunction;

public class SegmentTree<T> implements ISegmentTree<T> {
    private final int arraySize;
    private final T[] tree;
    private final T neutral;
    private final BiFunction<T, T, T> combinator;

    public SegmentTree(T[] arr, T neutral, BiFunction<T, T, T> combinator) {
        this.arraySize = arr.length;
        this.tree = (T[]) new Object[arraySize << 1];
        this.neutral = neutral;
        this.combinator = combinator;

        Arrays.fill(tree, neutral);

        for (int i = 0; i < arraySize; i++)
            tree[i + arraySize] = arr[i];

        for (int i = arraySize - 1; i > 0; i--)
            tree[i] = combinator.apply(tree[i << 1], tree[(i << 1) | 1]);
    }

    @Override
    public void pointUpdate(int idx, T value) {
        for (tree[idx += arraySize] = value; idx > 0; idx >>= 1)
            tree[idx >> 1] = combinator.apply(tree[idx], tree[idx ^ 1]);
    }

    @Override
    public T rangeQuery(int l, int r) {
        T res = neutral;
        for (l += arraySize, r += arraySize + 1; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res = combinator.apply(res, tree[l++]);
            if ((r & 1) == 1) res = combinator.apply(res, tree[--r]);
        }
        return res;
    }
}
