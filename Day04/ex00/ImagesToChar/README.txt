mkdir target
javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java
java -classpath ./target edu.school21.printer.app.Program *character for white color* *character for black color* *full path to the image on your disk*

Example of arguments to run Program:
java -classpath ./target edu.school21.printer.app.Program . o /Users/schips/Desktop/it.bmp