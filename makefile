# The java directories
SRC = src
BIN = bin

mrSSS.jar: $(BIN) classes
	cd $(BIN); jar cvfm ../mrSSS.jar ../$(SRC)/MANIFEST.MF . ../images

classes: $(BIN)
	build $(BIN)

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
	cmd /E:ON /C erase mrSSS.jar
	cmd /E:ON /C rmdir /s /q $(BIN)
	cmd /E:ON /C erase sources.txt
else
# Linux
	rm  sources.txt
	rm -rf $(BIN)
endif

run:
	run
