package org.hrv.web

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

//data class User(val name: String, val email: String, val id: Int)

class PlsqlDAO {

    fun insertIntoTable() {
        val conn = getConnection()
        val st = conn.prepareStatement("INSERT INTO usertable (user_id, user_name, user_email) VALUES (?, ?, ?)")
        st.setInt(1,1)
        st.setString(2,"username")
        st.setString(3, "emailAddesss")
        st.executeUpdate()
        st.close()
    }

    fun getUser():User? {
        val conn = getConnection()
        val st = conn.createStatement()
        val rs = st.executeQuery("SELECT * FROM usertable")
        var result:User? = null
        while(rs.next()){
            val id = rs.getInt(1)
            val name = rs.getString(2)
            val email = rs.getString(3)

            result = User(name = name, email = email, id = id)
        }
        return result

    }

    private fun getConnection():Connection {
        val url = "jdbc:postgresql://localhost:5432/sparkdatabase"
        val props = Properties()
        props.setProperty("user", "spark")
        props.setProperty("password", "password")
        return DriverManager.getConnection(url, props)
    }
}