
# Makefile to compile classes
# Ziyanda Mthethwa
# 2025/03/14
# binary search program makefile
# Binary tree and generics project makefile
# Based on Hussein Suleman's format
# March 15, 2025

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $$

CLASSES=BinaryTreeNode.class BinaryTree.class \
        BTQueueNode.class BTQueue.class \
        BinarySearchTree.class  \
        Statement.class GenericsKb.class \
        GenericsKbAppArray.class GenericsKbArrayApp.class \
        GenericsKbBST.class GenericsKbBSTApp.class

classes: $(CLASSES)

default: classes

clean:
	rm *.class






