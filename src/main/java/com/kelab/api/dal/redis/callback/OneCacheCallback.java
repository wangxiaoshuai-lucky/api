package com.kelab.api.dal.redis.callback;

public interface OneCacheCallback<K, V> {

    V queryFromDB(K missKey);

}
