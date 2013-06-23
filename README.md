inflect
=======

I hope to port Damian Conway's Inflect Perl Module (http://search.cpan.org/~dconway/Lingua-EN-Inflect-1.895/) to Scala. 

Started out with some simple parts.

Usage
-----

```scala
import uk.me.chrs.inflect.Inflect._

val n = 3
println("This is your " + ordinal(2)+ " attempt")
println("This is your " + n.th + " attempt")
```
prints

    This is your 2nd attempt
    This is your 3rd attempt