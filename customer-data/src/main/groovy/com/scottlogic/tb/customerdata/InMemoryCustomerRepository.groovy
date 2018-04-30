package com.scottlogic.tb.customerdata

import org.springframework.stereotype.Service

@Service
class InMemoryCustomerRepository implements CustomerRepository {

    @Override
    Map get(Number id) {
        [name: names.get(id.toInteger())]
    }

    private static List<String> names = [
            "Alma Evans",
            "Arturo Jensen",
            "Ollie Osborne",
            "Rodolfo Schmidt",
            "Suzanne Tran",
            "Jan Stewart",
            "Desiree Bryan",
            "Levi Coleman",
            "Colleen Becker",
            "Dexter Thornton",
            "Dwayne Roy",
            "Mabel Duncan",
            "Casey Marshall",
            "Minnie Nash",
            "William Stokes",
            "Lucille Garza",
            "Pamela Schwartz",
            "Jay Hardy",
            "Terry Carlson",
            "Lillie Gonzalez",
            "Vivian Guerrero",
            "Stephen Barnett",
            "Dana Phillips",
            "Maggie Webster",
            "Glenn Cross",
            "Kristopher Barker",
            "Marvin Bridges",
            "Robin Cannon",
            "Jesus Collins",
            "Abraham Saunders",
            "Lawrence Bishop",
            "Elena Burgess",
            "Julius Guzman",
            "Carmen Robertson",
            "Nellie Douglas",
            "Lynn Jackson",
            "Taylor Roberts",
            "Samuel Norris",
            "Lula Simon",
            "Willie Potter",
            "Lorena Montgomery",
            "Malcolm Tate",
            "Jane Haynes",
            "Christian Cook",
            "Luz Patton",
            "Stacey Murray",
            "Angelo Harrison",
            "Nathan Palmer",
            "Cristina Jennings",
            "Jeremy Frank",
            "Enrique Bryant",
            "June Cole",
            "Clyde Hawkins",
            "Harold Shelton",
            "Hugo Flowers",
            "Leslie Bates",
            "Stephanie Mendoza",
            "Violet Hall",
            "Sidney Howell",
            "Roger Colon",
            "Santiago Terry",
            "Sylvia Beck",
            "Caroline Figueroa",
            "Allan Price",
            "Myron Barton",
            "Monique Todd",
            "Chelsea Henderson",
            "Terence Wagner",
            "Renee Glover",
            "Christina Sharp",
            "Emmett Meyer",
            "Yvonne Lowe",
            "Gloria Sanders",
            "Ira Little",
            "Jennifer Thompson",
            "Amber Willis",
            "Erika Green",
            "Ernest Rose",
            "Caleb Burns",
            "Gregory Logan",
            "Joey Brewer",
            "Erik Hansen",
            "Celia Page",
            "Irene Diaz",
            "Duane Romero",
            "Emanuel Daniels",
            "Deborah Weaver",
            "Doyle Arnold",
            "Rufus Watkins",
            "Mamie Fields",
            "Gabriel Torres",
            "Gail Kelly",
            "Darryl Peterson",
            "Marty Nelson",
            "Ed Burke",
            "Alonzo Maxwell",
            "Lonnie Gill",
            "Rita Nunez",
            "Laverne Mathis",
            "Dean Parker",
    ]

}
