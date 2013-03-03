(ns omniplaylist.difm.playlist-url-test
  (:use midje.sweet
        omniplaylist.difm.playlist-url)
  (:require [omniplaylist.playlist-url :as playlist-url]))

(fact "A premium stream is recognized."
      (is-premium (playlist-url/map->PlaylistURL {:format "MP3" :name "128k Broadband (Premium)" :url "/premium"})) => true)

(fact "A normal stream is not recognized as premium."
      (is-premium (playlist-url/map->PlaylistURL {:format "MP3" :name "128k Broadband" :url "http://listen.di.fm/public2/chiptunes.pls"})) => false)

