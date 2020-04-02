package com.lynxspa.instagrammo

import com.lynxspa.instagrammo.db.DbHelper

val db : DbHelper by lazy {
    Application.db!!

}