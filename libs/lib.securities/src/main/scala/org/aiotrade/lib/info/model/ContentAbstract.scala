package org.aiotrade.lib.info.model

import ru.circumflex.orm.Table
import ru.circumflex.orm._

object ContentAbstracts extends Table[ContentAbstract]{
   val generalInfo =  "generalInfos_id" REFERENCES(GeneralInfos)
   var content = "content" VARCHAR(3000) NULLABLE
}

class ContentAbstract {
  var generalInfo : GeneralInfo = _
  var content : String = _
}

