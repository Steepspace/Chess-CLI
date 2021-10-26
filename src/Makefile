##
# Chess
#
# @author Apurva Narde, Max Geiger
# @file
# @version 0.1

JFLAGS = -d ../bin
PIECE = chess/piece
BOARD = chess/board
BINPIECE = ../bin/$(PIECE)
BINBOARD = ../bin/$(BOARD)

all: $(BINPIECE)/Piece.class \
	 $(BINPIECE)/Pawn.class \
	 $(BINPIECE)/Knight.class \
	 $(BINPIECE)/Bishop.class \
	 $(BINPIECE)/Rook.class \
	 $(BINPIECE)/Queen.class \
	 $(BINPIECE)/King.class \
	 $(BINBOARD)/Board.class \
	 ../bin/chess/Chess.class

$(BINPIECE)/Piece.class: $(PIECE)/Piece.java
	javac $(JFLAGS) $(PIECE)/Piece.java

$(BINPIECE)/Pawn.class: $(PIECE)/Pawn.java
	javac $(JFLAGS) $(PIECE)/Pawn.java

$(BINPIECE)/Knight.class: $(PIECE)/Knight.java
	javac $(JFLAGS) $(PIECE)/Knight.java

$(BINPIECE)/Bishop.class: $(PIECE)/Bishop.java
	javac $(JFLAGS) $(PIECE)/Bishop.java

$(BINPIECE)/Rook.class: $(PIECE)/Rook.java
	javac $(JFLAGS) $(PIECE)/Rook.java

$(BINPIECE)/Queen.class: $(PIECE)/Queen.java
	javac $(JFLAGS) $(PIECE)/Queen.java

$(BINPIECE)/King.class: $(PIECE)/King.java
	javac $(JFLAGS) $(PIECE)/King.java

$(BINBOARD)/Board.class: $(BOARD)/Board.java
	javac $(JFLAGS) $(BOARD)/Board.java

../bin/chess/Chess.class: chess/Chess.java
	javac $(JFLAGS) chess/Chess.java

clean:
	rm -r ../bin/*
