package com.literaturaPhatos.literaturaAPI.service;

public interface IDataConversor {
    <T> T convertData(String json, Class<T> clase);
}
