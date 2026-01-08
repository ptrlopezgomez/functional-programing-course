package collections;

import java.util.*;
import java.util.stream.Collectors;

import static collections.Logger.printlnf;

class Logger {
    public static void printlnf(String format, Object ... args) {
        System.out.printf(format, args);
        System.out.println();
    }
}

class ArrayListExample {

    class M {
        public Integer attribute1;
        public M(int value) {
            this.attribute1 = value;
        }

        @Override
        public String toString() {
            return "attribute1=" + attribute1;
        }
    }

    public static void main(String[] args) {

        try {
            /*
             * This is an Inmutable List so cannot be modified, this means cannot add/remove any members from the list
             * But the actual members in the list can be modified, like the value can be changed/updated. So the add method
             * will throw an error, so it was commented out.
             * */
            //--------------------------------------------------------------------------------------------
            // List<Integer> lst1 = List.of(1,3,4,5,6,7,8,9,10);
            // lst1.add(2);
            //--------------------------------------------------------------------------------------------
            // To achieve that we must use objects, in this case lets create a list of <M> type objects
            var m = new ArrayListExample().new M(1);
            var m2 = new ArrayListExample().new M(2);
            var m3 = new ArrayListExample().new M(4);
            List<M> mlist = List.of(m, m2, m3);
            var aux = mlist.get(1);
            System.out.println("Original list values=" + mlist);// 1,2.4
            System.out.println("Modify element in index 1, to 3");
            aux.attribute1=3;
            System.out.println("List mnodified:" + mlist);// 1,3,4
        } catch (Exception e) {
            e.printStackTrace();
            /*
            java.lang.UnsupportedOperationException
                at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
                at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
                at collections.ArraListExample.main(ArraListExample.java:11)
            */
        }
        //-------- Transform int[] to List<Integer> ------------
        System.out.println("Transform Array of int to List of Integer ----------");
        int[] ints = {9,8,7,6};
        List<Integer> integerList = new ArrayList<>(Arrays.stream(ints).boxed().toList());
        System.out.println("List of int[] as List: " + integerList);

        //-------- Transform byte[] to List<Byte> ------------
        System.out.println("Transform Array of byte to List of Byte ----------");
        // The Array.stream() does not support bytes as a parameter, so it has to be initialized in a manual loop
        byte[] bytes = {1, 2, 3, -128, 127};
        System.out.println("Array of bytes[]:" + bytes);
        List<Byte> byteList = new ArrayList<>();
        for (byte b: bytes) {
            byteList.add(b);
        }
        System.out.println("List of bytes:" + byteList);

        // Transform short[] to List<Short>
        System.out.println("Transform Array of short to List of Short ----------");
        short[] shorts = {10, 11, 12, 13, 14, 15};
        System.out.println("Array of short[]:" + shorts);
        List<Short> listOfShortValues = new ArrayList<>(shorts.length);
        for(short s : shorts) {
            listOfShortValues.add(s);
        }
        System.out.println("List of shorts:" + listOfShortValues);


    }
}

class ArrayListExercise {

    // TODO: Perform create, access, remove, iterate, search on ArrayList
    public List<String> performOperations() {
        List<String> fruits = new ArrayList<>(List.of("Apple", "Banana", "Mango", "Orange"));
        String fruit0 = fruits.getFirst();
        fruits.removeIf((it)-> it.equalsIgnoreCase("banana"));
        System.out.println("List with Banana removed: " + fruits);
        System.out.println(fruits);
        var containsMango = false;
        if (fruits.contains("Mango")) {
            containsMango = true;
        }
        fruits.replaceAll(String::toUpperCase);
        System.out.println("List uppercased:" + fruits);
        fruits.add("First:" + fruit0);
        fruits.add("CONTAINS_MANGO:" + containsMango);
        return fruits;
    }

    public static void main(String[] args) {
        System.out.println("resultado:" + new ArrayListExercise().performOperations());
    }
}


class LinkedListExamples {

    class R {
        String field1;
        String uuid;

