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
        throw new UnsupportedOperationException();
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

        Node<T> n = new Node<>(verdi);

        if ((hode == null) && (hale == null)) {
            hode = n;
            hale = n;
        } else {
            Node<T> bakerst = new Node<>(verdi);
            hale = n;
            hale.neste = bakerst;
            hale = bakerst;
        }
        antall = +1;
        endringer = +1;
        return true;
    }



    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
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
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        System.out.println(liste.toString() + " " + liste.omvendtString());
        for (int i = 1; i <= 3; i++) {
            liste.leggInn(i);
            System.out.println(liste.toString() + " " + liste.omvendtString());
        }
    }
} // class DobbeltLenketListe


