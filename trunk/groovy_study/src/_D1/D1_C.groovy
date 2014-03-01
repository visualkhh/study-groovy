package _D1

import groovy.sql.Sql 
//������ �ҽ� �̿� Ŀ�ؼ�
def source = new org.hsqldb.jdbc.JDBCDataSource();
source.database = 'jdbc:hsqldb:hsql://localhost/javaworld';
source.user = 'sa';
source.password = '';
def db = new groovy.sql.Sql(source);



//db.execute '''
//CREATE TABLE LOGIN_LOG (
//NAME VARCHAR(64),
//IP VARCHAR(64),
//LOGIN_DATE DATE
//);
//''';


//db.execute '''
//CREATE TABLE LOGIN_LOG(
//SEQ INTEGER GENERATED BY DEFAULT AS IDENTITY,
//NAME VARCHAR(64),
//IP VARCHAR(64),
//LOGIN_DATE DATE
//);
//CREATE INDEX SEQIdx ON LOGIN_LOG (SEQ);
//''';
/* �̹�  TABLE�־ ����.
CREATE INDEX SEQIDX ON LOGIN_LOG (SEQ);
 because: user lacks privilege or object not found: SEQ
Caught: java.sql.SQLSyntaxErrorException: user lacks privilege or object not found: SEQ
	at _D1.D1_2.run(D1_2.groovy:22)
*/


db.execute '''
	DROP INDEX SEQIDX IF EXISTS;
	DROP TABLE LOGIN_LOG IF EXISTS;
	CREATE TABLE LOGIN_LOG(
	SEQ INTEGER GENERATED BY DEFAULT AS IDENTITY,
	NAME VARCHAR(64),
	IP VARCHAR(64),
	LOGIN_DATE DATE
	);
	CREATE INDEX SEQIdx ON LOGIN_LOG (SEQ);
''';