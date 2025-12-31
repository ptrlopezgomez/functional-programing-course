import java.util.*;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    class E {
        String name;
    }
    class K {
        String name;
    }
    class V {
        String name;
    }
    public static void main(String[] args) {

        Iterable<E> iterable; //---------------------------------->[I] Main interface
            Collection<E> s; //----------------------------------->[I] Extends Iterable interface
                AbstractCollection<E> ac; //---------------------->[C] Implements Collection interface
                SequencedCollection<E> sc; //--------------------->[I] Extends Collections interface
                    List<E> l; //--------------------------------->[I] Extends SequencedCollection interface
                        AbstractList<E> al; //-------------------->[C] Implements List interface extends AbstractCollection class
                            AbstractSequentialList<E> asl; //----->[C] Extends AbstractList class
                                LinkedList<E> ll; //-------------->[C] Implements List interface and Dequeue extends AbstractSequentialList class
                            ArrayList<E> arrl; //----------------->[C] Implements List interface extends AbstractList class
                        Vector<E> vr; //-------------------------->[C] Implements List interface extends AbstractList class
                            Stack<E> st; //----------------------->[C] Implements List interface extends AbstractList class
            Set<E> set; //---------------------------------------->[I] Extends Collection interface
                AbstractSet<E> as; //----------------------------->[C] Implements Set interface extends AbstractCollection class
                SequencedSet<E> ss2; //--------------------------->[I] Extends SequencedCollection interface (since JDK 21)
                SortedSet<E> ss; //------------------------------->[I] Extends Set interface
                    NavigableSet<E> ns; //------------------------>[I] Extends SortedSet interface
                        TreeSet<E> ts; //------------------------->[C] Implements NavigableSet interface extends AbstractSet class
                HashSet<E> hs; //--------------------------------->[C] Implements Set interface extends AbstractSet class
                    LinkedHashSet<E> lhs; //---------------------->[C] Implements SequencedSet interface extends HashSet class
            Queue<E> q; //---------------------------------------->[I] Extends Collection interface
                Deque<E> d; //------------------------------------>[I] Extends Queue interface and extends SequencedCollection
                    ArrayDeque<E> ad; //-------------------------->[C] Implements Deque interface
                AbstractQueue<E> aq; //--------------------------->[C] Implements Queue interface extends AbstractCollection class
                    PriorityQueue<E> pq; //----------------------->[C] Extends AbstractQueue class
                BlockingQueue<E> bq; //--------------------------->[I] Extends Queue interface
                    ArrayBlockingQueue<E> abq; //----------------->[C] Implements BlockingQueue interface and extends AbstractQueue
                    LinkedBlockingQueue<E> lbq; //---------------->[C] Implements BlockingQueue interface and extends AbstractQueue

        Map<K, V> map; //----------------------------------------->[I] Main interface
            AbstractMap<K, V> am; //------------------------------>[C] Implements Map
                HashMap<K, V> hm; //------------------------------>[C] Extends AbstractMap class and implements Map
            SequencedMap<K, V> seqm; //--------------------------->[I] Extends Map class
                SortedMap<K, V> sm; // --------------------------->[I] Extends SequencedMap class
                    NavigableMap<K, V> nvm; // ------------------->[I] Extends SortedMap class
            TreeMap<K, V> tm; // --------------------------------->[C] Extends AbstractMap class and implements NavigableMap
        ConcurrentMap<K, V> cm; // ------------------------------->[C] Extends Map
            ConcurrentHashMap<K, V> chm; //----------------------->[C] Extends AbstractMap implements ConcurrentMap
        Dictionary<K, V> dct; //---------------------------------->[C] Obsolete class use Map instead
            Hashtable<K,V> ht; // -------------------------------->[C] Extends Dictionary class and implements Map

    }
}