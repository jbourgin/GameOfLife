mkdir class
javac -d class -Xlint:unchecked -Xlint:deprecation src/*.java
cd class
java GUI
cd ..
