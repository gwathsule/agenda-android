CREATE TABLE IF NOT EXISTS CONTATO (
    _id                INTEGER       NOT NULL
                                     PRIMARY KEY AUTOINCREMENT,
    TELEFONE           VARCHAR (14),
    TIPOTELEFONE       VARCHAR (1),
    EMAIL              VARCHAR (255),
    TIPOEMAIL          VARCHAR (1),
    ENDERECO           VARCHAR (255),
    TIPOENDERECO       VARCHAR (1),
    DATASESPECIAIS     DATE,
    TIPODATASESPECIAIS VARCHAR (1),
    GRUPOS             VARCHAR (255) 
);

sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO (");
sqlBuilder.append("_id					INTEGER       NOT NULL");
sqlBuilder.append("						PRIMARY KEY AUTOINCREMENT,");
sqlBuilder.append("TELEFONE				VARCHAR (14),");
sqlBuilder.append("TIPOTELEFONE			VARCHAR (1),");
sqlBuilder.append("EMAIL				VARCHAR (255),");
sqlBuilder.append("TIPOEMAIL			VARCHAR (1),");
sqlBuilder.append("ENDERECO				VARCHAR (255),");
sqlBuilder.append("TIPOENDERECO			VARCHAR (1),");
sqlBuilder.append("DATASESPECIAIS		DATE,");
sqlBuilder.append("TIPODATASESPECIAIS	VARCHAR (1),");
sqlBuilder.append("GRUPOS				VARCHAR (255)");
sqlBuilder.append(");");