        R(String field1) {
            this.field1 = field1;
        }

        R(String field1, String uuid) {
            this.field1 = field1;
            this.uuid = uuid;
        }

        @Override
        public String toString() {
            return "R(field1=" + field1 + ",uuid=" + uuid + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof R r) {
                //printlnf("r.field1=%s, this.uuid=%s, r.uuid=%S", r.field1, this.uuid, r.uuid);
                return r.field1.equalsIgnoreCase(this.field1)
                        || (r.uuid!=null && r.uuid.contains(this.uuid));
            }
            return false;
        }
    }

    public static R newR(String value) {
        return new LinkedListExamples().new R(value);
    }

    public static R newR(String value, String value2) {
        return new LinkedListExamples().new R(value, value2);
    }

    public static void main(String[] args) {
        List<R> linkedList = new LinkedList<>();
        {
            linkedList.add(newR("Cable")); //0
            linkedList.add(newR("Casa"));  //1
            linkedList.add(newR("Arbol")); //2
            linkedList.add(newR("Piso"));  //3
            printlnf("OriginalList: %s", linkedList);

            linkedList.add(2, newR("Ventana"));
            printlnf("ModifiedList: %s", linkedList);

            linkedList.addFirst(newR("Barda"));
            printlnf("Added first Barda: %s", linkedList);
            linkedList.addLast(newR("Techo"));
            printlnf("Added Last Techo: %s", linkedList);

            linkedList.addAll(List.of(newR("Baño"), newR("Mesa")));
            printlnf("Added All(mesa and baño): %s", linkedList);
            printlnf("This is the linked list %s, Lenght=%d", linkedList, linkedList.size());
        } // Add block
        {
            // To remove we can use:
            var element = linkedList.get(0); // Barda
            printlnf("Element to be removed: %s", element);
            linkedList.remove(element);
            printlnf("Lista after remove: %s", linkedList);
            printlnf("First removed (Cable)?: %b, result:%s", linkedList.removeFirst(), linkedList);
            printlnf("Last removed (Mesa)?: %b, result:%s", linkedList.removeLast(), linkedList);
            linkedList.add(newR("Pie"));
            printlnf("Removed by search? :%b, result: %s", linkedList.removeIf(it -> it.field1.length()<=3), linkedList);
            String before = linkedList.toString();
            var listToRemove = List.of(newR("techo"), newR("v"), newR("l"));
            printlnf("""
                    ----------- removeAll -------------
                    Before removing : %s
                    Values to Remove: %s
                    Removed all?    : %b
                    Results after   : %s
                    --------------------------------------
                    """, before
                    , listToRemove.toString()
                    , linkedList.removeAll(listToRemove)
                    , linkedList);
        } // Remove block
        {
            linkedList.replaceAll(it -> {
                it.uuid = String.valueOf(UUID.randomUUID());
                return it;
            });
            printlnf("list with uuids: %s", linkedList);

            printlnf("""
                    --------------- Search block ---------------
                    List with UUIDs: %s
                    UUID contains 'a':  %b
                    """, linkedList
            , linkedList.contains(newR("a", "a")));

        } // Search block



    }
}

class LinkedListExercise {

    // TODO: Perform create, retrieve, remove, iterate, and search operations
    public List<String> performOperations() {

        List<String> cities = new LinkedList<>(List.of("Mumbai", "Delhi", "Bangalore", "City4"));
        var city0 = cities.getFirst();
        cities.remove("Delhi");

        var contained = cities.contains("Bangalore");
        cities.replaceAll(String::toUpperCase);

        cities.add("FIRST:" + city0);
        cities.add("CONTAINS_BANGALORE:" + contained);

        return cities;
    }

    public static void main(String[] args) {
        System.out.println("resultado:" + new LinkedListExercise().performOperations());
    }
}

class SetExamples {

    class S {
        String field1;
        public S(String field1) {
            this.field1 = field1;
        }

