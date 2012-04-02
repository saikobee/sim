all:
	javac pris/*.java

jar:
	jar -cmf manifest pris.jar img/ pris/

run: jar
	java -jar pris.jar
