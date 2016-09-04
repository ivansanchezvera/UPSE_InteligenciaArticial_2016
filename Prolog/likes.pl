%% Demo coming from http://clwww.essex.ac.uk/course/LG519/2-facts/index_18.html
%%
%% Please load this file into SWI-Prolog
%%
%% Sam's likes and dislikes in food
%% 
%% Considering the following will give some practice
%% in thinking about backtracking.
%% ?- likes(sam,dahl).
%% ?- likes(sam,chop_suey).
%% ?- likes(sam,pizza).
%% ?- likes(sam,chips).
%% ?- likes(sam,curry).

likes(sam,Food) :-
        indian(Food),
        mild(Food).
likes(sam,Food) :-
        chinese(Food).
likes(sam,Food) :-
        italian(Food).
likes(sam,chips).
likes(sam,Food) :-
		ecuatoriana(Food).
		
likes(lucin, hamburguesa).
likes(lucin, Food) :-
		ecuatoriana(Food),
		ecuatoriana_costa(Food).

indian(curry).
indian(dahl).
indian(tandoori).
indian(kurma).

mild(dahl).
mild(tandoori).
mild(kurma).

chinese(chow_mein).
chinese(chop_suey).
chinese(sweet_and_sour).

italian(pizza).
italian(spaghetti).

ecuatoriana(ceviche).
ecuatoriana(encebollado).
ecuatoriana(caldo_de_manguera).
ecuatoriana(yaguarlocro).
ecuatoriana(hornado).
ecuatoriana(cuy).
ecuatoriana(seco_de_guatusa).

ecuatoriana_costa(ceviche).
ecuatoriana_costa(encebolldo).
ecuatoriana_costa(caldo_de_manguera).

ecuatoriana_sierra(yaguarlocro).
ecuatoriana_sierra(cuy).
ecuatoriana_sierra(hornado).

ecuatoriana_oriente(seco_de_guatusa).
ecuatoriana_oriente(mono_hornado).
