(ns omniplaylist.difm.channel_test
  (:use clojure.test)
  (:use omniplaylist.difm.channel))

(deftest streams-are-removed
  (is (= [1 3] (:streams (remove-streams even? (map->Channel {:name "channel" :streams [1 2 3 4]}))))))
