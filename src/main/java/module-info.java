module org.dreamteamdb.wigellsconcertjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.dreamteamdb.wigellsconcertjavafx to javafx.fxml;
    exports org.dreamteamdb.wigellsconcertjavafx;
}