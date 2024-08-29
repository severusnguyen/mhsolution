package com.mh.core.mhscommons.core.json;

@FunctionalInterface
public interface SupplierThrowable<T> {
    T get() throws Exception;
}
