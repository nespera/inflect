# inflect

I hope to port Damian Conway's Inflect Perl Module (http://search.cpan.org/~dconway/Lingua-EN-Inflect-1.895/) to Scala. 

Started out with some simple parts around inflection of numbers. I will then move onto pluralizing nouns and so on..

## Usage

### Ordinals

```scala
import uk.me.chrs.inflect.Inflect._

println("This is your " + ordinal(3) + " attempt")
println("This is your " + ordinal("five") + " attempt")
```
prints

    This is your 3rd attempt
    This is your fifth attempt

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

