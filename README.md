- Language finder helps you finding language of single word
- Input only alphabetic characters.
- The language name is derived from language file name. File names are expected to be in the format "LanguageName.extension"
- If word is available in more than one language, the first find will win(The application does not list all the languages in which the word is valid)


Softwares used:
- Java 7
- Servlets, JSP
- Apache commoms libratries
- Any web container (Tomcat, Jetty)

How to access:
Hit following url from browser after the artifact is deployed.

http://host:port/language-finder/find



Enhancements:
1. Implement multi threaded search on files to improve performance
2. Make language file location configurable from an external property file.
3. Introduce log4j for loggin and avoid printing stack traces on code.
4. The property files conf/lf/language-finder.properties, log4j.xml are currently not used. The idea is to use them to externalise the configuration.
5. Enable sentence search
6. List all the languages the word is valid
7. 