        @Override
        public String toString() {
            return "S{" +
                    "field1='" + field1 + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    static S newS(String field1) {
        return new SetExamples().new S(field1);
    }

    public static void main(String[] args) {

        {
            Set<String> setCollection = new HashSet<>(Set.of("A", "B", "C", "D", "ANOTHER", "JAVA", "SPRING"));
            var anotherSet = new HashSet<>(setCollection);
            anotherSet.add(null);
            var afterRemoveSet = new HashSet<>(anotherSet);
            //afterRemoveSet.removeIf(Objects::isNull);
            afterRemoveSet.remove(null);
            var clearedSet = new HashSet<>(afterRemoveSet);
            clearedSet.clear();
            var removedAllSet = new HashSet<>(afterRemoveSet);
            removedAllSet.removeAll(afterRemoveSet.stream().filter(it -> it.length() < 3).toList());
            var usingRemoveMethod = new HashSet<>(removedAllSet);
            usingRemoveMethod.addAll(List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
            var beforeRemoving = usingRemoveMethod.toString();
            // usingRemoveMethod.stream().filter(it -> it.length()> 10).forEach(usingRemoveMethod::remove);
            usingRemoveMethod.stream().filter(it -> it.length() > 10).toList().forEach(usingRemoveMethod::remove);

            printlnf("""
                            -- HASHSET BLOCK EXAMPLES -----------------------------------------------------------------------------------------------------------
                            initial collection: %s
                            added null element: %s
                            null removed set  : %s using remove(null) or removeIf(Objects::null)
                            cleared set       : %s
                            removed length<3  : %s using Set.removeAll
                            added uuids       : %s 
                            removed uuids     : %s using set.remove
                            ------------------------------------------------------------------------------------------------------------------------------------
                            """
                    , setCollection
                    , anotherSet
                    , afterRemoveSet
                    , clearedSet
                    , removedAllSet
                    , beforeRemoving
                    , usingRemoveMethod
            );
        } // HashSet block

        {
            Set<S> linkedSet = new LinkedHashSet<>(List.of(newS("El1"), newS("El2"), newS("El3"), newS("El4")));
            Set<S> hashSet = new HashSet<>(List.of(newS("El1"), newS("El2"), newS("El3"), newS("El4")));
            printlnf("""
                            -- LINKED HASHSET BLOCK EXAMPLES ---------------------------------------------------------------------------------------------------
                            initial linked hashset collection: %s -- this keeps the order as provided in the List collection
                            compared order with set          : %s -- different order
                            ------------------------------------------------------------------------------------------------------------------------------------
                            """
            , linkedSet
            , hashSet
            );

        } // LinkedHashSet block
    }
}

class TreeSetExamples {

    class E implements Comparable<E> {
        private String field1;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        E(String field1) {
            this.field1=field1;
        }

        @Override
        public String toString() {
            return "E{" +
                    "field1='" + field1 + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            E e = (E) o;
            return Objects.equals(field1, e.field1);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(field1);
        }

        @Override
        public int compareTo(E o) {
            return this.getField1().compareTo(o.getField1());
        }
    }

    class U implements Comparable<U>{
        private String field1;

        public U(String field1) {
            this.field1 = field1;
        }

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        @Override
        public String toString() {
            return "U{" +
                    "field1='" + field1 + '\'' +
                    '}';
        }

        @Override
        public int compareTo(U o) {
            return this.getField1().compareToIgnoreCase(o.getField1());
        }
    }

    public static E newE(String field1) {
        return new TreeSetExamples().new E(field1);
    }

    public static U newU(String field1) {
        return new TreeSetExamples().new U(field1);
    }

    public static void main(String[] args) {
        Set<E> treeSet1 = new TreeSet<>();
        treeSet1.add(newE("A"));
        treeSet1.add(newE("D"));
        treeSet1.add(newE("B"));
        treeSet1.add(newE("C"));
        treeSet1.add(newE("b"));

        SortedSet<E> treeSet2 = new TreeSet<>(Comparator.reverseOrder());
        treeSet2.add(newE("C"));
        treeSet2.add(newE("a"));
        treeSet2.add(newE("b"));
        treeSet2.add(newE("c"));
        treeSet2.add(newE("d"));
        treeSet2.add(newE("B"));
        treeSet2.add(newE("A"));
        treeSet2.add(newE("D"));

        SortedSet<U> treeSet3 = new TreeSet<>();
        treeSet3.add(newU("c"));
        treeSet3.add(newU("a"));
        treeSet3.add(newU("b"));
        treeSet3.add(newU("C"));
        treeSet3.add(newU("d"));
        treeSet3.add(newU("B"));
        treeSet3.add(newU("A"));
        treeSet3.add(newU("D"));

        printlnf("""
                ----------------------------------------------------------------------------------
                TreeSet #1: %s <-- by default is CASE SENSITIVE and ASCENDING order
                TreeSet #2: %s <-- DESCENDING order, uppercase before lowercase
                TreeSet #3: %s
                ----------------------------------------------------------------------------------
                """
                , treeSet1
                , treeSet2
                , treeSet3
        );
    }
}

class TreeSetExercise {
    // TODO: Perform create, retrieve, remove, iterate using TreeSet
    public Set<String> performOperations() {
        /*
        Create a TreeSet<String> and add: "Zara", "Amit", "John", "Bob"
        Retrieve the first and last elements using .first() and .last()
        Remove "John" from the set
        Iterate the remaining elements and convert each name to uppercase
        Add the following result markers:
        "FIRST:Amit"
        "LAST:Zara"
        "CONTAINS_JOHN:false"
        Return the final result Set<String>.
        * */
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Zara");
        treeSet.add("Amit");
        treeSet.add("John");
        treeSet.add("Bob");
        var first = treeSet.first();
        var last = treeSet.last();
        treeSet.remove("John");
        // Using the forEach remaining function (Java 8)
        TreeSet<String> treeSetUppercased = new TreeSet<>();
        treeSet.iterator().forEachRemaining(it -> treeSetUppercased.add(it.toUpperCase()));
        // Using Streams (Java 8)
        SortedSet<String> uppercasedSet = treeSet.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(TreeSet::new));
        treeSet.clear();
        treeSet.addAll(treeSetUppercased);
        treeSet.add("FIRST:" + first);
        treeSet.add("LAST:" + last);
        treeSet.add("CONTAINS_JOHN:" + treeSet.contains("John"));

        printlnf("""
                --------------------------------------
                TreeSet Uppercased Result: %s
                TreeSet Uppercased (Streams): %s
                TreeSet Exercise Result: %s
                --------------------------------------
                """
        , treeSet
        , treeSetUppercased
        , uppercasedSet);

        return treeSet;
    }

    public static void main(String[] args) {
        System.out.println("resultado:" + new TreeSetExercise().performOperations());
    }
}

class HashMapExamples {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Uno", 1);
        map.put("Dos", 2);
        map.put("Tres", 3);
        map.put("Cuatro", 4);
        map.putIfAbsent("Cuatro", 5);
        map.put(null, null);
        map.put(null, null);
        map.putAll(Map.of("Ocho", 8, "Nueve", 9));
        var valorObtenido = map.getOrDefault("Diez", 10);
        var contieneKey   = map.containsKey("Cuatro");
        var contieneValue = map.containsValue(9);
        var contieneNullKey = map.containsKey(null);
        var valorCompute = map.compute("Ocho", (key, val) -> val != null ? val*val: 0);
        var computeIfAbsentResult = map.computeIfAbsent("Diez", (llave) -> 10*10);
        var computeIfAbsentResult2 = map.computeIfAbsent("Diez", (llave) -> 10/2);
        var _10ValueAddedOrNot = map.putIfAbsent("Diez", 10); // Already added
        map.putIfAbsent("Diez", 10);
        var computeIfPresent = map.computeIfPresent("Ocho", (llave, valor) -> {
            System.out.println("Inside the compute if present");
            return valor/2;
        });
        // Using the merge
        Map<String, Integer> map2 = new HashMap<>(map);
        map2.merge("Nueve", 9*9, (llave, valor) -> {
            var resultado = (int) Math.log((double) valor);
            System.out.println("Resultado merge:" + resultado);
            return resultado;
        });

