# alexaan-restservice
 
Spring REST service. Running live at https://alexaan-restservice.herokuapp.com

*Early work in progress*


## Technologies

Spring, Gradle, JSON, Heroku, ClearDB



## Usage

- `https://alexaan-restservice.herokuapp.com/heroku?id=[cid]` - get Customer from DB with id=`[cid]` as JSON

- `https://alexaan-restservice.herokuapp.com/heroku/getall` - get all registered Customers from DB as JSON

- `curl --data "id=[cid]&name=[cname]&age=[cage]" https:alexaan-restservice.herokuapp.com/heroku` - post a new Customer with id=`[cid]`, name=`[cname]`, age=`[cage]` to the application's database

##ClearDB for Heroku

###Creating a ClearDB database for Heroku

Based on local database dump file 

- `heroku addons:add cleardb:ignite`

- `heroku config | grep CLEARDB_DATABASE_URL`

 - Should return something like `mysql://[username]:[password]@[databasename].cleardb.net/[tablename]?reconnect=true`

- `mysql -u [username] -h [databasename].cleardb.net -p [tablename] < [path to your local database dump]

- use [password] when prompted for a password
- push changes to your Heroku App via git
 - `git add .`
 - `git commit -m '[your message]'`
 - `git push Heroku master`

###Connect to your ClearDB database via MySQL Workbench

- `heroku config | grep CLEARDB_DATABASE_URL`

 - Should return something like `mysql://[username]:[password]@[databasename].cleardb.net/[tablename]?reconnect=true`


