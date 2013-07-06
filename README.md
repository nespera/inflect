# inflect

This is a Scala verison of Damian Conway's Inflect Perl Module (http://search.cpan.org/~dconway/Lingua-EN-Inflect-1.895/).
I hope to add most of the features from that module and I want the resulting library to be usable from both Java and Scala.


[![Travis Build](https://api.travis-ci.org/nespera/inflect.png)](https://travis-ci.org/nespera/inflect)

## Scala Usage

### Pluralizing Nouns

```scala
import uk.me.chrs.inflect.Inflect._

//Unconditional plural
println("I see dead " + plural("person"))

//Conditional plural
val pies = 2
println("The picnic contains " + cakes + " " + plural(cakes, "cake") +
  ", " + pies + " " + plural(pies, "pie")

//Simplify the above
println("The picnic contains " + count(cakes, "sweet") + ", " + count(pies, "savoury")

```

    I see dead people
    The picnic contains 1 cake, 2 pies
    The picnic contains 1 sweet, 2 savouries
    
### Adding indefinite article

```scala
import uk.me.chrs.inflect.Inflect._

val insect = "bee"
println("That is " + an(insect))
val person = "european"
println("He is " + a(person))
println("It was " + a("8 legged creature"))
```

    That is a bee
    He is a european
    It was an 8 legged beast

### Numbers in words

```scala
import uk.me.chrs.inflect.Inflect._

println("You are customer number " + cardinal(78))
println("This year's budget is " + cardinal("89000000000") + " dollars")
```


    That is a bee
    He is a european
    It was an 8 legged beast

You are customer number seventy-eight
    This year's budget is eighty-nine billion dollars

### Ordinals

```scala
import uk.me.chrs.inflect.Inflect._

println("This is your " + ordinal(3) + " attempt")
println("This is your " + ordinal("five") + " attempt")
println("While this is your " + textOrdinal(8))
```

    This is your 3rd attempt
    This is your fifth attempt
    While this is your eighth

### Implicit Conversions

There are some implicit conversions defined. In order to use them, you need to explicitly import them.

```scala
import uk.me.chrs.inflect.ImplicitConversions._

val n = 112
println("This is your " + n.th + " attempt")
val m = "one"
println("This is your " + m.th + " attempt")
val customerNumber = 789
println("Welcome customer " + customerNumber.inWords)
```

    This is your 112th attempt
    This is your first attempt
    Welcome customer seven hundred and eighty-nine

## Java Usage

### Pluralizing Nouns

```java
static import uk.me.chrs.inflect.Inflect.*

System.out.println("Here are your " + plural("photo"))
val shocks = 1
System.out.println("Applying electrical " + plural(shocks, "stimulus")
System.out.println("I ate " + count(7, "muffin")
```

    Here are your photos
    Applying electrical stimulus
    I ate 7 muffins

### Numbers in words

```java
static import uk.me.chrs.inflect.Inflect.*

System.out.println("You are customer number " + cardinal(78))
System.out.println("This year's budget is " + cardinal("89000000000") + " dollars")
```

    You are customer number seventy-eight
    This year's budget is eighty-nine billion dollars

### Ordinals

```java
static import uk.me.chrs.inflect.Inflect.*

System.out.println("This is your " + ordinal(3) + " attempt")
System.out.println("This is your " + ordinal("five") + " attempt")
System.out.println("While this is your " + textOrdinal(8))
```

    This is your 3rd attempt
    This is your fifth attempt
    While this is your eighth

## Customizing the output

It is possible to customize the output. At present it is possible to set the word used for negative numbers (defaults to
"minus") and the word used to separate the tens and units from the other numbers (defaults to "and")

```scala
import uk.me.chrs.inflect.Inflect._

println("Your score is " + cardinal(-6007))

object MyInflector extends CustomInflector(Options("negative", ""))

import MyInflector._

println("Your score is " + cardinal(-6007))
```

    Your score is minus six thousand and seven
    Your score is negative six thousand seven

```java
import static uk.me.chrs.inflect.Inflect.cardinal;

Inflector inflector = new CustomInflector(new Options("negative", ""));

System.out.println("The temperature is " + cardinal(-203))
System.out.println("The temperature is " + inflector.cardinal(-203))
```

    The temperature is minus two hundred and three
    The temperature is negative two hundred three
