conc([],L,L).
conc([X|Tail],L2,[X|L3]):- conc(Tail,L2,L3).

member(X,[X|Tail]).
member(X,[Y|Tail]):- member(X,Tail).

add([],[],Z).
add(X,Y,Z) :- add([],[],[X|Y]).

delete(X,[X|Tail],Tail).
delete(X,[Y|Tail],[Y|Tail1]):- delete(X,Tail,Tail1).

sublist([X|Tail1],[Y|Tail2],Z) :- sublist(Z,Tail2,Z).
sublist([X|Tail1],[X|Tail2],Z) :- sublist(Tail1,Tail2,Z).
sublist([],Y,Z).

hmm([X|Tail],[Y|Tail2]) :- sublist([X|Tail],[Y|Tail2],[X|Tail]).

addxtolist(X,[Y|Tail1],Z).










