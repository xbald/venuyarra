package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.Parameter;

import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public interface ParameterDao {
    List<Parameter> get(Long id);
}
