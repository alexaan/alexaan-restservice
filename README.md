# alexaan-restservice
 
> Spring REST service. Running live at https://alexaan-restservice.herokuapp.com

*Early work in progress*


## Technologies

Spring, Gradle, JSON, Heroku, ClearDB



## Usage

`https://alexaan-restservice.herokuapp.com/heroku?id=1`
get Customer from DB with id 1 as JSON

https://alexaan-restservice.herokuapp.com/heroku/getall 
>get all registered Customers from DB as JSON

curl --data "id=`[cid]`&name=`[cname]`&age=`[cage]`" https:alexaan-restservice.herokuapp.com/heroku
> post a new Customer with id=`[cid]`, name=`[cname]`, age=`[cage]` to the application's database
