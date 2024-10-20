# HTTP Status Codes



## 100
```text

```

## 200
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

## 300
```text

```

## 400
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