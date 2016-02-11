#			Meta-information Collector for relation databases                  #
	
		
####*If you want to extract metadata from a database - it is your tool!*####




###FUNCTIONALITY###

This service provides functionality for extracting metadata 
information of databases (SQLite & Postgres as for now), such as:
- tables,
- fields, their data types, default values,
- primary keys,
- foreign keys (including referenced fields and tables),
- indexes, their pseudonyms and unique; fields that are included into indexes.  


###USAGE###

Entry point of the project is: `com.qsci.database.metadata.Run`.

Databases are configurated in `resources/data.properties` file as follows:

1. SQLITE databases:

    * Path to the database should be set to the `url` property,
    * `null` should be set as a value of `login` and `password` properties.

    *Example:*
    ```
    url = jdbc:sqlite:mySQLiteBASE.sl3
    login = null
    password = null
    ```


2. POSTGRES databases:

   * Path to the database should be set to the `url` property,
   * `password` and `login` properties should be initialized.

    *Example:* 
    ```
    url = jdbc:sqlite:myPostgresBASE.sl3
    login = postgresql
    password = password
    ```


The default database is `SQLITE Northwind.sl3`.

###FUTURE PLANS###

To add mySQL and DERBY support.


