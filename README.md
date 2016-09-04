# TextAssociator
TextAssociator is a general tool that will allow maintain one directional word associations by using a self-resizing,
non-generic HashTable, using separate chaining as collision resolution

In this application, TextAssociator stores a set of "key words" into a HashTable, and their corrosponding "enhanced words". When user inputs any word, program will randomly choose an "enhanced" word to that word. If given word is not found in the key words set, original word will be returned.
![alt](cloud.githubusercontent.com/assets/10392539/18230417/9c20e610-72ab-11e6-86da-60c3fd4d7db3.gif)

##TextAssociator.java 
>>TextAssociator is Dictionary implemented using hashing in order to maintain associations between Strings. 

##ThesaurusClient.java
>>ThesaurusClient uses TextAssociator to maintain associations between words and their synonyms

##WordInfo.java
>>WordInfo represents a relationship between a source String and a collection of Strings it should be  associated with

##WordInfoSeparateChain.java (Inner class of TextAssociator)
>>Separate Chain as a resolution of data collision
