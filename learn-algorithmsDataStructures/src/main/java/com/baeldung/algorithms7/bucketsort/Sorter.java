package com.baeldung.algorithms7.bucketsort;

import java.util.List;

public interface Sorter<T> {

    List<T> sort(List<T> arrayToSort);
}
