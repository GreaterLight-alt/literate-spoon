SRC := $(wildcard src/*.java)
BIN := bin
DOC := doc

# Default target
all: classes javadoc

# Compile Java files into bin directory
classes: $(SRC)
	mkdir -p $(BIN)	
	javac -d $(BIN) $(SRC)

# Generate Javadoc into doc directory
javadoc:
	mkdir -p $(DOC)
	javadoc -d $(DOC) $(SRC)

# Clean up generated files
clean:
	rm -rf $(BIN)/*
	rm -rf $(DOC)/*

.PHONY: all classes javadoc clean

