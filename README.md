#HGE: the Hyper-Graph Explorer system

[![License](http://img.shields.io/:license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

##Introduction

This project contains a Java software implementing a data modeling and querying system relying on an hyper-graph.

HGE implements:

  * a hierarchical data modeling system used to describe real data 
  * a data topology representation relying on a hyper-graph
  * a query system enabling to explore the data graph using hyper-graph queries (relies on [subgraph isomorphism problem](https://en.wikipedia.org/wiki/Subgraph_isomorphism_problem))
  * two ways of writing queries: using an API or a graph query language ([more here](./doc/README.md))

##Requirements

Use a [Java Virtual Machine](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 1.5 (or above) from Oracle. 

*Not tested with any other JVM providers but Oracle... so there is no guarantee that the software will work as expected if not using Oracle's JVM.*

##HGE: table of contents

HGE provides several packages to enable the following features:

* "**datamodel**" package: use it to describe your real data. The data modeling scheme relies on a hyper-graph of data types: 
data elements are node types (or vertices) that are linked together using hyper-edge types. The latter offers the advantage 
of representing n-ary relations (n>=2). So, a "datamodel" is a hyper-graph of data "types". Each type may contain some attributes of type string, integer, float, boolean, etc.

* "**hypergraph**" package: use it to represent instances of real data with an hyper-graph topology. So, this is the real world graph, where
vertices and hyper-edges are typed with the data model.

* "**query**" package: use it to setup and run hyper-graph queries. It is worth noting that the query 
execution engine implements a method to solve the [subgraph isomorphism problem](https://en.wikipedia.org/wiki/Subgraph_isomorphism_problem) in the context of an hyper-graph 
containing data types and values. 

* "**function**" package: provides a way to implement custom-made manipulation of data. Functions are then used 
by their name in a query.

Other packages are support codes to implement the entire system, mostly the query language parser/validator and the query execution engine. 

##HGE: how to use it with real data?

HGE comes with a concrete implementaton of the entire query system: query representation and execution; as stated above, the query engine implements a method to solve the subgraph isomorphism problem.

HGE also provides an abstract formulation of data model and graph. However, it does not provide a concrete implementation of these model and graph. It is up to the developer to provide them, as follows.

To use the system you just have to implement the interfaces of packages "datamodel" and "hypergraph"; so, first have a look at these APIs. Then, you can review a real example by having a look at the [BLAST Filter Tool](https://github.com/pgdurand/BLAST-Filter-Tool) project: it provides an implementation of HGE to deal with annotated BLAST results data; this is an application of HGE in the field of bioinformatics.

##HGE: develop using an IDE or Ant

I use Eclipse for development purpose, so HGE can be imported in that IDE (see dependencies, below).

Otherwise, you can use any other Java IDE and/or work on the command-line: a 'build.xml' for Ant 1.7+ is provided. 

##HGE: dependencies

HGE does not depend on any other external libraries but JUnit 4. However, JAR for JUnit is not provided with HGE. 

Either you have JUnit available on your system (e.g. it is provided with Eclipse IDE) and you can compile HGE "as it is" 
(do not forget to add JUnit to your build path). Or you can compile HGE source code without the "test" package to avoid errors.

The HGE query language parser has been compiled from a grammar file using JavaCC 3.2. However, you do not need JavaCC jar file
to compile HGE, since parser classes have already been generated (within package bzh.plealog.hge.parser).

##License

HGE is is released under the [Apache 2 license](http://www.apache.org/licenses/LICENSE-2.0). 

##A short story of HGE (2003-today)

The idea of HGE comes by November 2003 when I had to develop a software to explore biological data sets. At that period of time, I was working in the field of "bioinformatics", dealing with DNA and protein sequences in general and with the deciphering of [BLAST](http://blast.ncbi.nlm.nih.gov/Blast.cgi) results in the context of bacterial genome annotation. 

Instead of using a classical approach relying on R-DBMS, I wanted to explore a solution of mine using the concept of a graph; mainly because graphs are wonderful objects to describe biological data. Two key issues of the system I needed were (1) to be able to represent n-ary relations, with n>2, and (2) to be able to setup and execute hyper-graph based queries. Because a standard graph cannot handle n-ary relations (e.g. [Genolink](http://bmcbioinformatics.biomedcentral.com/articles/10.1186/1471-2105-7-21)) and because I did not find any adapted solutions at that time, I decided to develop from scratch my own system. 

From 2007 to 2015, it was licensed to the company Korilog to become the core of the data modeling and querying system of two other softwares I designed: [KoriBlast and ngKLAST](http://plealog.com/korilog/product-sheets/ngKLAST.pdf).

Finally, when Korilog ceased its activities on June 2015, I decided to release HGE source code to the open source community. Of course, it has a direct usage in the bioinformatics field (through the [Blast Filter Tool](http://)). But who knows, maybe it could have other uses in other fields. 

First release of HGE was written using Java 1.4. Then, it was ported to Java 5.

--
(c) 2006-2016 - Patrick G. Durand
