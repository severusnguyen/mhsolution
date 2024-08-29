package com.mh.core.mhscommons.core.extension;

@FunctionalInterface
public interface SupplierThrowable<T> {
    T get() throws Exception;
}
