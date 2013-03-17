(ns omniplaylist.difm.track-test
  (:use omniplaylist.difm.track
        midje.sweet)
  (:require [omniplaylist.track :as track]))

(fact "The track's digitally imported title is is shortened"
  (:title (shorten-digitally-imported-name (track/map->Track { :title "Digitally Imported - Coolest track" }))) => "di.fm - Coolest track")

