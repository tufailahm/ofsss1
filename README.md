This JPA with EclipseLink program covers following topics:
1. Overview of JPA and Eclipselink
2. Mapping with JPA 
3. Updates and Queries 
4. Inserting and Updating
5. Entity Association (one-one, one-many……) & Inheritance
6. Entity Association Cont….
7.Querying and JPQL
8.Named Queries
9. Criteria Query API
10. Queries Across Relationships (Inner Joins, Outer Joins, Fetch Joins) 
11. Simple case study for 2 hours on JPA


JPA
==============
Java Persistence API


Use case : I want to save product information in database

productId
productName
quantityOnHand
price

Product p = new Product();	//1, HP,900,89999

jpa.save(p);


problems :
table in database to be created manually


Eclipselink



persistence.xml	- db related stuffs
























======================================
pom.xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.galaxe.training</groupId>
	<artifactId>b</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>b</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.eclipse.persistence</groupId>
			<artifactId>org.eclipse.persistence.jpa</artifactId>
			<version>2.5.1</version>
			<scope>provided</scope>
		</dependency>


		<dependency>
			<groupId>org.eclipse.persistence</groupId>
			<artifactId>eclipselink</artifactId>
			<version>2.0.0</version>
			<scope>>compile</scope>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.1.0.Final</version>
			<type>pom</type>
			<scope>compile</scope>
		</dependency>

		<dependency>
			<groupId>javax</groupId>
			<artifactId>javaee-api</artifactId>
			<version>7.0</version>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>5.3.7.Final</version>
		</dependency>


		


		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.7.25</version>
		</dependency>

		<dependency>
			<groupId>org.hibernate.common</groupId>
			<artifactId>hibernate-commons-annotations</artifactId>
			<version>5.1.0.Final</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc10 -->
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc10</artifactId>
			<version>19.19.0.0</version>
		</dependency>


		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.9</version>
		</dependency>


	</dependencies>
	<repositories>
		<repository>
			<id>EclipseLink Repo</id>
			<url>http://download.eclipse.org/rt/eclipselink/maven.repo</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>

</project>


==========================persistence.xml
E:\NewTrainingMaterial\EclipseLinkJPA\Demos\jpa-eclipselink-simple-demo\src\main\resources\META-INF

<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
  version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">
  <persistence-unit name="ahmed" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <class>com.training.jpa.model.Student</class>
    <properties>
       <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/galaxe" />
            <property name="javax.persistence.jdbc.user" value="root" />
            <property name="javax.persistence.jdbc.password" value="root" />
            
            
                        <property name="hibernate.hbm2ddl.auto" value="update" />
            
            
            <property name="eclipselink.ddl-generation" value="drop-and-create-tables"/>
      
<property name="eclipselink.ddl-generation" value="create-or-extend-tables" />
      <property name="eclipselink.ddl-generation.output-mode" value="galaxe" />
    </properties>

  </persistence-unit>
</persistence>

===============
For oracle

<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
  version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">
  <persistence-unit name="ahmed" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <class>com.test.jpa.Student</class>
    <properties>
      <property name="javax.persistence.jdbc.driver" value="oracle.jdbc.driver.OracleDriver" />
      <property name="javax.persistence.jdbc.url"    value="jdbc:oracle:thin:@localhost:1521:xe" />
      <property name="javax.persistence.jdbc.user" value="system" />
      <property name="javax.persistence.jdbc.password" value="oracle" />
                  <property name="hibernate.dialect" value="org.hibernate.dialect.OracleDialect" />
      
      <property name="eclipselink.ddl-generation" value="create-tables" />
      <property name="eclipselink.ddl-generation.output-mode" value="xe" />
    </properties>

  </persistence-unit>
</persistence>

================================

package com.training.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtil {
  private static final EntityManagerFactory entityManagerFactory;
  static {
    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("ahmed");
      

    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();

  }
}