PROJECT_PATH = projet

SRC = src/$(PROJECT_PATH)
BLD = build/$(PROJECT_PATH)

JC_OPT = -implicit:none -d build -classpath build -sourcepath "src:tmp"

MAIN_PATH = $(PROJECT_PATH).Interpreter

JAR_NAME = projet.jar
JAR_OPT = cvfe $(JAR_NAME) $(MAIN_PATH) -C build $(PROJECT_PATH) -C res .

jar : $(BLD)/Interpreter.class
	jar $(JAR_OPT)

run : jar
	java -jar $(JAR_NAME)


$(BLD)/Interpreter.class : $(SRC)/Interpreter.java json php
	javac $(JC_OPT) $(SRC)/Interpreter.java

api :	$(BLD)/api/Tree.class \
		$(BLD)/api/Token.class \
		$(BLD)/api/TreeFormatter.class

$(BLD)/api/Tree.class :	$(SRC)/api/Tree.java
	javac $(JC_OPT) $(SRC)/api/Tree.java

$(BLD)/api/Token.class :	$(SRC)/api/Token.java \
							$(BLD)/api/ValueType.class
	javac $(JC_OPT) $(SRC)/api/Token.java

$(BLD)/api/ValueType.class : $(SRC)/api/ValueType.java
	javac $(JC_OPT) $(SRC)/api/ValueType.java

$(BLD)/api/TreeFormatter.class :	$(SRC)/api/TreeFormatter.java \
									$(BLD)/api/Tree.class \
									$(BLD)/api/Token.class
	javac $(JC_OPT) $(SRC)/api/TreeFormatter.java

json :	api $(BLD)/json/JSONParser.class

$(BLD)/json/JSONParser.class :	$(SRC)/json/JSONParser.java \
								$(BLD)/json/JSONTree.class \
								$(BLD)/json/JSONFormatException.class
	javac $(JC_OPT) $(SRC)/json/JSONParser.java

$(BLD)/json/JSONTree.class :	$(SRC)/json/JSONTree.java \
								$(BLD)/json/JSONToken.class
	javac $(JC_OPT) $(SRC)/json/JSONTree.java

$(BLD)/json/JSONToken.class : $(SRC)/json/JSONToken.java
	javac $(JC_OPT) $(SRC)/json/JSONToken.java

$(BLD)/json/JSONFormatException.class : $(SRC)/json/JSONFormatException.java
	javac $(JC_OPT) $(SRC)/json/JSONFormatException.java

php :
	echo "WIP"