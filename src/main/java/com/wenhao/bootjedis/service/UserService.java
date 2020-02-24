package com.wenhao.bootjedis.service;

public interface UserService {

    String getString(String key);

    void delString(String key);

    void exprieString(String key,String value);
}
