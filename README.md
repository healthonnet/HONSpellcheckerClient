HON Spellchecker Client
=========================

Java library to be used to interact with the live HON Solr REST service.

The library uses Google Gson to parse the JSON result from the HON spellcheck server.  The Maven JUnit tests should demonstrate this.

Installation
----------

Download the code and do:

```
mvn install
```

Then add the following to your pom.xml:

```xml
<dependencies>
  ...
  <dependency>
  	<groupId>org.healthonnet.spellchecker</groupId>
  	<artifactId>hon-spellchecker-client</artifactId>
  	<version>0.0.1-SNAPSHOT</version>
  </dependency>
  ...
</dependencies>
```

Example Usage
-----------

```java
// language, number of suggestions to return, input string
SpellcheckResponse spellcheckResponse = 
        SpellcheckRequester.getSpellcheckResponse(SpellcheckDictionary.English, 1, "alzeimer");

List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();

// prints "alzheimer"
System.out.println(suggestions.get(0).getSuggestedCorrections().get(0).getWord());
```

Developer
-----------

Nolan Lawson

Health on the Net Foundation

License
-----------

[Apache 2.0][1].

[1]: http://www.apache.org/licenses/LICENSE-2.0.html
