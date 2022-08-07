package com.epsilon.cp4j.segmenttree;

public interface ISegmentTree<T> {
    void pointUpdate(int idx, T value);
    T rangeQuery(int l, int r);
}
