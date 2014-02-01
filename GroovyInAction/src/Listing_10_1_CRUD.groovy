// requires hsqldb.jar in classpath
import groovy.sql.Sql

dbHandle = null // XXX todo explain why Sql is missing 

def getDb(){                                            //#1
    if (dbHandle) return dbHandle
    def source = new org.hsqldb.jdbc.jdbcDataSource()
    source.database = 'jdbc:hsqldb:mem:GIA'
    source.user = 'sa'
    source.password = ''
    dbHandle = new Sql(source)
    return dbHandle
}
def reset() {                                           //#2
    db.execute '''
        DROP   INDEX athleteIdx IF EXISTS;
        DROP   TABLE Athlete IF EXISTS;
        CREATE TABLE Athlete (
            athleteId   INTEGER GENERATED BY DEFAULT AS IDENTITY,
            firstname   VARCHAR(64),
            lastname    VARCHAR(64),
            dateOfBirth DATE
        );
        CREATE INDEX athleteIdx ON Athlete (athleteId);
    '''
}
def create(firstname, lastname, dateOfBirth) {          //#3
    db.execute """
        INSERT INTO Athlete ( firstname, lastname, dateOfBirth)
                     VALUES ($firstname,$lastname,$dateOfBirth);
    """
}
def findAll() {                                         //#4
    db.rows 'SELECT * FROM Athlete'
}
def updateFirstName(wrong, right) {                     //#5
    db.execute """
        UPDATE Athlete
           SET firstname = $right WHERE firstname = $wrong;
    """
}
def delete(firstname) {                                 //#6
    db.execute "DELETE FROM Athlete WHERE firstname = $firstname;"
}

reset()
assert ! findAll(), 'we are intially empty'
create 'Dirk', 'Koenig', '1968-04-19'
assert 'Dirk'  == findAll()[0].firstname                //#7
updateFirstName  'Dirk', 'Dierk'
assert 'Dierk' == findAll()[0].firstname
delete 'Dierk'
assert ! findAll(), 'after delete, we are empty again'