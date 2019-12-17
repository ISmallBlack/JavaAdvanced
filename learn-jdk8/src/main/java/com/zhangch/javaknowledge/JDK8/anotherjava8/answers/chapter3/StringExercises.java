package com.zhangch.javaknowledge.JDK8.anotherjava8.answers.chapter3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringExercises {

    // Question 6
    public static int countLowercaseLetters(String string) {
        return (int) string.chars()
                           .filter(Character::isLowerCase)
                           .count();
    }
    //返回String
    public static String stringLowercaseLetters(String string) {
        //归一化
        return  string.chars()
                .filter(Character::isLowerCase)
                .mapToObj(i->new Character((char) i))
                .collect(Collectors.reducing(new StringBuilder(), name -> new StringBuilder().append(name),
                        (r1, r2) -> { r1.append(r2); return r1; })).toString();

   /*   return string.chars()
              .filter(Character::isLowerCase)
              .mapToObj(i->new Character((char) i))
              .collect(new CollectorImpl<>(
                      StringBuilder::new, StringBuilder::append,
                      (r1, r2) -> { r1.append(r2); return r1; },
                      StringBuilder::toString, Collections.emptySet()));*/
    }

    // Question 7
    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream()
                      .max(Comparator.comparing(StringExercises::countLowercaseLetters));
    }

}
