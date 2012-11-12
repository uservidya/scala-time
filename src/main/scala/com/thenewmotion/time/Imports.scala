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
object Imports extends Imports
object TypeImports extends TypeImports
object StaticForwarderImports extends StaticForwarderImports

trait Imports extends TypeImports with StaticForwarderImports with Implicits with Instances

trait TypeImports {
  type Chronology     = org.joda.time.Chronology
  type DateTime       = org.joda.time.DateTime
  type DateTimeFormat = org.joda.time.format.DateTimeFormat
  type DateTimeZone   = org.joda.time.DateTimeZone
  type Duration       = org.joda.time.Duration
  type Interval       = org.joda.time.Interval
  type LocalDate      = org.joda.time.LocalDate
  type LocalDateTime  = org.joda.time.LocalDateTime
  type LocalTime      = org.joda.time.LocalTime
  type Period         = org.joda.time.Period
  type Partial        = org.joda.time.Partial
}

trait StaticForwarderImports {
  val DateTime       = com.thenewmotion.time.StaticDateTime
  val DateTimeFormat = com.thenewmotion.time.StaticDateTimeFormat
  val DateTimeZone   = com.thenewmotion.time.StaticDateTimeZone
  val Duration       = com.thenewmotion.time.StaticDuration
  val Interval       = com.thenewmotion.time.StaticInterval
  val LocalDate      = com.thenewmotion.time.StaticLocalDate
  val LocalDateTime  = com.thenewmotion.time.StaticLocalDateTime
  val LocalTime      = com.thenewmotion.time.StaticLocalTime
  val Period         = com.thenewmotion.time.StaticPeriod
  val Partial        = com.thenewmotion.time.StaticPartial
}

import scalaz._

trait Instances extends TypeImports {
  implicit val durationInstance = new Monoid[Duration] {
    override def zero = StaticDuration.∅
    override def append(f1: Duration, f2: ⇒ Duration) = f1.withDurationAdded(f2, 1)
  }
}
