package com.wenhao.bootjedis.service;

import com.wenhao.bootjedis.domain.User;

public interface UserService {

    String getString(String key);

    void delString(String key);

    void exprieString(String key,String value);

    User setHash(String key);
}
