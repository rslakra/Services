# HTTP Reference Guide

The ```HTTP``` reference guide contains detailed information
about ````headers````, ```request methods```, ```status responses```, and
lists relevant specifications and standards documents.

HTTP is an extensible protocol that relies on concepts like resources and Uniform Resource Identifiers (URIs), a basic
message structure, and client-server communication model. On top of these concepts, numerous extensions have been
developed over the years that add functionality and updated semantics, including additional HTTP methods and headers.

## HTTP Headers

Message headers are used to send metadata about a resource or a HTTP message, and to describe the behavior of the client
or the server.

```Headers``` can be grouped according to their contexts:

- Request headers

Contain more information about the resource to be fetched, or about the client requesting the resource.

- Response headers

Hold additional information about the response, like its location or about the server providing it.

- Representation headers

Contain information about the body of the resource, like its MIME type, or encoding/compression applied.

- Payload headers

Contain representation-independent information about payload data, including content length and the encoding used for
transport.

### Authentication

- WWW-Authenticate

Defines the authentication method that should be used to access a resource.

- Authorization

Contains the credentials to authenticate a user-agent with a server.

- Proxy-Authenticate

Defines the authentication method that should be used to access a resource behind a proxy server.

- Proxy-Authorization

Contains the credentials to authenticate a user agent with a proxy server.

### Connection Management

- Connection

Controls whether the network connection stays open after the current transaction finishes.

- Keep-Alive

Controls how long a persistent connection should stay open.

### Cookies

- Cookie

Contains stored HTTP cookies previously sent by the server with the Set-Cookie header.

- Set-Cookie

Send cookies from the server to the user-agent.

### WebSockets

Headers used by the WebSockets API in the WebSocket handshake:

- Sec-WebSocket-Accept

Response header that indicates that the server is willing to upgrade to a WebSocket connection.

- Sec-WebSocket-Extensions

In requests, this header indicates the WebSocket extensions supported by the client in preferred order. In responses, it
indicates the extension selected by the server from the client's preferences.

- Sec-WebSocket-Key

Request header containing a key that verifies that the client explicitly intends to open a WebSocket.

- Sec-WebSocket-Protocol

In requests, this header indicates the sub-protocols supported by the client in preferred order. In responses, it
indicates the sub-protocol selected by the server from the client's preferences.

- Sec-WebSocket-Version

In requests, this header indicates the version of the WebSocket protocol used by the client. In responses, it is sent
only if the requested protocol version is not supported by the server, and lists the versions that the server supports.

## HTTP Methods

Request methods indicate the purpose of the request and what is expected if the request is successful. The most common
methods are ```GET``` and ```POST``` for retrieving and sending data to servers, respectively, but there are other
methods which
serve different purposes. Each request method has its own semantics, but some characteristics are shared across multiple
methods, specifically request methods can be ```safe```, ```idempotent```, or ```cacheable```.

### GET

The ```GET``` method requests a representation of the specified resource. Requests using GET should only retrieve data
and should not contain a request content.

### HEAD

The ```HEAD``` method asks for a response identical to a GET request, but without a response body.

### POST

The ```POST``` method submits an entity to the specified resource, often causing a change in state or side effects on
the
server.

### PUT

The ```PUT``` method replaces all current representations of the target resource with the request content.

### DELETE

The ```DELETE``` method deletes the specified resource.

### CONNECT

The ```CONNECT``` method establishes a tunnel to the server identified by the target resource.

### OPTIONS

The ```OPTIONS``` method describes the communication options for the target resource.

### TRACE

The ```TRACE``` method performs a message loop-back test along the path to the target resource.

### PATCH

The ```PATCH``` method applies partial modifications to a resource.

## HTTP Response Status Codes

Response status codes indicate the outcome of a specific HTTP request.

**Responses are grouped in 5 (five) classes:**

- Informational (100 – 199)
- Successful (200 – 299)
- Redirection (300 – 399)
- Client error  (400 – 499)
- Server error (500 – 599)

### Informational (1xx)

#### 100 Continue

