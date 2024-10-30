#!/usr/bin/env bash

echo "creating mongo users..."
mongosh --authenticationDatabase admin --host localhost -u dorian -p dorian01 <<EOF
use user-auth-service;
const userExists = db.getUser('devDorian')!=null;
if(!userExists){
    print("Creating the new user devDorian");
    db.createUser({user: 'devDorian', pwd: 'devDorianPass', roles: [{role: 'dbOwner', db: 'user-auth-service'}]});
}else{
    print("the user devDorian already exists");
}
EOF
echo "MongoDB users setup complete"