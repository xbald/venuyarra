package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.ResultDao;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.services.ResultsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NIKOLAI on 21.09.2016.
 */
@Transactional
public class ResultsServiceImpl implements ResultsService {
    private ResultDao resultDao;

    public void addResult(ClientResponse clientResponse) {
        resultDao.addResult(clientResponse);
    }

    public void setResultDao(ResultDao resultDao) {
        this.resultDao = resultDao;
    }
}
