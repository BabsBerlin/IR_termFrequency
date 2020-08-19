# hamlet term frequency counting

this tiny little project was inspired by an assignment to "test your programming skills" at my university.

## task description

A frequent task in Information Retrieval (IR) is the calculation of term frequencies. For all terms it is to be counted how often they occur in a text. For this a term is defined as the stem of a word. 

Examples:  
word --> word stem (term)  
going --> go  
apple --> appl  
apples --> appl
  
Within this task the document in question (the first scene of Shakespeare's Hamlet hamlet.xml) is in XML format. Therefore first that file must be downloaded and parsed. After this only the contents of the \<LINE\> is to be taken.

From this the term frequencies (after stemming) are to be calculated. The output of the program is a list of all terms, together with the respective occurrence frequencies within <LINE> elements. 
The output should look like this:  
  word --> count  
  go --> 7  
  appl --> 2  
  situat --> 5


For word stem reduction there is a variant of the famous **Porter Stemming Algorithm** available. 
http://www.ils.unc.edu/keyes/java/porter/PorterStemmer.java
