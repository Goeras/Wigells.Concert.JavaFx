/*
module org.dreamteamdb.wigellsconcertjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;




    opens org.dreamteamdb.wigellsconcertjavafx to javafx.fxml;
    exports org.dreamteamdb.wigellsconcertjavafx;
}*/

module org.dreamteamdb.wigellsconcertjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    //requires java.persistence;
    requires java.naming;


    exports org.dreamteamdb.wigellsconcertjavafx;
    opens org.dreamteamdb.wigellsconcertjavafx.Entitys;
    exports org.dreamteamdb.wigellsconcertjavafx.Entitys;
    opens org.dreamteamdb.wigellsconcertjavafx;
}
