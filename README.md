similarTo
======
Language description
```
triple
	key matcher value
	matcher value
	value

key
	item

value
	item

item
	string
	{ triple, triple, ... }

matcher
	string
			:
            -- string --
            matches
            contains

string
	chars
	'chars chars'
```

Example. To match an object
```
class Person {
    String getName() { return "Jimmy"; }
}
```

similarTo text would be
```
name : Jimmy
```

### String
```
{ name : Jimmy }
{ name matches Ji.*y }
{ name contains mmy }
```

### Array
```
{ colors : [red,green,blue]  }
```

### Map
```
{ people : {id1:{name:Jimmy}, id2:{name;Anna} } }
```

## Usage example
```
import static com.slobokot.similarto.SimilarToMatchers.similarTo;

assertThat(person, similarTo(" { name : Jimmy, " +
    " surname : Great " +
    " favouriteTvPrograms : { Music, Discovery } } ");
```