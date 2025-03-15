
# Makefile to compile classes
# Ziyanda Mthethwa
# 2025/03/14

# Java compiler path
JAVAC=/usr/bin/javac

# Find all .java files in the current directory
SOURCES=$(wildcard *.java)

# Convert .java files to .class files
CLASSES=$(SOURCES:.java=.class)

# Rule for compiling .java files to .class files
%.class: %.java
    $(JAVAC) $<

# Default target - compile all classes
all: $(CLASSES)

# Run Array
run-Array:
    @java GenericsKbArrayApp

# Run BST
run-BST:
    @java GenericsKbBSTApp

# Clean up class files
clean:
    rm -f *.class	
 