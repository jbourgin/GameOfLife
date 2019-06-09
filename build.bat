javac -d classes -Xlint:unchecked -Xlint:deprecation src/*.java
cd classes
java -classpath ".;sqlite-jdbc-3.21.0.jar" UI
cd ..
