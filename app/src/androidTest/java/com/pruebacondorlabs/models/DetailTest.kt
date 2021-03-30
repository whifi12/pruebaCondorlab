package com.pruebacondorlabs.models

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import com.example.domain.model.response.Teams
import com.example.team.R
import com.example.team.adapter.EventRecyclerAdapter

class DetailTest {

    val recyclerView = R.id.recyclerViewMatchs

    fun loadDataTeams() : Teams{
        val teams = Teams()
        teams.idTeam = "138277"
        teams.name = "Alaves"
        teams.stadium = "Vitoria"
        teams.badge = "https://www.thesportsdb.com/images/media/team/badge/prle2v1596049676.png"
        teams.jersey = "https://www.thesportsdb.com/images/media/team/jersey/2019-134221-Jersey.png"
        teams.website = "www.alaves.com"
        teams.facebook = "www.facebook.com/deportivoalaves"
        teams.twitter = "twitter.com/alaves"
        teams.instagram = "instagram.com/deportivoalaves"
        teams.description = "Deportivo Alavés, S.A.D., usually abbreviated to Alavés, is a Spanish football club based in Vitoria-Gasteiz, Álava, in the autonomous community of the Basque Country. Founded on January 23rd, 1921 as Sport Friends Club, it plays in the highest football category of The Liga Nacional de Fútbol Profesional, La Liga, since the 2016–17 season.\\r\\n\\r\\nIt is recognized as the least successful team in the Basque Country following Athletic Club of Bilbao and Real Sociedad de Futbol of San Sebastián. Its biggest success was in 2001 when, in the year of its debut in European competition, it was one of the finalists in the 2001 UEFA Cup Final against Liverpool, being defeated 5–4 by golden goal. In 2017, the club reached the final of the Copa del Rey, losing out 3–1 to Barcelona.\\r\\n\\r\\nThe team's home kit is blue and white-striped shirt, blue shorts and white socks. It holds home matches at the 19,800-seater Mendizorrotza Stadium and uses other facilities located in Ibaia dedicated to training."
        teams.year = "1921"
        return teams
    }


}