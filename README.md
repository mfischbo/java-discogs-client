# java-discogs-client
A client library to communicate with the API from discogs.com

### Currently supported
* Requests to the dicogs database API https://www.discogs.com/developers/#page:database
* Requests to the discogs user collection API
https://www.discogs.com/developers/#page:user-collection
(currently not fully implemented yet).
* Authorization using username / password
* Authorization using an access token
* First draft for a OAuth authentication (not fully implemented)

Please be aware that this library is still in early development. I try to keep the public API
of the client as small and stable as possible but things might change until the client is considered 
being feature complete.

### Usage
The main entry point is the class _DiscogsClient_ that provides access to API operations as well as authentication.

Example:
```java
// Create a client without any authentication
DisogsClient client = new DiscogsClient();
```

The different API's are accessible through _Operations_. These can be retrieved
using the following calls:

```java
// Create a new authorization strategy that should be used with the operation.
AuthorizationStrategy auth = new TokenAuthenticationStrategy("my-secret-api-token");

// To get access to the database API
DatabaseOperations dbOps = client.getDatabaseOperations(auth);

// To get access to the user collection API
UserCollectionOperations userOps = client.getUserCollectionOperations(auth);
```

### OAuth authentication
_Note: This is not fully implemented yet._

```java
// Create a new OAuth flow for a given clientKey and clientSecret
OAuthVector vector = new OAuthVector(clientKey, clientSecret);
OAuthFlow flow = client.getOAuthFlow(vector);

// Request a redirect URL to the authorization page
OAuthCredentials credentials = flow.getAuthenticationUrl();

// credentials will now contain a URL the user needs to be redirected to
// in order to grant access to his account through your application
String redirectUrl = credentials.getRedirectUrl();

// the user will be presented a page to grant access and has to enter
// the presented verification code in your application. You can then
// gain the access token with the following step
credentials = flow.getAccessToken(credentials, verificationCode);

// When the authenticatin was successful, credentials will now contain
// the access token and shared secret. This should be persisted somewhere.

```
