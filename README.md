# Services

---

The ```Services``` project contains various services.


## Build Status


| Build | Tests | GitHub Pages                                                                                                                                                                                                 |
|:------|:------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|       |       | [![Deploy Static Contents to Pages](https://github.com/rslakra/Services/actions/workflows/maven-publish-pages.yml/badge.svg)](https://github.com/rslakra/Services/actions/workflows/maven-publish-pages.yml) |



## Folder Structure/Conventions

---

```
    /
    ├── <module>                # The module service
    ├── Automotive              # The Automotive services
    ├── Business                # The Business services
    ├── cheat-sheets            # The cheat-sheets project
    ├── Commerce                # The Commerce services
    ├── Commodity               # The Commodity services
    ├── Education               # The Education services
    ├── Healthcare              # The Healthcare services
    ├── Logistics               # The Logistics services
    ├── Monitoring              # The Monitoring services
    ├── Multimedia              # The Multimedia services
    ├── Production              # The Production services
    ├── Samples                 # The Samples services
    ├── Security                # The Security services
    ├── Technology              # The Technology services
    |    ├── modules                # The modules
    |    ├── <service>              # The Service
    |    |    ├── <service>-iws     # The <service>-iws Service
    |    |    ├── webapp            # The WebApp for Service
    |    |    └── README.md
    |    └── README.md
    ├── README.md               # Instructions and helpful links
    ├── robots.txt              # tells which URLs the search engine crawlers can access on your site
    └── <module>                # The module service
```

## Project Setup

* Configuration
* Dependencies
* Database Configuration
* Build Instructions
* Unit Tests
* Deployment Instructions

### Python Settings
```shell
```

### Setup Virtual Environment
```
```

### Upgrade PIP Requirements (Dependencies)
```shell
```

### Setup Configurations

- Create or update local .env configuration file.

```shell
```

**By default**, Flask will run the application on **port 5000**.

### Build Instructions
```shell
```

### Save Requirements (Dependencies)
```shell
```

## Unit Tests
```shell
```



### Java Conventions

#### Package Name Conventions

Java package naming conventions are important for organizing code and avoiding naming conflicts. 
Here's a breakdown of the key rules:

- Lowercase:

    All package names should be in lowercase letters to avoid conflicts with class or interface names.


- Reverse Domain Name:

  Use your reversed internet domain name as the prefix for your package names.
  For example, if your domain is ```example.com```, your package names would start with ```com.example```.


- Meaningful Names:

    Use meaningful names that describe the purpose of the package and its contents.


- Subpackages:

    Organize code further using subpackages separated by dots (.). For instance, com.example.utilities or com.example.myapp.models.


- Avoid Underscores:

    Avoid using underscores (_) in package names.


#### Benefits of Packages

- Organization:

  Packages help organize code into logical units, making it easier to navigate and maintain.

- Avoid Conflicts:

    Using reverse domain names helps prevent naming collisions with other projects.

- Clarity:

    Meaningful package names improve code readability and understanding.



### REST Generic Endpoints

One generic endpoint:

| Method | API                |
|:-------|:-------------------|
| POST   | /api/:model        |
| GET    | /api/:model/:query |
| PUT    | /api/:model/:id    |
| DELETE | /api/:model/:id    |




## Cloud Environments

| Cloud Provider |     Region     | Region Name              |
|:---------------|:--------------:|:-------------------------|
| AWS            |   us-east-1    | US East (N. Virginia)    |
|                |   us-east-2    | US East (Ohio)           |
|                |   us-west-1    | US West (N. California)  |
|                |   us-west-2    | US West (Oregon)         |
|                |  ca-central-1  | Canada (Central)         |
|                |  eu-central-1  | Europe (Frankfurt)       |
|                |   eu-west-1    | Europe (Ireland)         |
|                |   eu-west-2    | Europe (London)          |
|                | ap-northeast-1 | Asia Pacific (Tokyo)     |
|                | ap-southeast-1 | Asia Pacific (Singapore) |
|                | ap-southeast-2 | Asia Pacific (Sydney)    |
| Azure          |     Region     | Name                     |
| Provider       |     Region     | Name                     |
| Provider       |     Region     | Name                     |
| Provider       |     Region     | Name                     |
| Provider       |     Region     | Name                     |



# Docker Version
```shell
docker --version
```

# AWS Version
```shell
aws --version
```



# Reference

---

- [MVN Repository](https://mvnrepository.com)
- [Automotive](./Automotive/README.md)
- [Business](./Business/README.md)
- [Commerce](./Commerce/README.md)
- [Commodity](./Commodity/README.md)
- [Education](./Education/README.md)
- [Healthcare](./Healthcare/README.md)
- [Infrastructure](./Infrastructure/README.md)
- [Docker](./Infrastructure/Docker/README.md)
- [Apache HTTP Server](./Infrastructure/Docker/Apache%20HTTP%20Server/README.md)
- [Jenkins](./Infrastructure/Docker/Jenkins/README.md)
- [Logistics](./Logistics/README.md)
- [Monitoring](./Monitoring/README.md)
- [Multimedia](./Multimedia/README.md)
- [Samples](./Samples/README.md)
- [Technology](./Technology/README.md)
- [The Java™ Tutorials - Naming a Package](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html)
- [Python](Python.md)
- [Web Links](./WebLinks.md)



# Author

---

- Rohtash Lakra



# Build Version Structure
```json
{
    "MAJOR": "MAJOR",
    "MINOR": "MINOR",
    "PATCH": "PATCH",
    "PRE-RELEASE": "PRE-RELEASE",
    "BUILD": "BUILD"
}
```
OR

```text
MAJOR.MINOR.PATCH.PRE-RELEASE.BUILD
```



# Contribution Guidelines

* Writing tests
* Code review
* Other Guidelines