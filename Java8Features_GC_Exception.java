import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Abstraction is the process of exposing the essential details of an entity, while ignoring the irrelevant details,
 * to reduce the complexity for the users.
 * Encapsulation is the process of bundling data and operations on the data together in an entity.
 * Inheritance is used to derive a new type from an existing type, thereby establishing a parent-child relationship.
 * Polymorphism lets an entity take on different meanings in different contexts.
 *
 * Generics add stability to your code by making more of your bugs detectable at compile time. Provides type safety.
 *
 *  wildcards in generics ?
 *
 * Collection<?> coll = new ArrayList<String>();
 * //OR
 * List<? extends Number> list = new ArrayList<Long>();
 * //OR
 * Pair<String,?> pair = new Pair<String,Integer>();
 *
 * GC: MARK AND SWEEP, CONCURRENT M N SWEEP, SERIAL GC, PARALLEL GC, G1 GC(DEFAULT FROM 9): BREAKS INTO MULTIPLE REGIONS OF MEMORY
 * GC: MARK-SWEEP APPROACH, MARK SWEEP COMPACT APPROACH, MARK COPY APPROACH
 * MEMORY - MANAGEMENT: YOUNG GEN(EDEN, SURVIVOR S0, SURVIVOR S1)[MINOR GC];
 *                      OLD GENERATION (MAJOR GC)
 *                      PERM MEMORY(BEFORE JAVA 8, REMOVED IN JAVA 8): THIS WAS EXCLUDING FROM HEAP MEMORY
 
 singleton pattern
 design patterns in java
 *
 */
public class Java8Features {

    /**
     *  Lambda expression: Anonymous functions as arguments
     *  Default methods: In interfaces for backward compatibility
     *  Functional Interfaces
     *  Streams
     *  LocalDate, LocalTime, LocalDateTime, Duration, Period
      */

    public static void main(String args[]) {

        // lambda
        // (parameters) -> expression OR () -> expression

        // forEach
        List<Integer> list = Arrays.asList(1,2,3);
        list.forEach(System.out::println);

        // Consumer action: takes one argument and returns result
        // Consumer can be used in all contexts where an object needs to be consumed,i.e. taken as input,
        // and some operation is to be performed on the object without returning any result
        Consumer<Integer> multiply = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.print(integer*2);
            }
        };

        list.forEach(multiply);

        // BiConsumer action: takes two arguments(x,y)
        // It represents a function which takes in two arguments and produces a result.
        // However these kind of functions don’t return any value
        BiConsumer<String, Integer> biConsumer = new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println("key: "+s+" , value: "+integer);
            }
        };

        Map<String, Integer> map = new HashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        map.forEach(biConsumer);
        
        //Supplier is functional interface which does not take any argument and produces result of type T.
        //It has a functional method called T get() As Supplier is functional interface, 
        //so it can be used as assignment target for lambda expressions.
         Supplier<String> supplier= ()-> "Arpit";
          System.out.println(supplier.get());
        
        @FunctionalInterface
