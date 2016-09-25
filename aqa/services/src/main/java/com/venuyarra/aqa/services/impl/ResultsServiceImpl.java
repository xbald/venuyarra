package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.ClientResultDao;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.services.ResultsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NIKOLAI on 21.09.2016.
 */
@Transactional
public class ResultsServiceImpl implements ResultsService {
    private ClientResultDao resultDao;

    public void saveOrUpdateResult(ClientResponse clientResponse) {
        resultDao.saveOrUpdate(clientResponse);
    }

    public void setResultDao(ClientResultDao resultDao) {
        this.resultDao = resultDao;
    }
}
