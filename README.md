# alexaan-restservice
 
Spring REST service. Running live at https://alexaan-restservice.herokuapp.com

*Early work in progress*


## Technologies

Spring, Gradle, JSON, Heroku, ClearDB



## Usage

The application serves data from a SQL database in JSON format. Returned Customer entries will be sorted by `[cid]` (customer id). 

- **Get customer by id**: `https://alexaan-restservice.herokuapp.com/?id=[cid]` returns Customer from DB with id=`[cid]` as JSON
- **Get customer(s) by name**: `https://alexaan-restservice.herokuapp.com/?name=[cname]` returns Customer(s) from DB with `[cname]` as part of their name as JSON
- **Get customer(s) by age**: `https://alexaan-restservice.herokuapp.com/?age=[cage]` returns Customer(s) from DB with age=`[cage]` as JSON
 
- **Get customer(s) by combining request parameters**: `https://alexaan-restservice.herokuapp.com/?id=[cid]&name=[cname]&age=[cage]` returns Customer(s) from DB that match atleast one of the request parameters as JSON. Works with any combination of `[cid]`, `[cname]`, `[cage]`.

- **Get all customers**: `https://alexaan-restservice.herokuapp.com/heroku/getall` returns all registered Customers from DB as JSON

- **Post customer**: `curl --data "id=[cid]&name=[cname]&age=[cage]" https:alexaan-restservice.herokuapp.com` posts a new Customer with id=`[cid]`, name=`[cname]`, age=`[cage]` to the application's database

##ClearDB for Heroku

###Creating a ClearDB database for Heroku

Based on local database dump file 

- `heroku addons:add cleardb:ignite`

- `heroku config | grep CLEARDB_DATABASE_URL`

 - Should return something like `mysql://[username]:[password]@[databasename].cleardb.net/[tablename]?reconnect=true`

- `mysql -u [username] -h [databasename].cleardb.net -p [tablename] < [path to your local database dump]`

- use `[password]` when prompted for a password
- push changes to your Heroku App via git
 - `git add .`
 - `git commit -m '[your message]'`
 - `git push Heroku master`

###Connect to your ClearDB database via MySQL Workbench

- `heroku config | grep CLEARDB_DATABASE_URL`

 - Should return something like `mysql://[username]:[password]@[databasename].cleardb.net/[tablename]?reconnect=true`
- Set up the connection in MySQL Workbench (see picture). Be sure to store your `[password]` to the vault.

 ![Workbench ClearDB SSL Connection](http://i.imgur.com/DODlX5o.png)


