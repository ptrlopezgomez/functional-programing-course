package collections;

import java.util.*;

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