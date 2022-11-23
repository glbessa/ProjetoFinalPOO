build: src/*/*.java
	javac -Xlint src/*/*.java -d build/

run:
	cd build ; java gui.JanelaPrincipal