public interface Supplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
        

        // forEach: Provides no ordering guarantees, can be used in sequential streams
        // forEachOrdered: provides ordering, can be used in parallel streams

        List<Integer> numberList = Arrays.asList(1,2,3,4,5);
        Consumer<Integer> action = System.out::println;

        // Sequential
        numberList.stream()
                .filter(n -> n%2  == 0) // takes a predicate
                .forEach( action );

        // Parallel
        numberList.stream()
                .filter(n -> n%2  == 0)
                .parallel()
                .forEachOrdered( action );

        // Stream operations return a stream i.e. pipelining of streams in done
        // Terminal operations return result of specific types
        // Intermediate operations return another stream
        // Coll, set and list, but Map not supported in streams, we can created stream of keys/values or entries.
        // Streams are lazy

        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9); // Stream.of(new Integer[]{1,2,3,4,5,6,7,8,9});
        stream.forEach(p -> System.out.println(p));

        Stream<Integer> randomNumbers = Stream
                .generate(() -> (new Random()).nextInt(100));

        randomNumbers.limit(20)
                .forEach(System.out::println);

        stream = list.stream();
        List<Integer> evenNumbersList = stream.filter(i -> i%2 == 0) // filter(predicate) intermediate opn
                .collect(Collectors.toList()); // .toArray(Integer[]::new) // foreach terminal opn
        System.out.print(evenNumbersList);

        // .map(String::toUpperCase) : converts each element in the stream into another object via the given function

        // boolean matchedResult = memberNames.stream()
        //        .anyMatch((s) -> s.startsWith("A"));

        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        // reduce() method performs a reduction on the elements of the stream with the given function.
        // The result is an Optional holding the reduced value
        Optional<String> reduced = memberNames.stream()
                .reduce((s1,s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);

        // Parallel streams
        List<Integer> ll = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++){
            list.add(i);
        }

        //Here creating a parallel stream
        Stream<Integer> st = ll.parallelStream();

        Integer[] evenNumbersArr = st.filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);

        // Boxed Streams: for primitives we need to box first
        //Get the collection and later convert to stream to process elements
        List<Integer> ints = IntStream.of(1,2,3,4,5)
                .boxed()
                .collect(Collectors.toList());

        // method references
        // we can refer a method from class or object using class::methodName

        // Optional is a way of replacing a nullable T reference with a non-null value
        Optional<Integer> canBeEmpty1 = Optional.of(5);
        canBeEmpty1.isPresent();                    // returns true
        canBeEmpty1.get();                          // returns 5

        Optional<Integer> canBeEmpty2 = Optional.empty();
        canBeEmpty2.isPresent();                    // returns false

        //  Predicate is a functional interface and can therefore be used as the
        //  assignment target for a lambda expression or method reference.
        //  you can use predicates anywhere where you need to evaluate a condition on group/collection of similar objects
        /**
         * public static Predicate<Employee> isAdultFemale()
         * {
         *     return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("F");
         * }
         *
         */


        // Join methods in Java 8
        // Join string array to produce single String
        String joinedString = String.join(", ", "How", "To", "Do", "In", "Java");

    }

    // functional interfaces has only one abstract method
    // The type in which lambda expressions are converted, are always of functional interface type
    // Two good examples of functional interface type are Consumer and BiConsumer interfaces
    @FunctionalInterface
    public interface Runnable {
        public abstract void run();

        // any numbers of default methods can be added in functional interfaces
        // You can define static / default methods in interface
        // Default methods implemented to enable the functionality of lambda expression in java
        default void doSomeMoreWork1(){
            //Method body
        }

        default void doSomeMoreWork2(){
            //Method body
        }

        static void meth(){

        }

    }

    /**
     * Checked exceptions: A checked exception is an exception that is typically a user error or a problem that cannot
     * be foreseen by the programmer.
     * Runtime exceptions: A runtime exception is an exception that occurs that probably could have been avoided by the programmer.
     * Errors: These are not exceptions at all, but problems that arise beyond the control of the user or the programmer.
     * throw keyword is used to explicitly throw an exception.
     * throws keyword is used declare the list of exceptions which may be thrown by that method or constructor.
     *
     * Synchronous exceptions happen at a specific program statement, no matter,
     * how many times we run a program in similar execution environment. (NPE)
     *
     * Asynchronous exceptions can raise practically anywhere.
     *
     */
    private void javaException() {

    }

    /**
     * Serializable is a marker interface i.e. does not contain any method but Externalizable
     * interface contains two methods writeExternal() and readExternal().
     * When any class in Java implement java.io.Externalizable than its your responsibility to implement Serialization process
     * i.e. preserving all important information. while in serializable no need.
     * Use transient and static keyword to block fields.
     *
     *
     *
     *
     */

    // When we declare a variable volatile, every thread reads its value from main memory and
    // don't used cached value available in every thread stack.


    // CONCURRENT HASHMAP: divides the map into segments and only locking during the write operation.

    // wait() method releases the lock or monitor, while sleep pauses.

    // IdentityHashMap uses == for equality instead of equals().

    /**
     * Why SpringBoot:
     * 1. Easy dependency Management by springboot-starter-web
     * 2. Auto Configuration
     * 3. Embedded Servlet Container Support by spring-boot-starter-tomcat
     * 4. Creates lighweight jar instead of war
     */

}
