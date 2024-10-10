package com.example.webtooninfoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var webtoonRecyclerView: RecyclerView
    private lateinit var webtoonAdapter: WebtoonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webtoonRecyclerView = findViewById(R.id.webtoonRecyclerView)

        // Sample webtoon data
        val webtoonList = listOf(
            Webtoon(
                "Solo Leveling",
                "One of the best action fantasy manhwa and the most talked about adaptation in recent times is undoubtedly Solo Leveling. It is set in a world where humans have discovered supernatural skills, while our protagonist, Sung Jin-Woo, is a nobody with his E-Rank hunting skills. Things will take an interesting turn for him when he becomes the sole survivor of a dungeon raid. Awakened with strange new powers, Sung Jin-Woo will level up from being the weakest Hunter and eventually become the most powerful entity in the universe.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Screenshot-2024-10-01-090334-750x375.webp"
            ),
            Webtoon(
                "Tower of God",
                "Even if you are not a manhwa fan, you must have heard of Tower of God. This action fantasy manhwa became especially popular after its anime adaptation. Tower of God focuses on Twenty-Fifth Bam, the young protagonist of the manhwa, who is determined to climb a mysterious Tower to find his friend Rachel. It is to be noted that the titular tower has different floors, and each floor has different obstacles. His quest is not going to be an easy one, and whether or not he will be able to meet his friend remains to be seen. Tower of God Season 2 has also been recently released.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/tower-of-god.webp"
            ),
            Webtoon(
                "Hardcore Leveling Warrior",
                "Gong Won-Ho is the top player of Lucid Adventure because he uses his alias, Hardcore Leveling Warrior, to stay on top. However, one day, the unimaginable happens – he gets defeated, and now he has to get back on the top from the bottom. The most fascinating aspect of this action fantasy manhwa is how he climbed his way to the top in the first place.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/hard-levelign-warrior-750x375.webp"
            ),
            Webtoon(
                "Noblesse",
                "After being in a slumber for over 800 years, Cadis Etrama Di Raizel, aka Rai, wakes up in an unfamiliar modern world. Fortunately, he meets his loyal servant Frankenstein, who is now the owner of a high school. After attending the school, Rai tries to live an ordinary life, concealing his true identity; however, that won’t happen for long. This supernatural action fantasy manhwa will keep you engaged with its beautiful illustration and unique narrative till the end. It also has its anime adaptation.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/noblesse-750x375.webp"
            ),
            Webtoon(
                "The God of High School",
                "The God of High School is one of the best action fantasy manhwa that has been adapted into an anime. Jin Mori, the protagonist of the story, takes part in a suspicious tournament, the prize of which is whatever the winner wants. Like Twenty-Fifth Baam, Mori seems like an ordinary individual, but as he confronts challenges, he understands more about the three realms and his powers.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Screenshot-2024-10-01-000548-750x375.webp"
            ),
            Webtoon(
                "Second Life Ranker",
                "Second Life Ranker tells the story of Yeon-Woo who is searching for his twin brother. He stumbles upon hints that lead to the certainty of his brother’s demise in an alternate dimension. Yeon-Woo is determined to find the people behind his brother’s death and eventually embarks on his journey of revenge and along the way he unravels the mysterious Tower of the Sun God.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Second-life-ranker-750x375.webp"
            ),
            Webtoon(
                "Eleceed",
                "How would you feel if one day you got superpowers or if you were just born with it? Would you use it to help others or to rule over the weak ones? The Eleceed webtoon focuses on the protagonist, Jiwoo. He is a young man with super speed, although he thinks he is a monster because of his power. One day, he finds an injured fat cat passed in an alley with blood splattered over it. He brings the cat home and learns it is a human in cat form – his name is Kayden, an awakened being with supernatural powers. Kayden helps Jiwoo learn how to defend himself against his opponents.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/elecceed-750x375.webp"
            ),
            Webtoon(
                "The Advanced Player of the Tutorial Tower",
                "Several monsters have evaded the earth and they began the ultimate destructions. Suddenly, a long tower called the tutorial tower came into existence, and no one knew if it was a structure or a monster. People from all over started being teleported inside that tower and had to fight monsters to survive. Only 20% of those people would make it out alive and were called ‘Hunters.’ They were given special powers to fight the monsters. Unfortunately, Hyeonu Kim was one of the first people to end up there but could not leave that place even after killing all the monsters. Instead, he would end up on the first level again and was trapped there for 12 years. ",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Advanced-Player-750x375.webp"
            ),
            Webtoon(
                "Leveling Up With The Gods",
                "Kim Yuwon has witnessed the destruction of gods and humans at the hands of The Tower. At the brink of death and having lost everything, Kim is given another opportunity through the sacrifice of his companions and return to the past. Now that he has more experience, he is determined to climb the Tower again and change everything within The Tower.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/leveling-up-with-the-gods-750x375.webp"
            ),
            Webtoon(
                "Villain to Kill",
                "Cassian Lee is a Pysker, a very powerful one at that. Psykers are people with inhumane abilities, and they use them to fight evil and protect the weak. While. Villians are the ones just as strong, but they use their ability to make people miserable. When Cassion’s only guardian, Jeff, died in front of him, he lost all hope. All he wanted was revenge, but he died helplessly. However, he woke up in the body of a villain because of the necklace Jeff gave him. What will he do now? Will he give in to his villain instincts or fight the evils instead? Read to the Villain to Kill webtoon to know more. ",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Villain-to-kill-750x375.webp"
            ),
            Webtoon(
                "Action Fantasy Manhwa: Hero Killer",
                "What would happen to the world if the heroes and villains all acted the same? They all want to exert their powers on the ones weaker than them. They all do not care about the safety of those weaker than them. Ihwa is a young beautiful girl who had a traumatic past, her only family, her sister, died at the hands of the ones who were supposed to protect all. What will happen when she sets out to fulfill her revenge? Who are those people helping her? What are their motives? Read the Hero Killer webtoon to know more. ",
                "https://animemangatoon.com/wp-content/uploads/2024/06/hero-killer-750x375.webp"
            ),
            Webtoon(
                "Return to Player",
                "Typical dark fantasy manhwa, Return To Player, is popular for its rich storyline and mythic constellation lore, which offers the reader an exciting experience. It is set in a fantasy clone of Earth, where gods have turned humans into players of a terrifying game of life and death. Sehan Kim has once witnessed the sadistic conclusion of this game, where everyone but him dies. Given a second opportunity, he is back in the time when everything began.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/return-to-player-750x375.webp"
            ),
            Webtoon(
                "Jungle Juice",
                "Suchan Jang is a brilliant student; however, beneath the perfection, he is concealing insect wings that abruptly grow when he uses the titular bug spray. Suchan’s life changes when he exposes his wings to everyone’s view to save somebody’s life. When he feels lost in the real world, he stumbles upon a concealed world of insect humans where there is no judgment. However, a jungle law controls this society, and everyone must fend for themselves to survive.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/junglee-juice-750x375.webp"
            ),
            Webtoon(
                "Lore Olympus",
                "We can’t talk about the best fantasy manhwa and not mention Lore Olympus. Have you ever read Greek Mythology? Do you find it interesting? Well, a lot of people certainly do. However, the storyline and the order of events can be a little too difficult for most people to remember or even understand. ‘Lore Olympus’ is such a webtoon based on Greek mythology, with an easy-to-understand plot that uses modern-day events and lifestyle. The plot is primarily based on Hades and Persephone’s love story. Moreover, it also covers the lifestyle of all the gods surrounding them and the events of great importance.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/lore-olympus-750x375.webp"
            ),
            Webtoon(
                "The Remarried Empress",
                "Navier, the empress of the Eastern Empire, was shattered in front of her when her husband Sovieshu, Emperor of the Eastern Empire, wanted to divorce her because she couldn’t give him an heir. He cast her aside when she couldn’t bear him a child, even though they groomed her to become the perfect empress ever since her childhood. Read The Remarried Empress to find out more about her struggles and dilemmas when an unexpected variable appeared in her life.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Remarried-Empress-750x375.webp"
            ),
            Webtoon(
                "Leveling Up My Husband to the Max",
                "he central character of Leveling Up My Husband to the Max, Amber, is in turmoil with her controlling mother-in-law and cruel husband. Following the time-travel theme, Amber is sent back 10 years into the past, and she confronts her husband, who now adores her. With her knowledge of the potential future, she tries to change her husband, navigate the complexities of the past, and create a new narrative for herself.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Leveling-up-my-husband-to-the-max-750x375.webp"
            ),
            Webtoon(
                "I’m the Queen in this Life\n",
                "Ariadne is the illegitimate daughter of a nobleman who is married to the king’s illegitimate son. Her husband Cesare conspires with her to kill his half-brother, the crown prince, to take over the throne for himself. Their plan is successful, but Ariadne’s life turns upside down when she finds her husband to betray her and marries her half-sister instead. What is more, her sister is the one who put the dagger in her heart. Ultimately, she vows to take revenge, and the heavens listen to her. She is sent back in time when she was 17 years old.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Im-the-queen-in-this-life-750x375.webp"
            ),
            Webtoon(
                "The Witch and the Bull",
                "Deo is trying to live a peaceful life, but his friend and advisor, Tan, denies this. Tan believes that if they show any signs of weakness, the witches will try to control them. When Tan goes to a popular cafe and learns that it is run by a beautiful witch named Aro, he uses his power to shut it down, and this changes his fate. And the only way to undo this curse is to rely on Aro. Can Aro’s kindness change Tan and finally break this spell?",
                "https://animemangatoon.com/wp-content/uploads/2024/06/the-witch-and-the-bull-750x375.webp"
            ),
            Webtoon(
                "From A Knight to A Lady",
                "Estelle is a young knight in the Kingdom of Ersha who fought with immense strength until her last breath for her country. Sadly, her most trusted comrade kills her. Miraculously, 3 years later, she finds herself reincarnated as Lucifella, the rumored partner of the crown prince of Jansgar, the enemy kingdom. What’s more? She is now the betrothed of Duke Heint, her mortal enemy from her last life.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/from-a-knight-to-a-lady-750x375.webp"
            ),
            Webtoon(
                "For My Derelict Favorite",
                "Hestia enters the fictional world of her favorite novel as a secondary character. She believes she will transfer to the real world once the story ends. However, she finds that the only thing awaiting her is the horrifying death of Hestia’s favorite character. Hestia decides not to be a viewer from the sidelines anymore. She is determined to save her derelict favorite. Read For My Derelict Favorite to know what happens after “happily ever after?”",
                "https://animemangatoon.com/wp-content/uploads/2024/06/for-my-derelict-750x375.webp"
            ),
            Webtoon(
                "Eaternal Nocturnal",
                "Eve has never met the expectations of her parents. When she again loses her job because of insomnia, she takes up a ghost singing gig. However, now that her financial condition is resolved temporarily, she can find a way to try to cure her insomnia. And, in the form of a cure, a handsome and mysterious dream eater visits her in her sleep. Read Eaternal Nocturnal to know how their story ends.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/eaternal-750x375.webp"
            ),
            Webtoon(
                "Act Like You Love Me!",
                "The central character of Act Like You Love Me! Ji-won is a 25-year-old penniless, overworked, and stressed woman who, after a mix-up, gets a job to be the personal assistant of 26-year-old popular actor Doyun Nam. When she takes her anger out on a doll, she realizes that it has a mysterious ability to take over him at her whim. Now, it’s Ji-won’s turn to act like the boss.",
                "https://animemangatoon.com/wp-content/uploads/2024/09/act-like-you-love-me-750x375.webp"
            ),
            Webtoon(
                "My In-Laws Are Obsessed With Me",
                "Pereshati Jahardt has an unfortunate luck when her mother dies at a young age, and her stepmother and half-sister, along with Pereshati’s lover, plans to kill her. Their plan becomes successful; however, miraculously, she transports back in time just before her murder. Hence, she plans revenge against them. Not long after, she proposes to Grand Duke, Therdeo Lapilean, to marry her to take her revenge in exchange for helping him find the truth about his family secret.",
                "https://animemangatoon.com/wp-content/uploads/2024/06/my-in-laws-750x375.webp"
            ),
            Webtoon(
                "Suitor Armor",
                "Lucia is the lady-in-waiting for Kirsi, the soon-to-be Queen of the Empire. She was rescued as a child by Kirsi’s father, and from then on, she decided to become Kirsi’s lady-in-waiting. Her life turned better from then on. However, Lucia has a secret of her own; she is not a human but a fairy. Even Kirsi does not know about her secret. One day, when they went to see the Knight’s Tournament, Lucia saw a knight and fell for him at first sight. However, he is not a normal knight; he is a knight made of magic by the Empire’s Mage. ",
                "https://animemangatoon.com/wp-content/uploads/2024/06/suitor-armour-750x375.webp"
            ),
            Webtoon(
                "Castle Swimmer",
                "If you are looking for a beautiful gay romance read, Castle Swimmer is the best romance fantasy manhwa. What happens when the creator of all creates a divine creature called Beacon, who is supposed to solve the misery of all the creatures? All creatures who belong to multiple species have a prophecy of their own, and the Beacon is supposed to free them from their miseries. Everyone is waiting for the arrival of the divine creature, but no one knows where they are. However, among all those prophecies, one prophecy contains that the prince from the castle of sharks, Siren, is going to kill the Beacon and free his kind from the curse.\n" +
                        "\n",
                "https://animemangatoon.com/wp-content/uploads/2024/06/castle-swimmer-750x375.webp"
            ),

        )

        // Convert Webtoon list to WebtoonEntity list
        val webtoonEntityList = webtoonList.map { webtoon ->
            WebtoonEntity(
                title = webtoon.title,
                description = webtoon.description,
                imageUrl = webtoon.imageUrl
            )

        }
        webtoonAdapter = WebtoonAdapter(webtoonEntityList) { webtoonEntity ->
            val intent = Intent(this, WebtoonDetailActivity::class.java).apply {
                putExtra("webtoonId", webtoonEntity.id)
                putExtra("title", webtoonEntity.title)
                putExtra("description", webtoonEntity.description)
                putExtra("imageUrl", webtoonEntity.imageUrl)
            }
            startActivity(intent)
        }
        webtoonRecyclerView.adapter = webtoonAdapter

        val favoritesButton: FloatingActionButton = findViewById(R.id.favoritesButton)
        favoritesButton.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}