        printlnf("""
                ------------------------------------------------------------------------------------------------
                Map                :%s
                Valor Obtenido     :%s 
                ---- The main 3 elements in the map ---
                EntrySet           :%s 
                KeySet             :%s 
                ValuesCollection   :%s
                ---- contains functions ---
                ContainsKey        :%s 
                ContainsValue      :%s 
                ContainsNull       :%s 
                ---- compute functions --- 
                ValorComputed      :%s 
                ComputeIfAbsent    :%s <-- This will add the result to the map with the corresponding key
                ComputeIfAbsent2   :%s <-- Not absent scenario 
                _10ValueAddedOrNot :%s <-- putIfAbsent 
                ComputeIfPresent   :%s <-- computeIfPresent
                ---- Using merge() ----
                Merged results     :%s 
                ------------------------------------------------------------------------------------------------
                """
                , map
                , valorObtenido
                , map.entrySet()
                , map.keySet()
                , map.values()
                , contieneKey
                , contieneValue
                , contieneNullKey
                , valorCompute
                , computeIfAbsentResult
                , computeIfAbsentResult2
                , _10ValueAddedOrNot
                , computeIfPresent
                , map2
                );

        /*{
            // Iterations
            System.out.println("------ Iterations -----------------");
            System.out.println("------ for each method -----------");
            map.forEach((llave, valor) -> {
                System.out.println("-[1]-->Key=" + llave + ", Value=" + valor);
            });
            System.out.println("------- for each remaining ------------");
            var iterator = map.entrySet().iterator();
            iterator.forEachRemaining(llave -> {
                System.out.println("-[2]-->Key=" + llave + ", Value=" + llave.getValue());
            });
            System.out.println("------- Using while and explicit iterator ------------");
            Iterator<Map.Entry<String, Integer>> iteratorExplicit = map.entrySet().iterator();
            while(iteratorExplicit.hasNext()) {
                Map.Entry<String, Integer> entry = iteratorExplicit.next();
                System.out.println("-[3]-->Key=" + entry.getKey() + ", Value=" + entry.getValue());
            }
            System.out.println("---- Using For statement -----------");
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println("-[4]-->Key=" + entry.getKey() + ", Value=" + entry.getValue());
            }

        }
        */ // Iterations examples

    }
}

class HashMapsExcercise {
    // TODO: Perform HashMap creation, add, update, retrieve, remove, iterate
    public Map<String, Integer> performOperations() {
        /*
        Create a HashMap<String, Integer> called stock
        Add the following key-value pairs:
        "Apple" → 50, "Banana" → 30, "Orange" → 20, "Grapes" → 40
        X Modify the value for "Banana" to 35
        X Retrieve and store the quantity of "Apple"
        X Remove the "Orange" entry
           Iterate the stock map and:
        X Create a new map with all keys in uppercase
            Add two special entries:
        "APPLE_QTY" → 50
        "CONTAINS_ORANGE" → 0 or 1
        */
        Map<String, Integer> map = new HashMap<>(Map.of("Apple", 50, "Banana", 10, "Orange", 20, "Grapes", 40));
        map.merge("Banana", 50, (llave, valor) -> valor+5);
        var appleValue = map.get("Apple");
        var orangeEntry = map.remove("Orange");
        var entries = new HashSet<>(map.entrySet());
        entries.forEach(entry -> {
            map.putIfAbsent(entry.getKey().toUpperCase(), entry.getValue());
            map.remove(entry.getKey());
        });
        map.put("APPLE_QTY", 50);
        if (map.get("Orange") == null) {
            map.put("CONTAINS_ORANGE", 0);
        } else {
            map.put("CONTAINS_ORANGE", 1);
        }
        return map;
    }

