package com.apps.ifaldyprayanda.jetpackpro1.utils;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;

import java.util.ArrayList;

public class FakeDataDummy {

    public static ArrayList<MovieEntity> generateDataMovie()
    {
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieEntity("m01",
                "Joker",
                "2019-10-04",
                "https://image.tmdb.org/t/p/w185/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "English",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."));

        movieEntities.add(new MovieEntity("m02",
                "Terminator: Dark Fate",
                "2019-10-18",
                "https://image.tmdb.org/t/p/w185/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg",
                "English",
                "Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play."));

        movieEntities.add(new MovieEntity("m03",
            "Maleficent: Mistress of Evil",
            "2019-10-18",
            "https://image.tmdb.org/t/p/w185/tBuabjEqxzoUBHfbyNbd8ulgy5j.jpg",
            "English",
            "Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play."));

        movieEntities.add(new MovieEntity("m04",
            "The Lion King",
            "2019-07-19",
            "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
            "English",
            "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his."));

        movieEntities.add(new MovieEntity("m05",
            "Fast & Furious Presents: Hobbs & Shaw",
            "2019-08-02",
            "https://image.tmdb.org/t/p/w185/kvpNZAQow5es1tSY6XW2jAZuPPG.jpg",
            "English",
            "Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have swapped smacks and bad words. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, both join forces to defeat him. (A spin-off of “The Fate of the Furious,” focusing on Johnson's Luke Hobbs and Statham's Deckard Shaw.)"));

        movieEntities.add(new MovieEntity("m06",
                "Cars",
            "2006-06-09",
            "https://image.tmdb.org/t/p/w185/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg",
            "English",
            "Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters."));

        movieEntities.add(new MovieEntity("m07",
                "One Piece: Stampede",
            "2019-10-24",
            "https://image.tmdb.org/t/p/w185/4E2lyUGLEr3yH4q6kJxPkQUhX7n.jpg",
            "Japan",
            "One Piece: Stampede is a stand-alone film that celebrates the anime's 20th Anniversary and takes place outside the canon of the One Piece TV series. Monkey D. Luffy and his Straw Hat pirate crew are invited to a massive Pirate Festival that brings many of the most iconic characters from throughout the franchise to participate in competition with the Straw Hats to find Roger's treasure. It also pits the Straw Hats against a new enemy named Bullet, a former member of Roger's crew."));

        movieEntities.add(new MovieEntity("m08",
            "Doctor Sleep",
            "2019-11-08",
            "https://image.tmdb.org/t/p/w185/p69QzIBbN06aTYqRRiCOY1emNBh.jpg",
            "English",
            "A traumatized, alcoholic Dan Torrance meets Abra, a kid who also has the ability to shine. He tries to protect her from the True Knot, a cult whose goal is to feed off of people like them in order to remain immortal."));

        movieEntities.add(new MovieEntity("m09",
            "The King",
            "2019-10-11",
            "https://image.tmdb.org/t/p/w185/8u0QBGUbZcBW59VEAdmeFl9g98N.jpg",
            "English",
            "England, 15th century. Hal, a capricious prince who lives among the populace far from court, is forced by circumstances to reluctantly accept the throne and become Henry V."));

        movieEntities.add(new MovieEntity("m10",
            "Dark Phoenix",
            "2019-06-07",
            "https://image.tmdb.org/t/p/w185/cCTJPelKGLhALq3r51A9uMonxKj.jpg",
            "English",
            "The X-Men face their most formidable and powerful foe when one of their own, Jean Grey, starts to spiral out of control. During a rescue mission in outer space, Jean is nearly killed when she's hit by a mysterious cosmic force. Once she returns home, this force not only makes her infinitely more powerful, but far more unstable. The X-Men must now band together to save her soul and battle aliens that want to use Grey's new abilities to rule the galaxy."));

        return movieEntities;
    }

    public static ArrayList<TvShowEntity> generateDataTv()
    {
        ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TvShowEntity("t01",
            "Supernatural",
            "2005-09-13",
            "https://image.tmdb.org/t/p/w500/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
            "English",
            "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way."));

        tvShowEntities.add(new TvShowEntity("t02",
            "Fear the Walking Dead",
            "2015-08-23",
            "https://image.tmdb.org/t/p/w500/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
            "English",
            "What did the world look like as it was transforming into the horrifying apocalypse depicted in The Walking Dead? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question."
      ));

        tvShowEntities.add(new TvShowEntity("t03",
            "His Dark Materials",
            "2019-11-03",
            "https://image.tmdb.org/t/p/w500/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
            "English",
            "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds."));

        tvShowEntities.add(new TvShowEntity("t04",
            "Arrow",
            "2012-10-10",
            "https://image.tmdb.org/t/p/w500/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
            "English",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."));

        tvShowEntities.add(new TvShowEntity("t05",
            "The Flash",
            "2014-10-07",
            "https://image.tmdb.org/t/p/w500/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
            "English",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only meta-human who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."));

        tvShowEntities.add(new TvShowEntity("t06",
            "The Simpsons",
            "1989-12-17",
            "https://image.tmdb.org/t/p/w500/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
            "English",
            "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general."));

        tvShowEntities.add(new TvShowEntity("t07",
            "See",
            "2019-11-01",
            "https://image.tmdb.org/t/p/w500/g3JsScc7mQCfc3e5e5rXwu7xVVP.jpg",
            "English",
            "A virus has decimated humankind. Those who survived emerged blind. Centuries later when twins are born with the mythic ability to see, their father must protect his tribe against a threatened queen."));

        tvShowEntities.add(new TvShowEntity("t08",
            "4 Blocks",
            "2017-05-08",
            "https://image.tmdb.org/t/p/w500/jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
            "Deutch",
            "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple."));

        tvShowEntities.add(new TvShowEntity("t09",
            "Riverdale",
            "2017-01-26",
            "https://image.tmdb.org/t/p/w500/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
            "English",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade."));

        tvShowEntities.add(new TvShowEntity("t10",
            "The End of the F***ing World",
            "2017-10-24",
            "https://image.tmdb.org/t/p/w500/xzwwzmXbz6n2Y3fc0GbjqGiFQPm.jpg",
            "English",
            "James is 17 and is pretty sure he is a psychopath. Alyssa, also 17, is the cool and moody new girl at school. The pair make a connection and she persuades him to embark on a darkly comedic road trip in search of her real father."));
        return tvShowEntities;
    }

//    untuk mendapatkan data dari generateDataMovie
    public static MovieEntity getMovie(String movieId)
    {
        for (int i = 0; i < generateDataMovie().size(); i++)
        {
            MovieEntity mEntity = generateDataMovie().get(i);
            if (mEntity.getMovieId().equals(movieId))
            {
                return mEntity;
            }
        }
        return null;
    }

//    untuk mendapatkan data dari generateDataTv
    public static TvShowEntity getTv(String tvId)
    {
        for (int i = 0; i < generateDataTv().size(); i++)
        {
            TvShowEntity tvEntity = generateDataTv().get(i);
            if (tvEntity.getTvId().equals(tvId))
            {
                return tvEntity;
            }
        }
        return null;
    }
}
