package com.example;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by atrposki on 20-Dec-16.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class WordFrequency {
    String text;
    int size;
}
