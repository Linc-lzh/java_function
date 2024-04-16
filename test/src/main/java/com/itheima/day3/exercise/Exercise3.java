package com.itheima.day3.exercise;

import java.util.List;
import java.util.stream.Stream;

public class Exercise3 {
    public static void main(String[] args) {
        List<String> lines = List.of(
                "The DC Universe's iconic heroes and villains have captured the imagination of fans worldwide, showcasing the enduring appeal of their rich and diverse stories.",
                "From the legendary Superman to the enigmatic Batman, each character brings a unique and compelling presence to the DC Universe."
        );
        lines.stream().flatMap(s -> Stream.of(s.split("('s\\s+)|(,\\s+)|\\.|\\s+")))
                .forEach(System.out::println);
    }
}
