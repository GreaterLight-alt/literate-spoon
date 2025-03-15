# makefile  to compile classes
# Ziyanda Mthethwa
# 2025/03/14

# Java compiler path
JAVAC=/usr/bin/javac

# All class files need for the  Assignment 1
classes = BinaryTreeNode.class BinaryTree.class \
         BTQueueNode.class BTQueue.class \
         BinarySearchTree.class \
		 Statement.class \
		 GenericsKbBST.class \
		 GenericsKb.class \
		 GenericsKbArrayApp.class 

# Rule for compiling .java files to .class files
%.class: %.java
	$(JAVAC) $<

# Default target - compile all classes
all: $(classes)


# Run Array
run-Array:
	@java GenericsKbArrayApp

run-BST:
	@java GenericsKbBSTApp
#clean up class files
clean:
	rm -f *.class
 