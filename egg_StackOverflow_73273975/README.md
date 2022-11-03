
Summary:
---------

* this example illustrates structured concurrency
    - Maven build derived from [this answer](https://stackoverflow.com/a/73273975/12704) on Stack Overflow
* two tasks: `foo` and `bar`
    - can be configured to succeed or fail
* NOTE
    - happy path: one task takes X seconds; the other takes Y seconds
    - when one task is configured to fail, the other is interrupted
* many other examples of virtual threads and structured concurrency are [here](https://github.com/codetojoy/easter_eggs_for_java_loom)
    - this example is essentially 'egg_5_sc_invoke_all'

Build Notes:
------------

* tested with [this jdk](./JDK.version.md)
* tested with [this version](./Maven.version.md) of Maven 

To Build (using Maven):
---------------------

* `./mvn-go.sh` will clean, compile, exec 
* for Windows users, the Bash scripts will port easily to `.bat` files, or to straight command line

To Build (using Bash):
----------------------

useful commands:

* `sdk env`
    - SDKMan! will set JDK to value in `.sdkmanrc`
* `./clean.sh`
* `./compile.sh`
* `./run.sh`
* note `./go.sh` is useful for clean-compile-run cycle

