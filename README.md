# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Stancioiu Laura Ioana, grupa 322CD

## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations
* org.json.simple-0.3-incubating.jar
* json-lib-2.4-jdk15.jar

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

## Implementare

### Entitati
Clasele care implementeaza interfata Entity:
* Consumer -> clasa pentru reprezentarea unui consumator
* Distributor -> clasa pentru reprezentarea unui distributor
* Producer -> clasa pentru reprezetarea unui producator

Alte clase din pachetul entities:
* EntityFactory -> genereaza in functie de tipul mentionat o instanta a unui clase ce implementeaza
interfata Entity
* MonthlyStats -> folosita pentru retinerea id urilor distribuitorilor lunari, pentru fiecare producator
* Contract -> contractul dintre un consumator si un distribuitor

Clase folosite pentru prelucrarea input/output:
* Input -> contine listele cu consumatori, distribuitori, producatori si schimbari de cost lunare
* InputLoader -> citeste informatiile din fisierele json date ca input si le incarca in clasa Input

* MonthlyUpdate
    * contine o lista cu noii consumatori per luna
    * contine o lista cu schimbarile de pret lunare pentru distribuitori(DistributorChanges)
    * contine o lista cu schimbarile cantitatilor de energie pentru producatori(ProducerChanges)
    
* EntityReader -> folosit pentru generarea de noi consumtori/distribuitori/producatori

* Writer -> scrie datele de output intr-un fisier json

Pachetul strategies 
* ProducerStrategy -> o clasa abstracta extinsa de clasele :
    * GreenStrategy
    * PriceStrategy
    * QuantityStrategy
    
    Toate aceste clase sunt folosite pentru implementarea strategiilor de alegerea a producatorilor pentru distribuitori.

* ProducerStrategyFactory -> genereaza instante ale claselor ce extind ProducerStrategy in functie de tipul mentionat

Pachetul simulator
* DistributorUtils -> contine metode statice pentru interatiunea dintre distribuitori si consumatori
* ProducerUtils -> contine metode statice pentru interactiunea dintre producatori si distribuitori
* Simulator -> simulatorul sistemului energetic

Pachetul Utils
* Constants -> o serie de constante folosite atat pentru scriere si citire, cat si pentru anumite formule
### Flow

In cadrul clasei Simulator se apeleaza toate metodele ce constituie flow ul programului in fiecare luna.
La inceputul lunii distribuitorii isi aleg producatorii si isi calculeaza costul de productie.

Apoi, incepe runda 0, in cadrul careia distribuitorii isi calculeaza costul contractelor. 
Consumatorii primesc salariul si aleg distribuitorii cu costul contractului cel mai mic. 
Distribuitorii primesc ratele de la consumatori si platesc taxele.

In fiecare luna, se aplica schimbarile, daca acestea exista, si se reia runda 0, cu modificarea ca 
daca un consumator are deja un distribuitor acesta nu isi alege altul.

Daca un producator are schimbari in cadrul energiei oferite acesta isi anunta distribuitorii, care la
finalul lunii isi vor aplica din nou strategia de alegere a producatorilor.

La finalul simularii datele relevante sunt scrise, in format json, in fisierul de output results.out 
prin intermediul clasei Writer.
### Elemente de design OOP

* Am folosit abstractizare pentru clasa ProducerStrategy.
* Am folosit incapsulare in clasa EntityFactory pentru clasa Builder.

### Design patterns

* Factory pentru generarea de entitati(EntityFactory) si de strategii(ProducerStrategyFactory)
* Singleton pentru ProducerStrategyFactory
* Builder pentru EntityFactory
* Observer pentru interactiunea dintre distribuitori si producatori, distribuitorii implementeaza
Observer si producatorii extind clasa Observable
* Strategy pentru strategiile de alegere a producatorilor de catre distribuitori

### Dificultati intalnite, limitari, probleme

Pentru a rezolva problema "magic number" de checkstyle am denumit constante in clasa Constants pentru
numerele din cadrul formulelor pentru calculul preturilor, penalizarilor sau costurilor de productie.



