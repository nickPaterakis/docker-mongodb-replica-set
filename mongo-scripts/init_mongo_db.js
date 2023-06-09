// create user
db = db.getSiblingDB("admin");
db.createUser({
    user: 'admin',
    pwd: 'admin',
    roles: ['dbOwner'],
});