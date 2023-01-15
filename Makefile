PROJECT_PATH = projet

SRC = src/$(PROJECT_PATH)
BLD = build/$(PROJECT_PATH)

JC_OPT = -implicit:none -d build -classpath build -sourcepath "src:tmp"

MAIN_PATH = $(PROJECT_PATH).Interpreter

JAR_NAME = Inspecteur.jar
JAR_OPT = cvfe $(JAR_NAME) $(MAIN_PATH) -C build $(PROJECT_PATH) -C res .

jar : $(BLD)/Interpreter.class
	jar $(JAR_OPT)

run : jar
	java -jar $(JAR_NAME)


$(BLD)/Interpreter.class : $(SRC)/Interpreter.java json php\
		$(BLD)/view/Accueil.class \
		$(BLD)/json/JSONPrettyPrinter.class \
		$(BLD)/php/PHPPrettyPrinter.class 
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

$(BLD)/api/PrettyPrinter.class : 	$(SRC)/api/PrettyPrinter.java \
									$(BLD)/api/ColoredNode.class
	javac $(JC_OPT) $(SRC)/api/PrettyPrinter.java

$(BLD)/api/ColoredString.class :	$(SRC)/api/ColoredString.java
	javac $(JC_OPT) $(SRC)/api/ColoredString.java

$(BLD)/api/ColoredNode.class : 	$(SRC)/api/ColoredNode.java \
										$(BLD)/api/ColoredString.class
	javac $(JC_OPT) $(SRC)/api/ColoredNode.java


json :	api $(BLD)/json/JSONParser.class

$(BLD)/json/BlockType.class : $(SRC)/json/BlockType.java 
	javac $(JC_OPT) $(SRC)/json/BlockType.java

$(BLD)/json/JSONValueParser.class : $(SRC)/json/JSONValueParser.java \
									$(BLD)/json/JSONFormatException.class
	javac $(JC_OPT) $(SRC)/json/JSONValueParser.java

$(BLD)/json/JSONParser.class :	$(SRC)/json/JSONParser.java \
								$(BLD)/json/JSONTree.class \
								$(BLD)/json/BlockType.class \
								$(BLD)/json/JSONValueParser.class \
								$(BLD)/json/JSONFormatException.class
	javac $(JC_OPT) $(SRC)/json/JSONParser.java

$(BLD)/json/JSONTree.class :	$(SRC)/json/JSONTree.java \
								$(BLD)/json/JSONToken.class
	javac $(JC_OPT) $(SRC)/json/JSONTree.java

$(BLD)/json/JSONToken.class : $(SRC)/json/JSONToken.java
	javac $(JC_OPT) $(SRC)/json/JSONToken.java

$(BLD)/json/JSONFormatException.class : $(SRC)/json/JSONFormatException.java
	javac $(JC_OPT) $(SRC)/json/JSONFormatException.java

$(BLD)/json/JSONPrettyPrinter.class : 	$(SRC)/json/JSONPrettyPrinter.java \
										$(BLD)/api/PrettyPrinter.class
	javac $(JC_OPT) $(SRC)/json/JSONPrettyPrinter.java

php :

$(BLD)/php/PHPPrettyPrinter.class : $(SRC)/php/PHPPrettyPrinter.java \
									$(BLD)/api/PrettyPrinter.class
	javac $(JC_OPT) $(SRC)/php/PHPPrettyPrinter.java

view :

$(BLD)/view/Accueil.class : $(SRC)/view/Accueil.java \
							$(BLD)/controller/RefreshButtonSwitcher.class \
							$(BLD)/controller/TestFichier.class
	javac $(JC_OPT) $(SRC)/view/Accueil.java


$(BLD)/view/FoldingListener.class :	$(SRC)/view/FoldingListener.java 
	

$(BLD)/view/AffichageJSON.class :	$(SRC)/view/AffichageJSON.java
	javac $(JC_OPT) $(SRC)/view/FoldingListener.java $(SRC)/view/AffichageJSON.java


controller :

$(BLD)/controller/RefreshButtonSwitcher.class :	$(SRC)/controller/RefreshButtonSwitcher.java 						
	javac $(JC_OPT) $(SRC)/controller/RefreshButtonSwitcher.java

$(BLD)/controller/TestFichier.class : 	$(SRC)/controller/TestFichier.java \
										$(BLD)/json/JSONPrettyPrinter.class \
										$(BLD)/php/PHPPrettyPrinter.class \
										$(BLD)/view/AffichageJSON.class \
										$(BLD)/controller/UnfoldListener.class
	javac $(JC_OPT) $(SRC)/controller/TestFichier.java

$(BLD)/controller/UnfoldListener.class :	$(SRC)/controller/UnfoldListener.java 						
	javac $(JC_OPT) $(SRC)/controller/UnfoldListener.java

