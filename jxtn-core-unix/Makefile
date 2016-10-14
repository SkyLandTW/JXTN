vpath %.c ../src-jni

CC = gcc
CFLAGS = -ggdb3 -O2 -fPIC -Wall -Wconversion -Wc++-compat -Werror -std=gnu99 -I$$JAVA_HOME/include -I$$JAVA_HOME/include/linux
LDFLAGS = -pie -Wl,-E -lpam

DEPDIR := dep
OBJDIR := obj
SRCDIR := src-jni

SRC = $(notdir $(wildcard $(SRCDIR)/*.c))
DEP = $(patsubst %.c,$(DEPDIR)/%.d,$(SRC))
OBJ = $(patsubst %.c,$(OBJDIR)/%.o,$(SRC))
BIN = ../dist/libjxtn-core-unix.so

all: $(BIN)

install: $(BIN)
	sudo install -m 4754 $(BIN) /usr/lib/jni/`basename $(BIN)`

-include $(DEP)

$(DEPDIR)/%.d: $(SRCDIR)/%.c Makefile
	@mkdir -p `dirname $@`
	echo -n '$(OBJDIR)/' > $@
	$(CC) -MM $(CFLAGS) $< >> $@

$(OBJDIR)/%.o : $(SRCDIR)/%.c $(DEPDIR)/%.d
	@mkdir -p `dirname $@`
	$(CC) $(CFLAGS) -c $< -o $@

$(BIN): $(OBJ)
	@mkdir -p dist
	$(CC) -shared $(OBJ) -o $(BIN) $(LDFLAGS)

clean:
	rm -f $(DEP) $(OBJ) $(BIN)
