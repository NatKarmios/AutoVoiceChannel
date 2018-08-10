package data.roles

import com.squareup.moshi.Json

data class RoleData (
        @Json(name = "user_role_id") var userRoleID: Long?,
        @Json(name = "mod_role_id")  var modRoleID : Long?
)