package com.github.christophelg.patternas.jcommander;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ParameterDomainValidator implements IParameterValidator {

  @Override
  public void validate(String name, String value) throws ParameterException {
    if (value.indexOf('=') == -1) {
      throw new ParameterException("Domain parameter should follow the pattern: X=file.txt");
    }
  }
}
