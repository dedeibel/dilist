(ns omniplaylist.difm.channel-test
  (:use midje.sweet
        omniplaylist.remote-playlist
        omniplaylist.difm.channel))

(fact "Streams can be removed using a predicate function. Matching streams are removed."
      (let [channel (map->Channel {:name "channel" :streams [1 2 3 4]})]
        (:streams (remove-streams channel even?))) => [1 3])

;(fact "A channel's playlist's tracks can be fetched with the channel's name prepended to the tracks' names"
;      (let [channel (map->Channel {:name "channel" :streams [
;                                                             map->RemotePlaylist {  }
;                                                             ]})]
;


