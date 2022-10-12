# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Tony Nguyen, S341492, s341492@oslomet.no


# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
Tony oppgaver fra 1 - 8, utenom oppgave 7

# Oppgavebeskrivelse

Oppgavebeskrivelse

I oppgave 1 ble det laget en metode som returnerer antall verdier i listen, og en metode som sjekker om listen er tom. Det er også laget en konstruktør som tar inn et array og gjør det om til en dobbelt lenket liste, denne sjekker om tabellen er tom, har nullverdier, og fjerner eventuelle nullmerker.

I oppgave 2 er det laget en toString metode, som skriver ut listen på ønsket form [1,2,3], og [] for tom liste. Så laget vi omvendtString som er tilsvarende toString men den starter bakerst. Så laget vi metoden leggInn, den sjekker etter nullverdier, så legger den inn verdien bakerst i listen. Dette gjøres ved å først sjekke om listen er tom, i så fall er det bare en verdi etter innsetting, og hode og hale er samme verdi. Hvis listen ikke er tom legges det inn en node etter hale og neste og forrige pekere blir satt til riktig node.

I oppgave 3 er det laget finnNode som leter etter en gitt indeks. Den leter i første halvdel av listen, hvis den ikke finner noden, begynner den bakerst og leter gjennom andre halvdel av listen. Så laget vi metoden hent som bruker metoden finnIndeks for å finne riktig node for så å returnere verdien til noden. Vi laget også metoden oppdater, denne bruker også finnNode, lagrer noden og verdien, legger inn den nye verdien i noden, og returnerer den gamle verdien. Så laget vi metode subliste som returnerer en del av en liste i intervallet [fra, til>. Denne metoden lager en ny liste og setter inn alle verdiene fra intervallet, så returnerer den den nye sublisten.

I oppgave 4 ble det brukt en løkke for å returnere indeksen til verdien, om den finnes i listen. Den har også funksjon om å returnere -1 hvis verdien ikke finnes. Dersom verdien er en duplikat, skal den første indeksen returnere fra venstre for listen. Deretter er det opprettet en boolean metode.

I oppgave 5 er det laget en metode som kan legge inn en verdi i listen, slik at den får indeks posisjonen. Det er også laget en løkke som kontrollerer negative indekser og tall som er større enn antall indeks, ikke blir godkjent.

I oppgave 6 er det laget to fjern-metoder. Den første metoden skal fjerne og returnere verdien på posisjonens indeks. Den andre fjern metoden skal fjerne verdien fra listen, og deretter returnere true.

I oppgave 8 er det opprettet en metode for å sjekke om iteratorendringer er lik endringer og hvis den ikke er det så vil det bli kastet enConcurrentModificationException. Så en NoSuchElementException hvis det ikke er flere igjen i listen. fjernOK settes til false. Deretter brukt metode indeksKontroll for å sjekke om indeksen er lovlig.
