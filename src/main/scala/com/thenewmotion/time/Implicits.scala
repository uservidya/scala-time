package com.thenewmotion.time

/**
 * Copyright 2009 Jorge Ortiz
 * Copyright 2009 Barry Kaplan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 **/

import org.joda.time.format.DateTimeFormatter
import org.joda.time.field.AbstractReadableInstantFieldProperty
import java.util.Date
import java.sql.Timestamp
import org.joda.time._
import base.{BaseDateTime, AbstractDateTime, AbstractInstant, AbstractPartial}

object Implicits extends Implicits

object BuilderImplicits extends Implicits

object IntImplicits extends IntImplicits

object JodaImplicits extends JodaImplicits

object JavaImplicits extends JavaImplicits

object SqlImplicits extends SqlImplicits

trait Implicits extends BuilderImplicits with IntImplicits with JodaImplicits with JavaImplicits
with SqlImplicits with TupleImplicits

trait BuilderImplicits {
  implicit def forcePeriod(builder: DurationBuilder): Period =
    builder.underlying

  implicit def forceDuration(builder: DurationBuilder): Duration =
    builder.underlying.toStandardDuration
}

trait IntImplicits {
  implicit def RichInt(n: Int): RichInt = new com.thenewmotion.time.RichInt(n)

  implicit def RichLong(n: Long): RichLong = new com.thenewmotion.time.RichLong(n)
}

trait JodaImplicits {
  implicit def RichAbstractDateTime(dt: AbstractDateTime): RichAbstractDateTime = new RichAbstractDateTime(dt)

  implicit def RichAbstractInstant(in: AbstractInstant): RichAbstractInstant = new RichAbstractInstant(in)

  implicit def RichAbstractPartial(pt: AbstractPartial): RichAbstractPartial = new RichAbstractPartial(pt)

  implicit def RichAbstractReadableInstantFieldProperty(pty: AbstractReadableInstantFieldProperty): RichAbstractReadableInstantFieldProperty =
    new RichAbstractReadableInstantFieldProperty(pty)

  implicit def RichChronology(ch: Chronology): RichChronology = new RichChronology(ch)

  implicit def RichDateMidnight(dm: DateMidnight): RichDateMidnight = new RichDateMidnight(dm)

  implicit def RichDateTime(dt: DateTime): RichDateTime = new RichDateTime(dt)

  implicit def RichDateTimeFormatter(fmt: DateTimeFormatter): RichDateTimeFormatter = new RichDateTimeFormatter(fmt)

  implicit def RichDateTimeProperty(pty: DateTime.Property): RichDateTimeProperty = new RichDateTimeProperty(pty)

  implicit def RichDateTimeZone(zone: DateTimeZone): RichDateTimeZone = new RichDateTimeZone(zone)

  implicit def RichDuration(dur: Duration): RichDuration = new RichDuration(dur)

  implicit def RichInstant(in: Instant): RichInstant = new RichInstant(in)

  implicit def RichLocalDate(ld: LocalDate): RichLocalDate = new RichLocalDate(ld)

  implicit def RichLocalDateProperty(pty: LocalDate.Property): RichLocalDateProperty = new RichLocalDateProperty(pty)

  implicit def RichLocalDateTime(dt: LocalDateTime): RichLocalDateTime = new RichLocalDateTime(dt)

  implicit def RichLocalDateTimeProperty(pty: LocalDateTime.Property): RichLocalDateTimeProperty = new RichLocalDateTimeProperty(pty)

  implicit def RichLocalTime(lt: LocalTime): RichLocalTime = new RichLocalTime(lt)

  implicit def RichLocalTimeProperty(pty: LocalTime.Property): RichLocalTimeProperty = new RichLocalTimeProperty(pty)

  implicit def RichPartial(pt: Partial): RichPartial = new RichPartial(pt)

  implicit def RichPartialProperty(pty: Partial.Property): RichPartialProperty = new RichPartialProperty(pty)

  implicit def RichPeriod(per: Period): RichPeriod = new RichPeriod(per)

  implicit def RichReadableDateTime(dt: ReadableDateTime): RichReadableDateTime = new RichReadableDateTime(dt)

  implicit def RichReadableDuration(dur: ReadableDuration): RichReadableDuration = new RichReadableDuration(dur)

  implicit def RichReadableInstant(in: ReadableInstant): RichReadableInstant = new RichReadableInstant(in)

  implicit def RichReadableInterval(in: ReadableInterval): RichReadableInterval = new RichReadableInterval(in)

  implicit def RichReadablePartial(rp: ReadablePartial): RichReadablePartial = new RichReadablePartial(rp)

  implicit def RichReadablePeriod(per: ReadablePeriod): RichReadablePeriod = new RichReadablePeriod(per)

  implicit object DateTimeOrdering extends Ordering[DateTime] {
    def compare(x: DateTime, y: DateTime) = x compare y
  }

}

trait JavaImplicits {
  implicit def date2DateTime(d: Date): DateTime = new DateTime(d)

  implicit def dateTime2Date(dt: DateTime): Date = dt.toDate
}

trait SqlImplicits {

  implicit def dateTime2Timestamp(d: BaseDateTime): Timestamp = new Timestamp(d.toDate.getTime)

  implicit def timestamp2dateTime(t: Timestamp): DateTime = new DateTime(t)

  implicit def with2Timestamp(d: BaseDateTime) = new {
    def toTimestamp: Timestamp = dateTime2Timestamp(d)
  }
}

trait TupleImplicits {
  type Year = Int
  type Month = Int
  type Day = Int
  type Hour = Int
  type Minute = Int
  type Second = Int
  type Millis = Int

  implicit def tuple2Time(t: (Year, Month, Day)): DateTime = {
    val (year, month, day) = t
    new DateTime(year, month, day, 0, 0, 0, 0)
  }

  implicit def tuple2Time(t: (Year, Month, Day, Hour)): DateTime = {
    val (year, month, day, hour) = t
    new DateTime(year, month, day, hour, 0, 0, 0)
  }

  implicit def tuple2Time(t: (Year, Month, Day, Hour, Minute)): DateTime = {
    val (year, month, day, hour, minute) = t
    new DateTime(year, month, day, hour, minute, 0, 0)
  }

  implicit def tuple2Time(t: (Year, Month, Day, Hour, Minute, Second)): DateTime = {
    val (year, month, day, hour, minute, second) = t
    new DateTime(year, month, day, hour, minute, second, 0)
  }

  implicit def tuple2Time(t: (Year, Month, Day, Hour, Minute, Second, Millis)): DateTime = {
    val (year, month, day, hour, minute, second, millis) = t
    new DateTime(year, month, day, hour, minute, second, millis)
  }
}
