package org.bonge.util;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayUtils {

    public static <E, T> T[] convert(Class<T> clazz, Function<E, T> function, E... array){
        T[] array1 = (T[]) Array.newInstance(clazz, array.length);
        for(int A = 0; A < array.length; A++){
            array1[A] = function.apply(array[A]);
        }
        return array1;
    }

    public static <E, I>  List<I> convert(Function<E, I> function, Collection<E> collection){
        return convert(new ArrayList<>(), function, collection);
    }

    public static <E, I, T extends Collection<I>> T convert(T array, Function<E, I> function, Collection<E> collection){
        collection.forEach(c -> {
            array.add(function.apply(c));
        });
        return array;
    }

    /**
     * Converts a specified array into a String.
     * @param split The devide between every instance
     * @param toString Converts the specified type into String
     * @param array The array to be converted
     * @param <T> The class type of the array
     * @return A string output
     */
    public static <T> String toString(String split, Function<T, String> toString, T... array){
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
    public static <T> String toString(String split, Function<T, String> toString, Iterable<T> array){
        String ret = null;
        for(T value : array){
            if(ret == null){
                ret = toString.apply(value);
            }else{
                ret = ret + split + toString.apply(value);
            }
        }
        return ret;
    }

    public static <T> Optional<T> getBest(Function<T, Integer> function, BiPredicate<Integer, Integer> compare, T... array){
        return getBest(function, compare, Arrays.asList(array));
    }

    public static <T> Optional<T> getBest(Function<T, Integer> function, BiPredicate<Integer, Integer> compare, Collection<T> collection){
        T value = null;
        Integer best = null;
        for(T value1 : collection){
            if(value == null){
                value = value1;
                best = function.apply(value1);
            }
            int current = function.apply(value1);
            if(compare.test(current, best)){
                value = value1;
                best = function.apply(value1);
            }
        }
        return Optional.ofNullable(value);
    }

    public static <T, N extends Number> Set<T> getBests(Function<T, N> function, BiPredicate<N, N> compare, BiPredicate<N, N> equal, T... array){
        return getBests(function, compare, equal, Arrays.asList(array));
    }

    public static <T, N extends Number> Set<T> getBests(Function<T, N> function, BiPredicate<N, N> compare, BiPredicate<N, N> equal, Collection<T> collection){
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

    public static String[] trim(int amount, String... array){
        String[] args = new String[array.length - amount];
        for(int A = 0; A < args.length; A++){
            args[A] = array[A];
        }
        return args;
    }

    public static String[] filter(int min, int max, String... array){
        if(max < min){
            throw new IndexOutOfBoundsException("min (" + min + ") is greater then max (" + max + ")");
        }
        String[] arr = new String[(max + 1) - min];
        for(int A = min; A <= max; A++){
            arr[A - min] = array[A];
        }
        return arr;
    }

    public static String[] filterOut(int start, int end, String... array){
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

    public static <X, T> T[] buildArray(Class<T> clazz, Function<X, T[]> function, X... array){
        return buildArray(clazz, function, Arrays.asList(array));
    }

    public static <X, T> T[] buildArray(Class<T> clazz, Function<X, T[]> function, Collection<X> collection){
        T[] array = (T[])Array.newInstance(clazz, 0);
        for(X ins : collection){
            join(clazz, array, function.apply(ins));
        }
        return array;
    }

    public static <T> T[] join(Class<T> clazz, T[]... arrays){
        T[] array = (T[])Array.newInstance(clazz, 0);
        for(T[] array1 : arrays){
            T[] array2 = (T[])Array.newInstance(clazz, array.length + array1.length);
            int A;
            for(A = 0; A < array.length; A++){
                array2[A] = array[A];
            }
            for(; A < array.length + array1.length; A++){
                array2[A] = array1[A - array.length];
            }
        }
        return array;
    }

    public static String[] splitBy(String toSplit, int startWith, boolean combineStartWith, Predicate<Character> splitBy){
        String[] split = new String[0];
        int previousSplit = startWith;
        for(int A = startWith; A < toSplit.length(); A++){
            char character = toSplit.charAt(A);
            if(splitBy.test(character)){
                String[] newSplit = new String[split.length + 1];
                for(int B = 0; B < split.length; B++){
                    newSplit[B] = split[B];
                }
                newSplit[split.length] = toSplit.substring(previousSplit, A);
                previousSplit = A;
                split = newSplit;
            }
        }
        String[] newSplit = new String[split.length + 1];
        for(int B = 0; B < split.length; B++){
            newSplit[B] = split[B];
        }
        newSplit[split.length] = toSplit.substring(previousSplit);
        split = newSplit;
        if(split.length == 0 && combineStartWith){
            return new String[]{toSplit};
        }
        if(split.length == 0){
            return split;
        }
        if(combineStartWith){
            split[0] = toSplit.substring(0, startWith + split[0].length());
        }
        if(startWith == 0){
            return split;
        }
        newSplit = new String[split.length];
        for(int B = 0; B < split.length; B++){
            newSplit[B + 1] = split[B];
        }
        newSplit[0] = toSplit.substring(0, startWith);
        return newSplit;
    }
}