This interim response indicates that the client should continue the request or ignore the response if the request is
already finished.

#### 101 Switching Protocols

This code is sent in response to an Upgrade request header from the client and indicates the protocol the server is
switching to.

#### 102 Processing Deprecated

This code was used in WebDAV contexts to indicate that a request has been received by the server, but no status was
available at the time of the response.

#### 103 Early Hints

This status code is primarily intended to be used with the Link header, letting the user agent start preloading
resources while the server prepares a response or preconnect to an origin from which the page will need resources.

```text

```

### Successful (2xx)

#### 200 OK

The request succeeded. The result and meaning of ```success``` depends on the HTTP method:

- GET

The resource has been fetched and transmitted in the message body.

- HEAD

Representation headers are included in the response without any message body.

- PUT or POST

The resource describing the result of the action is transmitted in the message body.

- TRACE

The message body contains the request as received by the server.

**Mostly used in the following methods:**

```text
GET: 200 OK
PUT: 200 OK
PATCH: 200 OK
```

#### 201 Created

The request succeeded, and a new resource was created as a result. This is typically the response sent after POST
requests, or some PUT requests.

#### 202 Accepted

The request has been received but not yet acted upon. It is noncommittal, since there is no way in HTTP to later send an
asynchronous response indicating the outcome of the request. It is intended for cases where another process or server
handles the request, or for batch processing.

I find 202 Accepted to be a very handy alternative to 201 Created.

It basically means:
> I, the server, have understood your request. I have not created the resource (yet), but that is fine.

There are two main scenarios which I find 202 Accepted to be especially suitable:

- If the resource will be created as a result of future processing — example: After a job/process has finished.
- If the resource already existed in some way, but this should not be interpreted as an error.

**Mostly used in the following methods:**

```text
POST: 201 Created, 
POST: 202 Accepted
```

#### 204 No Content

There is no content to send for this request, but the headers are useful. The user agent may update its cached headers
for this resource with the new ones.

**Mostly used in the following methods:**

```text
DELETE: 204 No Content
```

#### 205 Reset Content

Tells the user agent to reset the document which sent this request.

#### 206 Partial Content

This response code is used in response to a range request when the client has requested a part or parts of a resource.

### Redirection (3xx)

#### 300 Multiple Choices

In agent-driven content negotiation, the request has more than one possible response and the user agent or user should
choose one of them. There is no standardized way for clients to automatically choose one of the responses, so this is
rarely used.

#### 301 Moved Permanently

The URL of the requested resource has been changed permanently. The new URL is given in the response.

#### 302 Found

This response code means that the URI of requested resource has been changed temporarily. Further changes in the URI
might be made in the future, so the same URI should be used by the client in future requests.

#### 304 Not Modified

This is used for caching purposes. It tells the client that the response has not been modified, so the client can
continue to use the same cached version of the response.

#### 307 Temporary Redirect

The server sends this response to direct the client to get the requested resource at another URI with the same method
that was used in the prior request. This has the same semantics as the 302 Found response code, with the exception that
the user agent must not change the HTTP method used: if a POST was used in the first request, a POST must be used in the
redirected request.

#### 308 Permanent Redirect

This means that the resource is now permanently located at another URI, specified by the Location response header. This
has the same semantics as the 301 Moved Permanently HTTP response code, with the exception that the user agent must not
change the HTTP method used: if a POST was used in the first request, a POST must be used in the second request.

### Client Error  (4xx)

#### 400 Bad Request

The server cannot or will not process the request due to something that is perceived to be a client error (e.g.,
malformed request syntax, invalid request message framing, or deceptive request routing).

#### 401 Unauthorized

Although the HTTP standard specifies "unauthorized", semantically this response means "unauthenticated". That is, the
client must authenticate itself to get the requested response.

#### 402 Payment Required

The initial purpose of this code was for digital payment systems, however this status code is rarely used and no
standard convention exists.

#### 403 Forbidden

The client does not have access rights to the content; that is, it is unauthorized, so the server is refusing to give
the requested resource. Unlike 401 Unauthorized, the client's identity is known to the server.

