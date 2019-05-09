parent(dashrath,ram).
parent(koushaliya,ram).
parent(dashrath,laxman).
parent(koushaliya,laxman).
parent(dashrath,bharath).
parent(koushaliya,bharath).
parent(dashrath,shatrughan).
parent(koushaliya,shatrughan).
parent(ram,love).
parent(ram,kush).
parent(sita,love).
parent(sita,kush).
parent(janak,sita).
male(ram).
male(dashrath).
male(laxman).
male(kush).
male(love).
male(bharath).
male(shatrughan).
male(janak).
female(sita).
female(koushaliya).
father(X,Y):- male(X),parent(X,Y).
mother(X,Y):- female(X),parent(X,Y).
grandparent(X,Y):- father(Z,Y),parent(X,Z).
grandfather(X,Y):- grandparent(X,Y),male(X).
grandmother(X,Y):- grandparent(X,Y),female(X).
nana(X,Y):- father(X,Z),mother(Z,Y).
nani(X,Y):- mother(Z,Y),mother(X,Z).
brother(X,Y):- parent(Z,X),parent(Z,Y),male(X),not(X=Y).
sister(X,Y):- parent(Z,X),parent(Z,Y),female(X),not(X=Y).
ancestor(X,Y):- parent(X,Y).
ancestor(X,Y):- parent(Z,Y),ancestor(X,Z).
