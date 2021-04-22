package org.array.utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface ArrayUtils {

    @SafeVarargs
    static <T> Set<T> ofSet(T... values){
        return new HashSet<>(Arrays.asList(values));
    }

    static <A, B> boolean contains(A[] a, B[] b){
        for(int A = 0; A < Math.min(a.length, b.length); A++){
            if(!a[A].equals(b[A])){
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    static <O, T> Set<T> getCommon(Function<O, T> function, Function<T, String> toID, O... collection){
        return getCommon(function, toID, Arrays.asList(collection));
    }

    static <O, T> Set<T> getCommon(Function<O, T> function, Function<T, String> toID, Iterable<O> collection){
        Map<String, Map.Entry<T, Integer>> map = new HashMap<>();
        collection.forEach(v -> {
            T value = function.apply(v);
            if(value == null){
                return;
            }
            String id = toID.apply(value);
            if(map.containsKey(id)){
                map.get(id).setValue(map.get(id).getValue() + 1);
                return;
            }
            map.put(id, new AbstractMap.SimpleEntry<>(value, 1));
        });
        Set<Map.Entry<String, Map.Entry<T, Integer>>> best = getBests(e -> e.getValue().getValue(), (c, b) -> c > b, Integer::equals, map.entrySet());
        if(best.isEmpty()){
            return new HashSet<>();
        }
        return convert(Collectors.toSet(), v -> v.getValue().getKey(), best);
    }

    /**
     * Counts all int numbers within the array
     * @param start the number to start with
     * @param function convert element to number
     * @param collection the array
     * @param <T> the element type
     * @return the total
     */
    static <T> int countInt(int start, Function<T, Integer> function, Iterable<T> collection){
        return count(start, function, Integer::sum, collection);
    }

    /**
     * Counts all double numbers within the array
     * @param start the number to start with
     * @param function convert element to number
     * @param collection the array
     * @param <T> the element type
     * @return the total
     */
    static <T> double countDouble(double start, Function<T, Double> function, Iterable<T> collection){
        return count(start, function, Double::sum, collection);
    }

    /**
     * Count all values that match within an array
     * @param start the start value
     * @param function convert element to number
     * @param add add the number to the current total
     * @param collection the array
     * @param <T> the type within the array
     * @param <N> the type of number
     * @return the total.
     */
    static <T, N extends Number> N count(N start, Function<T, N> function, BiFunction<N, N, N> add, Iterable<T> collection){
        N num = start;
        for(T value : collection){
            num = add.apply(function.apply(value), num);
        }
        return num;
    }

    static <E, I, T extends Collection<I>> T build(T array, BiConsumer<T, E> consumer, Iterable<E> collection){
        collection.forEach(t -> consumer.accept(array, t));
        return array;
    }

    /**
     * Converts the array into another array
     * @param collector The return collection type
     * @param consumer Convert element from the original to the new
     * @param collection The original array
     * @param <A> The original array element type
     * @param <E> The new array element type
     * @param <I> The supplier
     * @param <T> The supplier
     * @return The new array
     */
    static <A, E, I, T extends Collection<E>> T convert(Collector<E, I, T> collector, Function<A, E> consumer, Iterable<A> collection){
        I supplier = collector.supplier().get();
        collection.forEach(e -> collector.accumulator().accept(supplier, consumer.apply(e)));
        return collector.finisher().apply(supplier);
    }

    /**
     * Converts the array into another array
     * @param clazz The new array type
     * @param function convert the original element to the new
     * @param array the original array
     * @param <E> The original array element
     * @param <T> The new array element
     * @return The new array
     */
    @SafeVarargs
    static <E, T> T[] convert(Class<T> clazz, Function<E, T> function, E... array){
        T[] array1 = (T[]) Array.newInstance(clazz, array.length);
        for(int A = 0; A < array.length; A++){
            array1[A] = function.apply(array[A]);
        }
        return array1;
    }

    /**
     * Converts the array into another array
     * @param function how to convert the element
     * @param collection the original collection
     * @param <E> The original collection element
     * @param <I> The new collection element type
     * @return The new list
     */
    static <E, I>  List<I> convert(Function<E, I> function, Iterable<E> collection){
        return convert(new ArrayList<>(), function, collection);
    }

    /**
     * Converts the array into another array
     * @param array array to add to
     * @param function convert element to the other
     * @param collection the original array
     * @param <E> original array element
     * @param <I> new array element type
     * @param <T> collection type
     * @return the provided new collection
     */
    static <E, I, T extends Collection<I>> T convert(T array, Function<E, I> function, Iterable<E> collection){
        collection.forEach(c -> array.add(function.apply(c)));
        return array;
    }

    static <K, V> Map<K, V> toMappedValues(Function<V, K> toKey, Collection<V> keys){
        return keys.parallelStream().collect(Collectors.toMap(toKey, v -> v));
    }

    static <K, V> Map<K, V> toMappedKeys(Function<K, V> toKey, Collection<K> keys){
        return keys.parallelStream().collect(Collectors.toMap(k -> k, toKey));
    }

    /**
     * Converts a specified array into a String.
     * @param split The devide between every instance
     * @param toString Converts the specified type into String
     * @param array The array to be converted
     * @param <T> The class type of the array
     * @return A string output
     */
    @SafeVarargs
    static <T> String toString(String split, Function<T, String> toString, T... array){
        return toString(split, toString, Arrays.asList(array));
    }

    /**
     * Converts a specified array into a String.
     * @param split The devide between every instance
     * @param toString Converts the specified type into String
     * @param array The array to be converted
     * @param <T> The class type of the array
     * @return A string output
     */
    static <T> String toString(String split, Function<T, String> toString, Iterable<T> array){
        StringBuilder ret = null;
        for(T value : array){
            if(ret == null){
                ret = new StringBuilder(toString.apply(value));
            }else{
                ret.append(split).append(toString.apply(value));
            }
        }
        if(ret == null){
            return null;
        }
        return ret.toString();
    }

    /**
     * Gets the "best" element from the provided array
     * @param function convert element to value
     * @param compare compare two values
     * @param array original array
     * @param <T> element type
     * @return the "best" element - optional if no best can be found
     */
    @SafeVarargs
    static <T> Optional<T> getBest(Function<T, Integer> function, BiPredicate<Integer, Integer> compare, T... array){
        return getBest(function, compare, Arrays.asList(array));
    }

    /**
     * Gets the "best" element from the provided array
     * @param function convert element to value
     * @param compare compare two values
     * @param collection original array
     * @param <T> element type
     * @return the "best" element - optional if no best can be found
     */
    static <T> Optional<T> getBest(Function<T, Integer> function, BiPredicate<Integer, Integer> compare, Iterable<T> collection){
        T value = null;
        Integer best = null;
        for (T value1 : collection) {
            if (value == null) {
                value = value1;
                best = function.apply(value1);
            }
            int current = function.apply(value1);
            if (compare.test(current, best)) {
                value = value1;
                best = function.apply(value1);
            }
        }
        return Optional.ofNullable(value);
    }

    /**
     * Gets the "best" values from the collection
     * @param function convert element to value
     * @param compare compare two values (current best > comparison)
     * @param equal checks the two value are equal
     * @param array the original array
     * @param <T> element type
     * @param <N> The value to compare
     * @return the best elements
     */
    @SafeVarargs
    static <T, N extends Number> Set<T> getBests(Function<T, N> function, BiPredicate<N, N> compare, BiPredicate<N, N> equal, T... array){
        return getBests(function, compare, equal, Arrays.asList(array));
    }

    /**
     * Gets the "best" values from the collection
     * @param function convert element to value
     * @param compare compare two values (current best > comparison)
     * @param equal checks the two value are equal
     * @param collection the original array
     * @param <T> element type
     * @param <N> The value to compare
     * @return the best elements
     */
    static <T, N extends Number> Set<T> getBests(Function<T, N> function, BiPredicate<N, N> compare, BiPredicate<N, N> equal, Iterable<T> collection){
        Set<T> value = new HashSet<>();
        N best = null;
        for(T value1 : collection){
            if(best == null){
                value.add(value1);
                best = function.apply(value1);
            }
            N current = function.apply(value1);
            if(compare.test(current, best)){
                value.clear();
                value.add(value1);
                best = function.apply(value1);
            }else if(equal.test(current, best)){
                value.add(value1);
            }
        }
        return value;
    }

    static String[] trim(int amount, String... array){
        String[] args = new String[array.length - amount];
        System.arraycopy(array, 0, args, 0, args.length);
        return args;
    }

    static String[] filter(int min, int max, String... array){
        if(max < min){
            throw new IndexOutOfBoundsException("min (" + min + ") is greater then max (" + max + ")");
        }
        String[] arr = new String[max - min];
        if (max + 1 - min >= 0) {
            if (max - min >= 0) System.arraycopy(array, min, arr, 0, max - min);
        }
        return arr;
    }

    static String[] filterOut(int start, int end, String... array){
        String[] arr = new String[array.length - (end - start)];
        for(int A = 0; A < array.length; A++){
            if(A > start && A < end){
                continue;
            }
            if(A >= end){
                arr[A - (end - start)] = array[A];
                continue;
            }
            arr[A] = array[A];
        }
        return arr;
    }

    @SafeVarargs
    static <X, T> T[] buildArray(Class<T> clazz, Function<X, T[]> function, X... array){
        return buildArray(clazz, function, Arrays.asList(array));
    }

    static <X, T> T[] buildArray(Class<T> clazz, Function<X, T[]> function, Iterable<X> collection){
        T[] array = (T[]) Array.newInstance(clazz, 0);
        for(X ins : collection){
            join(clazz, array, function.apply(ins));
        }
        return array;
    }

    static <T> T[] join(Class<T> clazz, T[]... arrays){
        List<T> list = new ArrayList<>();
        for (T[] array : arrays) {
            Collections.addAll(list, array);
        }
        return list.toArray((T[])Array.newInstance(clazz, list.size()));
    }

    static <T> T[] join(Class<T> clazz, T[] array1, T[] array2){
        T[] array = (T[])Array.newInstance(clazz, array1.length + array2.length);
        int A = 0;
        for(; A < array1.length; A++){
            array[A] = array1[A];
        }
        for(int B = 0; B < array2.length; B++){
            array[A + B] = array2[B];
        }
        return array;
    }

    static String[] splitBy(String toSplit, int startWith, boolean combineStartWith, Predicate<Character> splitBy){
        String[] split = new String[0];
        int previousSplit = startWith;
        for(int A = startWith; A < toSplit.length(); A++){
            char character = toSplit.charAt(A);
            if(splitBy.test(character)){
                String[] newSplit = new String[split.length + 1];
                System.arraycopy(split, 0, newSplit, 0, split.length);
                newSplit[split.length] = toSplit.substring(previousSplit, A);
                previousSplit = A;
                split = newSplit;
            }
        }
        String[] newSplit = new String[split.length + 1];
        System.arraycopy(split, 0, newSplit, 0, split.length);
        newSplit[split.length] = toSplit.substring(previousSplit);
        split = newSplit;
        if(combineStartWith){
            split[0] = toSplit.substring(0, startWith + split[0].length());
        }
        if(startWith == 0){
            return split;
        }
        newSplit = new String[split.length];
        System.arraycopy(split, 0, newSplit, 1, split.length);
        newSplit[0] = toSplit.substring(0, startWith);
        return newSplit;
    }
}
