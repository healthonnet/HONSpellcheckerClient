HON Spellchecker Client
=========================

Java library to be used to interact with the live HON Solr REST service.

Example usage:

```java
// language, number of suggestions to return, input string
SpellcheckResponse spellcheckResponse = 
        SpellcheckRequester.getSpellcheckResponse(SpellcheckDictionary.English, 1, "mispelled");

List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();

// prints "misspelled"
System.out.println(suggestions.get(0).getSuggestedCorrections().get(0).getWord();
```

Demo of a HON Spelling Correction client using Google Gson.  Shows how to parse the JSON result from the HON spellcheck server.  The Maven JUnit tests should demonstrate this.

Developer
=========

Nolan Lawson

Health on the Net Foundation

License
=========

[Apache 2.0][1].

[1]: http://www.apache.org/licenses/LICENSE-2.0.html
