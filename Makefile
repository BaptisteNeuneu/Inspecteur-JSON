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
		$(BLD)/Vue/TriCaractere.class \
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
									$(BLD)/controller/ColoredNode.class
	javac $(JC_OPT) $(SRC)/api/PrettyPrinter.java


json :	api $(BLD)/json/JSONParser.class

$(BLD)/json/BlockType.class : $(SRC)/json/BlockType.java 
	javac $(JC_OPT) $(SRC)/json/BlockType.java

$(BLD)/json/JSONValueParser.class : $(SRC)/json/JSONValueParser.java 
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

vue :

$(BLD)/Vue/TriCaractere.class :	$(SRC)/Vue/TriCaractere.java \
								$(BLD)/Vue/JSONArray.class \
								$(BLD)/Vue/JSONBoolean.class \
								$(BLD)/Vue/JSONObject.class \
								$(BLD)/Vue/JSONValue.class \
								$(BLD)/Vue/JSONWhitespace.class \
								$(BLD)/Vue/Nombre.class \
								$(BLD)/Vue/Texte.class
	javac $(JC_OPT) $(SRC)/Vue/TriCaractere.java

$(BLD)/Vue/JSONArray.class : 	$(SRC)/Vue/JSONArray.java \
								$(BLD)/Vue/JSONBoolean.class \
								$(BLD)/Vue/JSONObject.class \
								$(BLD)/Vue/JSONValue.class \
								$(BLD)/Vue/JSONWhitespace.class \
								$(BLD)/Vue/Nombre.class \
								$(BLD)/Vue/Texte.class
	javac $(JC_OPT) $(SRC)/Vue/JSONArray.java

$(BLD)/Vue/JSONBoolean.class : 	$(SRC)/Vue/JSONBoolean.java
	javac $(JC_OPT) $(SRC)/Vue/JSONBoolean.java

$(BLD)/Vue/JSONObject.class : 	$(SRC)/Vue/JSONObject.java \
								$(BLD)/Vue/JSONBoolean.class \
								$(BLD)/Vue/JSONArray.class \
								$(BLD)/Vue/JSONValue.class \
								$(BLD)/Vue/JSONWhitespace.class \
								$(BLD)/Vue/Nombre.class \
								$(BLD)/Vue/Texte.class
	javac $(JC_OPT) $(SRC)/Vue/JSONObject.java
	
$(BLD)/Vue/JSONValue.class : 	$(SRC)/Vue/JSONValue.java
	javac $(JC_OPT) $(SRC)/Vue/JSONValue.java

$(BLD)/Vue/JSONWhitespace.class : 	$(SRC)/Vue/JSONWhitespace.java
	javac $(JC_OPT) $(SRC)/Vue/JSONWhitespace.java

$(BLD)/Vue/Nombre.class : 	$(SRC)/Vue/Nombre.java
	javac $(JC_OPT) $(SRC)/Vue/Nombre.java

$(BLD)/Vue/Texte.class : 	$(SRC)/Vue/Texte.java
	javac $(JC_OPT) $(SRC)/Vue/Texte.java

$(BLD)/Vue/Window.class : 	$(SRC)/Vue/Window.java
	javac $(JC_OPT) $(SRC)/Vue/Window.java

php :

$(BLD)/php/PHPPrettyPrinter.class : $(SRC)/php/PHPPrettyPrinter.java \
									$(BLD)/api/PrettyPrinter.class
	javac $(JC_OPT) $(SRC)/php/PHPPrettyPrinter.java

	echo "WIP"


view :

$(BLD)/view/Accueil.class : $(SRC)/view/Accueil.java \
							$(BLD)/controller/RefreshButtonSwitcher.class \
							$(BLD)/controller/TestFichier.class
	javac $(JC_OPT) $(SRC)/view/Accueil.java


$(BLD)/view/AffichageJSON.class :	$(SRC)/view/AffichageJSON.java
	javac $(JC_OPT) $(SRC)/view/AffichageJSON.java

controller :

$(BLD)/controller/RefreshButtonSwitcher.class :	$(SRC)/controller/RefreshButtonSwitcher.java 						
	javac $(JC_OPT) $(SRC)/controller/RefreshButtonSwitcher.java

$(BLD)/controller/TestFichier.class : 	$(SRC)/controller/TestFichier.java \
										$(BLD)/Vue/TriCaractere.class \
										$(BLD)/json/JSONPrettyPrinter.class \
										$(BLD)/php/PHPPrettyPrinter.class \
										$(BLD)/view/AffichageJSON.class 
	javac $(JC_OPT) $(SRC)/controller/TestFichier.java

$(BLD)/controller/ColoredString.class :	$(SRC)/controller/ColoredString.java
	javac $(JC_OPT) $(SRC)/controller/ColoredString.java

$(BLD)/controller/ColoredNode.class : 	$(SRC)/controller/ColoredNode.java \
										$(BLD)/controller/ColoredString.class
	javac $(JC_OPT) $(SRC)/controller/ColoredNode.java
