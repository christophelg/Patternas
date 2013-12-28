package com.github.christophelg.patternas.printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class FilePrinter implements Printer {

  private STGroup templateGroup;
  private String contentTemplateName;
  private String filenameTemplateName;
  private String outputDirectory;

  private ST contentTemplate;
  private ST filenameTemplate;

  public FilePrinter(STGroup tGroup, String ctn, String ftn, String od) {
    templateGroup = tGroup;
    contentTemplateName = ctn;
    filenameTemplateName = ftn;
    outputDirectory = od;
  }

  @Override
  public void open() {
    contentTemplate = templateGroup.getInstanceOf(contentTemplateName);
    filenameTemplate = templateGroup.getInstanceOf(filenameTemplateName);
  }

  @Override
  public void add(String domain, Object value) {
    contentTemplate.add(domain, value);
    filenameTemplate.add(domain, value);
  }

  @Override
  public void render() {
    String outputFilename = filenameTemplate.render();
    File outputFile = new File(outputDirectory, outputFilename);
    try {
      PrintStream ps = new PrintStream(outputFile);
      ps.println(contentTemplate.render());
      ps.close();
    } catch (FileNotFoundException e) {
      System.err.println("Unable to output template in:" + outputFile.getAbsolutePath());
      e.printStackTrace();
    }
  }
}
