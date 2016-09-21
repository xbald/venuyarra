package com.venuyarra.aqa.executor;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.SelectCommand;
import com.venuyarra.aqa.dto.ValidationCommand;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface SeleniumCommandExecutor  {
    ClientResponse execute(ClickCommand command);
    ClientResponse execute(EnterCommand command);
    ClientResponse execute(ValidationCommand command);
    ClientResponse execute(SelectCommand selectCommand);
}
