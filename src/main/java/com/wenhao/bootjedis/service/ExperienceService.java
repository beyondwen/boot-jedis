package com.wenhao.bootjedis.service;

import java.util.concurrent.Future;

public interface ExperienceService {

    void businessExperience(Long increment);

    Future<Long> controlService(String id);
}
