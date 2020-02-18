# Kotlin Native

[twitter]: # (@James Millner)
[event]: # (Leeds Kotlin User Group)
[eventurl]: # (https://leedskotlinusergroup.netlify.com)
[title]: # (LKUG Kotlin Native)
[date]: # (February 18, 2020)

<!-- .slide: data-transition="zoom" -->

---

<img src="/images/james-millner-me.jpg">
#### James Millner

--

<img src="/images/james-milner1.png">

--

<img src="/images/james-milner2.png">

---

<img src="/images/kotlin-native-inspiration.png" style="width: 700px">

#### https://www.youtube.com/watch?v=KfdNiMP0emE

--

<img src="/images/raspberry-pi.png" style="height: 700px">

---

### What is Kotlin Native?

---

<img src="/images/one-language-to-rule.png" style="height: 500px">

<!-- Kotlin/Native is another step towards making Kotlin usable throughout modern applications.

- Eventually, it will be possible to use Kotlin to write every component, from the server back-end to the web or mobile clients.
- Sharing the skill set is one big motivation for this scenario. Another is sharing actual code. -->

--

![Kotlin Native Github](/images/kotlin-native-github.png)

--

* Initial Release Mar 2017 - Technical Preview Only

<img src="/images/kotlin-native-initial-targets.png">

<!--
- https://blog.jetbrains.com/kotlin/2017/04/kotlinnative-tech-preview-kotlin-without-a-vm/
- Targets supported:
Mac OS X 10.10 and later (x86-64)
x86-64 Ubuntu Linux (14.04, 16.04 and later), other Linux flavours may work as well
Apple iOS (arm64), cross-compiled on MacOS X host
Raspberry Pi, cross-compiled on Linux host

- Open Source Apache 2 OSS license.

-->


--

* Second Release May 2017

<!--
This is where things got interesting... Most of the Kotlin standard library was compatible with Kotlin Native
-->

```
- Added support for coroutines
- Fixed most stdlib incompatibilities
- Improved memory management performance
- Cross-module inline function support
- Unicode support independent from installed system locales
- Interoperability improvements
    - file-based filtering in definition file
    - stateless lambdas could be used as C callbacks
    - any Unicode string could be passed to C function
- Very basic debugging support
- Improve compilation and linking performance

```
--

<img src="/images/interesting.png" style="height: 500px">

--

<img src="/images/kotlin-native-latest-release.png" style="height: 700px">

--

<img src="/images/kotlin-native-targets.png" >

--

<!-- Kotlin/Native follows the general tradition of Kotlin to provide excellent existing platform software interoperability. -->

In the case of a native platform, the most important interoperability target is a C library. 

Kotlin/Native comes with a cinterop tool, which can be used to quickly generate everything needed to interact with an external library.

--

<img src="/images/what-is-it.png" style="height: 600px">


---

Kotlin/Native is an LLVM based backend for the Kotlin compiler and native implementation of the Kotlin standard library.

--

<img src="/images/whaaat.png" style="height: 500px">

--

<img src="/images/youre-right.png" style="height: 500px">
--

<img src="/images/compilation-process.png" style="height: 500px">
### On the JVM
<!--
- Now to clarify the previous statement I'm going to talk about the Kotlin Native Compiler.
- It's split into a Frontend & Backend process.
- The initial Kotlin code is convert into an intermediet representation.
- The Kotlin Native Compiler then takes this intermediet representation and converts it into the many different platform specific representations

--->

--

## So LLVM?

--

<img src="/images/llvm-logo.png" style="height: 500px">

--

LLVM takes a variety of intermediate bytecode and allows it to be compiled and run on different architectures.

--

<!-- LLVM Initial released in 2003 -->

<img src="/images/llvm-website.png">

--

<img src="/images/kotlin-native-compilation-process.png" style="height: 300px">

### On Kotlin Native


<!-- 

Kotlin Native really is the process that takes the intermediet representation and converts it into an LLVM representation. This is then passed to LLVM to create the native binaries.

Kotlin Native creates kotlin bindings which are the kotlin representations of functions and attributes of a native C library.

Sketch IO Drawing.... -->

---

### The Dream

<img src="/images/kotlin-logo.png" style="height: 300px">
<img src="/images/rpi-logo.png" style="height: 300px">

--

<img src="/images/gradle-please-work.png" style="height: 600px">

--

<img src="/images/almost-there.png" style="height: 90px">

--

<img src="/images/it-works.png" style="height: 400px">

---

## So, What did I make?

* Kotlin App
* Integrating with Curl
* Calling out each systems implementation of POSIX
    * PID of the app running

<!-- POSIX = Portable Operating System Interface
-->

---

## The Finished Result

--

<img src="/images/demo-code/macos-working.png">

--

<img src="/images/demo-code/linux-working.png">

--

## Demo Code
<img src="/images/let-us-pray.png" style="height: 500px">

---

# Thank you 
### Follow me on Twitter: jmillner_

