package no.oslomet.cs.algdat.Oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////


import java.awt.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        public T verdi;                   // nodens verdi
        public Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    //Hjelpemetode
    Node<T> finnNode(int indeks) {
        int idx;
        Node<T> n;

        if (indeks < antall / 2) { //Starter fra hode og skal finne indeks
            idx = 0;
            n = hode;
            while (idx < indeks) {
                n = n.neste;
                idx++;
            }
        } else {  //Starter å lete fra hale, bak listen
            idx = antall - 1;
            n = hale;

            while (idx > indeks) {
                n = hale.forrige;
                idx--;
            }
        }
        //Nooden blir returnert som følge av while loopene
        return n;
    }


    public DobbeltLenketListe() {

    }

    public DobbeltLenketListe(T[] a) {
        if (a == null) //Hvis tabellen a er tom
            throw new NullPointerException("Tabellen a er nulL!");
        endringer = 0;

        Node forrigeNode = null;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) { //verdier som er null hoppes over
                continue;
            }
            Node n = new Node(a[i]); //Nodene opprettes

            if (forrigeNode != null) { //Sjekker om forrige finnes
                n.forrige = forrigeNode; // Setter denne nodens forrige til å peke til forrige node
                forrigeNode.neste = n; //Setter forrige node til å peke til nye noden
            } else { //Hvis forrige node ikke finnes
                this.hode = n;
            }
            antall++;
            forrigeNode = n; //Setter noden som ble opprettet til forrigeNode som skal brukes videre

        }
        this.hale = forrigeNode; //Setter hale til siste verdi

    }

    public Liste<T> subliste(int fra, int til) {
        int idx = 0; // Denne gjør at vi kan starte [fra, ...
        Node<T> n = hode; //Starter fra hode
        while (idx < fra) { //Går gjennom listen til den kommer til fra
            n = n.neste; //Flytter node til neste node, node vil da være node = fra etter endt løkke
            idx++; // Neste løkke vil da starte fra 1,2... osv
        }

        DobbeltLenketListe<T> list = new DobbeltLenketListe<T>();
        while (idx < til) { // While loop som går gjennom [fra, til>
            list.leggInn(n.verdi); // Legger til alle verdiene [fra, til> i list
            n = n.neste;
            idx++;
        }

        //setter endringer til 0, siden leggInn() inkrementer endringer.
        return list;
    }

    //Hjelpemetode fratilKontroll
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    @Override
    public int antall() {
        return this.antall;
    }

    @Override
    public boolean tom() {
        return this.antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Tabellen a er null!"); // Sjekker om verdien er null

        Node<T> n = new Node<>(verdi); // Oppretter ny node

        if (this.tom()) { //Hvis ny liste er tom. Setter hode hale til ny node
            hode = n;
            hale = n;
        } else {
            hale.neste = n; //Nest siste node sin neste er nye node
            n.forrige = hale; //Nye noden siden forrige er nest siste
            hale = n; // Ny noden er nye halen

        }
        antall += 1;
        endringer += 1;
        return true;
    }


    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        Objects.requireNonNull(verdi); //Ikke tillatt med null-verdier

        //Hvis vi legger til med indeks 0, så legger vi til noden som hode
        if (indeks == 0) {
            if (antall == 0) {
                hale = hode = new Node<>(verdi, null, null); //Sette hale og hode til å peke mot null hvis antall == 0
            } else {
                hode.forrige = new Node<>(verdi, null, hode);
                hode = hode.forrige;
            }
        }
    }


    @Override
    public boolean inneholder(T verdi) {
//indeksTil() metoden blir brukt til å returnere true hvis listen inneholder verdi, og false ellers
        return indeksTil(verdi) != -1;
    }

    @Override
    //finnNode() brukes til å finne noden på indeks, også blir verdien returnert eller null om den ikke finnes
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> n = finnNode(indeks);
        return n != null ? n.verdi : null;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) //En metode som ikke kaster unntak dersom verdien er null
            return -1;

        //Starter fra hode og looper gjennom fram til man finner verdien, returnerer posisjon til verdi hvis den finnes
        Node<T> n = hode;
        for (int i = 0; i < antall; i++, n = n.neste) {
            if (n.verdi.equals(verdi)) return i;
        }
        return -1; //Hvis vi ikke fant noe node med verdien så returnerer vi -1
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        Objects.requireNonNull(nyverdi);

        //finnNode() finner noden på indeks
        indeksKontroll(indeks, false);      //sjekker indeks
        if (nyverdi == null) {                     //sjekker at nyverdi ikke er null
            throw new NullPointerException("nyverdi kan ikke være nulll");
        }
        Node<T> n = finnNode(indeks);
        T tmp = n.verdi; //Lagrer gamle noden i tmp
        n.verdi = nyverdi; //Verdien i noden blir endret til ny verdi
        endringer += 1; //Øker endringer i telleren
        return tmp; //Returnerer gamle verdi
    }


    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        Node<T> node = hode;
        while (node != null) { //Forsetter å kjøre så lenge node ikke er null
            sj.add(node.verdi.toString());
            node = node.neste; //Node blir neste for neste løkke i while løkken
        }
        return sj.toString();
    }


    public String omvendtString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        Node<T> node = hale;
        while (node != null) { //Forsetter å kjøre så lenge node ikke er null
            sj.add(node.verdi.toString());
            node = node.forrige; //Node blir forrige for neste løkke i while løkken
        }
        return sj.toString();
    }


    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Character[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.subliste(3, 8));  // [D, E, F, G, H]
        System.out.println(liste.subliste(5, 5));  // []
        System.out.println(liste.subliste(8, liste.antall()));  // [I, J]
        // System.out.println(liste.subliste(0,11));  // skal kaste unntak
    }
} // class DobbeltLenketListe


