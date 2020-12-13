%%%%%%%%Bienvenida%%%%%%%%%%%%%%
inicio:-
    write("|------------------------------------------------------------------------|"),nl,
    write("|-------------Universidad Tecnologica de Pereira------------|"),nl,
    write("|-------------Facultad de Ingenieria en Sistemas------------|"),nl,
    write("|---Juan Carlos Yepes Tamayo - Juan Manuel Suarez---|"),nl,
    menu.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%FUNCION%%%%%%%%%%%%%%%
coloracion(Mapa, Colores, Solve):-
    coloracion_func(Mapa, Colores,[], Solve).
coloracion_func([],_, Solve, Solve).
coloracion_func([R-V|Mapa], Colores, Aux, Solve):-
    member(Color, Colores),
	not((member(R1, V), member(R1-Color, Aux))),
	coloracion_func(Mapa, Colores, [R-Color|Aux], Solve).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%%%%%%%%BORRAR PANTALLA%%%%%%%%%%%%%%
borraPantalla :- borraLinea(10).
borraLinea(1) :- !,nl.
borraLinea(N) :- nl,N1 is N-1,borraLinea(N1).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%%%%%%%INVERTIR LISTA%%%%%%%%%%%%%%%%%
reverso([],[]).
reverso([X|Xs], R) :-
       reverso_func(Xs, [X], R).

reverso_func([], R, R).
reverso_func([X|Xs], Acc, R) :-
       reverso_func(Xs, [X|Acc], R).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%MENU%%%%%%%%%%%%%%%%%%
menu :- 
    write('1) Ejecutar ejemplo 1'),nl,
    write('2) Ejecutar ejemplo 2'),nl,
    write('3) Ejecutar personalizada'),nl,
    write('0) Para salir'), nl,
    write('seleccione una opcion'), nl,
    read(Opcion),
    ejecutar(Opcion).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%%%%%%%%%EJECUTABLE%%%%%%%%%%%%%%
ejecutar(Opcion) :-
    Opcion == 1,nl,write("PROBLEMA"),nl, 
    			write("Mapa:"),nl,write("[a-[b,c,d],b-[a,d,e],c-[a,d,f],d-[a,b,c,e,f,g],e-[b,d,g],f-[c,d,g],g-[f,d,e]]"),nl,
    			write("Colores:"),nl, write("[azul, amarillo, gris]"), nl,
                (coloracion([a-[b,c,d],b-[a,d,e],c-[a,d,f],d-[a,b,c,e,f,g],e-[b,d,g],f-[c,d,g],g-[f,d,e]], [azul, amarillo, gris], S)),nl, 
    			write("SOLUCION"), nl, reverse(S, L), write(L), nl, nl, menu;
    
	Opcion == 2,nl,write("PROBLEMA"),nl, 
    			write("Mapa:"), nl, write("[a-[b,c],b-[a,d],c-[a,d],d-[b,c]]"), nl,
    			write("Colores:"), nl, write("[azul, rojo]"), nl, 
    			(coloracion([a-[b,c],b-[a,d],c-[a,d],d-[b,c]], [azul, rojo], S)), nl, 
    			write("SOLUCION"), nl, reverso(S, L), write(L), nl, nl, menu;
    
    Opcion == 3, write("Escriba el mapa"),
    			read(Map),
    			write("Escriba los colores"),
    			read(Colores),
    			coloracion(Map, Colores, S), nl, 
    			write("SOLUCION"), nl, reverse(S, L), write(L), nl, nl, menu;
    
    Opcion == 0, true.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

