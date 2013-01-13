(ns dilist.channel_test
  (:use clojure.test)
  (:use dilist.channel))

(deftest streamsAreRemoved
  (is (= [1 3] (:streams (removeStreams even? (map->Channel {:name "channel" :streams [1 2 3 4]}))))))
