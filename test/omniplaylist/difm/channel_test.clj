(ns omniplaylist.difm.channel-test
  (:use midje.sweet
        omniplaylist.remote-playlist
        omniplaylist.difm.channel))

(fact "Streams can be removed using a predicate function. Matching streams are removed."
      (let [channel (map->Channel {:name "channel" :streams [1 2 3 4]})]
        (:streams (remove-streams channel even?))) => [1 3])

