package com.mst.instagrammo.model

import com.google.gson.annotations.SerializedName
import com.mst.instagrammo.utilities.Session

class StoriesRequest (
    @SerializedName("profileId") val profileId: Int = Session.profileId
)