# CUSTOM MESSAGING PROTOCOL APPLICATION


<br />
<hr />

Table of contents
=================


<!--ts-->  
   * [Installation](#installation)
        * [backend](#backend)
	        * [application](#application)
	        * [database](#database)
        * [frontend](#frontend)
<!--te-->


Installation
============

backend
-------


```bash
$ cd backend/

$ java -jar cmp-application.jar
```

database
--------

<h5> for creating docker image </h5>
```bash
$ sudo docker run --name cmp-database -e POSTGRES_PASSWORD=test123 -d postgres

$ sudo docker exec -it cmp-database bash

$ sudo docker cp database-file.sql cmp-database:/

$ sudo docker container exec -it cmp-database bash
```

<h5> inside the docker image to run </h5>

```bash
$ psql -U postgres --file database-file.sql
```
<hr/>
<br/>

frontend
-------

```bash
$ cd frontend/

# for installing dependency
$ npm install # or
$ yarn install

# and then run the following command
$ npm run start
```