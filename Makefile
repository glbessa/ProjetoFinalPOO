build: src/*/*.java
	javac src/*/*.java -d build/

run:
	cd build ; java gui.JanelaPrincipal