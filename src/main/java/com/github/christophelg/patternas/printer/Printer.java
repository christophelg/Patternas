package com.github.christophelg.patternas.printer;


public interface Printer {

  void open();

  void add(String domain, Object value);

  void render();
}
