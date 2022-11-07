# vinyl-store-app

Spring Restful web service End Points <br>
We will have the following rest web services endpoints.


|    	| URI                             	| HTTP Method 	| Details                	|
|----	|---------------------------------	|-------------	|------------------------	|
| 1  	| /api/artist/all                 	| GET         	|  Gets all   artists    	|
| 2  	| /api/vinyl/all                  	| GET         	| Gets all vinyls        	|
| 3  	| /api/artist/find/{id}           	| GET         	| Find an artist by id   	|
| 4  	| /api/vinyl/find/{id}            	| GET         	| Find a vinyl by id     	|
| 5  	| /api/artist/find-by-name/{name} 	| GET         	| Find artist by name    	|
| 6  	| /api/vinyl/find-by-name/{name}  	| GET         	| Find vinyl by name     	|
| 7  	| /api/artist/add                 	| POST        	| Add an artist          	|
| 8  	| /api/vinyl/add                  	| POST        	| Add a vinyl            	|
| 9  	| /api/artist/update              	| PATCH       	| Update an artist       	|
| 10 	| /api/vinyl/update               	| PATCH       	| Update a vinyl         	|
| 11 	| /api/artist/delete{id}          	| DELETE      	| Delete an artist       	|
| 12 	| /api/vinyl/delete{id}           	| DELETE      	| Delete a vinyl         	|
			
### Create an artist 

```
POST /api/artist/add
Accept: */*
Content-Type: application/json
{
    "name": "Pink Floyd",
    "description": "Pink Floyd are an English rock band formed in London in 1965. Gaining an early following as one of the first British psychedelic groups, they were distinguished by their extended compositions, sonic experimentation, philosophical lyrics and elaborate live shows. They became a leading band of the progressive rock genre, cited by some as the greatest progressive rock band of all time.",
    "albums": [
        {
            "artist": "Pink Floyd",
            "albumName": "The Dark Side of the Moon",
            "description": "The Dark Side of the Moon is the eighth studio album by the English rock band Pink Floyd, released on 1 March 1973 by Harvest Records. "
        }
    ]
}
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/artist/23
```
