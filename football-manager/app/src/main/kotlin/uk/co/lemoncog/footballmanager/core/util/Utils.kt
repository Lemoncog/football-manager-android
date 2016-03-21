package uk.co.lemoncog.footballmanager.core.util

import java.text.SimpleDateFormat
import java.util.*


   fun parseServerDate(stringDate: String) : Date {
       val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
       return dateFormat.parse(stringDate);
   }
