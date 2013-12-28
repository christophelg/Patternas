package com.github.christophelg.patternas.generator;

import java.util.List;

import com.github.christophelg.patternas.ParameterDomain;
import com.github.christophelg.patternas.printer.Printer;

public class CartesienGenerator implements Generator {

  private List<ParameterDomain> domains;
  private Printer printer;

  public CartesienGenerator(List<ParameterDomain> doms, Printer p) {
    domains = doms;
    printer = p;
  }

  public void generate() {

    int cardinal = 1;
    for (ParameterDomain domain : domains) {
      cardinal *= domain.getValues().size();
    }

    for (int i = 0; i < cardinal; ++i) {
      printer.open();

      int chunk = 1;
      for (ParameterDomain domain : domains) {
        List<String> values = domain.getValues();
        printer.add(domain.getVariableName(), values.get((i / chunk) % values.size()));
        chunk *= values.size();
      }
      printer.render();
    }
  }
}
