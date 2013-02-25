(ns omniplaylist.difm.unaccepted-playlists-test
  (:use omniplaylist.difm.unaccepted-playlists
        midje.sweet)
  (:require [omniplaylist.difm.remote-playlist :as difm-playlist]))

(fact "The predicate function is a combination of all tests, all false means false"
      (unacceptable-playlist-predicate nil) => false
      (provided (difm-playlist/is-premium nil) => false
                (difm-playlist/is-asx nil)     => false))

(fact "The predicate function is a combination of all tests, one true means all true"
      (unacceptable-playlist-predicate nil) => true
      (provided (difm-playlist/is-premium nil) => false
                (difm-playlist/is-asx nil)     => true))

