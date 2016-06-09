vpath %.c ../src-jni

CC = gcc
CFLAGS = -c -fPIC -Wall -Wconversion -Wc++-compat -Werror -std=gnu99 -I$$JAVA_HOME/include -I$$JAVA_HOME/include/linux
LDFLAGS =

OBJDIR := obj
SRCDIR := src-jni

DEP = $(SRCDIR)/dirent.h $(SRCDIR)/internals.h Makefile
SRC = dirs.c dirs2.c env.c errno.c files.c files2.c io.c limits.c mmap.c prctl.c prctl2.c proc.c proc2.c sigs.c stat.c stdio.c wait.c xattr.c
OBJ = $(patsubst %.c,$(OBJDIR)/%.o,$(SRC))
BIN = ../dist/libjxtn-core-unix.so

all: $(BIN)

install: $(BIN)
	sudo install $(BIN) /usr/lib/jni/`basename $(BIN)`

$(OBJDIR)/%.o : $(SRCDIR)/%.c $(DEP)
	@mkdir -p `dirname $@`
	$(CC) $(CFLAGS) -c $< -o $@

$(BIN): $(OBJ) Makefile
	@mkdir -p dist
	$(CC) -shared $(OBJ) -o $(BIN) $(LDFLAGS)

clean:
	rm -f $(OBJ) $(BIN)
