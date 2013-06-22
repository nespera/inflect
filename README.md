inflect
=======

I hope to port Damian Conway's Inflect Perl Module (http://search.cpan.org/~dconway/Lingua-EN-Inflect-1.895/) to Scala. 

Started out with some simple parts.

Usage
-----

```scala
import uk.me.chrs.inflect.Inflect.ordinal

val attempt = 3
println("This is your " + ordinal(attempt)+ " attempt")
```
prints

    This is your 3rd attempt