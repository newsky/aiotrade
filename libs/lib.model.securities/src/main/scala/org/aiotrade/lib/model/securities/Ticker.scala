package org.aiotrade.lib.model.securities

import ru.circumflex.orm.LongIdPK
import ru.circumflex.orm.Record
import ru.circumflex.orm.Table

object Ticker extends Table[Ticker] with LongIdPK[Ticker] {
  val intraDay = longColumn("intraDay_id").references(IntraDay)
  val time = longColumn("time")
  
  val prevClose   = numericColumn("prevClose",  12, 2)
  val latestPrice = numericColumn("lastPrice",  12, 2)

  val dayOpen    = numericColumn("dayOpen",    12, 2)
  val dayHigh    = numericColumn("dayHigh",    12, 2)
  val dayLow     = numericColumn("dayLow",     12, 2)
  val dayVolume  = numericColumn("dayVolume",  18, 2)
  val dayAmount  = numericColumn("dayAmount",  18, 2)

  val dayChange  = numericColumn("dayChange",  12, 2)
}

class Ticker extends Record[Ticker](Ticker) {
  val id = field(Ticker.id)
  val intraDay = manyToOne(Ticker.intraDay)
  val time = field(Ticker.time)

  val prevClose    = field(Ticker.prevClose)
  val latestPrice  = field(Ticker.latestPrice)

  val dayOpen   = field(Ticker.dayOpen)
  val dayHigh   = field(Ticker.dayHigh)
  val dayLow    = field(Ticker.dayLow)
  val dayVolume = field(Ticker.dayVolume)
  val dayAmount = field(Ticker.dayAmount)

  val dayChange = field(Ticker.dayChange)
}
