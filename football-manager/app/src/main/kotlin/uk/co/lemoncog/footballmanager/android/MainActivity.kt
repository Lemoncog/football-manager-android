package uk.co.lemoncog.footballmanager.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) supportFragmentManager.beginTransaction().add(R.id.fragment_container, GameFragment(), "game_frag").commit();
    }
}
