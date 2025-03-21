# binary search program makefile
# Hussein Suleman




# Makefile for CSC2001F Assignment 2
#
# This Makefile compiles Java source files from the src/ directory into bin/
# and generates Javadoc documentation in the doc/ directory.
#
# Usage:
#   make         (to compile the project)
#   make doc     (to generate Javadoc documentation)
#   make clean   (to remove compiled class files and documentation)
#
# Note: Javadoc does not generate documentation for classes in the default package
# when using -subpackages. In this variant, we list the source files directly.

# Define the Java compiler command.
JAVAC = javac
# Define the Javadoc command.
JAVADOC = javadoc

# Compiler flags:
# '-d bin' directs the compiler to output the class files into the 'bin' directory.
# '-sourcepath src' tells the compiler where to find the source files.
JFLAGS = -d bin -sourcepath src

# Define SRC as all Java source files in the src/ directory.
SRC := $(wildcard src/*.java)

# Define the 'all' target. This is a phony target (not a file) that depends on the 'bin' target.
.PHONY: all
all: bin
	$(JAVAC) $(JFLAGS) $(SRC)

# Define the 'bin' target to create the bin/ directory if it doesn't exist.
bin:
	mkdir -p bin

# Define the 'doc' target as a phony target for generating documentation.
.PHONY: doc
doc:
	$(JAVADOC) -d doc $(SRC)

# Define the 'clean' target as a phony target to remove generated files.
.PHONY: clean
clean:
	$(RM) bin/*
	rm -rf doc