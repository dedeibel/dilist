(ns omniplaylist.difm.unaccepted-playlists-test
  (:use omniplaylist.difm.unaccepted-playlists
        midje.sweet)
  (:require [omniplaylist.difm.playlist-url :as playlist-url]))

(fact "The predicate function is a combination of all tests, all false means false"
      (unacceptable-playlist-predicate nil) => false
      (provided (playlist-url/is-premium nil) => false
                (playlist-url/is-asx nil)     => false))

(fact "The predicate function is a combination of all tests, one true means all true"
      (unacceptable-playlist-predicate nil) => true
      (provided (playlist-url/is-premium nil) => false
                (playlist-url/is-asx nil)     => true))

