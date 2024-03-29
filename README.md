# Football-manager-REST-API

## Entity relationship diagram

![Entity Relationship Diagram](https://github.com/Bellkross/Football-manager-REST-API/blob/master/documentation/EntityRelationshipDiagram.png)


- **"Player" entity:** Data required for each player consists of first name, last name, position and birthday

- **"Team" entity:** A team should also have a name which is mandatory

- **"Belongs to" relation:** Each team consists of multiple players

- **"Belongs to" relation:** A player can be assigned to one and only one team, but can exists without a team

- **"Is captain of" relation:** Apart from that, team should have a capitan. Capitan is one of the players assigned to that team.

## Relationship diagram

![Relationship diagram](https://github.com/Bellkross/Football-manager-REST-API/blob/master/documentation/RelationshipDiagram.png)

## REST API
### **CRUD**
___
- GET **/teams/{id}**

    Get team by id
___
- POST **/teams**

    **Request body:** Team entity without id

    Add a new(not existing) team
___
- PUT **/teams/{id}**

    **Request body:** Team entity with id

    Modify a existing team
___
- DELETE **/teams/{id}**

    Delete a existing team
___
- GET **/players/{id}**

    Get player by id
___
- POST **/players**

    **Request body:** Player entity without id

    Add a new(not existing) player
___
- PUT **/players/{id}**

    **Request body:** Player entity with id

    Modify a existing player
___
- DELETE **/players/{id}**

    Delete a existing player
___
### **Additional endpoints**
___
- GET **/players**

    Get list of all players
___
- GET **/teams**

    Get list of all teams
___
- GET **/teams/{id}/players**

    Get a list of players by team
___
- GET **/teams/{id}/captain**

    Get a captain by team
___
- POST **/teams/{id}/player**

    **Request body:** Player entity without id

    Add a new(not existing) player to a team
___
- PUT **/teams/{team_id}/captain/{player_id}**

    **Request body:** Player entity with id

    Assign a captain
___