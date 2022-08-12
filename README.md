# Unified Abstract Syntax Tree

![Build and test](https://github.com/unified-ast/unified-ast/workflows/Build%20and%20test/badge.svg)
[![Codecov](https://codecov.io/gh/unified-ast/unified-ast/branch/master/graph/badge.svg)](https://codecov.io/gh/unified-ast/unified-ast)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/unified-ast/unified-ast/blob/master/LICENSE.txt)
___

## Brief

*Unified Abstract Syntax Tree (UAST)* is a generalized model of a syntax tree
constructed from abstract syntax trees of several programming languages.

This project collects classes describing the UAST and also adapters that convert initial formats of ASTs of different 
languages into the unified structure.

We assume that our UASTs can be used to simplify static code analysis algorithms.

For now the project contains a partial unification of:
- Java - from [JavaParser](https://javaparser.org/) AST
- JavaScript - from [ANTLR](https://github.com/antlr/grammars-v4/tree/master/javascript/javascript) AST
- Python - from [ANTLR](https://github.com/antlr/grammars-v4/tree/master/python/python) AST

To create a source code for UAST nodes and transformation rules we use [Astranaut](https://github.com/cqfn/astranaut).

## Motivation

There are static code analyzers that use AST models to process source code.
In the case of increasing demand for a software product, developers face the need to expand the functionality 
of the program and transfer the logic of algorithms to new relevant programming languages.

In general, for each programming language to be processed, a different parser is used.
Each parser provides a unique structure of an AST.
Even for similar languages and similar constructions of these languages, existing third-party parsers 
yield ASTs of different structures.
Therefore, adaptation of existing algorithms for new syntax trees is a time-consuming task.

We suppose that creation of a UAST for several required programming languages would allow developers 
to use the same algorithms to analyze the code of several languages.

## Requirements

* Java 1.8
* Maven 3.6.3+ (to build)

## How to build and download

TODO

## How to use

### Command line interface

> Here and below, it is assumed that the name of the executable file is `uast.jar`.

In the CLI mode, the project performs transformation of initial ASTs into UAST:
it loads a file with a source code in a general-purpose programming language, parses it with a third-party parser 
and adapts the result into the unified format.

Syntax:

```
java -jar uast.jar --parse <path to source file> [optional arguments] 
```

Required arguments:

* `--parse` (short: `-p`), the path to a file that contains a source code in some supported programming languages.
  Expected file extensions are `.txt`, `.java`, `.js`, `.py`.

* `--lang` (short: `-l`), the name of the source file language. For Java, it should be `java`, 
  for JavaScript - `js` or `javascript`, for Python - `python`. 
  This option is required if the file with the source code has a `.txt` format, otherwise you can omit it.
  
Optional arguments:

* `--output` (short: `-o`), the path to the `JSON` file where the result syntax tree will be saved
  in a serialized format, file extension is `.json`. The `JSON` format is described [here](https://github.com/cqfn/astranaut#syntax-tree-representation).

* `--visualize ` (short: `-v`), the path to the image file where the result syntax tree will be saved
    in a graphical format. Supported image extensions are `.png` and`.svg`.

* `--raw` (short: `-r`), without a parameter, indicates the necessity to disable the conversion of an AST into a UAST. 
  Supported image extensions are `.png` and`.svg`. You can use it, for example, in combination with visualizing
  to conduct research on a unification of syntax trees.

Example:

```
java -jar uast.jar --parse D:\sources\MyClass.java -o D:\storage\MyClass.json -v D:\storage\MyClass.png -r
```

## Project structure

TODO: redraw existing scheme

## Ongoing research

The unification of ASTs is a research task. Currently, we conduct experiments on creation of the UAST from Java,
JavaScript and Python ASTs.
This projects helps us to test and collect results of our approach.

### Example

To describe the *prerequisites* for the creation of UAST more clearly, let us provide an example.

Below there are 3 code snippets in Java, JavaScript and Python languages. All of them are semantically similar.

Java:

```java
class Count {
  public int add(final int a) {
    return a + 10;
  }
}
```

JavaScript:

```javascript
class Count {
    add(a) {
        return a + 10;
    }
}
```

Python:

```python
class Count:
    def add(a):
        return a + 10
```

We used the JavaParser parser to compile the Java code, and ANTLR grammars with some additional simplification
for JavaScript and Python languages.
The following images show ASTs constructed from the provided code snippets.

Java:

![ast_java_raw](src/main/documents/ast_java_raw.png)

JavaScript:

![ast_js_raw](src/main/documents/ast_js_raw.png)

Python:

![ast_py_raw](src/main/documents/ast_py_raw.png)


It can be noticed that same language constructs (like `class declaration` or `addition` binary expression), 
which have identical syntax in all three languages, have different representation in the corresponding ASTs.


It is also worth noting that it is not only ASTs built with different parsers that distinguish. 
Parsers from the same ANTLR developer for Python and JavaScript grammar create ASTs which subtrees are also different.

### Approach

With the usage of [Astranaut](https://github.com/cqfn/astranaut), we generated a set of classes and rules that
unify the constructions in code snippets provided above.

Result trees look like this:

Java:

![ast_java_unified](src/main/documents/ast_java_unified.png)

JavaScript:

![ast_js_unified](src/main/documents/ast_js_unified.png)

Python:

![ast_py_unified](src/main/documents/ast_py_unified.png)


In the UASTs JavaScript and Python snippets have equal representation.
The UAST of Java has additional nodes, but base nodes, which are significant within the semantics, are
also the same.

### Contributors

* Ivan Kniazkov, @kniazkov
* Polina Volkhontseva, @pollyvolk

See our [Contributing policy](CONTRIBUTING.md).