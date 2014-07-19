# Patternas

Patternas is a tool that generates its output from a template definition and some data series.
The output is the result of a generation strategy that will multiplex the data series with the template.

## Motivation

This tool was created to answer the need to generate SQL scripts, for test data or migration, or configuration scripts.
For example for an invoicing application, one might want to generate some exhaustive SQL test data from 3 data sets:
1 set of dates
2 set of customers
3 set of products 

## Solution

Use the StringTemplate template to define the templates to generate the output, and optionally, the name of the 
files where to direct the output, the tool will accept command line parameter of the form '--domain X=data4X.txt'.
The 'X' is a name of a parameter as it appears in the template definition, the content of 'data4X.txt' will be bind to 
this variable.

### Generation Strategy

The tool support 2 types of generation
* Cartesian (default): All the data series are multiplexes with each other
* Linear: The data series are used row by row, shorter data series are warped around to match the length of longer ones

### Output Strategy

* Syso based (default): All the generation is outputted to the standard output
* File based: Every generation is outputted to its own file

# Examples

There is a detailed usage displayed when the tool is called without any argument, use it !
NB: The data for the examples is in the source tree.

## Cartesian output

	java -jar patternas-1.0.jar 
		--templateFilename test.stg
		--domain date=dates.txt 
		--domain quote=quotes.txt 
		--filenameTemplateName outputFilename

This example will:
* read the template file 'test.stg', and use the template 'content' for content generation
* bind the 2 parameters 'date' and 'quote' to their respective data
* use the cartesian generation strategy
* output each generation in the filename given by the template 'outputFilename'

		
## Linear output

	java -jar patternas-1.0.jar 
		--templateFilename test.stg
		--generationStrategy linear
		--domain date=dates.txt 
		--domain quote=quotes.txt

This example will:
* read the template file 'test.stg', and use the template 'content' for content generation
* bind the 2 parameters 'date' and 'quote' to their respective data
* use the linear generation strategy, because data for 'date' is shorter than for 'quote', we loop over it several times
* output everything to the standard ouput
