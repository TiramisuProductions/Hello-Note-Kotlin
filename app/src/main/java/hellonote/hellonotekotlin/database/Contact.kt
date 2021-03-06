package hellonote.hellonotekotlin.database

import com.orm.SugarRecord

 class Contact : SugarRecord <Contact> {
    var name: String? = null
    var contactNumber: String? = null
    var calledNumber: String? = null
    var calledName: String? = null
    var incoming: Boolean = false
    var tsMilli: String? = null
    var isSavedFromApp: Boolean = false
    var isBackedUp: Boolean = false

     constructor(){}
     constructor(
         name: String?,
         contactNumber: String?,
         calledNumber: String?,
         calledName: String?,
         incoming: Boolean,
         tsMilli: String?,
         isBackedUp: Boolean
     ) : super() {
         this.name = name
         this.contactNumber = contactNumber
         this.calledNumber = calledNumber
         this.calledName = calledName
         this.incoming = incoming
         this.tsMilli = tsMilli
         this.isBackedUp = isBackedUp
     }

     constructor(
         name: String?,
         contactNumber: String?,
         calledNumber: String?,
         calledName: String?,
         incoming: Boolean,
         tsMilli: String?
     ) : super() {
         this.name = name
         this.contactNumber = contactNumber
         this.calledNumber = calledNumber
         this.calledName = calledName
         this.incoming = incoming
         this.tsMilli = tsMilli
     }

     constructor(
         name: String?,
         contactNumber: String?,
         tsMilli: String?,
         isSavedFromApp: Boolean,
         isBackedUp: Boolean
     ) : super() {
         this.name = name
         this.contactNumber = contactNumber
         this.tsMilli = tsMilli
         this.isSavedFromApp = isSavedFromApp
         this.isBackedUp = isBackedUp
     }


 }





