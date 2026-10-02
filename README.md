Nom del vostre sistema
NOVA


Tipus de sistema
☐ Substitució   ☐ Desplaçament   ⛝ Combinació   ☐ Altres: __________
Tipus de clau

Cadena de text (String)
Com funciona l’encriptació?

En el sistema NOVA primer convertim el missatge i la clau en Bytes per poder treballar amb numeros.
Despres, relacionem cada caracter del missatge amb un caracter de la clau. Si la clau es mes curta que el missatge, la clau es repeteix des del principi. Si la clau es mes llarga, nomes utilitzem els caracters necessaris.

Per a cada posicio, fem una operacio XOR entre el byte del missatge i el byte corresponent de la clau.

Despres del XOR, sumem al resultat el numero de la posicio que ocupa el caracter dins del missatge. Finalment, convertim els bytes resultants a Base64 per obtenir el missatge xifrat en forma de text.


Com funciona la desencriptació?

En el sistema NOVA, primer rebem el missatge xifrat en forma de Base64 i el covetim de nou en els bytes originals.

Despres, per a cada byte, restem la posicio que ocupa dins del missatge. A continuacio, fem una operacio XOR amb el mateix byte de la clau que s’ha utilitzat durant l’encriptacio.

Si la clau es mes curta que el missatge, es torna a començar des del principi de la clau.

Finalment, convertim els bytes obtinguts en text i recuperem el missatge original.


Com sé que el meu sistema funciona?



Podem veure que funciona fent diferents proves,

Primer, comprovem que si encriptem el mateix missatge amb la mateixa clau, obtenim sempre el mateix resultat. 

Després, comprovem que si canviem la clau, el resultat de l’encriptació també canvia. 

També comprovem que si intentem desencriptar el missatge amb una clau incorrecta, no recuperem el missatge original.

Finalment, fem la prova més important: encriptem un missatge i després el desencriptem utilitzant la mateixa clau. Si recuperem exactament el missatge inicial, el sistema funciona correctament. 

