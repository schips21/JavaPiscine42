mkdir target
javac -d target ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
mkdir target/resources
cp src/resources/image.bmp target/resources/image.bmp
cd target
jar cfev images-to-chars-printer.jar edu/school21/printer/app/Program edu/school21/printer/app/Program.class edu/school21/printer/logic/ImageConverter.class resources/image.bmp
cd ..
java -jar target/images-to-chars-printer.jar *character for white color* *character for black color*

Example of arguments to run Program:
java -jar target/images-to-chars-printer.jar . o
