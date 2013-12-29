package com.github.christophelg.patternas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.github.christophelg.patternas.generator.CartesienGenerator;
import com.github.christophelg.patternas.generator.Generator;
import com.github.christophelg.patternas.generator.LinearGenerator;
import com.github.christophelg.patternas.jcommander.ParameterDomainConverter;
import com.github.christophelg.patternas.jcommander.ParameterDomainValidator;
import com.github.christophelg.patternas.printer.FilePrinter;
import com.github.christophelg.patternas.printer.Printer;
import com.github.christophelg.patternas.printer.SysoutPrinter;

public class Patternas {

  @Parameter(names = "--domain", required = true, validateWith = ParameterDomainValidator.class, converter = ParameterDomainConverter.class)
  private List<ParameterDomain> domains = new ArrayList<ParameterDomain>();

  @Parameter(names = "--templateFilename", required = true)
  private String templateFilename;

  @Parameter(names = "--contentTemplateName")
  private String contentTemplateName = "content";

  // Kept null will mean no per-file output
  @Parameter(names = "--filenameTemplateName")
  private String filenameTemplateName;

  @Parameter(names = "--outputDir")
  private String outputDirname = ".";

  @Parameter(names = "--generationStrategy")
  private String generationStrategy = "cartesian";

  public static void main(String[] args) throws IOException {
    Patternas sp = new Patternas();

    JCommander jc = new JCommander(sp);
    jc.setProgramName("Patternas");
    try {
      jc.parse(args);
      sp.run();
    } catch (ParameterException pe) {
      jc.usage();
    }

  }

  public void run() throws IOException {

    // preload the domains
    for (ParameterDomain pd : domains) {
      pd.load();
    }

    // get the template file
    STGroup templateGroup = new STGroupFile(templateFilename, '$', '$');

    // get the printer
    Printer printer = new SysoutPrinter(templateGroup, contentTemplateName);
    if (filenameTemplateName != null) {
      printer = new FilePrinter(templateGroup, contentTemplateName, filenameTemplateName, outputDirname);
    }

    Generator generator = new CartesienGenerator(domains, printer);
    if (generationStrategy.equals("linear")) {
      generator = new LinearGenerator(domains, printer);
    }
    generator.generate();
  }
}
