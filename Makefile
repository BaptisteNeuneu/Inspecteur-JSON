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
		$(BLD)/Vue/TriCaractere.class
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
	echo "WIP"