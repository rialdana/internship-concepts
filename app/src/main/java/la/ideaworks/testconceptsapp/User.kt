package la.ideaworks.testconceptsapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    val id: Int,

    val name: String,

    val age: Int,

    val occupation: String

) : Parcelable