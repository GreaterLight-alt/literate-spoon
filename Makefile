# Makefile for MyRepo Java Project
# This Makefile compiles all Java source files in src/,
# outputs the .class files to bin/, and generates Javadoc in doc/.

JAVAC    = javac
JAVADOC  = javadoc

# Directories
SRC = src
BIN = bin
DOC = doc

# Find all Java source files in the src directory.
SOURCES = $(wildcard $(SRC)/*.java)

# Default target: Create directories, compile classes, and generate Javadoc.
all: directories classes doc

# Create the bin and doc directories if they do not exist.
directories:
	mkdir -p $(BIN) $(DOC)

# Compile all Java source files, outputting .class files to the bin directory.
classes: $(SOURCES)
	$(JAVAC) -d $(BIN) $(SOURCES)

# Generate Javadoc from the source files and store it in the doc directory.
doc: $(SOURCES)
	$(JAVADOC) -d $(DOC) -sourcepath $(SRC) $(SOURCES)

# Clean up compiled classes and Javadoc.
clean:
	-rm -rf $(BIN)/* $(DOC)/*



