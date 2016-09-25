package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.ClientResponse;

/**
 * Created by NIKOLAI on 25.09.2016.
 */
public interface ClientResultDao {
    ClientResponse saveOrUpdate(ClientResponse clientResponse);
}
