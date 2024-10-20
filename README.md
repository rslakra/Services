# Services

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for?

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact

## HTTP Status Codes

### 100
```text

```

### 200
```text
GET: 200 OK
PUT: 200 OK
POST: 201 Created
PATCH: 200 OK
DELETE: 204 No Content
```

```text
201 Created, 
202 Accepted
```

I find 202 Accepted to be a very handy alternative to 201 Created.

It basically means:
> I, the server, have understood your request. I have not created the resource (yet), but that is fine.

There are two main scenarios which I find 202 Accepted to be especially suitable:
- If the resource will be created as a result of future processing — example: After a job/process has finished.
- If the resource already existed in some way, but this should not be interpreted as an error.

### 300
```text

```

### 400
```text
401 Unauthorized, and 
403 Forbidden
```

#### 401 vs 403

- Has the consumer not provided authentication credentials? Was their SSO Token invalid/timed out? 👉 401 Unauthorized.

- Was the consumer correctly authenticated, but they don’t have the required permissions/proper clearance to access the resource? 👉 403 Forbidden.



### 500
```text

```

## [The Zen of Python](https://peps.python.org/pep-0020/#id3)
- Beautiful is better than ugly.
- Explicit is better than implicit.
- Simple is better than complex.
- Complex is better than complicated.
- Flat is better than nested.
- Sparse is better than dense.
- Readability counts.
- Special cases aren't special enough to break the rules.
- Although practicality beats purity.
- Errors should never pass silently.
- Unless explicitly silenced.
- In the face of ambiguity, refuse the temptation to guess.
- There should be one-- and preferably only one --obvious way to do it.
- Although that way may not be obvious at first unless you're Dutch.
- Now is better than never.
- Although never is often better than *right* now.
- If the implementation is hard to explain, it's a bad idea.
- If the implementation is easy to explain, it may be a good idea.
- Namespaces are one honking great idea -- let's do more of those!



## Version

```text
MAJOR.MINOR.PATCH.BUILD
```

# Author

- Rohtash Lakra
