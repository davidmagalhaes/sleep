# sleep
A Spring boot project to manage user's sleep time

### Summary

This is a project for an API that registers 
the user's sleep data and generate aggregate data on it
for consultation purposes.

### Stack

- Postgresql database
- Hibernate and JPA
- Gson
- Mockk

### How to run
Go to the root of the project and run `docker compose up`, which will deploy the web application on port 8080

### Endpoints
#### Create Sleep log ( POST `sleep/create`)

- Request parameters (form-data):
  - date (yyyy-MM-dd)
  - startTime (hh:MM:ss)
  - endTime (hh:MM:ss)
  - rating (BAD, OK, GOOD)
- Successful response: `HTTP 200 OK`


#### Get most recent sleep log ( GET `sleep/last`)

Example response:
```json 
{
    "date": "2024-08-15",
    "interval": {
        "start": "00:00:00",
        "end": "02:00:00"
    },
    "duration": "02:00:00",
    "rating": "GOOD"
}
```
** If there is no register in the database, 
this endpoint will return `HTTP 404 NOT FOUND`

#### Get sleep log average for the last 30 days ( GET `sleep/avg`)

Example response:
```json
{
    "avgTime": "03:00:00",
    "avgInterval": {
        "start": "01:30:00",
        "end": "04:30:00"
    },
    "period": {
        "start": "2024-08-02",
        "end": "2024-08-15"
    },
    "ratingsCount": {
        "BAD": 0,
        "OK": 0,
        "GOOD": 2
    }
}
```
** If there is no register in the database,
this endpoint will return `HTTP 404 NOT FOUND`

