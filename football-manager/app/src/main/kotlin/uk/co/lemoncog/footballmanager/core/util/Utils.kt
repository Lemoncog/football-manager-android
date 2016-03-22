package uk.co.lemoncog.footballmanager.core.util


import uk.co.lemoncog.footballmanager.core.GameModel
import uk.co.lemoncog.footballmanager.core.GameViewModel
import uk.co.lemoncog.footballmanager.core.gameModel
import java.text.SimpleDateFormat
import java.util.*


   fun parseServerDate(stringDate: String) : Date {
       val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
       return dateFormat.parse(stringDate);
   }

   fun convertGameModelToViewModel(gameModel: GameModel) : GameViewModel {
       val sdf = SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
       val prettyDate = sdf.format(gameModel.date);

       return GameViewModel(gameModel.name, gameModel.description, prettyDate, gameModel.replies.count());
   }
