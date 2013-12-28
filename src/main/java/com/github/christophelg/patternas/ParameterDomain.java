package com.github.christophelg.patternas;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ParameterDomain {

  private String variableName;
  private File inputVariableFile;
  private List<String> domainValues;

  public String getVariableName() {
    return variableName;
  }

  public void setVariableName(String variableName) {
    this.variableName = variableName;
  }

  public File getInputVariableFile() {
    return inputVariableFile;
  }

  public void setInputVariableFile(File inputVariableFile) {
    this.inputVariableFile = inputVariableFile;
  }

  public void load() throws IOException {
    domainValues = Files.readLines(inputVariableFile, Charsets.US_ASCII);
  }

  public List<String> getValues() {
    return domainValues;
  }

  @Override
  public String toString() {
    return "ParameterDomain [variableName=" + variableName + ", inputVariableFile="
        + inputVariableFile + ", domainValues=" + domainValues + "]";
  }



}
