module org.dreamteamdb.wigellsconcertjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    exports org.dreamteamdb.wigellsconcertjavafx;
    opens org.dreamteamdb.wigellsconcertjavafx.Entitys;
    exports org.dreamteamdb.wigellsconcertjavafx.Entitys;
    opens org.dreamteamdb.wigellsconcertjavafx;
    exports org.dreamteamdb.wigellsconcertjavafx.Controllers;
    opens org.dreamteamdb.wigellsconcertjavafx.Controllers;
}
