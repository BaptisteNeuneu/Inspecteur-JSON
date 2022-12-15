PROJECT_PATH = projet

SRC = src/$(PROJECT_PATH)
BLD = build/$(PROJECT_PATH)

JV_OPT = -classpath build
JC_OPT = -implicit:none -d build -classpath build -sourcepath "src:tmp"

MAIN_PATH = $(PROJECT_PATH).Interpreter

JAR_NAME = projet.jar
JAR_OPT = cvfe $(JAR_NAME) $(MAIN_PATH) -C build $(PROJECT_PATH) -C res .

jar : $(BLD)/Interpreter.class
	mkdir build
	jar $(JAR_OPT)

run : jar
	java -jar $(JAR_NAME)


$(BLD)/Interpreter.class : $(SRC)/Interpreter.java api json php
	java $(JV_OPT) Interpreter.java

api :	$(BLD)/api/Tree.class \
		$(BLD)/api/Token.class \
		$(BLD)/api/TreeFormatter.class

$(BLD)/api/Tree.class :	$(SRC)/api/Tree.java \
						$(BLD)/api/TreePath.java
	java $(JV_OPT) $(SRC)/api/Tree.java

