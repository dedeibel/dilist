(ns omniplaylist.difm.playlist
  (:require [omniplaylist.difm.track :as difm-track]
            [omniplaylist.playlist   :as playlist]))

(defn shorten-tracks-digitally-imported-name [playlist]
  (playlist/update-tracks playlist difm-track/shorten-digitally-imported-name))

