Setup database 
docker run --name kakao-gamification-mysql -e MYSQL_ROOT_PASSWORD=123456a@ -d -p 3306:3306 mysql:5.7.41

Authentication refs
https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/
https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-2/
https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-3/

https://developer.okta.com/blog/2020/01/23/pkce-oauth2-spring-boot
https://developer.twitter.com/en/docs/authentication/oauth-2-0/user-access-token
https://docs.spring.io/spring-security/reference/servlet/oauth2/client/index.html

SNS
FB_Secretket=af100197a1e999474e032628e3d027ab
FB_APP_ID=3106848232869961

Google_Client_ID=454714594333-9s0k5egl9hvq6q9g4rgsteg7i8pn2ebj.apps.googleusercontent.com
Google_Client_Secret=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

clientId: X1dUMnUzZzlTM3Zka2lMamtfeG06MTpjaQ
clientSecret: P9kKXtnAtY7dg3gZ3GTdO2LJeIvZg7WJluR419Bt0k-ix824_J

API
POST http://localhost:8080/auth/signup
{
"name": "Administrator",
"username": "admin",
"password": "admin"
}

POST http://localhost:8080/auth/login
{
"username": "admin",
"password": "admin"
}

GET http://localhost:8080/user/me