    public static void main(String[] args) {
        printlnf("""
                ----
                Resultado performOperations: %s
                ----
                """, new HashMapsExcercise().performOperations());
    }
}

class LinkedHashMapExamples {
    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Uno", "1");
        linkedHashMap.put("Dos", "2");
        linkedHashMap.put("Tres", "3");
        linkedHashMap.put("Cuatro", "4");

        var firstElement = linkedHashMap.firstEntry();
        var lastElement = linkedHashMap.lastEntry();

        var firstElementRemoved = new LinkedHashMap<>(linkedHashMap);
        firstElementRemoved.pollFirstEntry();

        var lastElementRemoved = new LinkedHashMap<>(linkedHashMap);
        lastElementRemoved.pollLastEntry();

        firstElementRemoved.putFirst("Cero", "0");
        lastElementRemoved.putLast("Cinco", "5");

        printlnf("""
                ----
                Linked Hashmap: %s
                First element : %s
                Last element  : %s
                After poll first: %s
                After poll last: %s
                Map reversed  : %s
                Normal Set    : %s
                Sequenced Set : %s
                ----
                """, linkedHashMap, firstElement , lastElement,
                firstElementRemoved, lastElementRemoved, linkedHashMap.reversed()
                , linkedHashMap.entrySet(), linkedHashMap.sequencedEntrySet()
        );
    }
}

