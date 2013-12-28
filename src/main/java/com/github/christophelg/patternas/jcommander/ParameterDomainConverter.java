package com.github.christophelg.patternas.jcommander;

import java.io.File;

import com.beust.jcommander.IStringConverter;
import com.github.christophelg.patternas.ParameterDomain;

public class ParameterDomainConverter implements IStringConverter<ParameterDomain> {

  @Override
  public ParameterDomain convert(String value) {
    ParameterDomain result = new ParameterDomain();
    String[] s = value.split("=");
    result.setVariableName(s[0]);
    result.setInputVariableFile(new File(s[1]));

    return result;
  }
}
