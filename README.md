# imdb-data-scraper
A command line based java application to scrape data from IMDb website. Just mention the specified **imdb url** of **"top-something"** and **total no of movies/shows** data you want as a argument, and this application shall return a JSON object of the movies/shows data.

## How to configure the project?
* Clone the project from GIT repo and import the project as "Existing Maven Project"(If you are using Eclipse)
* All the dependencies are metioned in the **'pom.xml'** file, after cloning the project simply clean and build it
* Dependencies used in the project : 
  
        <dependencies>
          <!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
          <dependency>
              <groupId>org.jsoup</groupId>
              <artifactId>jsoup</artifactId>
              <version>1.13.1</version>
          </dependency>

          <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
          <dependency>
              <groupId>com.google.code.gson</groupId>
              <artifactId>gson</artifactId>
              <version>2.8.6</version>
          </dependency>
        </dependencies>
  

### How to create the command line based runnable jar?
Create a runnable JAR with **com.sezzle.imdb.scrapper.Driver.java** in **Launch Configuration**

#### How to run from command line ?

* Go to the target directory of the jar and run as shown in the examples : 

 **Examples** : 
 
**1. java -jar jar_name.jar https://www.imdb.com/india/top-rated-indian-movies 10**

**2. java -jar jar_name.jar https://www.imdb.com/india/top-rated-tamil-movies 2**

**3. java -jar jar_name.jar https://www.imdb.com/india/top-rated-telugu-movies 3**


This application takes 2 arguments, the 1st argument is the url of the imdb and the 2nd argument is total no of movies/shows data you want

Upon running you shall get data as shown in the image below : 
<img src="https://github.com/pradipmudi/imdb-data-scrapper/blob/master/imdb-data-scrapper/images/howToRun.png?raw=true"/>
