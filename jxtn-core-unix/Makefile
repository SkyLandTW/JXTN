vpath %.c ../src

CC = gcc
CFLAGS = -c -fPIC -Wall -std=gnu99 -I/usr/lib/jvm/default-java/include
LDFLAGS =

OBJDIR := obj
SRCDIR := src

SRC = unix.c
OBJ = $(patsubst %.c,$(OBJDIR)/%.o,$(SRC))
BIN = dist/libjxtn-core-unix.so

all: $(BIN)

install: $(BIN)
	sudo install $(BIN) /usr/lib/jni/`basename $(BIN)`

$(OBJDIR)/%.o : $(SRCDIR)/%.c Makefile
	@mkdir -p `dirname $@`
	$(CC) $(CFLAGS) -c $< -o $@

$(BIN): $(OBJ) Makefile
	@mkdir -p dist
	$(CC) -shared $(OBJ) -o $(BIN) $(LDFLAGS)

clean:
	rm -f $(OBJ_VCAP) $(BIN_VCAP)
	rm -f $(OBJ_VCOMPACT) $(BIN_VCOMPACT)
