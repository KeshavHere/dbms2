
member(X,[X|Tail]).
member(X,[Y|Tail]):- member(X,Tail).

solution([]).
solution([X/Y|Tail]):-solution(Tail),member(Y,[1,2,3,4,5,6,7,8]),noattack(X/Y,Tail).

noattack(_,[]).
noattack(X/Y,[X1/Y1|Tail]):-
    Y=\=Y1,
    X-X1=\=Y-Y1,
    X-X1=\=Y1-Y,
    noattack(X/Y,Tail).

template([1/R1,2/R2,3/R3,4/R4,5/R5,6/R6,7/R7,8/R8]).
