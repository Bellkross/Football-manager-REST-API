# Football-manager-REST-API

## Entity relationship diagram

![Entity Relationship Diagram](documentation\EntityRelationshipDiagram.png)


- **"Player" entity:** Data required for each player consists of first name, last name, position and birthday

- **"Team" entity:** A team should also have a name which is mandatory

- **"Belongs to" relation:** Each team consists of multiple players

- **"Belongs to" relation:** A player can be assigned to one and only one team, but can exists without a team

- **"Is captain of" relation:** Apart from that, team should have a capitan. Capitan is one of the players assigned to that team.

## Relationship diagram

![Relationship diagram](documentation\RelationshipDiagram.png)