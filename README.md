# AnimeCrawlerAPI
A java based Anime crawler API. 

Currently the API is under development 

For Maven, do the below steps

1) Add gitlab-maven repository

```xml
<repositories>
  <repository>
    <id>gitlab-maven</id>
    <url>https://gitlab.com/api/v4/projects/14189257/packages/maven</url>
  </repository>
</repositories>

<distributionManagement>
  <repository>
    <id>gitlab-maven</id>
    <url>https://gitlab.com/api/v4/projects/14189257/packages/maven</url>
  </repository>

  <snapshotRepository>
    <id>gitlab-maven</id>
    <url>https://gitlab.com/api/v4/projects/14189257/packages/maven</url>
  </snapshotRepository>
</distributionManagement>
```

2) Add Maven dependency

```xml 
<dependency>
	<groupId>com.codingotaku.api</groupId>
	<artifactId>anime-crawler-api</artifactId>
	<version>0.1</version>
</dependency>
```

JavaDoc coming soon!