##### 401 vs 403

- Has the consumer not provided authentication credentials? Was their SSO Token invalid/timed out? 👉 401 Unauthorized.

- Was the consumer correctly authenticated, but they don’t have the required permissions/proper clearance to access the
  resource? 👉 403 Forbidden.

```text
401 Unauthorized, and 
403 Forbidden
```

#### 404 Not Found

The server cannot find the requested resource. In the browser, this means the URL is not recognized. In an API, this can
also mean that the endpoint is valid but the resource itself does not exist. Servers may also send this response instead
of 403 Forbidden to hide the existence of a resource from an unauthorized client. This response code is probably the
most well known due to its frequent occurrence on the web.

#### 405 Method Not Allowed

The request method is known by the server but is not supported by the target resource. For example, an API may not allow
DELETE on a resource, or the TRACE method entirely.

#### 406 Not Acceptable

This response is sent when the web server, after performing server-driven content negotiation, doesn't find any content
that conforms to the criteria given by the user agent.

#### 407 Proxy Authentication Required

This is similar to 401 Unauthorized but authentication is needed to be done by a proxy.

#### 408 Request Timeout

This response is sent on an idle connection by some servers, even without any previous request by the client. It means
that the server would like to shut down this unused connection. This response is used much more since some browsers use
HTTP pre-connection mechanisms to speed up browsing. Some servers may shut down a connection without sending this
message.

#### 409 Conflict

This response is sent when a request conflicts with the current state of the server. In WebDAV remote web authoring, 409
responses are errors sent to the client so that a user might be able to resolve a conflict and resubmit the request.

#### 410 Gone

This response is sent when the requested content has been permanently deleted from server, with no forwarding address.
Clients are expected to remove their caches and links to the resource. The HTTP specification intends this status code
to be used for "limited-time, promotional services". APIs should not feel compelled to indicate resources that have been
deleted with this status code.

#### 411 Length Required

Server rejected the request because the Content-Length header field is not defined and the server requires it.

#### 412 Precondition Failed

In conditional requests, the client has indicated preconditions in its headers which the server does not meet.

#### 413 Content Too Large

The request body is larger than limits defined by server. The server might close the connection or return an Retry-After
header field.

#### 414 URI Too Long

The URI requested by the client is longer than the server is willing to interpret.

#### 415 Unsupported Media Type

The media format of the requested data is not supported by the server, so the server is rejecting the request.

#### 416 Range Not Satisfiable

The ranges specified by the Range header field in the request cannot be fulfilled. It's possible that the range is
outside the size of the target resource's data.

#### 417 Expectation Failed

This response code means the expectation indicated by the Expect request header field cannot be met by the server.

#### 421 Misdirected Request

The request was directed at a server that is not able to produce a response. This can be sent by a server that is not
configured to produce responses for the combination of scheme and authority that are included in the request URI.

#### 422 Unprocessable Content (WebDAV)

The request was well-formed but was unable to be followed due to semantic errors.

#### 423 Locked (WebDAV)

The resource that is being accessed is locked.

#### 424 Failed Dependency (WebDAV)

The request failed due to failure of a previous request.

#### 425 Too Early Experimental

Indicates that the server is unwilling to risk processing a request that might be replayed.

#### 426 Upgrade Required

The server refuses to perform the request using the current protocol but might be willing to do so after the client
upgrades to a different protocol. The server sends an Upgrade header in a 426 response to indicate the required
protocol(s).

#### 428 Precondition Required

The origin server requires the request to be conditional. This response is intended to prevent the 'lost update'
problem, where a client GETs a resource's state, modifies it and PUTs it back to the server, when meanwhile a third
party has modified the state on the server, leading to a conflict.

#### 429 Too Many Requests

The user has sent too many requests in a given amount of time (rate limiting).

#### 431 Request Header Fields Too Large

The server is unwilling to process the request because its header fields are too large. The request may be resubmitted
after reducing the size of the request header fields.

#### 451 Unavailable For Legal Reasons

