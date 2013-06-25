# inflect

I hope to port Damian Conway's Inflect Perl Module (http://search.cpan.org/~dconway/Lingua-EN-Inflect-1.895/) to Scala.
I also want the resulting library to be usable from both Java and Scala.

I've started out with some simple parts around inflection of numbers. I will then move onto pluralizing nouns and so on..

[![Travis Build](https://api.travis-ci.org/nespera/inflect.png)](https://travis-ci.org/nespera/inflect)

## Scala Usage

### Numbers in words

```scala
import uk.me.chrs.inflect.Inflect._

println("You are customer number " + cardinal(78))
println("This year's budget is " + cardinal("89000000000") + " dollars")
```
prints

    You are customer number seventy-eight
    This year's budget is eighty-nine billion dollars


### Ordinals

```scala
import uk.me.chrs.inflect.Inflect._

println("This is your " + ordinal(3) + " attempt")
println("This is your " + ordinal("five") + " attempt")
```
prints

    This is your 3rd attempt
    This is your fifth attempt

### Implicit Conversions

```scala
import uk.me.chrs.inflect.ImplicitConversions._

val n = 112
println("This is your " + n.th + " attempt")
val m = "one"
println("This is your " + m.th + " attempt")
```

prints

    This is your 112th attempt
    This is your first attempt

## Java Usage

```java
static import uk.me.chrs.inflect.Inflect.*

System.out.println("You are customer number " + cardinal(78))
System.out.println("This year's budget is " + cardinal("89000000000") + " dollars")
```

### Ordinals

```java
static import uk.me.chrs.inflect.Inflect.*

System.out.println("This is your " + ordinal(3) + " attempt")
System.out.println("This is your " + ordinal("five") + " attempt")
```
## Customizing the output

It is possible to customize the output. At present it is possible to set the word used for negative numbers (defaults to
"minus") and the word used to separate the tens and units (defaults to "and")

```scala
import uk.me.chrs.inflect.Inflect._

println("Your score is " + cardinal(-6007))

object MyInflector extends CustomInflector(Options(minusIndicator = "negative", andSeparator = ""))

import MyInflector._

println("Your score is " + cardinal(-6007))
```
prints

    Your score is minus six thousand and seven
    Your score is negative six thousand seven

```java
import static uk.me.chrs.inflect.Inflect.cardinal;

Inflector inflector = new CustomInflector(new Options("negative", ""));

System.out.println("The temperature is " + cardinal(-203))
System.out.println("The temperature is " + inflector.cardinal(-203))
```

prints

    The temperature is minus two hundred and three
    The temperature is negative two hundred three
