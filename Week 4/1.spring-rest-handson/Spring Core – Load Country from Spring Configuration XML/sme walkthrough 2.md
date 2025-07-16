
### My Journey with Spring Core: A Step-by-Step Walkthrough 

"So, I've been working on this Spring Core project in IntelliJ, and I wanted to share my process, especially how I tackled a tricky configuration error. My project is named `Ex2`."

---

#### **Step 1: Checking My Project Structure**

"First off, I always start by looking at my project's layout in the Project Explorer on the left side of IntelliJ. I wanted to be sure everything was in its right place.

- I saw my project root, named `Ex2`.
    
- Inside `Ex2`, I expanded `src` and then `main`.
    
- Under `main`, I confirmed I had both a `java` folder and a `resources` folder.
    
- When I expanded `java`, I found my `com.cognizant.Ex2` package, and inside it were my `Country.java` and `SpringLearnApplication.java` files. Perfect!
    
- Then, I checked `resources`, and there were my `country.xml` and `logback.xml`. Everything looked organized, which was a good start."
    

---

#### **Step 2: Confirming My `pom.xml` Dependencies**

"Next, I opened up my `pom.xml` file. This is where I manage all the external libraries my project needs. I made sure I had the essential Spring Context and logging dependencies in there.

Here’s what my `pom.xml`'s `<dependencies>` section looked like, ensuring I had everything for Spring and logging:

XML

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.cognizant</groupId>
    <artifactId>Ex2</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring.version>6.0.0</spring.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.7</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.4.7</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

"After confirming, I made sure to click 'Load Maven Changes' in IntelliJ to pull in all those libraries."

---

#### **Step 3: Setting Up My `logback.xml` for Logging**

"I knew I needed to see what was happening behind the scenes, so I configured my `logback.xml` file. This tells my application how to display its logs, especially my debug messages.

I opened `logback.xml` in `src/main/resources` and made sure it looked like this:

XML

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>

    <logger name="org.springframework" level="info" />
    <logger name="com.cognizant.Ex2" level="debug" />

</configuration>
```

"The `level="debug"` for my `com.cognizant.Ex2` package was crucial to see all my custom debug messages."

---

#### **Step 4: Crafting My `Country.java` Class**

"Then, I focused on my `Country.java` class. This is my simple data model for a country. I made sure the package declaration at the top was correct and that all the required methods were in place.

Here’s my `Country.java` code:

Java

```
package com.cognizant.Ex2; // My new package name!

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    public String getCode() {
        LOGGER.debug("Inside Country Getter - code: {}", code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside Country Setter - code: {}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside Country Getter - name: {}", name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside Country Setter - name: {}", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
```

"I made sure to add debug logs in the constructor and setters to track Spring's lifecycle."

---

#### **Step 5: Setting Up My `SpringLearnApplication.java`**

"This is my main application class, where everything kicks off. I put my `main` method here and a separate `displayCountry()` method to handle the Spring context loading.

My `SpringLearnApplication.java` looked like this:

Java

```
package com.cognizant.Ex2; // Again, my new package name!

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.debug("Starting SpringLearnApplication main method...");
        displayCountry();
        LOGGER.debug("Finished SpringLearnApplication main method.");
    }

    public static void displayCountry() {
        LOGGER.debug("Starting displayCountry method...");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        Country country = (Country) context.getBean("country", Country.class);

        LOGGER.debug("Country : {}", country.toString());

        LOGGER.debug("Finished displayCountry method.");
    }
}
```

"I used `ClassPathXmlApplicationContext` to load my `country.xml` and `context.getBean()` to fetch my `Country` object."

---

#### **Step 6: The "Aha!" Moment: Fixing My `country.xml`**

"This was the crucial part where I found my error! I was getting a class not found issue, and after looking closely, I realized my `country.xml` was pointing to the _old_ package name for my `Country` class.

I opened `country.xml` in `src/main/resources`.

My `<bean>` tag for `country` initially had `class="com.cognizant.springlearn.Country"`.

I immediately corrected it to:

XML

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="country" class="com.cognizant.Ex2.Country"> <!-- THIS WAS THE FIX! -->
        <property name="code" value="IN" />
        <property name="name" value="India" />
    </bean>

</beans>
```

"That `class="com.cognizant.Ex2.Country"` was the key! Spring needs that exact path to find and instantiate the class."

---

#### **Step 7: Rebuilding My Project**

"After making that critical change to `country.xml`, I knew I had to rebuild my project. This ensures that all my changes, especially in XML configuration, are properly compiled and picked up by IntelliJ.

I went to `Build` in the top menu and selected `Rebuild Project`. I waited for the build process to finish, confirming there were no errors."

---

#### **Step 8: Running My Application (Finally!)**

"With everything in place, I was ready to run. I opened `SpringLearnApplication.java` and clicked the green 'Play' triangle icon next to my `main` method. I selected 'Run 'SpringLearnApplication.main()''.

---

#### **Step 9: Observing the Console Output (My Logs)**

"The moment of truth! The 'Run' tool window popped up at the bottom of IntelliJ, and this time, I saw exactly what I expected: my debug logs!

Here's a snippet of what I saw:

```
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.SpringLearnApplication - Starting SpringLearnApplication main method...
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.SpringLearnApplication - Starting displayCountry method...
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.Country - Inside Country Constructor.
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.Country - Inside Country Setter - code: IN
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.Country - Inside Country Setter - name: India
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.Country - Inside Country Getter - code: IN
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.Country - Inside Country Getter - name: India
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.SpringLearnApplication - Country : Country [code=IN, name=India]
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.SpringLearnApplication - Finished displayCountry method.
HH:mm:ss.SSS [main] DEBUG com.cognizant.Ex2.SpringLearnApplication - Finished SpringLearnApplication main method.
```

"From these logs, I could clearly see the Spring lifecycle in action:

- **Constructor first!** The `Inside Country Constructor.` message appeared, proving Spring instantiated my `Country` object.
    
- **Then the setters!** The `Inside Country Setter - code: IN` and `Inside Country Setter - name: India` messages showed that Spring then injected the values from my `country.xml` using the setter methods.
    
- **Finally, the getters!** When `country.toString()` was called, it triggered the getters to retrieve the data, and then the final `Country : Country [code=IN, name=India]` message confirmed that my `Country` bean was perfectly loaded and configured by Spring.
    

"It was a great feeling to see it all work after understanding and fixing that small but important configuration detail!"
