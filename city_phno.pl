name_citycode_phone(ram,0721,52345).
name_citycode_phone(shyam,0721,23456).
name_citycode_phone(abdul,0712,13579).
name_citycode_phone(john,0712,26480).
name_citycode_phone(umesh,011,32578).
name_citycode_phone(subhash,011,23467).
name_citycode_phone(manish,011,13698).
citycide_city(0721,indore).
citycide_city(0712,nagpur).
citycide_city(011,delhi).
name_city(X,Y):- name_citycode_phone(X,A,B),citycide_city(A,Y).
same_city(X,Y):- name_city(X,A),name_city(Y,A),not(X=Y).
