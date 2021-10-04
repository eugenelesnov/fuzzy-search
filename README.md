[![](https://jitpack.io/v/EugeneLesnov/fuzzy-search.svg)](https://jitpack.io/#EugeneLesnov/fuzzy-search)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/815f744ded9b4ebcaeccde25ff0da4e2)](https://www.codacy.com/manual/zheka.lesnov1996/fuzzy-search?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=EugeneLesnov/fuzzy-search&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.com/EugeneLesnov/fuzzy-search.svg?branch=master)](https://travis-ci.com/EugeneLesnov/fuzzy-search)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg)](https://conventionalcommits.org)

# FuzzySearch
Java implementation of some fuzzy search algorithms.

## How to use

1. Add to your build.gradle:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add dependency:
```groovy
dependencies {
    implementation 'com.github.EugeneLesnov:fuzzy-search:{version}'
}
```

## Some examples

1. NgramSearch
```java
int power = 3;
String token = "query";
Collection<String> source = getSomeStringCollection();

Map<String, Float> matched = ngramSearch(power, token, source, String::toString);
matched.forEach((k, v) -> System.out.println("Token: " + k + "; Percentage: " + v));
```

2. LevenshteinSearch
```java
int precision = 4;
String token = "query";
Collection<String> source = getStringCollection();

Map<String, Integer> matched = levenshteinSearch(precision, token, source, String::toString);
matched.forEach((k, v) -> System.out.println("Token: " + k + "; Levenshtein distance: " + v));
```
