vpath %.c ../src

CC = gcc
CFLAGS = -c -fPIC -Wall -Wconversion -Wc++-compat -Werror -std=gnu99 -I$$JAVA_HOME/include -I$$JAVA_HOME/include/linux
LDFLAGS =

OBJDIR := obj
SRCDIR := src

SRC = errno.c limits.c prctl.c stdio.c syscall.c
OBJ = $(patsubst %.c,$(OBJDIR)/%.o,$(SRC))
BIN = ../dist/libjxtn-core-unix.so

all: $(BIN)

install: $(BIN)
	sudo install $(BIN) /usr/lib/jni/`basename $(BIN)`

$(OBJDIR)/%.o : $(SRCDIR)/%.c $(SRCDIR)/internals.h Makefile
	@mkdir -p `dirname $@`
	$(CC) $(CFLAGS) -c $< -o $@

$(BIN): $(OBJ) Makefile
	@mkdir -p dist
	$(CC) -shared $(OBJ) -o $(BIN) $(LDFLAGS)

clean:
	rm -f $(OBJ_VCAP) $(BIN_VCAP)
	rm -f $(OBJ_VCOMPACT) $(BIN_VCOMPACT)
