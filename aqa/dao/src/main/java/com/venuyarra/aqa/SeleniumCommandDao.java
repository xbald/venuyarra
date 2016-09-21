package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.SeleniumCommand;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface SeleniumCommandDao {
    List<SeleniumCommand> getByTestCaseId(Long testCaseId);

    SeleniumCommand get(Long id);
}
