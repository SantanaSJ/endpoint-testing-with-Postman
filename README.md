### Basic testing with Postman
Spring Restful web service end points <br>
We will have the following rest web services endpoints.


|    	| URI                             	| HTTP Method 	| Details                	|
|----	|---------------------------------	|-------------	|------------------------	|
| 1  	| /api/artist/all                 	| GET         	|  Gets all   artists    	|
| 2  	| /api/album/all                  	| GET         	| Gets all albums        	|
| 3  	| /api/artist/find/{id}           	| GET         	| Find an artist by id   	|
| 4  	| /api/album/find/{id}            	| GET         	| Find an album by id     	|
| 5  	| /api/artist/find-by-name/{name} 	| GET         	| Find artist by name    	|
| 6  	| /api/album/find-by-name/{name}  	| GET         	| Find album by name     	|
| 7  	| /api/artist/add                 	| POST        	| Add an artist          	|
| 8  	| /api/album/add                  	| POST        	| Add an album            	|
| 9  	| /api/artist/update              	| PATCH       	| Update an artist       	|
| 10 	| /api/album/update               	| PATCH       	| Update an album         	|
| 11 	| /api/artist/delete{id}          	| DELETE      	| Delete an artist       	|
| 12 	| /api/vinyl/delete{id}           	| DELETE      	| Delete a album         	|



### Get all artists

```
GET /api/artist/all

RESPONSE: HTTP 200 (OK)
```
<img src="https://github.com/SantanaSJ/vinyl-store-app/blob/master/all artists.png" alt="postman" title="postman">


### Get all albums

```
GET /api/album/all

RESPONSE: HTTP 200 (OK)
```
<img src="https://github.com/SantanaSJ/vinyl-store-app/blob/master/all.png" alt="postman" title="postman">

### Create an artist 

```
POST /api/artist/add
Accept: */*
Content-Type: application/json
{
    "name": "Pink Floyd",
    "description": "Pink Floyd are an English rock band formed in London in 1965.",
    "albums": [
        {
            "artist": "Pink Floyd",
            "albumName": "The Dark Side of the Moon",
            "description": "The Dark Side of the Moon is the eighth studio album by the English rock band Pink Floyd. "
        }
    ]
}
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/artist/23
```
<img src="https://github.com/SantanaSJ/vinyl-store-app/blob/master/add artist.png" alt="postman" title="postman">

### Create an album 
```
POST /api/album/add
Accept: */*
Content-Type: application/json
{
    "artist": "Pink Floyd",
    "albumName": "Atom Heart Mother",
    "description": "Atom Heart Mother is the fifth studio album by the English progressive rock band Pink Floyd."
}
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/album/23
```
<img src="https://github.com/SantanaSJ/vinyl-store-app/blob/master/add album.png" alt="postman" title="postman">

### Update an artist 
```
PATCH /api/artist/update
Accept: */*
Content-Type: application/json
{
    "id": {{artistId}},
    "name": "Smashing Pumpkins3",
    "description": "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo."
}
RESPONSE: HTTP 200 (artist)
```
<img src="https://github.com/SantanaSJ/vinyl-store-app/blob/master/artist update.png" alt="postman" title="postman">

### Update an album 
```
PATCH /api/album/update
Accept: */*
Content-Type: application/json
{
    "id": {{albumId}},
    "albumName": "Stormbringer1",
    "description": "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."
}
RESPONSE: HTTP 200 (album)
```
<img src="https://github.com/SantanaSJ/vinyl-store-app/blob/master/album update.png" alt="postman" title="postman">
