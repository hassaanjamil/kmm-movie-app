package data.db

import app.cash.sqldelight.db.SqlDriver

val DB_NAME = "movies.db"

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    return Database(driverFactory.createDriver())
}
