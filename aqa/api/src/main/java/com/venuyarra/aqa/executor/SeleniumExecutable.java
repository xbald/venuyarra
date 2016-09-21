package com.venuyarra.aqa.executor;

import com.venuyarra.aqa.dto.ClientResponse;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface SeleniumExecutable {
    ClientResponse execute(SeleniumCommandExecutor executor);
}
