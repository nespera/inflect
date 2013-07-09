# inflect

This is a Scala verison of Damian Conway's Inflect Perl Module (http://search.cpan.org/~dconway/Lingua-EN-Inflect-1.895/).
I hope to add most of the features from that module and I want 
the resulting library to also be [usable from Java](#java-usage).


[![Travis Build](https://api.travis-ci.org/nespera/inflect.png)](https://travis-ci.org/nespera/inflect)

## Scala Usage

### Pluralizing Nouns

```scala
import uk.me.chrs.inflect.Inflect_EN._

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
import uk.me.chrs.inflect.Inflect_EN._

val insect = "bee"
println("That is " + one(insect))
val person = "European"
println("He is " + one(person))
println("It was " + one("8 legged creature"))
println("He works for " + one("NGO"))
```

    That is a bee
    He is a European
    It was an 8 legged beast
    He works for an NGO

### Numbers in words

```scala
import uk.me.chrs.inflect.Inflect_EN._

println("You are customer number " + cardinal(78))
println("This year's budget is " + cardinal("89000000000") + " dollars")
```

    You are customer number seventy-eight
    This year's budget is eighty-nine billion dollars

### Ordinals

```scala
import uk.me.chrs.inflect.Inflect_EN._

println("This is your " + ordinal(3) + " attempt")
println("This is your " + ordinal("five") + " attempt")
println("While this is your " + textOrdinal(8))
```

    This is your 3rd attempt
    This is your fifth attempt
    While this is your eighth

### Choosing the locale

The output defaults to British English, since I'm a Brit. But you can make this explicit, or use US variants by
making the relevant imports

```scala

println("Your score is " + uk.me.chrs.inflect.Inflect_EN_GB.cardinal(-6007))
println("Your score is " + uk.me.chrs.inflect.Inflect_EN_US.cardinal(-6007))
```

    Your score is minus six thousand and seven
    Your score is negative six thousand seven

## Java Usage

### Pluralizing Nouns

```java
static import uk.me.chrs.inflect.Inflect_EN.*

System.out.println("Here are your " + plural("photo"))
val shocks = 1
System.out.println("Applying electrical " + plural(shocks, "stimulus")
System.out.println("I ate " + count(7, "muffin")
```

    Here are your photos
    Applying electrical stimulus
    I ate 7 muffins

### Indefinite Article

```java
static import uk.me.chrs.inflect.Inflect_EN.*

System.out.println("That is " + one("XML document"))
```

    That is an XML document

### Numbers in words

```java
static import uk.me.chrs.inflect.Inflect_EN.*

System.out.println("You are customer number " + cardinal(78))
System.out.println("This year's budget is " + cardinal("89000000000") + " dollars")
```

    You are customer number seventy-eight
    This year's budget is eighty-nine billion dollars

### Ordinals

```java
static import uk.me.chrs.inflect.Inflect_EN.*

System.out.println("This is your " + ordinal(3) + " attempt")
System.out.println("This is your " + ordinal("five") + " attempt")
System.out.println("While this is your " + textOrdinal(8))
```

    This is your 3rd attempt
    This is your fifth attempt
    While this is your eighth
