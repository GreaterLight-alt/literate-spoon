
# Makefile to compile classes
# Ziyanda Mthethwa
# 2025/03/14
# binary search program makefile
# Binary tree and generics project makefile
# Based on Hussein Suleman's format
# March 15, 2025



JAVAC = /usr/bin/javac
JAR = /usr/bin/jar
JVM = java

# Java source files
SOURCES = $(wildcard *.java)

# Java class files (created by compiling Java source files)
CLASSES = $(SOURCES:.java=.class)

# Default target: compile all the classes
default: classes

# Target to compile all Java source files
classes: $(CLASSES)

# Rule to compile a .java file into a .class file
%.class: %.java
	$(JAVAC) $<

# Clean up: remove all class files
clean:
	rm -f $(CLASSES)
