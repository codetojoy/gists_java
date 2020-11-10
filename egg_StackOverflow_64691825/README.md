
### Summary 

* example for [this question](https://stackoverflow.com/questions/64691825) on Stack Overflow
* tested with JDK 8 and Gradle 6.7

### Usage

* `./test.sh` 
* NOTE:
    - `SomeClass.java` will sleep for 5 seconds, three times
    - if we comment-out the mock of `Thread` in `SomeClassTestCase`, the test takes 15 seconds
    - if we do mock `Thread` in `SomeClassTestCase`, the test is fast
    - note that `SomeClass.java` logs elapsed time to `System.out`