The user agent requested a resource that cannot legally be provided, such as a web page censored by a government.

### Server Error (5xx)

#### 500 Internal Server Error

The server has encountered a situation it does not know how to handle. This error is generic, indicating that the server
cannot find a more appropriate 5XX status code to respond with.

#### 501 Not Implemented

The request method is not supported by the server and cannot be handled. The only methods that servers are required to
support (and therefore that must not return this code) are GET and HEAD.

#### 502 Bad Gateway

This error response means that the server, while working as a gateway to get a response needed to handle the request,
got an invalid response.

#### 503 Service Unavailable

The server is not ready to handle the request. Common causes are a server that is down for maintenance or that is
overloaded. Note that together with this response, a user-friendly page explaining the problem should be sent. This
response should be used for temporary conditions and the Retry-After HTTP header should, if possible, contain the
estimated time before the recovery of the service. The webmaster must also take care about the caching-related headers
that are sent along with this response, as these temporary condition responses should usually not be cached.

#### 504 Gateway Timeout

This error response is given when the server is acting as a gateway and cannot get a response in time.

#### 505 HTTP Version Not Supported

The HTTP version used in the request is not supported by the server.

#### 506 Variant Also Negotiates

The server has an internal configuration error: during content negotiation, the chosen variant is configured to engage
in content negotiation itself, which results in circular references when creating responses.

#### 507 Insufficient Storage (WebDAV)

The method could not be performed on the resource because the server is unable to store the representation needed to
successfully complete the request.

#### 508 Loop Detected (WebDAV)

The server detected an infinite loop while processing the request.

#### 510 Not Extended

The client request declares an HTTP Extension (RFC 2774) that should be used to process the request, but the extension
is not supported.

#### 511 Network Authentication Required

Indicates that the client needs to authenticate to gain network access.

## REST

REST (```Representational State Transfer```) refers to a group of software architecture design constraints that bring
about efficient, reliable and scalable distributed systems.

![https://upload.wikimedia.org/wikipedia/commons/8/83/REST_information_model.png)](https://upload.wikimedia.org/wikipedia/commons/8/83/REST_information_model.png)

REST APIs utilize HTTP status codes to indicate the outcome of a request, with common codes
like ```200 (OK)```, ```201 (Created)```, ```400 (Bad Request)```, ```404 (Not Found)```,
and ```500 (Internal Server Error)```.

**Breakdown of HTTP status codes used in REST APIs:**

### Success Codes (2xx):

#### 200 OK

The request was successful.

#### 201 Created

The request has been fulfilled and a new resource has been created.

#### 204 No Content

The request was successful, but there's no content to return.

#### 206 Partial Content

The server has fulfilled a partial GET request for a resource.

### Client Error Codes (4xx)

#### 400 Bad Request

The server cannot process the request due to a client error.

#### 401 Unauthorized

Authentication is required to access the resource.

#### 403 Forbidden

The client does not have permission to access the resource.

#### 404 Not Found

The requested resource could not be found.

#### 405 Method Not Allowed

The HTTP method used is not allowed for the resource.

#### 409 Conflict

The request could not be completed due to a conflict with the current state of the resource.

### Server Error Codes (5xx):

#### 500 Internal Server Error

A generic error indicating that the server encountered an unexpected condition.

#### 503 Service Unavailable

The server is temporarily unavailable.

# Reference

---

- [HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP)
- [HTTP 2](https://developer.mozilla.org/en-US/docs/Glossary/HTTP_2)
- [REST](https://developer.mozilla.org/en-US/docs/Glossary/REST)
- [Wiki REST](https://en.wikipedia.org/wiki/REST)
- [HTTP Status Codes](https://restfulapi.net/http-status-codes/)
- [REST API HTTP Status Codes](https://docs.oracle.com/en/cloud/iaas-classic/messaging-cloud/csmes/rest-api-http-status-codes-and-error-messages-reference.html#GUID-8BC6BDC0-4C08-4229-98EA-BBFAA24A923A)

# Author

---

- Rohtash Lakra

