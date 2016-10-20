# The java directories
SRC = "src/"
BIN = "bin/"

# The java compiler, virtual machine, and jar maker
JAVAC = "javac"
JAVA = "java"
JAR = "jar"

mrSSS.jar: bin classes
	cd bin; jar cvfe ../mrSSS.jar Game .

classes: bin src/*.java
	build

bin:
# Windows
ifeq ($(OS), Windows_NT)
	cmd /E:ON /C mkdir $@
else
# Linux
	mkdir $@
endif

clean:
# Windows
ifeq ($(OS), Windows_NT)
	cmd /E:ON /C erase sources.txt
	cmd /E:ON /C rmdir /s /q bin
else
# Linux
	rm  sources.txt
	rm -rf bin
endif