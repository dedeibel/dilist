(ns dilist.channel_test
  (:use clojure.test)
  (:use dilist.channel))

(deftest streams-are-removed
  (is (= [1 3] (:streams (remove-streams even? (map->Channel {:name "channel" :streams [1 2 3 4]}))))))
