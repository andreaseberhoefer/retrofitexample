#Retrofit Example

This is a little example project using [Retrofit](http://square.github.io/retrofit/)
and [JOOX](https://code.google.com/p/joox/) for ~~Rest~~-API

You need nodejs for the server and maven for the client.

1. Clone the project
git clone git@github.com:andreaseberhoefer/retrofitexample.git

2. Setup the server

cd retrofitexample/server
npm install

3. Start the server
node server.js

4. Go to http://localhost:8080/foo/bar?cmd=command&count=10&fields=somebody,other&&magic=true&magicCount=true and play with the params

5. Run the client JUnit tests
cd ../client
mvn test

6. Look at the client code