class LinkedHashMapExcercise {
    public static void main(String[] args) {
        /*
        Create a LinkedHashMap<String, String> called capitals

        Add the following country-capital pairs:
        "India" → "New Delhi"
        "USA" → "Washington DC"
        "UK" → "London"
        "Japan" → "Tokyo"

        Remove the entry for "UK"
        Use .keySet(), .values(), .entrySet() to access data
        Check if "Japan" and "UK" exist using .containsKey()

        Build and return a String that includes:
        All remaining entries in insertion order (key=value)
        Result markers like HAS_JAPAN=true and HAS_UK=false
        * */

        LinkedHashMap<String, String> capitals = new LinkedHashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington DC");
        capitals.put("UK", "London");
        capitals.put("Japan", "Tokyo");

        capitals.remove("UK");
        var keys = capitals.keySet();
        var values = capitals.values();
        var entries = capitals.entrySet();
        // ---- above methods access these below implementations from SequencedMap interface
        var seqKeys = capitals.sequencedEntrySet();
        var seqValues = capitals.sequencedValues();
        var seqEntries = capitals.sequencedEntrySet();

        var existJP = capitals.containsKey("Japan");
        var existUK = capitals.containsKey("UK");

        capitals.put("HAS_JAPAN", String.valueOf(existJP));
        capitals.put("HAS_UK", String.valueOf(existUK));

        System.out.println(capitals);
    }
}

class TreeMapExamples {
    public static void main(String[] args) {
        TreeMap<Double, String> tmt = new TreeMap<>();
        tmt.put(100.0, "SKU1");
        tmt.put(87.52, "SKU2");
        tmt.put(65.52, "SKU3");
        tmt.put(54.0, "SKU4");
        tmt.put(2.0, "SKU5");

        printlnf("""
                ---------------------------------------------------------------------
                Treemap                           :%s
                The least higher key than 100     : %s
                The least higher key than 2       : %s
                The highest lower key than 65     : %s
                The highest lower key than 2      : %s
                The ceiling entry than 100        : %s
                The ceiling entry than 2          : %s
                The floor entry than 65          : %s
                The floor entry than 2            : %s
                ---------------------------------------------------------------------
                """
                , tmt
                , tmt.higherEntry(100.0)
                , tmt.higherEntry(2.0)
                , tmt.lowerEntry(65.0)
                , tmt.lowerEntry(2.0)
                , tmt.ceilingEntry(100.0)
                , tmt.ceilingEntry(2.0)
                , tmt.floorEntry(65.0)
                , tmt.floorEntry(2.0)
        );
    }
}