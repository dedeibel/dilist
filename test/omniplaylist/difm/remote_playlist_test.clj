(ns omniplaylist.difm.remote-playlist-test
  (:use omniplaylist.difm.remote-playlist)
  (:use omniplaylist.remote-playlist)
  (:use midje.sweet))

(fact "A premium stream is recognized."
      (is-premium (map->RemotePlaylist {:format "MP3" :name "128k Broadband (Premium)" :url "/premium"})) => true)

(fact "A normal stream is not recognized as premium."
      (is-premium (map->RemotePlaylist {:format "MP3" :name "128k Broadband" :url "http://listen.di.fm/public2/chiptunes.pls"})) => false)

