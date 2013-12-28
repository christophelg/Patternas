package com.github.christophelg.patternas.printer;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class SysoutPrinter implements Printer {

  private STGroup templateGroup;
  private String contentTemplateName;
  private ST contentTemplate;

  public SysoutPrinter(STGroup tGroup, String ctn) {
    templateGroup = tGroup;
    contentTemplateName = ctn;
  }

  @Override
  public void open() {
    contentTemplate = templateGroup.getInstanceOf(contentTemplateName);
  }

  @Override
  public void add(String domain, Object value) {
    contentTemplate.add(domain, value);
  }

  @Override
  public void render() {
    System.out.println(contentTemplate.render());
  }

}
